package tcc.gabriel.datamining;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tcc.gabriel.datamining.sentiment.SenticNet;

@SpringBootApplication
public class App implements CommandLineRunner{
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		            int [] vetor = { 9, 5, 7, 2 };

		            int aux = 0;
		            for (int i = 0; i < vetor.length; i++)
		            {
		                for (int j = 0; j < vetor.length; j++)
		                {
		                    if (vetor[i] < vetor[j])
		                    {
		                        aux = vetor[i];
		                        vetor[i] = vetor[j];
		                        vetor[j] = aux;
		                    }
		                }
		            }
		            
		            for (int k = 0; k < vetor.length; k++)
		            {
		            	System.out.println(vetor[k]);
		            }
		            
		            String vetor1[] = new String[9];
		            vetor1[0] = "Thiago";
		            vetor1[1] = "Lucas";
		            vetor1[2] = "Joao";
		            vetor1[3] = "Marcos";
		            vetor1[4] = "Maria";
		            vetor1[5] = "José";
		            vetor1[6] = "Carol";
		            vetor1[7] = "Ariane";
		            vetor1[8] = "Zander";
		            
		            Arrays.sort(vetor1);
		            
		            System.out.print ("O nomes em ordem alfabética são ");
		            for (int i = 0 ; i<vetor1.length;i++)
		            {
		                      System.out.print (vetor1[i]+", ");
		            }

		
	}
}