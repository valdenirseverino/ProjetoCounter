package objetos;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCPBasico {

	private static ServerSocket servidor;

	public static void main(String[] args) {
		try {

			servidor = new ServerSocket(8080);

			System.out.println("Servidor ouvindo a porta 8080");

			while (true) {

				Socket cliente = servidor.accept();
				System.out.println("Cliente conectado: " + cliente.getInetAddress().getHostAddress());

				ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());

				String m = (String) entrada.readObject();
				System.out.println(m);

			}

		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

}
