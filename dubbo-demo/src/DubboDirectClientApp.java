///usr/bin/env jbang "$0" "$@" ; exit $?
//JAVA 8
//DEPS org.apache.dubbo:dubbo-dependencies-bom:3.0.5@pom
//DEPS org.apache.dubbo:dubbo:3.0.5
//DEPS org.apache.curator:curator-x-discovery
//DEPS org.apache.zookeeper:zookeeper
//SOURCES GreetingsService.java

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.model.ApplicationModel;
import org.apache.dubbo.rpc.service.GenericService;

import java.net.URI;

public class DubboDirectClientApp {
    public static void main(String[] args) {
        ApplicationModel.defaultModel().getApplicationConfigManager().setApplication(new ApplicationConfig("ConsumerTest"));
        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
        try {
            final URI uri = URI.create("dubbo://127.0.0.1:20880/GreetingsService?methods=sayHi(java.lang.String)");
            reference.setUrl(uri.toString());
            reference.setInterface(uri.getPath().substring(1));
            RpcContext.getClientAttachment().setAttachment("generic", "gson");
            reference.setGeneric("gson");
            reference.setCheck(false);
            Object responseObject = reference.get().$invoke("sayHi", new String[]{"java.lang.String"}, new Object[]{"Jackie"});
            System.out.println(responseObject.toString());
        } catch (Throwable ex) {
            ex.printStackTrace();
        } finally {
            reference.destroy();
        }
    }

}
