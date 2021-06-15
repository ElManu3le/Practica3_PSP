package ChatPSP;

import java.net.Socket;
import java.util.ArrayList;
import java.io.*;

public class ComunHilos {

	private ArrayList<String> history = new ArrayList<>();
	private ArrayList<Socket> sockets = new ArrayList<>();

	public void anadirMensaje(String msg) throws IOException {
		history.add(msg);

		for (Socket sc : sockets) {
			DataOutputStream out = new DataOutputStream(sc.getOutputStream());
			out.writeUTF(msg);
		}
	}

	public void showHistory() throws IOException {
		for (Socket sc : sockets) {
			for (String m : history) {
				DataOutputStream out = new DataOutputStream(sc.getOutputStream());
				out.writeUTF(m);
			}
		}
	}

	public synchronized void anadirClient(Socket client) throws IOException {

		DataOutputStream out = new DataOutputStream(client.getOutputStream());

		while (sockets.size() == 2) {
			try {
				out.writeUTF("*Actualmente el servidor esta lleno. Se encuentra en cola para poder unirse al chat*");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		sockets.add(client);
		out.writeUTF("*Se ha unido al chat, ya puede ver el historial y participar*");
		notifyAll();
	}

	public synchronized void eliminarClient(Socket client) {
		sockets.remove(client);
		notifyAll();
	}

	/*
	 * // VARIABLES private static int conexionesMax; private int conexionesTotales;
	 * private int conexcionesActuales; private Socket[] tablaDeConexiones = new
	 * Socket[10]; private String historial;
	 * 
	 * // CONSTRUCTOR public ComunHilos(int conexionesMax) { this.conexionesMax =
	 * conexionesMax; this.conexionesTotales = 0; this.conexcionesActuales = 0;
	 * this.historial = ""; }
	 * 
	 * // SETTERS Y GETTERS
	 * 
	 * public void setHistorial(String historial) { this.historial = historial; }
	 * 
	 * // OTROS METODOS public synchronized void anadirMensaje(String mensaje) {
	 * historial += "\n" + mensaje; // setHistorial(historial);
	 * 
	 * for (int i = 0; i < conexcionesActuales; i++) { if (tablaDeConexiones ==
	 * null) { DataOutputStream salida; try { salida = new
	 * DataOutputStream(tablaDeConexiones[i].getOutputStream());
	 * salida.writeUTF(mensaje); } catch (IOException e) { e.printStackTrace(); } }
	 * } }
	 * 
	 * public synchronized void anadirCliente(Socket conexion) { if
	 * (tablaDeConexiones == null) { tablaDeConexiones[conexionesTotales] =
	 * conexion; } conexionesTotales++; conexcionesActuales++; try {
	 * DataOutputStream salida = new DataOutputStream(conexion.getOutputStream());
	 * salida.writeUTF(historial); } catch (IOException e) { e.printStackTrace(); }
	 * }
	 */
}
