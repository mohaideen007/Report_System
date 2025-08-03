package com.example.problems.Config;

import java.util.Base64;
import java.util.List;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class report_config implements WebMvcConfigurer{
	
	
	@Bean
	public SecurityFilterChain sfc(HttpSecurity http) throws Exception {
		return http.cors(c->c.configurationSource(corsconfiguration()))
				.csrf(c->c.disable())
				.authorizeHttpRequests(a->a
				.requestMatchers("/uploads/**","reports/**").permitAll()
						.anyRequest().authenticated())
				.oauth2ResourceServer(a->a.jwt(j->j.jwtAuthenticationConverter(jwtconvertor())))
				.build();
	}

	@Bean
	public CorsConfigurationSource corsconfiguration() {
		CorsConfiguration config=new CorsConfiguration();
		config.setAllowedOrigins(List.of("http://localhost:5173"));
		config.setAllowedHeaders(List.of("*"));
		config.setAllowedMethods(List.of("GET","POST","PUT","DELETE"));
		config.setAllowCredentials(true);
		
		UrlBasedCorsConfigurationSource url=new UrlBasedCorsConfigurationSource();
		url.registerCorsConfiguration("/**", config);
		return url;
	}

	@Bean
	public JwtAuthenticationConverter jwtconvertor() {
		JwtGrantedAuthoritiesConverter grand=new JwtGrantedAuthoritiesConverter();
		grand.setAuthorityPrefix("ROLE_");
		grand.setAuthoritiesClaimName("role");
		
		JwtAuthenticationConverter convo=new JwtAuthenticationConverter();
		convo.setJwtGrantedAuthoritiesConverter(grand);
		return convo;
	}
	
	@Bean
	public JwtDecoder jwtdecode() {
		String secretkey="iIMtq3jUiFI7AZzxrziz0W5gtM41ZbP//6BEc/sG4Y8=";
		byte[]keybytes=Base64.getDecoder().decode(secretkey);
		SecretKeySpec spec=new SecretKeySpec(keybytes, secretkey);
		return NimbusJwtDecoder.withSecretKey(spec).build();
	}
	
	@Override
	 public void addResourceHandlers(ResourceHandlerRegistry regis){
        regis.addResourceHandler("/reports/**")
        .addResourceLocations("file:reports/");
		
		
		regis.addResourceHandler("/uploads/**")
        .addResourceLocations("file:uploads/");
                
        // .setCachePeriod(3600);

    }



}
