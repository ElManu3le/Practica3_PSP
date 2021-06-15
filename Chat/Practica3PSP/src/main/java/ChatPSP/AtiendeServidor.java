package ChatPSP;

import java.net.Socket;
import java.io.*;

public class AtiendeServidor extends Thread {

	private Socket sc;

    public AtiendeServidor(Socket sc) {
        this.sc = sc;
    }

    @Override
    public void run() {
        try {
            while (true) {
                DataInputStream in = new DataInputStream(sc.getInputStream());
                System.out.println(in.readUTF());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
	// VARIABLES
	private Socket socket;
	private DataOutputStream salida;

	// CONSTRUCTOR
	public AtiendeServidor(Socket socket) {
		this.socket = socket;
	}

	// OTRO METODOS
	@Override
	public void run() {
		try {
			DataInputStream entrada = new DataInputStream(socket.getInputStream());
			salida = new DataOutputStream(socket.getOutputStream());
			while (true) {
				String respuestaDelServidor = entrada.readUTF();
				System.out.println(respuestaDelServidor);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/

    
}
