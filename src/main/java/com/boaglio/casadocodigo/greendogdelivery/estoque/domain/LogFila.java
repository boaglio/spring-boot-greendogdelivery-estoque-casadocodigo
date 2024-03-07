package com.boaglio.casadocodigo.greendogdelivery.estoque.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;


@Document(collection = "logDaFila")
public class LogFila {

	@Id
	private String id;

	private String descricao;

	private String mensagem;

	public LogFila() {}

	public LogFila(String descricao, String mensagem) {
		this.descricao = descricao;
		this.mensagem = mensagem;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		LogFila logFila = (LogFila) o;
		return Objects.equals(id, logFila.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "LogFila [id=" + id + ", descricao=" + descricao + ", mensagem=" + mensagem + "]";
	}

}
