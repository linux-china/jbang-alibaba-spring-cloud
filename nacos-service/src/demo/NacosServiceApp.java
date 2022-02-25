///usr/bin/env jbang "$0" "$@" ; exit $?
//JAVA 8
//DEPS org.springframework.boot:spring-boot-dependencies:2.5.10@pom
//DEPS org.springframework.cloud:spring-cloud-dependencies:2020.0.5@pom
//DEPS com.alibaba.cloud:spring-cloud-alibaba-dependencies:2021.1@pom
//DEPS org.springframework.boot:spring-boot-starter-webflux
//DEPS com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery
//DEPS org.springframework.cloud:spring-cloud-starter-loadbalancer
//JAVA_OPTIONS -Dspring.application.name=server-app -Dserver.port=0
//JAVA_OPTIONS -Dspring.cloud.nacos.config.server-addr=127.0.0.1:8848

package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
public class NacosServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(NacosServiceApp.class, args);
    }

    @RestController
    public static class ServiceController {
        @GetMapping("/")
        public String index() {
            return "Hello from Service!";
        }
    }
}
