package com.mycompany.app;


import com.mycompany.app.services.ConsumerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsumerDemo {

    @Value("${BOOTSTRAP_SERVERS}")
    private String bootstrap_servers;

    private static String BOOTSTRAP_SERVERS;

    @Value("${BOOTSTRAP_SERVERS}")
    public void setBootstrapServersStatic(String bootstrapServers){
        ConsumerDemo.BOOTSTRAP_SERVERS = bootstrapServers;
    }

    @Value("${TOPIC_NAME}")
    private String topicName;

    private static String TOPIC_NAME;

    @Value("${TOPIC_NAME}")
    public void setTopicNameStatic(String topicName){
        ConsumerDemo.TOPIC_NAME = topicName;
    }


    // Default port is 8080
    public static void main(String[] args) {
        SpringApplication.run(ConsumerDemo.class, args);
        ConsumerService.consume(BOOTSTRAP_SERVERS, TOPIC_NAME );
    }
}
