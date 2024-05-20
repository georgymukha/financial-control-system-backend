package kz.mukha.fcs.security

import kz.mukha.fcs.util.LoggerUtil.getLogger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

// TODO: set up JWT token authentication

@Configuration
@EnableWebSecurity
class SecurityConfig {

    private val logger = getLogger(this::class.java)

    @Autowired
    private lateinit var customUserDetailsService: CustomUserDetailsService

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf {
                it.disable()
            }
            .authorizeHttpRequests {
                it
                    .requestMatchers(
                        "/",
                        "/login/**",
                        "/api/users/token",
                        "/api/authenticate",
                        "/api/users/register",
                        "/swagger-ui/**",
                        "/api-docs/**"
                    ).permitAll()
                    .anyRequest().authenticated()
            }
            .formLogin { it.disable() }
            .httpBasic {}
            .logout {}
        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun customAuthenticationManager(): AuthenticationManager {
        return AuthenticationManager { authentication ->
            val username = authentication.name
            val password = authentication.credentials.toString()
            val userDetails = customUserDetailsService.loadUserByUsername(username)
            if (username != userDetails.username) {
                logger.warn("User `$username` found")
                throw UsernameNotFoundException("User `$username` not found")
            }
            if (!passwordEncoder().matches(password, userDetails.password)) {
                logger.warn("Password for user `$username` is incorrect")
                throw UsernameNotFoundException("Password for user `$username` is incorrect")
            }
            UsernamePasswordAuthenticationToken(userDetails, password, userDetails.authorities)
        }
    }
}