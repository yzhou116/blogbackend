package com.yizhou.yiblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class Swagger2Configration {
    public static final String VERSION = "1.0.0";

    /**
     * 门户api，接口前缀：portal
     *
     * @return
     */
    @Bean
    public Docket portalApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(portalApiInfo())
                .select()
                //视频里用的是.不是/不行的话改成.试试
                .apis(RequestHandlerSelectors.basePackage("com.yizhou.yiblog.controller.portal"))
                .paths(PathSelectors.any()) // 可以根据url路径设置哪些请求加入文档，忽略哪些请求
                .build()
                .groupName("FrontEnd Desk");
    }

    private ApiInfo portalApiInfo() {
        return new ApiInfoBuilder()
                .title("Yi's Blog Api file") //设置文档的标题
                .description("Api file document") // 设置文档的描述
                .version(VERSION) // 设置文档的版本信息-> 1.0.0 Version information
                .build();
    }


    /**
     * 管理中心api，接口前缀：admin
     *
     * @return
     */
    @Bean
    public Docket adminApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(adminApiInfo())
                .select()

                .apis(RequestHandlerSelectors.basePackage("com.yizhou.yiblog.controller.admin"))
                .paths(PathSelectors.any()) // 可以根据url路径设置哪些请求加入文档，忽略哪些请求
                .build()
                .groupName("Admin Center");
    }


    private ApiInfo adminApiInfo() {
        return new ApiInfoBuilder()
                .title("Yi's Blog Api File Document") //设置文档的标题
                .description("Enter Manage Center") // 设置文档的描述
                .version(VERSION) // 设置文档的版本信息-> 1.0.0 Version information
                .build();
    }


    @Bean
    public Docket UserApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(userApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yizhou.yiblog.controller.user"))
                .paths(PathSelectors.any()) // 可以根据url路径设置哪些请求加入文档，忽略哪些请求
                .build()
                .groupName("User Center");
    }

    private ApiInfo userApiInfo() {
        return new ApiInfoBuilder()
                .title("Yi's Blog System") //设置文档的标题
                .description("User Api Explain Center") // 设置文档的描述
                .version(VERSION) // 设置文档的版本信息-> 1.0.0 Version information
                .build();
    }

}
