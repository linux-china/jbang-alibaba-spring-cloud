Spring Cloud Alibaba JBang Catalog
===============================

# Artifacts

```
$ jbang trust add https://github.com/linux-china/jbang-alibaba-spring-cloud/
```

# Nacos

Please install and start Nacos server first:  https://nacos.io/zh-cn/docs/quick-start.html

* Nacos Service: `jbang NacosService@linux-china/jbang-alibaba-spring-cloud`
* Nacos Client: `jbang NacosClient@linux-china/jbang-alibaba-spring-cloud`

# RocketMQ

Please install and start RocketMQ first: https://rocketmq.apache.org/docs/quick-start/

* RocketMQ Consumer: `jbang RocketmqConsumer@linux-china/jbang-alibaba-spring-cloud`
* RocketMQ Sender: `jbang RocketmqSender@linux-china/jbang-alibaba-spring-cloud testTopic '{"id":"1","name":"test"}'`

# Apache Dubbo

JBang Dubbo demo uses multicast for registry, Please use ZooKeeper for production env.

* Dubbo Service Provider: `jbang DubboServiceApp@linux-china/jbang-alibaba-spring-cloud`
* Dubbo Service Consumer: `jbang DubboClientApp@linux-china/jbang-alibaba-spring-cloud`

# Templates

* RocketMQ Consumer App: `jbang init -t RocketmqConsumer@linux-china/jbang-alibaba-spring-cloud RocketmqConsumerApp`

# References

* Spring Cloud Alibaba: https://spring.io/projects/spring-cloud-alibaba
* JBang: https://jbang.dev
