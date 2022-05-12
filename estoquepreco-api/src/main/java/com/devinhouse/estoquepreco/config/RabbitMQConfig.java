package com.devinhouse.estoquepreco.config;

import com.devinhouse.estoquepreco.constants.RabbitMQConstants;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(RabbitMQConstants.EXCHANGE);
    }

    @Bean
    Queue filaEstoque() {
        return QueueBuilder.durable(RabbitMQConstants.FILA_ESTOQUE)
                .build();
    }

    @Bean
    Queue filaPreco() {
        return QueueBuilder.durable(RabbitMQConstants.FILA_PRECO).build();
    }

    @Bean
    Binding bindingFilaEstoque(Queue filaEstoque, DirectExchange directExchange) {
        return BindingBuilder.bind(filaEstoque).to(directExchange)
                .with(RabbitMQConstants.FILA_ESTOQUE);
    }

    @Bean
    Binding bindingFilaPreco(Queue filaPreco, DirectExchange directExchange) {
        return BindingBuilder.bind(filaPreco).to(directExchange)
                .with(RabbitMQConstants.FILA_PRECO);
    }

}
