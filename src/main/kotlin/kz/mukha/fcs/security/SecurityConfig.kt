package kz.mukha.fcs.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf {
                it.disable()
            }
            .authorizeHttpRequests {
                it
                    .requestMatchers("/swagger-ui/**", "/api-docs/**").permitAll()
                    .requestMatchers("/", "/login", "/token", "register").permitAll() // Разрешаем доступ к URL для авторизации
                    .anyRequest().authenticated() // Требуем аутентификацию для остальных запросов
            }
            .formLogin {
                // Отключаем форму входа (по умолчанию на /login)
                it.disable()
            }
            .httpBasic {
                // Включаем HTTP Basic авторизацию
            }
            .logout {
                // Включаем логаут (по умолчанию на /logout)
            }
        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}