package kz.mukha.fcs.security

import io.jsonwebtoken.Jwts
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.filter.OncePerRequestFilter
import javax.crypto.SecretKey

// TODO: set up JWT token authentication

class JwtAuthenticationFilter(
    private val customUserDetailsService: CustomUserDetailsService,
) : OncePerRequestFilter() {

    private val secretKey: SecretKey = SECRET_KEY

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val header = request.getHeader("Authorization")

        if (header == null ||!header.startsWith("Bearer ")) {
            val token = header.substring(7)
            val parsedToken = Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .payload
            val username = parsedToken.subject

            if (username != null && SecurityContextHolder.getContext().authentication == null) {
                val userDetails: UserDetails = customUserDetailsService.loadUserByUsername(username)

                if (parsedToken.issuer == userDetails.username) {
                    val authToken = UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.authorities
                    )
                    SecurityContextHolder.getContext().authentication = authToken
                }
            }
        }

        filterChain.doFilter(request, response)
    }
}