package ma.cloud.consumer.config;

import ma.cloud.consumer.entities.Bill;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    ConsumerFactory<String, Bill> consumerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "enset_uh2c");
        JsonDeserializer<Bill> jsonDeserializer=new JsonDeserializer<>(Bill.class);
        jsonDeserializer.addTrustedPackages("*");
        jsonDeserializer.setUseTypeHeaders(false);
        return new DefaultKafkaConsumerFactory(config,new StringDeserializer(),jsonDeserializer);
    }
    @Bean
    ConcurrentKafkaListenerContainerFactory<String, Bill> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, Bill> factory=new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}