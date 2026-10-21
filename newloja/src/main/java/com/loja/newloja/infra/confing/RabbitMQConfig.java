package com.loja.newloja.infra.confing;


import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_CHECKOUT = "ex.checkout";
    public static final String QUEUE_CHECKOUT = "fila.checkout.pedidos";
    public static final String ROUTING_KEY_CHECKOUT = "checkout.pedido";

    @Bean
    public DirectExchange checkoutExchange(){
        return new DirectExchange(EXCHANGE_CHECKOUT);
    }

    @Bean
    public Queue checkoutQueue(){
        return QueueBuilder.durable(QUEUE_CHECKOUT).build();
    }

    @Bean
    public Binding checkoutBinding(Queue checkoutQueue, DirectExchange checkoutExchange){
        return BindingBuilder.bind(checkoutQueue).to(checkoutExchange).with(ROUTING_KEY_CHECKOUT);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}

