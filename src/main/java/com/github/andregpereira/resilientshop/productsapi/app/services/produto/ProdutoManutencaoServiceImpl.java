package com.github.andregpereira.resilientshop.productsapi.app.services.produto;

import com.github.andregpereira.resilientshop.productsapi.app.dtos.produto.ProdutoAtualizacaoDto;
import com.github.andregpereira.resilientshop.productsapi.app.dtos.produto.ProdutoAtualizarEstoqueDto;
import com.github.andregpereira.resilientshop.productsapi.app.dtos.produto.ProdutoDetalhesDto;
import com.github.andregpereira.resilientshop.productsapi.app.dtos.produto.ProdutoRegistroDto;
import com.github.andregpereira.resilientshop.productsapi.cross.exceptions.ProdutoAlreadyExistsException;
import com.github.andregpereira.resilientshop.productsapi.cross.exceptions.ProdutoNotFoundException;
import com.github.andregpereira.resilientshop.productsapi.cross.exceptions.SubcategoriaNotFoundException;
import com.github.andregpereira.resilientshop.productsapi.cross.mappers.ProdutoMapper;
import com.github.andregpereira.resilientshop.productsapi.infra.entities.Produto;
import com.github.andregpereira.resilientshop.productsapi.infra.repositories.ProdutoRepository;
import com.github.andregpereira.resilientshop.productsapi.infra.repositories.SubcategoriaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class ProdutoManutencaoServiceImpl implements ProdutoManutencaoService {

    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper mapper;
    private final SubcategoriaRepository subcategoriaRepository;

    @Override
    public ProdutoDetalhesDto registrar(ProdutoRegistroDto dto) {
        if (produtoRepository.existsBySku(dto.sku())) {
            log.info("Produto já cadastrado com o SKU {}", dto.sku());
            throw new ProdutoAlreadyExistsException(dto.sku());
        } else if (produtoRepository.existsByNome(dto.nome())) {
            log.info("Produto já cadastrado com o nome {}", dto.nome());
            throw new ProdutoAlreadyExistsException(dto.nome());
        } else
            return subcategoriaRepository.findById(dto.idSubcategoria()).map(sc -> {
                Produto produto = mapper.toProduto(dto);
                produto.setDataCriacao(LocalDateTime.now());
                produto.setSubcategoria(sc);
                produtoRepository.save(produto);
                log.info("Produto criado");
                return mapper.toProdutoDetalhesDto(produto);
            }).orElseThrow(() -> {
                log.info("Subcategoria não encontrada com id {}", dto.idSubcategoria());
                return new SubcategoriaNotFoundException(dto.idSubcategoria());
            });
    }

    @Override
    public ProdutoDetalhesDto atualizar(Long id, ProdutoAtualizacaoDto dto) {
        return produtoRepository.findById(id).map(produtoAntigo -> {
            if (produtoRepository.existsByNome(dto.nome())) {
                log.info("Produto já cadastrado com o nome {}", dto.nome());
                throw new ProdutoAlreadyExistsException(dto.nome());
            } else
                return subcategoriaRepository.findById(dto.idSubcategoria()).map(sc -> {
                    Produto produtoAtualizado = mapper.toProduto(dto);
                    produtoAtualizado.setId(id);
                    produtoAtualizado.setSku(produtoAntigo.getSku());
                    produtoAtualizado.setDataCriacao(produtoAntigo.getDataCriacao());
                    produtoAtualizado.setSubcategoria(sc);
                    produtoRepository.save(produtoAtualizado);
                    log.info("Produto com id {} atualizado", id);
                    return mapper.toProdutoDetalhesDto(produtoAtualizado);
                }).orElseThrow(() -> {
                    log.info("Subcategoria não encontrada com id {}", dto.idSubcategoria());
                    return new SubcategoriaNotFoundException(dto.idSubcategoria());
                });
        }).orElseThrow(() -> {
            log.info("Produto não encontrado com id {}", id);
            return new ProdutoNotFoundException(id);
        });
    }

    @Override
    public String remover(Long id) {
        return produtoRepository.findById(id).map(p -> {
            produtoRepository.deleteById(id);
            log.info("Produto com id {} removido", id);
            return MessageFormat.format("Produto com id {0} removido com sucesso", id);
        }).orElseThrow(() -> {
            log.info("Produto não encontrado com id {}", id);
            return new ProdutoNotFoundException(id);
        });
    }

    @Override
    public void subtrair(Long id, ProdutoAtualizarEstoqueDto dto) {
        produtoRepository.findById(id).ifPresentOrElse(p -> {
            if (p.getEstoque() >= dto.estoque()) {
                p.setEstoque(p.getEstoque() - dto.estoque());
                produtoRepository.save(p);
                log.info("Produto {} com {} itens subtraídos do estoque. Estoque atual: {}", p.getNome(), dto.estoque(),
                        p.getEstoque());
            } else
                throw new RuntimeException("estoque insuficiente");
        }, () -> {
            log.info("Produto não encontrado com id {}", id);
            throw new ProdutoNotFoundException(id);
        });
    }

}
