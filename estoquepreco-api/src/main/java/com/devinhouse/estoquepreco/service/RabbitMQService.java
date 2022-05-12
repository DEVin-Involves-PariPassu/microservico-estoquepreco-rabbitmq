package com.devinhouse.estoquepreco.service;

import com.devinhouse.estoquepreco.constants.RabbitMQConstants;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQService {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void enviarMensagem(String routingKey, Object mensagem) {
        this.rabbitTemplate.convertAndSend(RabbitMQConstants.EXCHANGE, routingKey, mensagem);
    }
}
