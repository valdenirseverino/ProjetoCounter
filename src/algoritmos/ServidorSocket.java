package algoritmos;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSocket {

	private static ServerSocket servidor;

	public static void main(String[] args) {
		try {
			
			servidor = new ServerSocket(8080);
			PesquisarMac scan = new PesquisarMac();

			System.out.println("Servidor ouvindo a porta 8080");

			while (true) {

				Socket cliente = servidor.accept();

				scan.realizarVarredura();

				ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());

				String mensagem[] = ((String) entrada.readObject()).split(";");
				String mac = mensagem[0];

				System.out.println("MAC: " + mac + " Localização: " + mensagem[1] + " Sinal: "
						+ scan.pesquisarMac(mac).getPotenciaSinal());

			}

		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

}
