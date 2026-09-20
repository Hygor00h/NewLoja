package com.loja.newloja.infra.service;

import com.loja.newloja.infra.model.dto.carrinhoDtos.AdicionarItemDTO;
import com.loja.newloja.infra.model.dto.carrinhoDtos.CarrinhoResponseDTO;
import com.loja.newloja.infra.model.dto.carrinhoDtos.ItemCarrinhoResponseDTO;
import com.loja.newloja.infra.model.entity.carrinho.CarrinhoEntity;
import com.loja.newloja.infra.model.entity.carrinho.ItemCarrinhoEntity;
import com.loja.newloja.infra.model.entity.produto.ItemEstoqueEntity;
import com.loja.newloja.infra.repository.CarrinhoRepository;
import com.loja.newloja.infra.repository.ItemEstoqueRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final ItemEstoqueRepository itemEstoqueRepository;

    public CarrinhoService(CarrinhoRepository carrinhoRepository, ItemEstoqueRepository itemEstoqueRepository) {
        this.carrinhoRepository = carrinhoRepository;
        this.itemEstoqueRepository = itemEstoqueRepository;
    }

    @Transactional(readOnly = true)
    public CarrinhoResponseDTO obterOuCriarCarrinho(UUID usuarioId) {
        CarrinhoEntity carrinho = carrinhoRepository.findByUsuarioId(usuarioId)
                .orElseGet(() -> carrinhoRepository.save(new CarrinhoEntity(usuarioId)));
        return converterParaDTO(carrinho);
    }

    @Transactional
    public CarrinhoResponseDTO adicionarItem(UUID usuarioId, AdicionarItemDTO dto) {
        CarrinhoEntity carrinho = carrinhoRepository.findByUsuarioId(usuarioId)
                .orElseGet(() -> carrinhoRepository.save(new CarrinhoEntity(usuarioId)));

        ItemEstoqueEntity estoque = itemEstoqueRepository.findById(dto.itemEstoqueId())
                .orElseThrow(() -> new IllegalArgumentException("Item de estoque não encontrado com ID: " + dto.itemEstoqueId()));

        if (estoque.getQuantidade() < dto.quantidade()) {
            throw new IllegalArgumentException("Estoque insuficiente. Disponível: " + estoque.getQuantidade());
        }

        // Verifica se o item já está no carrinho
        ItemCarrinhoEntity itemExistente = carrinho.getItens().stream()
                .filter(i -> i.getItemEstoque().getId().equals(dto.itemEstoqueId()))
                .findFirst()
                .orElse(null);

        if (itemExistente != null) {
            int novaQtd = itemExistente.getQuantidade() + dto.quantidade();
            if (estoque.getQuantidade() < novaQtd) {
                throw new IllegalArgumentException("Quantidade total excede o estoque disponível.");
            }
            itemExistente.setQuantidade(novaQtd);
        } else {
            ItemCarrinhoEntity novoItem = new ItemCarrinhoEntity(carrinho, estoque, dto.quantidade());
            carrinho.getItens().add(novoItem);
        }

        carrinho = carrinhoRepository.save(carrinho);
        return converterParaDTO(carrinho);
    }

    @Transactional
    public CarrinhoResponseDTO removerItem(UUID usuarioId, Long itemEstoqueId, Long quantidadeRemover) {
        CarrinhoEntity carrinho = carrinhoRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Carrinho não encontrado."));
        ItemCarrinhoEntity itemEncontrado = carrinho.getItens().stream()
                .filter(item -> item.getItemEstoque().getId().equals(itemEstoqueId))
                .findFirst()
                .orElseThrow(()-> new EntityNotFoundException(""));
        if(itemEncontrado.getQuantidade() > quantidadeRemover) {
            itemEncontrado.setQuantidade((int) (itemEncontrado.getQuantidade() - quantidadeRemover));
        }else {
            carrinho.getItens().remove(itemEncontrado);
        }
        carrinho = carrinhoRepository.save(carrinho);

        return converterParaDTO(carrinho);
    }




    @Transactional
    public void limparCarrinho(UUID usuarioId) {
        carrinhoRepository.findByUsuarioId(usuarioId).ifPresent(carrinho -> {
            carrinho.getItens().clear();
            carrinhoRepository.save(carrinho);
        });
    }

    private CarrinhoResponseDTO converterParaDTO(CarrinhoEntity carrinho) {
        List<ItemCarrinhoResponseDTO> itensDTO = new ArrayList<>();
        BigDecimal valorTotal = BigDecimal.ZERO;
        int quantidadeTotal = 0;

        for (ItemCarrinhoEntity item : carrinho.getItens()) {
            BigDecimal preco = item.getItemEstoque().getProduto().getPreco();
            BigDecimal subtotal = preco.multiply(BigDecimal.valueOf(item.getQuantidade()));

            valorTotal = valorTotal.add(subtotal);
            quantidadeTotal += item.getQuantidade();

            itensDTO.add(new ItemCarrinhoResponseDTO(
                    item.getId(),
                    item.getItemEstoque().getId(),
                    item.getItemEstoque().getProduto().getNome(),
                    item.getItemEstoque().getProduto().getImagemUrl(),
                    item.getItemEstoque().getTamanho(),
                    item.getItemEstoque().getCor(),
                    preco,
                    item.getQuantidade(),
                    subtotal
            ));
        }

        return new CarrinhoResponseDTO(carrinho.getId(), itensDTO, valorTotal, quantidadeTotal);
    }
}