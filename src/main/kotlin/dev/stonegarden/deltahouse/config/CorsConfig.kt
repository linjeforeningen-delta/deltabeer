package dev.stonegarden.deltahouse.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

@Configuration
class CorsConfig {

    @Bean
    fun corsFilter(): CorsFilter {
        val config = CorsConfiguration()
        config.allowedOrigins = listOf("http://localhost:5173")
        config.allowedMethods = listOf("GET", "POST", "PUT", "DELETE")
        config.allowedHeaders = listOf("*") // Allow all headers
        config.allowCredentials = true // Allow cookies if needed

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", config) // Apply CORS config to all routes
        return CorsFilter(source)
    }
}
