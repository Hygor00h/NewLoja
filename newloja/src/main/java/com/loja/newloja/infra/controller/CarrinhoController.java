package com.loja.newloja.infra.controller;


import com.loja.newloja.infra.model.dto.carrinhoDtos.AdicionarItemDTO;
import com.loja.newloja.infra.model.dto.carrinhoDtos.CarrinhoResponseDTO;
import com.loja.newloja.infra.service.CarrinhoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/carrinho")
@RequiredArgsConstructor
public class CarrinhoController {

    private final CarrinhoService carrinhoService;

    @GetMapping
    public ResponseEntity<CarrinhoResponseDTO> obterCarrinho(@RequestParam UUID usuarioId) {
        return ResponseEntity.ok(carrinhoService.obterOuCriarCarrinho(usuarioId));
    }

    @PostMapping("/itens")
    public ResponseEntity<CarrinhoResponseDTO> adicionarItem(
            @RequestParam UUID usuarioId,
            @Valid @RequestBody AdicionarItemDTO dto) {
        return ResponseEntity.ok(carrinhoService.adicionarItem(usuarioId, dto));
    }

    @DeleteMapping("/itens/{itemEstoqueId}")
    public ResponseEntity<CarrinhoResponseDTO> removerItem(
            @RequestParam UUID usuarioId,
            @PathVariable Long itemEstoqueId,
            @RequestParam Long quantidade) {
        return ResponseEntity.ok(carrinhoService.removerItem(usuarioId, itemEstoqueId, quantidade));
    }

    @DeleteMapping
    public ResponseEntity<Void> limparCarrinho(@RequestParam UUID usuarioId) {
        carrinhoService.limparCarrinho(usuarioId);
        return ResponseEntity.noContent().build();
    }

}