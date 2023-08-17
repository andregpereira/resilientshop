package com.github.andregpereira.resilientshop.discountsapi.app.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.andregpereira.resilientshop.discountsapi.app.constant.TipoDesconto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.desconto.DescontoCreateDto;
import com.github.andregpereira.resilientshop.discountsapi.app.service.desconto.DescontoConsultaService;
import com.github.andregpereira.resilientshop.discountsapi.app.service.desconto.DescontoManutencaoService;
import com.github.andregpereira.resilientshop.discountsapi.cross.exception.DescontoNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;
import java.time.format.DateTimeFormatter;

import static com.github.andregpereira.resilientshop.discountsapi.constant.DescontoDtoConstants.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DescontoController.class)
class DescontoControllerTest {

    private static final String PATH = "/descontos";

    @MockBean
    private DescontoManutencaoService manutencaoService;
    @MockBean
    private DescontoConsultaService consultaService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createWithValidDto_Return201() throws Exception {
        given(manutencaoService.criar(any(DescontoCreateDto.class))).willReturn(DESCONTO_DTO);
        mockMvc.perform(post(URI.create(PATH)).content(
                objectMapper.writeValueAsString(DESCONTO_CREATE_DTO)).contentType(
                MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andExpectAll(
                jsonPath("$.dataExpiracao").value(
                        DESCONTO_DTO.dataExpiracao().format(DateTimeFormatter.ofPattern("dd/MM/uuuu"))),
                jsonPath("$.ativo").value(DESCONTO_DTO.ativo()));
    }

    @Test
    void createWithInvalidDto_Return400() throws Exception {
        mockMvc.perform(post(URI.create(PATH)).content(
                objectMapper.writeValueAsString(DESCONTO_CREATE_DTO_INVALIDO)).contentType(
                MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    @Test
    void updateWithValidDto_Return200() throws Exception {
        given(manutencaoService.update(anyLong(), any(DescontoCreateDto.class))).willReturn(DESCONTO_DTO_ATUALIZADO);
        mockMvc.perform(put(URI.create(PATH + "/1")).content(
                objectMapper.writeValueAsString(DESCONTO_CREATE_DTO_ATUALIZADO)).contentType(
                MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpectAll(jsonPath("$.dataExpiracao").value(
                        DESCONTO_DTO_ATUALIZADO.dataExpiracao().format(DateTimeFormatter.ofPattern("dd/MM/uuuu"))),
                jsonPath("$.ativo").value(DESCONTO_DTO_ATUALIZADO.ativo()));
    }

    @Test
    void updateWithInvalidDto_Return400() throws Exception {
        mockMvc.perform(put(URI.create(PATH + "/1")).content(
                objectMapper.writeValueAsString(DESCONTO_CREATE_DTO_INVALIDO)).contentType(
                MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    @Test
    void updateByNonExistentId_Return404() throws Exception {
        given(manutencaoService.update(anyLong(), any(DescontoCreateDto.class))).willThrow(
                new DescontoNotFoundException(10L));
        mockMvc.perform(put(URI.create(PATH + "/10")).content(
                objectMapper.writeValueAsString(DESCONTO_CREATE_DTO_ATUALIZADO)).contentType(
                MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }

    @Test
    void activateByExistentId_Return200() throws Exception {
        willDoNothing().given(manutencaoService).activate(anyLong());
        mockMvc.perform(patch(URI.create(PATH + "/1"))).andExpect(status().isOk());
    }

    @Test
    void activateByNonExistentId_Return404() throws Exception {
        willThrow(new DescontoNotFoundException(10L)).willDoNothing().given(manutencaoService).activate(anyLong());
        mockMvc.perform(patch(URI.create(PATH + "/10"))).andExpect(status().isNotFound());
    }

    @Test
    void deactivateByExistentId_Return200() throws Exception {
        willDoNothing().given(manutencaoService).deactivate(anyLong());
        mockMvc.perform(delete(URI.create(PATH + "/1"))).andExpect(status().isOk());
    }

    @Test
    void deactivateByNonExistentId_Return404() throws Exception {
        willThrow(new DescontoNotFoundException(10L)).willDoNothing().given(manutencaoService).deactivate(anyLong());
        mockMvc.perform(delete(URI.create(PATH + "/10"))).andExpect(status().isNotFound());
    }

    @Test
    void findAll_Return200() throws Exception {
        given(consultaService.consultarTodos(any(Pageable.class))).willReturn(PAGE_DESCONTO_DTO);
        mockMvc.perform(get(URI.create(PATH))).andExpect(status().isOk()).andExpectAll(
                jsonPath("$.numberOfElements").value(1));
    }

    @Test
    void findByExistentId_Return200() throws Exception {
        given(consultaService.consultarPorId(anyLong())).willReturn(DESCONTO_DTO);
        mockMvc.perform(get(URI.create(PATH + "/1"))).andExpect(status().isOk()).andExpectAll(
                jsonPath("$.dataExpiracao").value(
                        DESCONTO_DTO.dataExpiracao().format(DateTimeFormatter.ofPattern("dd/MM/uuuu"))));
    }

    @Test
    void findByNonExistentId_Return404() throws Exception {
        given(consultaService.consultarPorId(anyLong())).willThrow(new DescontoNotFoundException(10L));
        mockMvc.perform(get(URI.create(PATH + "/10"))).andExpect(status().isNotFound());
    }

    @Test
    void findByTipoDesconto_Return200() throws Exception {
        given(consultaService.consultarPorTipoDesconto(any(String.class), any(Pageable.class))).willReturn(PAGE_DESCONTO_DTO);
        mockMvc.perform(get(URI.create(PATH + "/tipo-desconto")).queryParam("tipo-desconto",
                TipoDesconto.PROD.toString())).andExpect(status().isOk()).andExpectAll(
                jsonPath("$.numberOfElements").value(1));
    }

    @Test
    void nullJson_Returns400() throws Exception {
        mockMvc.perform(post(URI.create(PATH)).content(objectMapper.writeValueAsString(null)).contentType(
                MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    @Test
    void nullRequestParameter_Returns400() throws Exception {
        mockMvc.perform(get(URI.create(PATH + "/tipo-desconto"))).andExpect(status().isBadRequest());
    }

}
