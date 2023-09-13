package br.com.billing.custom;

import org.springframework.core.annotation.AliasFor;
import org.springframework.kafka.annotation.KafkaListener;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@KafkaListener
public @interface JsonKafkaListener {

    @AliasFor(annotation = KafkaListener.class, attribute = "id")
    String id() default "";

    @AliasFor(annotation = KafkaListener.class, attribute = "topics")
    String[] topics() default "";

    @AliasFor(annotation = KafkaListener.class, attribute = "containerFactory")
    String containerFactory() default "jsonKafkaListenerContainerFactory";
}
