package com.github.andregpereira.resilientshop.userapi.dtos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.github.andregpereira.resilientshop.userapi.entities.Usuario;

public record UsuarioDto(Long id, String nome, String sobrenome, String cpf, String telefone,
		@JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy") LocalDate dataCriacao,
		@JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy") LocalDate dataModificacao) {

	public UsuarioDto(Usuario usuario) {
		this(usuario.getId(), usuario.getNome(), usuario.getSobrenome(), usuario.getCpf(), usuario.getTelefone(),
				usuario.getDataCriacao(), usuario.getDataModificacao());
	}

	public static final List<UsuarioDto> criarLista(List<Usuario> usuarios) {
		List<UsuarioDto> usuariosDto = new ArrayList<>();
		usuarios.forEach((usuario) -> usuariosDto.add(new UsuarioDto(usuario)));
		return usuariosDto;
	}

}
