package com.loja.newloja.infra.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

public record PedidoCheckoutMessage(UUID usuarioId,
                                    Long itemEstoqueId,
                                    Long quantidade,
                                    BigDecimal valorPago) implements Serializable {
}
