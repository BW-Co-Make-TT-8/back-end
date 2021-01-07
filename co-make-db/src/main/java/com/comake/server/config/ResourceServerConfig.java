package com.comake.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfig
        extends ResourceServerConfigurerAdapter
{
    private static final String RESOURCE_ID = "resource_id";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources)
    {
        resources.resourceId(RESOURCE_ID)
                .stateless(false);
    }

    @Override
    public void configure(HttpSecurity http)
            throws
            Exception
    {
        http.authorizeRequests()
                .antMatchers("/",
                        "/h2-console/**",
                        "/swagger-resources/**",
                        "/swagger-resource/**",
                        "/swagger-ui.html",
                        "/v2/api-docs",
                        "/webjars/**",
                        "/login",
                        "/createnewuser")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/posts").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.GET, "/posts/{zipcode}").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.GET, "/posts/{id}").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.DELETE, "/posts/{id}").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.PUT, "/posts/{id}").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/posts").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.GET, "/users").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/users/{userId}").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.GET, "/users/name/{userName}").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.GET, "/users/like-name/{userName}").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.GET, "/users/{userid}/posts").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.PUT, "/users/{id}").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.DELETE, "/users/{id}").hasAnyRole("ADMIN", "USER")
                .antMatchers("/oauth/revoke-token",
                        "/logout")
                .authenticated()
                .antMatchers("/roles/**")
                .hasAnyRole("ADMIN", "USER")
                .and()
                .exceptionHandling()
                .accessDeniedHandler(new OAuth2AccessDeniedHandler());

        http.csrf()
                .disable();

        http.headers()
                .frameOptions()
                .disable();

        http.logout()
                .disable();
    }
}

