package br.com.billing.config.kafka.consumer;

import br.com.billing.props.ConsumerDefinition;
import br.com.billing.props.KafkaProperties;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamFunctionProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.LoggingErrorHandler;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConsumerLocalConfig {

    @Autowired
    private StreamFunctionProperties streamFunctionProperties;

    @PostConstruct
    public void init() {
        streamFunctionProperties.setDefinition(ConsumerDefinition.getDefinition());
    }

    @Bean
    public ConsumerFactory<String, Object> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerProps());
    }

    protected Map<String, Object> consumerProps() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        props.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, StringDeserializer.class);
        props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, KafkaAvroDeserializer.class);
        props.put(KafkaProperties.SCHEMA_REGISTRY_URL, "http://localhost:8081");
        props.put(KafkaProperties.AVRO_READER, Boolean.TRUE.toString());
        props.put(KafkaProperties.ENABLE_AUTO_COMMIT, Boolean.TRUE.toString());
        props.put(KafkaProperties.AUTO_COMMIT_INTERVAL, "1000");
        props.put(KafkaProperties.MAX_POLL_RECORDS, 500);
        return props;
    }

    @Bean
    public ConsumerFactory<String, Object> jsonConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerJsonProps());
    }

    protected Map<String, Object> consumerJsonProps() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        props.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, StringDeserializer.class);
        props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, StringDeserializer.class);
        props.put(KafkaProperties.SCHEMA_REGISTRY_URL, "http://localhost:8081");
        props.put(KafkaProperties.ENABLE_AUTO_COMMIT, Boolean.TRUE.toString());
        props.put(KafkaProperties.AUTO_COMMIT_INTERVAL, "1000");
        props.put(KafkaProperties.MAX_POLL_RECORDS, 500);
        return props;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory jsonKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(jsonConsumerFactory());
        factory.setMessageConverter(new JsonMessageConverter());
        factory.setErrorHandler(new LoggingErrorHandler());
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory(
            ConsumerFactory<String, Object> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setErrorHandler(new LoggingErrorHandler());
        return factory;
    }

}
