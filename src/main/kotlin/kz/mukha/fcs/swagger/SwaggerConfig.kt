package kz.mukha.fcs.swagger

import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
                .info(
                    Info()
                        .title("Financial Control API")
                        .version("v1")
                        .description("API documentation for Financial Control Backend Application")
                        .contact(
                            Contact()
                                .name("Georgii Mukha")
                                .url("https://themukha.com")
                                .email("george@themukha.com")
                        )
                )
    }
}