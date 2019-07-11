package com.mouse.bms.product.config.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : SwaggerConfig
 * @date : 2019/3/19 14:17
 * @description :
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket buildDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(buildApiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.mouse.bms.product"))
            .paths(PathSelectors.any())
            .build();
    }

    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder()
            .title("商品中心API")
            .description("商品中心对外接口，供前端页面调用")
            .termsOfServiceUrl("http://127.0.0.1:8899")
            .contact(new Contact(
                "mouse",
                "http://127.0.0.1:8899",
                "1379202076@qq.com"))
            .version("1.0")
            .build();
    }

}
