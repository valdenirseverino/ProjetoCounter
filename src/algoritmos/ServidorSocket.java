package algoritmos;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServidorSocket {

	private static ServerSocket servidor;

	ArrayList<Integer> listaDentro = new ArrayList<>();
	ArrayList<Integer> listaFora = new ArrayList<>();

	public double media(ArrayList<Integer> lista) {

		double media = 0.0;

		for (Integer valor : lista)
			media = media + valor;

		media = media / lista.size();

		return media;

	}

	public ServidorSocket() {

		try {

			servidor = new ServerSocket(8080);
			PesquisarMac scan = new PesquisarMac();

			int contador = 0;

			System.out.println("Servidor ouvindo a porta 8080");

			while (true) {

				Socket cliente = servidor.accept();

				scan.realizarVarredura();

				ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());

				String mensagem[] = ((String) entrada.readObject()).split(";");

				String mac = mensagem[0];
				String resposta = mensagem[1];
				String sinal = scan.pesquisarMac(mac).getPotenciaSinal();

				double mediaDentro = 0.0, mediaFora = 0.0;

				switch (resposta) {

				case "Dentro":
					listaDentro.add(Integer.parseInt(sinal));
					mediaDentro = media(listaDentro);
					break;

				case "Fora":
					listaFora.add(Integer.parseInt(sinal));
					mediaFora = media(listaFora);
					break;

				}

				contador++;

				System.out.println("\n\n\nMAC: " + mac + "\nResposta: " + resposta + "\nSinal: " + sinal);
				System.out.println("Interacao: " + contador);
				System.out.println("Media Dentro: " + mediaDentro + "\nMedia Fora: " + mediaFora);
			}

		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		new ServidorSocket();
	}

}
