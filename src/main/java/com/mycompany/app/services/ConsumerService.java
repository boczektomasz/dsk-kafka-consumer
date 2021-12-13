package com.mycompany.app.services;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class ConsumerService {


    private String bootstrap_servers;
    private String topic_name;
    public static List<String> MESSAGES = new ArrayList<String>();

    public static void consume(String bootstrap_servers, String topic_name) {

        final Logger logger = LoggerFactory.getLogger(ConsumerService.class.getName());

        String groupId = "my_group_id";

        // create consumer configs
        Properties props = new Properties();
        String bootstrapServer = bootstrap_servers;
        String topic = topic_name;

        System.out.println("Bootstrap servers: " + bootstrapServer);
        System.out.println("Topic name: " + topic);

        props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); // possible values: earliest/latest/none

        // create consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);

        // subscribe consumer to topic
        consumer.subscribe(Collections.singleton(topic));


        // poll for new data
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                logger.info("Key:" + record.key());
                logger.info("Value:" + record.value());
                logger.info("Partition:" + record.partition());
                logger.info("Offset:" + record.offset());
                // Add message to custom stack
                MESSAGES.add(record.key() + " -- " +  record.value() + " -- " + record.partition() + " -- " + record.offset());
            }
        }
    }
}
