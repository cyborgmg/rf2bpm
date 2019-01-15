package br.com.original.rf2bpm.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the ARQUIVO database table.
 * 
 */
@Entity
@Table(name="ARQUIVO")
@NamedQuery(name="Arquivo.findAll", query="SELECT a FROM Arquivo a")
public class Arquivo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ARQUIVO_ID_GENERATOR", sequenceName="SQ_ARQUIVO", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ARQUIVO_ID_GENERATOR")
	private long id;

	private String arquivo;

	private String operacao;

	@Column(name="SISTEMA_ORIGEM")
	private String sistemaOrigem;

	@Column(name="TIME_STAMP")
	private String timeStamp;

	//bi-directional many-to-one association to Linha
	@OneToMany(mappedBy="arquivo", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Linha> linhas = new ArrayList<Linha>();

	public Arquivo() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getArquivo() {
		return this.arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}

	public String getOperacao() {
		return this.operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	public String getSistemaOrigem() {
		return this.sistemaOrigem;
	}

	public void setSistemaOrigem(String sistemaOrigem) {
		this.sistemaOrigem = sistemaOrigem;
	}

	public String getTimeStamp() {
		return this.timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public List<Linha> getLinhas() {
		return this.linhas;
	}

	public void setLinhas(List<Linha> linhas) {
		this.linhas = linhas;
	}

	public Linha addItem(Linha linha) {
		getLinhas().add(linha);
		linha.setArquivo(this);

		return linha;
	}

	public Linha removeItem(Linha linha) {
		getLinhas().remove(linha);
		linha.setArquivo(null);

		return linha;
	}

	@Override
	public String toString() {
		
		return "Arquivo [id=" + id + ", arquivo=" + arquivo + ", operacao=" + operacao + ", sistemaOrigem="
				+ sistemaOrigem + ", timeStamp=" + timeStamp + "]";
	}

}