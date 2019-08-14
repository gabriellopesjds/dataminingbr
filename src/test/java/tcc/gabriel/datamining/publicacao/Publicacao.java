package tcc.gabriel.datamining.publicacao;

import java.util.Date;

public class Publicacao {
	private String mensagem;
	
	private String polaridade;
	
	private String polaridadeAnalisada;
	
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
	
	public void setPolaridadeAnalisada(double polaridade) {
		if (polaridade < 0) {
			this.polaridadeAnalisada = "NEGATIVO";
		} else if (polaridade > 0) {
			this.polaridadeAnalisada = "POSITIVO";
		} else {
			this.polaridadeAnalisada = "NEUTRO";
		}
	}
	
	public String getPolaridadeAnalisada() {
		return polaridadeAnalisada;
	}
		
}
