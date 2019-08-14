package tcc.gabriel.datamining.sentiment.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class NLPUtil {
	public static final Set<String> stopWordListPortugues = new HashSet<String>(Arrays.asList("agora", "ainda", "alguém",
			"a", "algum", "alguma", "algumas", "alguns", "ampla", "amplas", "amplo", "amplos", "ante", "antes", "ao", "aos",
			"após", "aquela", "aquelas", "aquele", "aqueles", "aquilo", "as", "até", "através", "cada", "coisa",
			"coisas", "com", "como", "contra", "contudo", "da", "daquele", "daqueles",
			"dele", "deles", "depois", "dessa", "dessas", "desse", "desses", "desta", "destas", "deste", "deste",
			"destes", "deve", "devem", "devendo", "dever", "deverá", "deverão", "deveria", "deveriam", "devia",
			"deviam", "disse", "disso", "disto", "dito", "diz", "dizem", "do", "dos", "e", "é", "ela", "elas", "ele",
			"eles", "em", "enquanto", "entre", "era", "essa", "essas", "esse", "esses", "esta", "está", "estamos",
			"estão", "estas", "estava", "estavam", "estávamos", "este", "estes", "estou", "eu", "fazendo", "fazer",
			"feita", "feitas", "feito", "feitos", "foi", "for", "foram", "fosse", "fossem", "grande", "grandes", "há",
			"isso", "isto", "já", "la", "lá", "lhe", "lhes", "lo", "mas", "me", "mesma", "mesmas", "mesmo", "mesmos",
			"meu", "meus", "minha", "minhas", "muita", "muitas", "muito", "muitos", "na", "não", "nas", "nem", "nenhum",
			"nessa", "nessas", "nesta", "nestas", "ninguém", "no", "nos", "nós", "nossa", "nossas", "nosso", "nossos",
			"num", "numa", "nunca", "o", "os", "ou", "outra", "outras", "outro", "outros", "para", "pela", "pelas",
			"pelo", "pelos", "pequena", "pequenas", "pequeno", "pequenos", "per", "perante", "pode", "pude", "podendo",
			"poder", "poderia", "poderiam", "podia", "podiam", "pois", "por", "porém", "porque", "posso", "pouca",
			"poucas", "pouco", "poucos", "primeiro", "primeiros", "própria", "próprias", "próprio", "próprios", "quais",
			"qual", "quando", "quanto", "quantos", "que", "quem", "são", "se", "seja", "sejam", "sem", "sempre",
			"sendo", "será", "serão", "seu", "seus", "si", "sido", "só", "sob", "sobre", "sua", "suas", "talvez",
			"também", "tampouco", "te", "tem", "tendo", "tenha", "ter", "teu", "teus", "ti", "tido", "tinha", "tinham",
			"toda", "todas", "todavia", "todo", "todos", "tu", "tua", "tuas", "tudo", "última", "últimas", "último",
			"últimos", "um", "uma", "umas", "uns", "vendo", "ver", "vez", "vindo", "vir", "vos", "vós"));
	
	/**
	 * Stop-words from SenticNet method.
	 */
	public static final Set<String> stopWordsListIngles = new HashSet<String>(Arrays.asList("because",
			"ve", "then", "doing", "when", "is", "am", "an", "himself", "are", 
			"yourselves", "our", "its", "if", "ourselves", "these", "what", "i", 
			"her", "whom", "would", "there", "had", "s", "been", "should", "re", 
			"does", "those", "which", "ours", "themselves", "has", "was", "t", 
			"be", "we", "his", "yo", "that", "any", "than", "who", "here", "were", 
			"but", "they", "hers", "during", "herself", "him", "nor", "he", "me", 
			"myself", "don", "d", "did", "theirs", "having", "such", "yours", "their", 
			"this", "while", "so", "she", "each", "my", "or"));


}