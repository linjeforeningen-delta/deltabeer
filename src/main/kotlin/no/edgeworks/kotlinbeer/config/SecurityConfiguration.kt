package no.edgeworks.kotlinbeer.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.web.SecurityFilterChain

/**
 * @author Eleftheria Stein
 */
@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun webSecurityCustomizer(http: HttpSecurity): WebSecurityCustomizer {
        return WebSecurityCustomizer { web: WebSecurity ->
            web.ignoring().antMatchers("/**", "/h2-console/**")
        }
    }

    @Bean
    fun apiFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeRequests { request -> request.anyRequest().permitAll() }
        return http.build()
    }
}