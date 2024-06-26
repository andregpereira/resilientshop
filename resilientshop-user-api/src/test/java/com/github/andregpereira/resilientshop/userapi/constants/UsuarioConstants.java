package com.github.andregpereira.resilientshop.userapi.constants;

import com.github.andregpereira.resilientshop.userapi.infra.entities.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;

import static com.github.andregpereira.resilientshop.userapi.constants.EnderecoConstants.*;

public class UsuarioConstants {

    public static final LocalDate LOCAL_DATE = LocalDate.now();

    public static final Usuario USUARIO = new Usuario(null, "nome", "apelido", "22426853093", LOCAL_DATE,
            "teste@teste.com", null, LOCAL_DATE, LOCAL_DATE, true, LISTA_ENDERECOS);

    public static final Usuario USUARIO_SEM_ENDERECO = new Usuario(null, "nome", "apelido", "22426853093", LOCAL_DATE,
            "teste@teste.com", null, LOCAL_DATE, LOCAL_DATE, true, new ArrayList<>());

    public static final Usuario USUARIO_SEM_ENDERECO_MAPEADO = new Usuario(null, "nome", "apelido", "22426853093",
            LOCAL_DATE, "teste@teste.com", null, null, null, true, new ArrayList<>());

    public static final Usuario USUARIO_INATIVO = new Usuario(null, "nome", "apelido", "22426853093", LOCAL_DATE,
            "teste@teste.com", null, LOCAL_DATE, LOCAL_DATE, false, LISTA_ENDERECOS_USUARIO_INATIVO);

    public static final Usuario USUARIO_MAPEADO = new Usuario(null, "nome", "apelido", "22426853093", LOCAL_DATE,
            "teste@teste.com", null, null, null, false, LISTA_ENDERECOS_PADRAO_FALSE_MAPEADO);

    public static final Usuario USUARIO_PAIS_NOVO = new Usuario(null, "nome", "apelido", "22426853093", LOCAL_DATE,
            "teste@teste.com", null, LOCAL_DATE, LOCAL_DATE, true, LISTA_ENDERECOS_PAIS_NOVO);

    public static final Usuario USUARIO_PAIS_NOVO_MAPEADO = new Usuario(null, "nome", "apelido", "22426853093",
            LOCAL_DATE, "teste@teste.com", null, null, null, true, LISTA_ENDERECOS_PAIS_NOVO_PADRAO_FALSE_MAPEADO);

    public static final Usuario USUARIO_ATUALIZADO = new Usuario(null, "nome2", "sobrenome2", "22426853093", LOCAL_DATE,
            "teste2@teste2.com", null, LOCAL_DATE, LOCAL_DATE, true, LISTA_ENDERECOS);

    public static final Usuario USUARIO_ATUALIZADO_MAPEADO = new Usuario(null, "nome2", "sobrenome2", null, LOCAL_DATE,
            "teste2@teste2.com", null, null, null, false, null);

    public static final Usuario USUARIO_ATUALIZADO_PAIS_NOVO = new Usuario(null, "nome2", "sobrenome2", "22426853093",
            LOCAL_DATE, "teste2@teste2.com", null, LOCAL_DATE, LOCAL_DATE, true, LISTA_ENDERECOS_PAIS_NOVO);

    public static final Usuario USUARIO_INVALIDO = new Usuario(null, "", "", "", LOCAL_DATE, "", "", null, null, false,
            LISTA_ENDERECOS_INVALIDO);

    public static final Usuario USUARIO_VAZIO = new Usuario();

}
