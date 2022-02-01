///usr/bin/env jbang "$0" "$@" ; exit $?
//JAVA 8
//DEPS org.apache.dubbo:dubbo-dependencies-bom:3.0.5@pom
//DEPS org.apache.dubbo:dubbo:3.0.5
//DEPS org.apache.curator:curator-x-discovery
//DEPS org.apache.zookeeper:zookeeper
//SOURCES GreetingsService.java

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.rpc.model.ApplicationModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class DubboServiceApp {
    public static void main(String[] args) throws Exception {
        ApplicationModel.defaultModel().getApplicationConfigManager().setApplication(new ApplicationConfig("greeting-service-provider"));
        //String registryURL = "zookeeper://127.0.0.1:2181";  // production
        String registryURL = "multicast://224.5.6.7:1234";
        ServiceConfig<GreetingsService> service = new ServiceConfig<>();
        service.setRegistry(new RegistryConfig(registryURL));
        service.setInterface(GreetingsService.class);
        service.setRef(new GreetingsServiceImpl());
        service.export();
        System.out.println("Dubbo service started");
        new CountDownLatch(1).await();
    }

    public static class GreetingsServiceImpl implements GreetingsService {
        @Override
        public String sayHi(String name) {
            return "hi, " + name;
        }

        @Override
        public String findNick(Integer id) {
            return "nick:" + id;
        }

        @Override
        public List<Map<String, String>> statistics() {
            List<Map<String, String>> statistics = new ArrayList<>();
            statistics.add(Collections.singletonMap("abc", "1234"));
            return statistics;
        }

        @Override
        public User info(String name) {
            return new User(1, name);
        }

        @Override
        public String exceptionCall(String name) throws Exception {
            throw new Exception("Hi");
        }
    }

}
