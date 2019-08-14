package tcc.gabriel.datamining.timeline;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import javax.validation.constraints.NotNull;

public class Timeline {

	private String id;
	private long idPublicacao;
	private long idAutor;
	private String nomeAutor;
	private String mensagem;
	private Date data;
	private String polaridade;
	private String linguagem;

	public String getLinguagem() {
		return linguagem;
	}

	public void setLinguagem(String linguagem) {
		this.linguagem = linguagem;
	}

	public Timeline(long idPublicacao, long idAutor, String nome, String mensagem, Date data, String linguagem) {
		this();
		this.idPublicacao = idPublicacao;
		this.idAutor = idAutor;
		this.nomeAutor = nome;
		this.mensagem = mensagem;
		this.data = data;
		this.linguagem = linguagem;
	}

	public Timeline() {
		this.id = UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getIdPublicacao() {
		return idPublicacao;
	}

	public void setIdPublicacao(long idPublicacao) {
		this.idPublicacao = idPublicacao;
	}

	public long getIdAutor() {
		return idAutor;
	}

	public void setIdAutor(long idAutor) {
		this.idAutor = idAutor;
	}

	public String getNomeAutor() {
		return nomeAutor;
	}

	public void setNomeAutor(String nomeAutor) {
		this.nomeAutor = nomeAutor;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getData() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String dataFormatada = formato.format(data);
		return dataFormatada;
	}


	public void setData(Date data) {
		this.data = data;
	}
	
	public String getPolaridade() {
		return polaridade;
	}

	public void setPolaridade(double polaridade) {
		if (polaridade < 0d) {
			this.polaridade = "NEGATIVO";
		} else if (polaridade > 0d) {
			this.polaridade = "POSITIVO";
		} else {
			this.polaridade = "NEUTRO";
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Timeline other = (Timeline) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


}