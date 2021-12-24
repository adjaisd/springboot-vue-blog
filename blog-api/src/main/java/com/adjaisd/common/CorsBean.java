package com.adjaisd.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author zhangyuxuan 2019年5月20日
 */

@Configuration
public class CorsBean implements WebMvcConfigurer {

  @Value("${CORS_MAPPING}")
  private String mapping;

  @Value("${CORS_ALLOWED_ORIGINS}")
  private String[] allowedOrigins;

  @Value("${CORS_ALLOWED_METHOD}")
  private String[] allowedMethods;

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping(mapping).allowedOrigins(allowedOrigins).allowedMethods(allowedMethods).allowCredentials(true);
  }
}
