package com.loja.newloja.infra.service;


import com.loja.newloja.infra.confing.RabbitMQConfig;
import com.loja.newloja.infra.model.dto.PedidoCheckoutMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final RabbitTemplate rabbitTemplate;

    public void solicitarCheckout(UUID usuarioId,
                                  Long itemEstoqueId,
                                  Long quantidade,
                                  BigDecimal valorPago){
        PedidoCheckoutMessage mensage = new PedidoCheckoutMessage(usuarioId, itemEstoqueId, quantidade, valorPago);



        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_CHECKOUT,
                RabbitMQConfig.ROUTING_KEY_CHECKOUT,
                mensage
        );
    }
}
