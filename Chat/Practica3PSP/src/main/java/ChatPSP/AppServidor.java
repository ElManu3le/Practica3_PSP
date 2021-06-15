package ChatPSP;

import java.io.*;
import java.net.*;

public class AppServidor {

	public static void main(String[] args) throws IOException {

		// try-with-resource
		try (ServerSocket server = new ServerSocket(4444)) {

			ComunHilos ch = new ComunHilos();

			System.out.println("SERVIDOR INICIADO");

			while (true) {
					
				Socket client = server.accept();
				System.out.println("***Cliente conectado al servidor***");

				AtiendeCliente clientThread = new AtiendeCliente(client, ch);
				clientThread.start();
			}
		}
	}
	/*
  // VARIABLES
	static final int puerto = 5555;
	private static final int conexionesMax = 10;

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(puerto);
		System.out.println("Escuchando en el puerto " + puerto);

		ComunHilos comunhilos = new ComunHilos(conexionesMax);
		while (true) {
			Socket socket = serverSocket.accept();
			AtiendeCliente hilosServidor = new AtiendeCliente(socket, comunhilos);

			comunhilos.anadirCliente(socket);
			hilosServidor.start();
			System.out.println("Hay un nuevo cliente");
		}
	}
	*/
}