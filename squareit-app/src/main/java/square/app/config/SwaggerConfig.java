package square.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.ZonedDateTime;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  /**
   * A bean constructor for Swagger2 api.
   * @return Swagger2 api
   */
  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .directModelSubstitute(ZonedDateTime.class, String.class)
        .select()
        .paths(PathSelectors.any())
        .apis(RequestHandlerSelectors.any())
        .build();
  }
}
