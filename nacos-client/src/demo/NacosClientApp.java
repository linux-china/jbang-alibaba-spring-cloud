///usr/bin/env jbang "$0" "$@" ; exit $?
//JAVA 8
//DEPS org.springframework.boot:spring-boot-dependencies:2.5.7@pom
//DEPS org.springframework.cloud:spring-cloud-dependencies:2020.0.4@pom
//DEPS com.alibaba.cloud:spring-cloud-alibaba-dependencies:2021.1@pom
//DEPS org.springframework.boot:spring-boot-starter-webflux
//DEPS com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery
//DEPS org.springframework.cloud:spring-cloud-starter-loadbalancer
//JAVA_OPTIONS -Dspring.application.name=client-app -Dserver.port=8082
//JAVA_OPTIONS -Dspring.cloud.nacos.config.server-addr=127.0.0.1:8848

package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@SpringBootApplication
@EnableDiscoveryClient
public class NacosClientApp {
    public static void main(String[] args) {
        SpringApplication.run(NacosClientApp.class, args);
    }

    @LoadBalanced
    @Bean
    WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @RestController
    public static class PortalController {
        private final WebClient.Builder loadBalancedWebClientBuilder;

        public PortalController(WebClient.Builder webClientBuilder) {
            this.loadBalancedWebClientBuilder = webClientBuilder;
        }

        @GetMapping("/")
        public Mono<String> callServer() {
            return loadBalancedWebClientBuilder.build().get().uri("http://server-app/").retrieve().bodyToMono(String.class).map(s -> "Response from server-app: " + s);
        }
    }

}
