package sockets;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor {

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		ServerSocket servidor = new ServerSocket(8080);
		System.out.println("Porta 8080 aberta!");

		Socket cliente = servidor.accept();
		System.out.println("Nova conexao com o cliente " + cliente.getInetAddress().getHostAddress());

		InputStream is = cliente.getInputStream();
		ObjectInputStream ois = new ObjectInputStream(is);

		String to = (String) ois.readObject();

		if (to != null)
			System.out.println(to);

		System.out.println((String) ois.readObject());


		
		
		// Scanner entrada = new Scanner(cliente.getInputStream());
		//
		// while (true) {
		// System.out.println(entrada.nextLine());
		// }

	}
}