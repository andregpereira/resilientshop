package com.github.andregpereira.resilientshop.productsapi.app.services.produto;

import com.github.andregpereira.resilientshop.productsapi.app.dtos.produto.ProdutoAtualizacaoDto;
import com.github.andregpereira.resilientshop.productsapi.app.dtos.produto.ProdutoAtualizarEstoqueDto;
import com.github.andregpereira.resilientshop.productsapi.app.dtos.produto.ProdutoDetalhesDto;
import com.github.andregpereira.resilientshop.productsapi.app.dtos.produto.ProdutoRegistroDto;

import java.util.List;

public interface ProdutoManutencaoService {

    ProdutoDetalhesDto criar(ProdutoRegistroDto dto);

    ProdutoDetalhesDto atualizar(Long id, ProdutoAtualizacaoDto dto);

    String remover(Long id);

    void subtrairEstoque(List<ProdutoAtualizarEstoqueDto> dtos);

    void retornarEstoque(List<ProdutoAtualizarEstoqueDto> dtos);

}
