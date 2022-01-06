///usr/bin/env jbang "$0" "$@" ; exit $?
//JAVA 8
//DEPS org.apache.dubbo:dubbo-dependencies-bom:3.0.5@pom
//DEPS org.apache.dubbo:dubbo:3.0.5
//DEPS org.apache.curator:curator-x-discovery
//DEPS org.apache.zookeeper:zookeeper
//SOURCES GreetingsService.java

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

public class DubboClientApp {
    public static void main(String[] args) throws Exception {
        //String registryURL = "zookeeper://127.0.0.1:2181";  // production
        String registryURL = "multicast://224.5.6.7:1234";
        ReferenceConfig<GreetingsService> reference = new ReferenceConfig<>();
        reference.setApplication(new ApplicationConfig("greeting-service-consumer"));
        reference.setRegistry(new RegistryConfig(registryURL));
        reference.setInterface(GreetingsService.class);
        GreetingsService service = reference.get();
        String message = service.sayHi("Dubbo");
        System.out.println(message);
    }


}
