///usr/bin/env jbang "$0" "$@" ; exit $?
//JAVA 11
//DEPS org.apache.dubbo:dubbo-dependencies-bom:3.0.5@pom
//DEPS org.apache.dubbo:dubbo:3.0.5
//DEPS org.apache.curator:curator-x-discovery
//DEPS org.apache.zookeeper:zookeeper
//DEPS com.google.code.gson:gson:2.9.0
//DEPS com.example:greetings-service:1.0.0-SNAPSHOT

import com.google.gson.Gson;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.model.ApplicationModel;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import user.GreetingsService;

    public class Dubbo {
        String registryURL = "multicast://224.5.6.7:1234";

        public Dubbo() {
            BasicConfigurator.configure();
            Logger.getRootLogger().setLevel(Level.ERROR);
            final ApplicationConfig applicationConfig = new ApplicationConfig("greeting-service-consumer");
            applicationConfig.setQosEnable(false);
            ApplicationModel.defaultModel().getApplicationConfigManager().setApplication(applicationConfig);
        }

        public GreetingsService greetingsService() {
            ReferenceConfig<GreetingsService> reference = new ReferenceConfig<>();
            reference.setRegistry(new RegistryConfig(registryURL));
            reference.setInterface(GreetingsService.class);
            return reference.get();
        }
    }
    Gson gson = new Gson();
    GreetingsService greetingsService = new Dubbo().greetingsService();



