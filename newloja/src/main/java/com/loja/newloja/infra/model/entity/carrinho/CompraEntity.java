package com.loja.newloja.infra.model.entity.carrinho;

import com.loja.newloja.infra.model.enums.StatusProcesso;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_compra")
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class CompraEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_produto", nullable = false)
    private String nomeProduto;

    @Column(name = "nome_comprador", nullable = false)
    private String nomeComprador;

    @Column(name = "comprador_id", nullable = false)
    private UUID compradorId;

    @Column(name = "valor_produto", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorProduto;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_processo", nullable = false)
    private StatusProcesso statusProcesso;

    @Column(name = "horario_compra", nullable = false, updatable = false)
    private LocalDateTime horarioCompra;

    @PrePersist
    public void prePersist(){
        if(this.horarioCompra == null){
            this.horarioCompra = LocalDateTime.now();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getNomeComprador() {
        return nomeComprador;
    }

    public void setNomeComprador(String nomeComprador) {
        this.nomeComprador = nomeComprador;
    }

    public UUID getCompradorId() {
        return compradorId;
    }

    public void setCompradorId(UUID compradorId) {
        this.compradorId = compradorId;
    }

    public BigDecimal getValorProduto() {
        return valorProduto;
    }

    public void setValorProduto(BigDecimal valorProduto) {
        this.valorProduto = valorProduto;
    }

    public StatusProcesso getStatusProcesso() {
        return statusProcesso;
    }

    public void setStatusProcesso(StatusProcesso statusProcesso) {
        this.statusProcesso = statusProcesso;
    }

    public LocalDateTime getHorarioCompra() {
        return horarioCompra;
    }

    public void setHorarioCompra(LocalDateTime horarioCompra) {
        this.horarioCompra = horarioCompra;
    }
}
