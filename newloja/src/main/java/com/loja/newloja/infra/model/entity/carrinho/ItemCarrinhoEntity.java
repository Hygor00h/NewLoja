package com.loja.newloja.infra.model.entity.carrinho;

import com.loja.newloja.infra.model.entity.produto.ItemEstoqueEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_item_carrinho")
public class ItemCarrinhoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carrinho_id", nullable = false)
    private CarrinhoEntity carrinho;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_estoque_id", nullable = false)
    private ItemEstoqueEntity itemEstoque;

    @Column(nullable = false)
    private Integer quantidade;

    public ItemCarrinhoEntity() {}

    public ItemCarrinhoEntity(CarrinhoEntity carrinho, ItemEstoqueEntity itemEstoque, Integer quantidade) {
        this.carrinho = carrinho;
        this.itemEstoque = itemEstoque;
        this.quantidade = quantidade;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public CarrinhoEntity getCarrinho() { return carrinho; }
    public void setCarrinho(CarrinhoEntity carrinho) { this.carrinho = carrinho; }

    public ItemEstoqueEntity getItemEstoque() { return itemEstoque; }
    public void setItemEstoque(ItemEstoqueEntity itemEstoque) { this.itemEstoque = itemEstoque; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
}
