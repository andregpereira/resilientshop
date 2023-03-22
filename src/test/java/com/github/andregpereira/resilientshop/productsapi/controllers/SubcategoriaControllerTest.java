package com.github.andregpereira.resilientshop.productsapi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.andregpereira.resilientshop.productsapi.dtos.subcategoria.SubcategoriaDto;
import com.github.andregpereira.resilientshop.productsapi.infra.exception.CategoriaNotFoundException;
import com.github.andregpereira.resilientshop.productsapi.infra.exception.SubcategoriaAlreadyExistsException;
import com.github.andregpereira.resilientshop.productsapi.infra.exception.SubcategoriaNotFoundException;
import com.github.andregpereira.resilientshop.productsapi.services.subcategoria.SubcategoriaConsultaService;
import com.github.andregpereira.resilientshop.productsapi.services.subcategoria.SubcategoriaManutencaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static com.github.andregpereira.resilientshop.productsapi.constants.SubcategoriaDtoConstants.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SubcategoriaController.class)
public class SubcategoriaControllerTest {

    @MockBean
    private SubcategoriaManutencaoService manutencaoService;

    @MockBean
    private SubcategoriaConsultaService consultaService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void criarSubcategoriaComDadosValidosRetornaCreated() throws Exception {
        given(manutencaoService.registrar(SUBCATEGORIA_REGISTRO_DTO)).willReturn(SUBCATEGORIA_DETALHES_DTO);
        mockMvc.perform(post("/subcategorias").content(
                objectMapper.writeValueAsString(SUBCATEGORIA_REGISTRO_DTO)).contentType(
                MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andExpectAll(jsonPath("$").exists(),
                jsonPath("$.nome").value(SUBCATEGORIA_DETALHES_DTO.nome()),
                jsonPath("$.descricao").value(SUBCATEGORIA_DETALHES_DTO.descricao()),
                jsonPath("$.categoria.nome").value(SUBCATEGORIA_DETALHES_DTO.categoria().nome()));
    }

    @Test
    public void criarSubcategoriaComDadosInvalidosRetornaUnprocessableEntity() throws Exception {
        mockMvc.perform(post("/subcategorias").content(
                objectMapper.writeValueAsString(SUBCATEGORIA_REGISTRO_DTO_INVALIDO)).contentType(
                MediaType.APPLICATION_JSON)).andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void criarSubcategoriaComCategoriaInexistenteRetornaNotFound() throws Exception {
        given(manutencaoService.registrar(SUBCATEGORIA_REGISTRO_DTO)).willThrow(CategoriaNotFoundException.class);
        mockMvc.perform(post("/subcategorias").content(
                objectMapper.writeValueAsString(SUBCATEGORIA_REGISTRO_DTO)).contentType(
                MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }

    @Test
    public void criarSubcategoriaComNomeExistenteRetornaConflict() throws Exception {
        given(manutencaoService.registrar(SUBCATEGORIA_REGISTRO_DTO)).willThrow(
                SubcategoriaAlreadyExistsException.class);
        mockMvc.perform(post("/subcategorias").content(
                objectMapper.writeValueAsString(SUBCATEGORIA_REGISTRO_DTO)).contentType(
                MediaType.APPLICATION_JSON)).andExpect(status().isConflict());
    }

    @Test
    public void atualizarSubcategoriaComDadosValidosRetornaOk() throws Exception {
        given(manutencaoService.atualizar(1L, SUBCATEGORIA_REGISTRO_DTO_ATUALIZADO)).willReturn(
                SUBCATEGORIA_DETALHES_DTO_ATUALIZADO);
        mockMvc.perform(put("/subcategorias/1").content(
                objectMapper.writeValueAsString(SUBCATEGORIA_REGISTRO_DTO_ATUALIZADO)).contentType(
                MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpectAll(jsonPath("$").exists(),
                jsonPath("$.nome").value(SUBCATEGORIA_DETALHES_DTO_ATUALIZADO.nome()),
                jsonPath("$.descricao").value(SUBCATEGORIA_DETALHES_DTO_ATUALIZADO.descricao()),
                jsonPath("$.categoria.nome").value(SUBCATEGORIA_DETALHES_DTO_ATUALIZADO.categoria().nome()));
    }

    @Test
    public void atualizarSubcategoriaInexistenteRetornaNotFound() throws Exception {
        given(manutencaoService.atualizar(1L, SUBCATEGORIA_REGISTRO_DTO_ATUALIZADO)).willThrow(
                SubcategoriaNotFoundException.class);
        mockMvc.perform(put("/subcategorias/1").content(
                objectMapper.writeValueAsString(SUBCATEGORIA_REGISTRO_DTO_ATUALIZADO)).contentType(
                MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }

    @Test
    public void atualizarSubcategoriaComDadosInvalidosRetornaUnprocessableEntity() throws Exception {
        mockMvc.perform(put("/subcategorias/1").content(
                objectMapper.writeValueAsString(SUBCATEGORIA_REGISTRO_DTO_ATUALIZADO_INVALIDO)).contentType(
                MediaType.APPLICATION_JSON)).andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void atualizarSubcategoriaComCategoriaInexistenteRetornaNotFound() throws Exception {
        given(manutencaoService.atualizar(1L, SUBCATEGORIA_REGISTRO_DTO)).willThrow(CategoriaNotFoundException.class);
        mockMvc.perform(put("/subcategorias/1").content(
                objectMapper.writeValueAsString(SUBCATEGORIA_REGISTRO_DTO)).contentType(
                MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }

    @Test
    public void removerSubcategoriaPorIdExistenteRetornaOk() throws Exception {
        given(manutencaoService.remover(10L)).willReturn("Subcategoria removida");
        mockMvc.perform(delete("/subcategorias/10")).andExpect(status().isOk()).andExpectAll(
                jsonPath("$").value("Subcategoria removida"));
    }

    @Test
    public void removerSubcategoriaPorIdInexistenteRetornaNotFound() throws Exception {
        given(manutencaoService.remover(10L)).willThrow(SubcategoriaNotFoundException.class);
        mockMvc.perform(delete("/subcategorias/10")).andExpectAll(status().isNotFound());
    }

    @Test
    public void listarSubcategoriasExistentesRetornaOk() throws Exception {
        PageRequest pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "id");
        List<SubcategoriaDto> listaSubcategorias = new ArrayList<>();
        listaSubcategorias.add(SUBCATEGORIA_DTO);
        Page<SubcategoriaDto> pageSubcategorias = new PageImpl<>(listaSubcategorias, pageable, 10);
        given(consultaService.listar(pageable)).willReturn(pageSubcategorias);
        mockMvc.perform(get("/subcategorias")).andExpect(status().isOk()).andExpectAll(jsonPath("$").exists(),
                jsonPath("$.empty").value(false), jsonPath("$.numberOfElements").value(1));
    }

    @Test
    public void listarSubcategoriasInexistentesRetornaNotFound() throws Exception {
        PageRequest pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "id");
        given(consultaService.listar(pageable)).willThrow(SubcategoriaNotFoundException.class);
        mockMvc.perform(get("/subcategorias")).andExpect(status().isNotFound());
    }

    @Test
    public void consultarSubcategoriaPorIdExistenteRetornaOk() throws Exception {
        given(consultaService.consultarPorId(1L)).willReturn(SUBCATEGORIA_DETALHES_DTO);
        mockMvc.perform(get("/subcategorias/1")).andExpect(status().isOk()).andExpectAll(jsonPath("$").exists(),
                jsonPath("$.nome").value(SUBCATEGORIA_DETALHES_DTO.nome()),
                jsonPath("$.descricao").value(SUBCATEGORIA_DETALHES_DTO.descricao()),
                jsonPath("$.categoria.nome").value(SUBCATEGORIA_DETALHES_DTO.categoria().nome()));
    }

    @Test
    public void consultarSubcategoriaPorIdInexistenteRetornaNotFound() throws Exception {
        given(consultaService.consultarPorId(10L)).willThrow(SubcategoriaNotFoundException.class);
        mockMvc.perform(get("/subcategorias/10")).andExpect(status().isNotFound());
    }

    @Test
    public void consultarSubcategoriaPorIdInvalidoRetornaBadRequest() throws Exception {
        mockMvc.perform(get("/subcategorias/a")).andExpect(status().isBadRequest());
    }

    @Test
    public void criarSubcategoriaComRequestBodyNuloRetornaBadRequest() throws Exception {
        mockMvc.perform(post("/subcategorias").content(objectMapper.writeValueAsString(null)).contentType(
                MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andExpectAll(
                jsonPath("$").value("Informação inválida. Verifique os dados e tente novamente"));
    }

}
