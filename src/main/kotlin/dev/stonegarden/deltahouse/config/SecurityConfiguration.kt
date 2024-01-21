package dev.stonegarden.deltahouse.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.csrf.CookieCsrfTokenRepository
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler


@Configuration
@EnableWebSecurity
class SecurityConfiguration {

    @Bean
    @Throws(Exception::class)
    fun filterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        httpSecurity.csrf { request ->
            request.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .csrfTokenRequestHandler(CsrfTokenRequestAttributeHandler())
                //TODO: DON'T DISABLE CSRF!!!
                .disable()
        }.authorizeHttpRequests { request ->
            request.anyRequest().permitAll()
        }
        return httpSecurity.build()
    }

//    @Bean
//    fun corsConfigurationSource(): CorsConfigurationSource {
//        val configuration = CorsConfiguration()
//        configuration.allowedOrigins = listOf("*")
//        configuration.allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "OPTIONS")
//        configuration.allowedHeaders = listOf("*")
//        configuration.allowCredentials = true
//        configuration.maxAge = 3600
//        val source = UrlBasedCorsConfigurationSource()
//        source.registerCorsConfiguration("/**", configuration)
//        return source
//    }

}