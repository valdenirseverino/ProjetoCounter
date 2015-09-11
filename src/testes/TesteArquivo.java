package testes;

import java.io.BufferedReader;
import java.io.FileReader;

public class TesteArquivo {
	public static void main(String[] args) {

		try {

			BufferedReader lerArq = new BufferedReader(new FileReader("/home/Devices_MAC.csv"));

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
