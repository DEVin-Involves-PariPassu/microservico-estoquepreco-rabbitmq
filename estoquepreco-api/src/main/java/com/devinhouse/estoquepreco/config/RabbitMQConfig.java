package com.devinhouse.estoquepreco.config;

import com.devinhouse.estoquepreco.constants.RabbitMQConstants;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class RabbitMQConfig {

    private AmqpAdmin amqpAdmin;

    public RabbitMQConfig(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    private DirectExchange estoquePrecoExchange() {
        return new DirectExchange("estoqueproduto.exchange");
    }

    private Queue fila(String nomeFila) {
        return new Queue(nomeFila, true, false, false);
    }

    private Binding relacionamento(Queue fila, DirectExchange exchange) {
        return new Binding(fila.getName(), Binding.DestinationType.QUEUE, exchange.getName(), fila.getName(), null);
    }

    @PostConstruct
    private void construirFilas() {
        Queue filaEstoque = this.fila(RabbitMQConstants.FILA_ESTOQUE);
        Queue filaPreco = this.fila(RabbitMQConstants.FILA_PRECO);

        DirectExchange exchange = this.estoquePrecoExchange();

        Binding ligacaoEstoque = this.relacionamento(filaEstoque, exchange);
        Binding ligacaoPreco = this.relacionamento(filaPreco, exchange);

        this.amqpAdmin.declareQueue(filaEstoque);
        this.amqpAdmin.declareQueue(filaPreco);

        this.amqpAdmin.declareExchange(exchange);

        this.amqpAdmin.declareBinding(ligacaoEstoque);
        this.amqpAdmin.declareBinding(ligacaoPreco);
    }
}
