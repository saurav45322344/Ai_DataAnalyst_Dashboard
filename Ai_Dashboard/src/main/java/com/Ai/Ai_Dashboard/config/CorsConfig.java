package com.Ai.Ai_Dashboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SuppressWarnings("unused")
@Configuration
public class CorsConfig {

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
				.allowedOrigins("http://localhost:5173")
				.allowedMethods("*");
			}
		};
	}
}

// 	@Bean
//     public CorsFilter corsFilter() {

//         CorsConfiguration config = new CorsConfiguration();

//         config.setAllowCredentials(true);
//         config.addAllowedOriginPattern("*"); // allow all (for dev)
//         config.addAllowedHeader("*");
//         config.addAllowedMethod("*");

//         UrlBasedCorsConfigurationSource source =
//                 new UrlBasedCorsConfigurationSource();

//         source.registerCorsConfiguration("/**", config);

//         return new CorsFilter(source);
//     }
// }



