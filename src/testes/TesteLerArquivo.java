package testes;

import java.io.BufferedReader;
import java.io.FileReader;

import algoritmos.PesquisarFabricante;

public class TesteLerArquivo {
	public static void main(String[] args) {

		try {

			BufferedReader lerArq = new BufferedReader(new FileReader(PesquisarFabricante.enderecoArquivo));

			String linha = lerArq.readLine();

			while (linha != null) {
				System.out.println(linha);
				linha = lerArq.readLine();
			}

			lerArq.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}