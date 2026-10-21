package com.loja.newloja.infra.confing;

import com.loja.newloja.infra.exceptions.ResourceNotFoundException;
import com.loja.newloja.infra.model.dto.PedidoCheckoutMessage;
import com.loja.newloja.infra.model.entity.carrinho.CompraEntity;
import com.loja.newloja.infra.model.entity.produto.ItemEstoqueEntity;
import com.loja.newloja.infra.model.entity.usuario.Usuarios;
import com.loja.newloja.infra.model.enums.StatusProcesso;
import com.loja.newloja.infra.repository.CompraRepository;
import com.loja.newloja.infra.repository.ItemEstoqueRepository;
import com.loja.newloja.infra.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Slf4j
@Component
@RequiredArgsConstructor
public class PedidoCheckoutConsumer {

    private final ItemEstoqueRepository itemEstoqueRepository;
    private final UsuarioRepository usuarioRepository;
    private final CompraRepository compraRepository;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_CHECKOUT, concurrency = "1")
    @Transactional
    public void processarCheckout(PedidoCheckoutMessage mensagem) {

        log.info("Processando pedido do usuário: {}", mensagem.usuarioId());

        Usuarios usuario = usuarioRepository.findById(mensagem.usuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado"));

        ItemEstoqueEntity estoque = itemEstoqueRepository.findById(mensagem.itemEstoqueId())
                .orElseThrow(() -> new ResourceNotFoundException("Item de estoque não encontrado ID: " + mensagem.itemEstoqueId()));

        String nomeProduto = estoque.getProduto().getNome();
        BigDecimal precoUnitario = estoque.getProduto().getPreco();
        BigDecimal valorTotalEsperado = precoUnitario.multiply(BigDecimal.valueOf(mensagem.quantidade()));


        CompraEntity compraBase = new CompraEntity();
        compraBase.setNomeProduto(nomeProduto);
        compraBase.setNomeComprador(usuario.getName());
        compraBase.setCompradorId(usuario.getId());
        compraBase.setValorProduto(valorTotalEsperado);


        if (estoque.getQuantidade() < mensagem.quantidade()) {
            log.warn("Estoque insuficiente");
            compraBase.setStatusProcesso(StatusProcesso.ESGOTADO);
            compraRepository.save(compraBase);
            return;
        }

        if(mensagem.valorPago().compareTo(valorTotalEsperado) < 0){
            log.warn("Valor insuficiente");
            compraBase.setStatusProcesso(StatusProcesso.PAGAMENTO_INCORRETO);
            compraRepository.save(compraBase);
        }

        estoque.setQuantidade((int) (estoque.getQuantidade() - mensagem.quantidade()));
        itemEstoqueRepository.save(estoque);


        compraBase.setStatusProcesso(StatusProcesso.SUCESSO);
        compraRepository.save(compraBase);

        log.info("Compra realizada com sucesso");
    }
}
