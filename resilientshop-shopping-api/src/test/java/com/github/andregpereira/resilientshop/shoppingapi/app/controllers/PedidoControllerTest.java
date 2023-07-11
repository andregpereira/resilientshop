package com.github.andregpereira.resilientshop.shoppingapi.app.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.andregpereira.resilientshop.shoppingapi.app.dto.pedido.PedidoDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dto.pedido.PedidoRegistrarDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.services.PedidoConsultaService;
import com.github.andregpereira.resilientshop.shoppingapi.app.services.PedidoManutencaoService;
import com.github.andregpereira.resilientshop.shoppingapi.cross.exceptions.PedidoNotFoundException;
import feign.FeignException;
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

import java.security.InvalidParameterException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.github.andregpereira.resilientshop.shoppingapi.constants.PedidoDtoConstants.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PedidoController.class)
class PedidoControllerTest {

    @MockBean
    private PedidoManutencaoService manutencaoService;

    @MockBean
    private PedidoConsultaService consultaService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void criarPedidoComDadosValidosRetornaPedidoDetalharDto() throws Exception {
        given(manutencaoService.criar(PEDIDO_REGISTRAR_DTO)).willReturn(PEDIDO_DETALHAR_DTO);
        mockMvc.perform(post("/pedidos").content(objectMapper.writeValueAsString(PEDIDO_REGISTRAR_DTO)).contentType(
                MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andExpectAll(
                jsonPath("$.dataCriacao").value(
                        PEDIDO_DETALHAR_DTO.dataCriacao().format(DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm"))),
                jsonPath("$.dataModificacao").value(
                        PEDIDO_DETALHAR_DTO.dataCriacao().format(DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm"))),
                jsonPath("$.status").value(1), jsonPath("$.total").value(48),
                jsonPath("$.detalhePedido[0].quantidade").value(4), jsonPath("$.detalhePedido[0].subtotal").value(48),
                jsonPath("$.detalhePedido[0].produto.nome").value("nome"));
        then(manutencaoService).should().criar(PEDIDO_REGISTRAR_DTO);
    }

    @Test
    void criarPedidoComDadosInvalidosThrowsException() throws Exception {
        mockMvc.perform(post("/pedidos").content(
                objectMapper.writeValueAsString(PEDIDO_REGISTRAR_DTO_INVALIDO)).contentType(
                MediaType.APPLICATION_JSON)).andExpect(status().isUnprocessableEntity());
        then(manutencaoService).should(never()).criar(PEDIDO_REGISTRAR_DTO);
    }

    @Test
    void criarPedidoComRequestBodyNuloRetornaBadRequest() throws Exception {
        mockMvc.perform(post("/pedidos").content(objectMapper.writeValueAsString(null)).contentType(
                MediaType.APPLICATION_JSON)).andExpectAll(status().isBadRequest(),
                jsonPath("$").value("Informação inválida. Verifique os dados e tente novamente"));
    }

    @Test
    void cancelarPedidoPorIdExistenteRetornaOk() throws Exception {
        given(manutencaoService.cancelar(10L)).willReturn("Pedido com id 10 cancelado");
        mockMvc.perform(delete("/pedidos/10")).andExpect(status().isOk()).andExpectAll(
                jsonPath("$").value("Pedido com id 10 cancelado"));
    }

    @Test
    void listarPedidosExistentesRetornaOk() throws Exception {
        PageRequest pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "id");
        List<PedidoDto> listaPedidos = new ArrayList<>();
        listaPedidos.add(PEDIDO_DTO);
        Page<PedidoDto> pagePedidos = new PageImpl<>(listaPedidos, pageable, 10);
        given(consultaService.listar(pageable)).willReturn(pagePedidos);
        mockMvc.perform(get("/pedidos")).andExpect(status().isOk()).andExpectAll(
                jsonPath("$.numberOfElements").value(1));
    }

    @Test
    void listarPedidosInexistentesRetornaNotFound() throws Exception {
        PageRequest pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "id");
        given(consultaService.listar(pageable)).willThrow(PedidoNotFoundException.class);
        mockMvc.perform(get("/pedidos")).andExpect(status().isNotFound());
    }

    @Test
    void cancelarPedidoPorIdInexistenteRetornaNotFound() throws Exception {
        given(manutencaoService.cancelar(100L)).willThrow(PedidoNotFoundException.class);
        mockMvc.perform(delete("/pedidos/100")).andExpect(status().isNotFound());
    }

    @Test
    void consultarPedidoPorIdExistenteRetornaPedidoDetalharDto() throws Exception {
        given(consultaService.consultarPorId(1L)).willReturn(PEDIDO_DETALHAR_DTO);
        mockMvc.perform(get("/pedidos/1")).andExpect(status().isOk()).andExpectAll(jsonPath("$.dataCriacao").value(
                        PEDIDO_DETALHAR_DTO.dataCriacao().format(DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm"))),
                jsonPath("$.dataModificacao").value(
                        PEDIDO_DETALHAR_DTO.dataCriacao().format(DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm"))),
                jsonPath("$.status").value(1), jsonPath("$.total").value(48));
    }

    @Test
    void consultarPedidoPorIdInexistenteRetornaNotFound() throws Exception {
        given(consultaService.consultarPorId(10L)).willThrow(PedidoNotFoundException.class);
        mockMvc.perform(get("/pedidos/10")).andExpect(status().isNotFound());
    }

    @Test
    void consultarPedidoPorIdInvalidoThrowsException() throws Exception {
        mockMvc.perform(get("/pedidos/a")).andExpect(status().isBadRequest()).andExpectAll(
                jsonPath("$").value("Parâmetro inválido. Verifique e tente novamente"));
    }

    @Test
    void consultarPedidoExistentePorStatusRetornaPedidoDto() throws Exception {
        PageRequest pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "id");
        List<PedidoDto> listaPedidos = new ArrayList<>();
        listaPedidos.add(PEDIDO_DTO);
        Page<PedidoDto> pagePedidos = new PageImpl<>(listaPedidos, pageable, 10);
        given(consultaService.consultarPorStatus(1, pageable)).willReturn(pagePedidos);
        mockMvc.perform(get("/pedidos/status").param("status", "1")).andExpect(status().isOk()).andExpectAll(
                jsonPath("$.numberOfElements").value(1));
    }

    @Test
    void consultarPedidoInexistentePorStatusThrowsException() throws Exception {
        PageRequest pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "id");
        given(consultaService.consultarPorStatus(1, pageable)).willThrow(PedidoNotFoundException.class);
        mockMvc.perform(get("/pedidos/status").param("status", "1")).andExpect(status().isNotFound());
    }

    @Test
    void consultarPedidoPorStatusInvalidoThrowsException() throws Exception {
        PageRequest pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "id");
        given(consultaService.consultarPorStatus(-1, pageable)).willThrow(InvalidParameterException.class);
        mockMvc.perform(get("/pedidos/status").param("status", "-1")).andExpect(status().isBadRequest());
    }

    @Test
    void inserirParametroStatusNuloThrowsException() throws Exception {
        mockMvc.perform(get("/pedidos/status")).andExpect(status().isBadRequest()).andExpectAll(
                jsonPath("$.campo").value("status"), jsonPath("$.mensagem").value("O campo status é obrigatório"));
    }

    @Test
    void cadastrarPedidoPorComAPIProdutosIndisponívelRetornaServiceUnavailable() throws Exception {
        given(manutencaoService.criar(any(PedidoRegistrarDto.class))).willThrow(
                FeignException.ServiceUnavailable.class);
        mockMvc.perform(post("/pedidos").content(objectMapper.writeValueAsString(PEDIDO_REGISTRAR_DTO)).contentType(
                MediaType.APPLICATION_JSON)).andExpect(status().isServiceUnavailable());
    }

    @Test
    void consultarPedidoPorIdComAPIProdutosIndisponívelRetornaServiceUnavailable() throws Exception {
        given(consultaService.consultarPorId(25L)).willThrow(FeignException.ServiceUnavailable.class);
        mockMvc.perform(get("/pedidos/25")).andExpect(status().isServiceUnavailable());
    }

    @Test
    void criarPedidoComApiIndisponivelThrowsException() throws Exception {
        given(manutencaoService.criar(PEDIDO_REGISTRAR_DTO)).willThrow(FeignException.NotFound.class);
        mockMvc.perform(post("/pedidos").content(objectMapper.writeValueAsString(PEDIDO_REGISTRAR_DTO)).contentType(
                MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }

}
