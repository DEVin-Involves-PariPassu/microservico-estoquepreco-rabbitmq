package com.devinhouse.estoquepreco.controller;

import com.devinhouse.estoquepreco.constants.RabbitMQConstants;
import com.devinhouse.estoquepreco.dto.EstoqueDto;
import com.devinhouse.estoquepreco.service.RabbitMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    @Autowired
    private RabbitMQService rabbitMQService;

    @PutMapping
    public ResponseEntity alterarEstoque(@RequestBody EstoqueDto estoqueDto) {

        this.rabbitMQService.enviarMensagem(RabbitMQConstants.FILA_ESTOQUE, estoqueDto);

        return ResponseEntity.ok().build();
    }

}
