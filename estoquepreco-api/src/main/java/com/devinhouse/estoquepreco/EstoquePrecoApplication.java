package com.devinhouse.estoquepreco;

import com.devinhouse.estoquepreco.constants.RabbitMQConstants;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EstoquePrecoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EstoquePrecoApplication.class, args);
	}

}
