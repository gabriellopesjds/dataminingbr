package tcc.gabriel.datamining;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tcc.gabriel.datamining.publicacao.Publicacao;
import tcc.gabriel.datamining.publicacao.SuporteExcel;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void compararPublicacoes() throws IOException {
		List<Publicacao> lista = new ArrayList<>();
		SuporteExcel excel = new SuporteExcel("pt");
		excel.abrirExcel();
		lista = excel.analisarTexto();
		excel.criarExcel();

		double quantidadeTotalDePublicacoes = 0;
		double quantidadeTotalDePublicacoesPositivas = 0;
		double quantidadeTotalDePublicacoesNegativas = 0;
		double quantidadeTotalDePublicacoesNeutras = 0;

		double quantidadeDePublicacoesPositivasAnalisadas = 0;
		double quantidadeDePublicacoesNegativasAnalisadas = 0;
		double quantidadeDePublicacoesNeutrasAnalisadas = 0;

		double quantidadeDePublicacoesPositivasQueGerouNegativa = 0;
		double quantidadeDePublicacoesPositivasQueGerouPositivas = 0;
		double quantidadeDePublicacoesPositivasQueGerouNeutras = 0;

		double quantidadeDePublicacoesNegativasQueGerouNegativa = 0;
		double quantidadeDePublicacoesNegativasQueGerouPositivas = 0;
		double quantidadeDePublicacoesNegativasQueGerouNeutras = 0;

		double quantidadeDePublicacoesNeutrasQueGerouNegativa = 0;
		double quantidadeDePublicacoesNeutrasQueGerouPositivas = 0;
		double quantidadeDePublicacoesNeutrasQueGerouNeutras = 0;

		for (Publicacao publicacao : lista) {
			if (publicacao.getPolaridade().equals("POSITIVO")) {
				if (publicacao.getPolaridadeAnalisada().equals("POSITIVO")) {
					quantidadeDePublicacoesPositivasQueGerouPositivas++;
					quantidadeDePublicacoesPositivasAnalisadas++;
				} else if (publicacao.getPolaridadeAnalisada().equals("NEGATIVO")) {
					quantidadeDePublicacoesPositivasQueGerouNegativa++;
					quantidadeDePublicacoesNegativasAnalisadas++;
				} else if (publicacao.getPolaridadeAnalisada().equals("NEUTRO")) {
					quantidadeDePublicacoesPositivasQueGerouNeutras++;
					quantidadeDePublicacoesNeutrasAnalisadas++;
				}
				quantidadeTotalDePublicacoesPositivas++;
			} else if (publicacao.getPolaridade().equals("NEGATIVO")) {
				if (publicacao.getPolaridadeAnalisada().equals("POSITIVO")) {
					quantidadeDePublicacoesNegativasQueGerouPositivas++;
					quantidadeDePublicacoesPositivasAnalisadas++;
				} else if (publicacao.getPolaridadeAnalisada().equals("NEGATIVO")) {
					quantidadeDePublicacoesNegativasQueGerouNegativa++;
					quantidadeDePublicacoesNegativasAnalisadas++;
				} else if (publicacao.getPolaridadeAnalisada().equals("NEUTRO")) {
					quantidadeDePublicacoesNegativasQueGerouNeutras++;
					quantidadeDePublicacoesNeutrasAnalisadas++;
				}
				quantidadeTotalDePublicacoesNegativas++;
			} else if (publicacao.getPolaridade().equals("NEUTRO")) {
				if (publicacao.getPolaridadeAnalisada().equals("POSITIVO")) {
					quantidadeDePublicacoesNeutrasQueGerouPositivas++;
					quantidadeDePublicacoesPositivasAnalisadas++;
				} else if (publicacao.getPolaridadeAnalisada().equals("NEGATIVO")) {
					quantidadeDePublicacoesNeutrasQueGerouNegativa++;
					quantidadeDePublicacoesNegativasAnalisadas++;
				} else if (publicacao.getPolaridadeAnalisada().equals("NEUTRO")) {
					quantidadeDePublicacoesNeutrasQueGerouNeutras++;
					quantidadeDePublicacoesNeutrasAnalisadas++;
				}
				quantidadeTotalDePublicacoesNeutras++;
			}
			quantidadeTotalDePublicacoes++;
		}

		System.out.println("************************************************************************");
		System.out.println("***************************RESULTADOS OBTIDOS***************************");
		System.out.println("************************************************************************");

		System.out.println("Quantidade Total de Publicações: " + String.format("%.0f", quantidadeTotalDePublicacoes));
		System.out.println("Quantidade Total de Publicações POSITIVA: "
				+ String.format("%.0f", quantidadeTotalDePublicacoesPositivas));
		System.out.println("Quantidade Total de Publicações NEGATIVA: "
				+ String.format("%.0f", quantidadeTotalDePublicacoesNegativas));
		System.out.println(
				"Quantidade Total de Publicações NEUTRA:" + String.format("%.0f", quantidadeTotalDePublicacoesNeutras));
		System.out.println("************************************************************************");
		System.out.println("Quantidade Total de Publicações POSITIVA Analisada: "
				+ String.format("%.0f", quantidadeDePublicacoesPositivasAnalisadas));
		System.out.println("Quantidade Total de Publicações NEGATIVA Analisada: "
				+ String.format("%.0f", quantidadeDePublicacoesNegativasAnalisadas));
		System.out.println("Quantidade Total de Publicações NEUTRA Analisada: "
				+ String.format("%.0f", quantidadeDePublicacoesNeutrasAnalisadas));
		System.out.println("************************************************************************");
		System.out.println("Quantidade de Publicações POSITIVAS que gerou POSITIVA: "
				+ String.format("%.0f", quantidadeDePublicacoesPositivasQueGerouPositivas));
		System.out.println("Quantidade de Publicações NEGATIVAS que gerou POSITIVA: "
				+ String.format("%.0f", quantidadeDePublicacoesNegativasQueGerouPositivas));
		System.out.println("Quantidade de Publicações NEUTRAS que gerou POSITIVA: "
				+ String.format("%.0f", quantidadeDePublicacoesNeutrasQueGerouPositivas));
		System.out.println("************************************************************************");
		System.out.println("Quantidade de Publicações POSITIVAS que gerou NEGATIVA: "
				+ String.format("%.0f", quantidadeDePublicacoesPositivasQueGerouNegativa));
		System.out.println("Quantidade de Publicações NEGATIVAS que gerou NEGATIVA: "
				+ String.format("%.0f", quantidadeDePublicacoesNegativasQueGerouNegativa));
		System.out.println("Quantidade de Publicações NEUTRAS que gerou NEGATIVA: "
				+ String.format("%.0f", quantidadeDePublicacoesNeutrasQueGerouNegativa));
		System.out.println("************************************************************************");
		System.out.println("Quantidade de Publicações POSITIVAS que gerou NEUTRA: "
				+ String.format("%.0f", quantidadeDePublicacoesPositivasQueGerouNeutras));
		System.out.println("Quantidade de Publicações NEGATIVAS que gerou NEUTRA: "
				+ String.format("%.0f", quantidadeDePublicacoesNegativasQueGerouNeutras));
		System.out.println("Quantidade de Publicações NEUTRAS que gerou NEUTRA: "
				+ String.format("%.0f", quantidadeDePublicacoesNeutrasQueGerouNeutras));
		System.out.println("************************************************************************");
		System.out.println("Percentual de acertos POSITIVOS: " + String.format("%.2f",
				((quantidadeDePublicacoesPositivasQueGerouPositivas / quantidadeTotalDePublicacoesPositivas) * 100))
				+ "%");
		System.out.println("Quantidade de acertos NEGATIVOS: " + String.format("%.2f",
				((quantidadeDePublicacoesNegativasQueGerouNegativa / quantidadeTotalDePublicacoesNegativas) * 100))
				+ "%");
		System.out.println("Quantidade de acertos NEUTROS: "
				+ String.format("%.2f",
						((quantidadeDePublicacoesNeutrasQueGerouNeutras / quantidadeTotalDePublicacoesNeutras) * 100))
				+ "%");
		System.out.println("************************************************************************");

	}

}
