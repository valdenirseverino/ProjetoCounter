package algoritmos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PesquisarFabricante {

	private BufferedReader lerArq;
	public static String enderecoArquivo = "/home/valdenir/Documentos/Devices_MAC.csv";

	public String buscarFabricante(String mac) {

		try {

			if (mac.length() == 17)
				mac = mac.substring(0, 8).replace(":", "-");
			else
				return "Desconhecido";

			lerArq = new BufferedReader(new FileReader(enderecoArquivo));

			String linha = lerArq.readLine();
			String fabricante = null;

			while (linha != null) {

				String[] colunas = linha.split(",");
				String[] endressMac = colunas[0].split(" ");

				if (endressMac[0].equalsIgnoreCase(mac))
					return colunas[1];

				linha = lerArq.readLine();
			}

			lerArq.close();

			return fabricante;

		} catch (IOException e) {
			System.err.printf("Erro: %s.\n", e.getMessage());
			return "Desconhecido";
		}
	}
}
