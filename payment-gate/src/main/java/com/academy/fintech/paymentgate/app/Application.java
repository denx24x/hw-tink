package com.academy.fintech.paymentgate.app;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;

@SpringBootApplication(
        exclude = {
//				GsonAutoConfiguration.class,
//				MultipartAutoConfiguration.class,
//				WebSocketServletAutoConfiguration.class,
//				NettyAutoConfiguration.class,

//				**Use it if work via Rest**

                RestTemplateAutoConfiguration.class,
                HttpMessageConvertersAutoConfiguration.class,
                HttpEncodingAutoConfiguration.class,
                ErrorMvcAutoConfiguration.class
        }
)
public class Application {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class)
                .beanNameGenerator(new FullyQualifiedAnnotationBeanNameGenerator())
                .run(args);
    }

}
