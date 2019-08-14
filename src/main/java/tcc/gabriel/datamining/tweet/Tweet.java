package tcc.gabriel.datamining.tweet;

import java.util.UUID;

public class Tweet {
	private String id;
	private long idAuthor;
	private String nome;
	private String mensagem;
	private String polaridade;
	private String linguagem;
	
	public Tweet(long id, String nome, String mensagem, String linguagem) {
		this();
		this.idAuthor = id;
		this.nome = nome;
		this.mensagem = mensagem;
		this.setLinguagem(linguagem);
	}

	public Tweet() {
		this.id = UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getIdAuthor() {
		return idAuthor;
	}

	public void setIdAuthor(long idAuthor) {
		this.idAuthor = idAuthor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getPolaridade() {
		return polaridade;
	}

	public void setPolaridade(double polaridade) {
		if (polaridade < 0) {
			this.polaridade = "NEGATIVO";
		} else if (polaridade > 0) {
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
		Tweet other = (Tweet) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getLinguagem() {
		return linguagem;
	}

	public void setLinguagem(String linguagem) {
		this.linguagem = linguagem;
	}


}
