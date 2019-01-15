package br.com.original.rf2bpm.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the ITEM database table.
 * 
 */
@Entity
@NamedQuery(name = "Linha.findAll", query = "SELECT i FROM Linha i")
@Table(name="ITEM")
public class Linha implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ITEM_ID_GENERATOR", sequenceName = "SQ_ITEM", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ITEM_ID_GENERATOR")
	private long id;

	@Lob
	private String payload;
	
	@Column(name="RETORNO")
	private String retorno;

	// bi-directional many-to-one association to Arquivo
	@ManyToOne
	private Arquivo arquivo;

	public Linha() {
	}

	public Linha(String payload, Arquivo arquivo) {
		super();
		this.payload = payload;
		this.arquivo = arquivo;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPayload() {
		return this.payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public Arquivo getArquivo() {
		return this.arquivo;
	}

	public void setArquivo(Arquivo arquivo) {
		this.arquivo = arquivo;
	}

	public String getRetorno() {
		return retorno;
	}

	public void setRetorno(String retorno) {
		this.retorno = retorno;
	}

	@Override
	public String toString() {
		return "Linha [id=" + id + ", payload=" + payload + ", arquivo=" + arquivo + "]";
	}

}