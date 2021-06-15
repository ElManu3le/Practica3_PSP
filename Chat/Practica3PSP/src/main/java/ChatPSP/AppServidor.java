package ChatPSP;

import java.io.*;
import java.net.*;

public class AppServidor {
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

}