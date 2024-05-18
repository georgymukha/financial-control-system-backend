package kz.mukha.fcs.security

import io.jsonwebtoken.Jwts
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.Date
import javax.crypto.SecretKey

val SECRET_KEY : SecretKey = Jwts.SIG.HS512.key().build()
const val EXPIRATION_TIME: Long = 1000 * 60 * 60 * 24 * 10 // 10 days

@Component
class JwtUtil {

    private val secretKey: SecretKey = SECRET_KEY

    fun generateToken(userDetails: UserDetails): String {
        val username = userDetails.username
        val expirationMillis: Long = System.currentTimeMillis() + EXPIRATION_TIME

        return Jwts.builder()
            .subject(username)
            .issuer(username)
            .issuedAt(Date())
            .expiration(Date(expirationMillis))
            .signWith(secretKey)
            .compact()
    }
}