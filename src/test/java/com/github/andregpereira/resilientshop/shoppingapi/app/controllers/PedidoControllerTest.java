package com.github.andregpereira.resilientshop.shoppingapi.app.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.andregpereira.resilientshop.shoppingapi.app.services.PedidoConsultaService;
import com.github.andregpereira.resilientshop.shoppingapi.app.services.PedidoManutencaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.format.DateTimeFormatter;

import static com.github.andregpereira.resilientshop.shoppingapi.constants.PedidoDtoConstants.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
                jsonPath("$.detalhePedido[0].produto.sku").value(123456789));
        then(manutencaoService).should().criar(PEDIDO_REGISTRAR_DTO);
    }

    @Test
    void criarPedidoComDadosInvalidosThrowsException() throws Exception {
        mockMvc.perform(post("/pedidos").content(
                objectMapper.writeValueAsString(PEDIDO_REGISTRAR_DTO_INVALIDO)).contentType(
                MediaType.APPLICATION_JSON)).andExpect(status().isUnprocessableEntity());
        then(manutencaoService).should(never()).criar(PEDIDO_REGISTRAR_DTO);
    }

}
