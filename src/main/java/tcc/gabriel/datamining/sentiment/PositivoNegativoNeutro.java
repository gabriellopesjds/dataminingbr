package tcc.gabriel.datamining.sentiment;

public class PositivoNegativoNeutro {
	private int positivo;
	private int negativo;
	private int neutro;
	
	public PositivoNegativoNeutro(int positivo, int negativo, int neutro) {
		this.positivo = positivo;
		this.negativo = negativo;
		this.neutro = neutro;
	}

	public int getPositivo() {
		return positivo;
	}

	public int getNegativo() {
		return negativo;
	}

	public int getNeutro() {
		return neutro;
	}
	
	
}
