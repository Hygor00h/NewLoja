package com.loja.newloja.infra.controller;

import com.loja.newloja.infra.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {


    private final PedidoService pedidoService;

    @PostMapping("/checkout")
    public ResponseEntity<Map<String, String>> checkout (@RequestParam UUID usuarioId,
                                                         @RequestParam Long itemEstoqueId,
                                                         @RequestParam Long quantidade,
                                                         @RequestParam BigDecimal valorPago){
        pedidoService.solicitarCheckout(usuarioId, itemEstoqueId, quantidade, valorPago);

        return ResponseEntity.accepted().body(Map.of(
                "mensagem", "Pedido recebido com sucesso! Seu checkout está sendo processado."
        ));
    }
}
