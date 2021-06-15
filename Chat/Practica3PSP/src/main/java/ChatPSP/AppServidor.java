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

}