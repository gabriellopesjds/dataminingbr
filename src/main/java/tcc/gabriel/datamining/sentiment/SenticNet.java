package tcc.gabriel.datamining.sentiment;

import java.io.*;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tcc.gabriel.datamining.sentiment.util.NLPUtil;

public class SenticNet {
	protected final int POSITIVO = 1;
	protected final int NEGATIVO = -1;
	protected final int NEUTRO = 0;

	private static final String REGEX_TOKENIZER = "([a-zà-úA-ZÀ-Ú]+-[a-zà-úA-ZÀ-Ú]+)|([a-zà-úA-ZÀ-Ú]+)";
	private static final int POLARITY = 4; // focus
	private Map<String, Vector<Double>> senticnetDataPortugues;
	private Map<String, Vector<Double>> senticnetDataIngles;
	private String filenamePortugues;
	private String filenameIngles;

	public SenticNet() {
		this.filenamePortugues = "src/main/resources/senticnet/senticnet_dataset_portugues.tsv";
		this.filenameIngles = "src/main/resources/senticnet/senticnet_dataset_english.tsv";

		this.carregarDicionarioPortugues();
		this.carregarDicionarioIngles();
	}

	public String removeAcentos(String str) {
		str = Normalizer.normalize(str, Normalizer.Form.NFD);
		str = str.replaceAll("[^\\p{ASCII}]", "");
		return str;
	}

	public void carregarDicionarioPortugues() {

		this.senticnetDataPortugues = new HashMap<String, Vector<Double>>();

		try {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(new FileInputStream(filenamePortugues), "UTF-8"));

			String line = br.readLine();

			while (line != null) {
				Vector<Double> parameters = new Vector<>();

				String[] parametersAux = line.split("\t");
				String word = parametersAux[0].split("=")[1];
				String sentenca;

				parameters.add(Double.valueOf(parametersAux[1].split("=")[1])); // Pleasantness score
				parameters.add(Double.valueOf(parametersAux[2].split("=")[1])); // Attention score
				parameters.add(Double.valueOf(parametersAux[3].split("=")[1])); // Sensitivity score
				parameters.add(Double.valueOf(parametersAux[4].split("=")[1])); // Aptitude score
				parameters.add(Double.valueOf(parametersAux[5].split("=")[1])); // Polarity score
				sentenca = removeAcentos(word);
				senticnetDataPortugues.put(sentenca.toLowerCase(), parameters);
				line = br.readLine();
			}

			br.close();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void carregarDicionarioIngles() {

		this.senticnetDataIngles = new HashMap<String, Vector<Double>>();

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filenameIngles), "UTF-8"));

			String line = br.readLine();

			while (line != null) {
				Vector<Double> parameters = new Vector<>();

				String[] parametersAux = line.split("\t");
				String word = parametersAux[0].split("=")[1];
				String sentenca;

				parameters.add(Double.valueOf(parametersAux[1].split("=")[1])); // Pleasantness score
				parameters.add(Double.valueOf(parametersAux[2].split("=")[1])); // Attention score
				parameters.add(Double.valueOf(parametersAux[3].split("=")[1])); // Sensitivity score
				parameters.add(Double.valueOf(parametersAux[4].split("=")[1])); // Aptitude score
				parameters.add(Double.valueOf(parametersAux[5].split("=")[1])); // Polarity score
				sentenca = removeAcentos(word);
				senticnetDataIngles.put(sentenca.toLowerCase(), parameters);
				line = br.readLine();
			}

			br.close();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int analisarTexto(String texto, String linguagem) {
		double resultado = this.verificarTexto(removeAcentos(texto), linguagem);

		if (resultado > 0d) {
			return POSITIVO;
		} else if (resultado < 0d) {
			return NEGATIVO;
		}

		return NEUTRO;
	}

	/**
	 * @param texto
	 * @param stopwords
	 * @return texto indexado removendo palavras irrelevantes e tudo o que não se
	 *         encaixa a regex.
	 */
	public static Vector<String> tokenizarTexto(String text, Set<String> stopwords) {
		Vector<String> tokens = new Vector<String>();
		Pattern p = Pattern.compile(REGEX_TOKENIZER);
		Matcher m = p.matcher(text.toLowerCase());

		while (m.find()) {
			if (!stopwords.contains(m.group())) {
				tokens.add(m.group());
			}
		}
		return tokens;
	}

	/**
	 * @param texto
	 *            que será analisado
	 * @return polarity
	 */
	@SuppressWarnings("unused")
	public Double verificarTexto(String texto, String linguagem) {
		Map<String, Vector<Double>> senticnetDatabase;
		Vector<String> tokens;
		double sumPolarity = 0.0;

		if (linguagem.equals("pt")) {
			tokens = tokenizarTexto(texto.toLowerCase(), NLPUtil.stopWordListPortugues);
			senticnetDatabase = this.senticnetDataPortugues;
		} else {
			tokens = tokenizarTexto(texto.toLowerCase(), NLPUtil.stopWordsListIngles);
			senticnetDatabase = this.senticnetDataIngles;
		}
		if (tokens.size() == 0) {
			return 0d;
		}
		
		// check for occurrences of 4-, 3-, 2- and 1-grams
		String token;
		int index = 3;
		int ngram = 0;
		int quantidadeToken = tokens.size();

		while (index - 3 < quantidadeToken) {

			if ((quantidadeToken > index) && (senticnetDatabase.containsKey(tokens.get(index - 3) + " "
					+ tokens.get(index - 2) + " " + tokens.get(index - 1) + " " + tokens.get(index)))) {
				// 4-gram
				token = tokens.get(index - 3) + " " + tokens.get(index - 2) + " " + tokens.get(index - 1) + " "
						+ tokens.get(index);
				index += 3;
				ngram += 3;
			} else if ((quantidadeToken > index - 1) && (senticnetDatabase
					.containsKey(tokens.get(index - 3) + " " + tokens.get(index - 2) + " " + tokens.get(index - 1)))) {
				// 3-gram
				token = tokens.get(index - 3) + " " + tokens.get(index - 2) + " " + tokens.get(index - 1);
				index += 2;
				ngram += 2;
			} else if ((quantidadeToken > index - 2)
					&& (senticnetDatabase.containsKey(tokens.get(index - 3) + " " + tokens.get(index - 2)))) {
				// 2-gram
				token = tokens.get(index - 3) + " " + tokens.get(index - 2);
				index += 1;
				ngram += 1;
			} else if (senticnetDatabase.containsKey(tokens.get(index - 3))) {
				// 1-gram
				token = tokens.get(index - 3);
			} else {
				index += 1;
				continue;
			}
			index += 1;
			sumPolarity += senticnetDatabase.get(token).get(POLARITY);
		}
		return sumPolarity / (quantidadeToken - ngram);
	}
}