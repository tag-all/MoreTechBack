package ru.tagallteam.hackstarter.config;

import java.time.LocalDateTime;
import java.util.Collections;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import ru.tagallteam.hackstarter.application.common.Endpoints;
import ru.tagallteam.hackstarter.errors.ErrorDescriptor;
import ru.tagallteam.hackstarter.errors.model.ApplicationError;
import ru.tagallteam.hackstarter.filter.JwtFilter;
import ru.tagallteam.hackstarter.utils.HttpResponseUtils;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringConfig extends WebSecurityConfigurerAdapter {
    private final JwtFilter jwtFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .configurationSource(corsConfigurationSource())
                .and()
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler())
                .authenticationEntryPoint(authenticationEntryPoint())
                .and()
                .logout()
                .logoutUrl("/api/v1/logout")
                .invalidateHttpSession(true)
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, Endpoints.AuthService.LOGIN)
                .permitAll()
                .antMatchers(HttpMethod.POST, Endpoints.AuthService.REGISTRATION)
                .permitAll()
                .antMatchers(HttpMethod.POST, "/logout")
                .permitAll()
                .antMatchers(HttpMethod.PUT, Endpoints.AuthService.UPDATE_TOKEN)
                .permitAll()
                .antMatchers(AUTH_WHITELIST)
                .permitAll()
                .anyRequest().authenticated();
        http.headers().frameOptions().sameOrigin();
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * Конфигарация CORS.
     */
    private CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Collections.singletonList("*"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedMethods(Collections.singletonList("*"));
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.setAlwaysUseFullPath(true);
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /**
     * {@see AccessDeniedHandler}.
     */
    private AccessDeniedHandler accessDeniedHandler() {
        ErrorDescriptor errorDescription = ErrorDescriptor.ACCESS_DENIED;
        ApplicationError error = ApplicationError.of(errorDescription.getType(), errorDescription.getMessage(),
                LocalDateTime.now(), errorDescription.getStatus());
        return (request, response, ex) -> HttpResponseUtils.writeError(response, error,
                HttpServletResponse.SC_FORBIDDEN);
    }

    /**
     * {@see AuthenticationEntryPoint}.
     */
    private AuthenticationEntryPoint authenticationEntryPoint() {
        ErrorDescriptor errorDescription = ErrorDescriptor.UNAUTHORIZED_ACCESS;
        ApplicationError error = ApplicationError.of(errorDescription.getType(), errorDescription.getMessage(),
                LocalDateTime.now(), errorDescription.getStatus());
        return (request, response, ex) -> HttpResponseUtils.writeError(response, error,
                HttpServletResponse.SC_UNAUTHORIZED);
    }

    private static final String[] AUTH_WHITELIST = {
            "/authenticate",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/webjars/**"
    };

}
