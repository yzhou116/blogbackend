package com.yizhou.yiblog;

import com.google.gson.Gson;
import com.yizhou.yiblog.util.RedisUtil;
import com.yizhou.yiblog.util.SnowflakeIdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Random;

@EnableSwagger2
@SpringBootApplication
public class YiblogApplication extends SpringBootServletInitializer  {
//
    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
   //     System.setProperty("server.connection-timeout","300000");
        SpringApplication.run(YiblogApplication.class, args);
    }

    protected SpringApplicationBuilder config(SpringApplicationBuilder applicationBuilder){
        return applicationBuilder.sources(YiblogApplication.class);
    }




    @Bean
    public SnowflakeIdWorker getUserID() {
        return new SnowflakeIdWorker(0, 0);

    }

    @Bean
    public BCryptPasswordEncoder cryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RedisUtil getRedisUtil() {
        return new RedisUtil();
    }

    @Bean
    public Random randomInt() {
        return new Random();
    }

    @Bean
    public Gson createGson() {
        return new Gson();
    }
    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedSlash(true);
        return firewall;
    }

}
