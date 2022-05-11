///usr/bin/env jbang "$0" "$@" ; exit $?
//JAVA 8
//DEPS org.springframework.boot:spring-boot-dependencies:2.6.7@pom
//DEPS org.springframework.cloud:spring-cloud-dependencies:2021.0.2@pom
//DEPS com.alibaba.cloud:spring-cloud-alibaba-dependencies:2021.1@pom
//DEPS org.springframework.boot:spring-boot-starter-webflux
//DEPS com.alibaba.cloud:spring-cloud-starter-stream-rocketmq
//FILES ../../resources/application.properties

package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

@SpringBootApplication
public class RocketmqConsumerApp {
    public static void main(String[] args) {
        SpringApplication.run(RocketmqConsumerApp.class, args);
    }

    @Bean
    public Consumer<String> sink() {
        return payload -> System.out.println("Payload:" + payload);
    }

}
