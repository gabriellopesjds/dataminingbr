package tcc.gabriel.datamining;

class Ordenacao {
	private String[] vetor;
	public String[] getVetor() {
		return vetor;
	}
	public void setVetor(String[] vetor) {
		this.vetor = vetor;
	}
	public void bubbleSort() {
		int tamanho = vetor.length;
		for (int i = 0; i < tamanho - 1; i++) {
			for (int j = 0; j < tamanho - 1 - i; j++) {
				if(vetor[j].compareTo(vetor[ j+1 ])>0) { 
					String auxiliar = vetor[j];
					vetor[j] = vetor[j + 1];
					vetor[j + 1] = auxiliar;
				}
			}
		}
	}
}