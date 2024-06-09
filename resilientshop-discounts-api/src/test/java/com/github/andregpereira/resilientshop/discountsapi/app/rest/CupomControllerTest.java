package com.github.andregpereira.resilientshop.discountsapi.app.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomCreateDto;
import com.github.andregpereira.resilientshop.discountsapi.app.dto.cupom.CupomUpdateDto;
import com.github.andregpereira.resilientshop.discountsapi.app.service.cupom.CupomConsultaService;
import com.github.andregpereira.resilientshop.discountsapi.app.service.cupom.CupomManutencaoService;
import com.github.andregpereira.resilientshop.discountsapi.cross.exception.CupomAlreadyExistsException;
import com.github.andregpereira.resilientshop.discountsapi.cross.exception.CupomNotFoundException;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;
import java.time.format.DateTimeFormatter;

import static com.github.andregpereira.resilientshop.discountsapi.constant.CupomDtoConstants.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RequiredArgsConstructor
@WebMvcTest(CupomController.class)
class CupomControllerTest {

    private static final String PATH = "/cupons";

    @MockBean
    private CupomManutencaoService manutencaoService;

    @MockBean
    private CupomConsultaService consultaService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createWithValidDto_Return201() throws Exception {
        given(manutencaoService.criar(any(CupomCreateDto.class))).willReturn(CUPOM_DTO);
        mockMvc.perform(post(URI.create(PATH)).content(objectMapper.writeValueAsString(CUPOM_CREATE_DTO)).contentType(
                MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andExpectAll(
                jsonPath("$.codigo").value(CUPOM_DTO.codigo()), jsonPath("$.dataExpiracao").value(
                        CUPOM_DTO.dataExpiracao().format(DateTimeFormatter.ofPattern("dd/MM/uuuu"))),
                jsonPath("$.ativo").value(CUPOM_DTO.ativo()));
    }

    @Test
    void createWithInvalidDto_Return400() throws Exception {
        mockMvc.perform(post(URI.create(PATH)).content(
                objectMapper.writeValueAsString(CUPOM_CREATE_DTO_INVALIDO)).contentType(
                MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }
    @Test
    void createWithInvalidDto_Return409() throws Exception {
        given(manutencaoService.criar(any(CupomCreateDto.class))).willThrow(new CupomAlreadyExistsException(anyString()));
        mockMvc.perform(post(URI.create(PATH)).content(
                objectMapper.writeValueAsString(CUPOM_CREATE_DTO)).contentType(
                MediaType.APPLICATION_JSON)).andExpect(status().isConflict());
    }

    @Test
    void updateWithValidDto_Return200() throws Exception {
        given(manutencaoService.update(anyLong(), any(CupomUpdateDto.class))).willReturn(CUPOM_DTO_ATUALIZADO);
        mockMvc.perform(put(URI.create(PATH + "/1")).content(
                objectMapper.writeValueAsString(CUPOM_UPDATE_DTO)).contentType(MediaType.APPLICATION_JSON)).andExpect(
                status().isOk()).andExpectAll(jsonPath("$.codigo").value(CUPOM_DTO_ATUALIZADO.codigo()),
                jsonPath("$.dataExpiracao").value(
                        CUPOM_DTO_ATUALIZADO.dataExpiracao().format(DateTimeFormatter.ofPattern("dd/MM/uuuu"))),
                jsonPath("$.ativo").value(CUPOM_DTO_ATUALIZADO.ativo()));
    }

    @Test
    void updateWithInvalidDto_Return400() throws Exception {
        mockMvc.perform(put(URI.create(PATH + "/1")).content(
                objectMapper.writeValueAsString(CUPOM_UPDATE_DTO_INVALIDO)).contentType(
                MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    @Test
    void updateByNonExistentId_Return404() throws Exception {
        given(manutencaoService.update(anyLong(), any(CupomUpdateDto.class))).willThrow(
                new CupomNotFoundException(10L));
        mockMvc.perform(put(URI.create(PATH + "/10")).content(
                objectMapper.writeValueAsString(CUPOM_UPDATE_DTO)).contentType(MediaType.APPLICATION_JSON)).andExpect(
                status().isNotFound());
    }

    @Test
    void activateByExistentId_Return200() throws Exception {
        willDoNothing().given(manutencaoService).activate(anyLong());
        mockMvc.perform(patch(URI.create(PATH + "/1"))).andExpect(status().isOk());
    }

    @Test
    void activateByNonExistentId_Return404() throws Exception {
        willThrow(new CupomNotFoundException(10L)).willDoNothing().given(manutencaoService).activate(anyLong());
        mockMvc.perform(patch(URI.create(PATH + "/10"))).andExpect(status().isNotFound());
    }

    @Test
    void deactivateByExistentId_Return200() throws Exception {
        willDoNothing().given(manutencaoService).deactivate(anyLong());
        mockMvc.perform(delete(URI.create(PATH + "/1"))).andExpect(status().isOk());
    }

    @Test
    void deactivateByNonExistentId_Return404() throws Exception {
        willThrow(new CupomNotFoundException(10L)).willDoNothing().given(manutencaoService).deactivate(anyLong());
        mockMvc.perform(delete(URI.create(PATH + "/10"))).andExpect(status().isNotFound());
    }

    @Test
    void findAll_Return200() throws Exception {
        given(consultaService.consultarTodos(any(Pageable.class))).willReturn(PAGE_CUPOM_DTO);
        mockMvc.perform(get(URI.create(PATH))).andExpect(status().isOk()).andExpectAll(
                jsonPath("$.numberOfElements").value(1));
    }

    @Test
    void findAllAtivo_Return200() throws Exception {
        given(consultaService.consultarAtivo(any(Pageable.class))).willReturn(PAGE_CUPOM_DTO_ATIVO);
        mockMvc.perform(get(URI.create(PATH + "/ativo"))).andExpect(status().isOk()).andExpectAll(
                jsonPath("$.numberOfElements").value(1));
    }

    @Test
    void findAllInativo_Return200() throws Exception {
        given(consultaService.consultarInativo(any(Pageable.class))).willReturn(PAGE_CUPOM_DTO);
        mockMvc.perform(get(URI.create(PATH + "/inativo"))).andExpect(status().isOk()).andExpectAll(
                jsonPath("$.numberOfElements").value(1));
    }

    @Test
    void findByExistentId_Return200() throws Exception {
        given(consultaService.consultarPorId(anyLong())).willReturn(CUPOM_DTO);
        mockMvc.perform(get(URI.create(PATH + "/1"))).andExpect(status().isOk()).andExpectAll(
                jsonPath("$.codigo").value(CUPOM_DTO.codigo()));
    }

    @Test
    void findByNonExistentId_Return404() throws Exception {
        given(consultaService.consultarPorId(anyLong())).willThrow(new CupomNotFoundException(10L));
        mockMvc.perform(get(URI.create(PATH + "/10"))).andExpect(status().isNotFound());
    }

    @Test
    void findByExistentCodigo_Return200() throws Exception {
        given(consultaService.consultarPorCodigo(anyString())).willReturn(CUPOM_DTO);
        mockMvc.perform(get(URI.create(PATH + "/codigo")).queryParam("codigo", "codigo")).andExpect(
                status().isOk()).andExpectAll(jsonPath("$.codigo").value(CUPOM_DTO.codigo()),
                jsonPath("$.dataExpiracao").value(
                        CUPOM_DTO.dataExpiracao().format(DateTimeFormatter.ofPattern("dd/MM/uuuu"))));
    }

    @Test
    void findByNonExistentCodigo_Return404() throws Exception {
        given(consultaService.consultarPorCodigo(anyString())).willThrow(new CupomNotFoundException("codigo"));
        mockMvc.perform(get(URI.create(PATH + "/codigo")).queryParam("codigo", "codigo")).andExpect(
                status().isNotFound());
    }

    @Test
    void nullJson_Returns400() throws Exception {
        mockMvc.perform(post(URI.create(PATH)).content(objectMapper.writeValueAsString(null)).contentType(
                MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    @Test
    void nullRequestParameter_Returns400() throws Exception {
        mockMvc.perform(get(URI.create(PATH + "/codigo"))).andExpect(status().isBadRequest());
    }

}
