package ChatPSP;

import java.net.Socket;
import java.util.ArrayList;
import java.io.*;

public class ComunHilos {
	// VARIABLES
	private static int conexionesMax;
	private int conexionesTotales;
	private int conexcionesActuales;
	private Socket[] tablaDeConexiones = new Socket[10];
	private String historial;

	// CONSTRUCTOR
	public ComunHilos(int conexionesMax) {
		this.conexionesMax = conexionesMax;
		this.conexionesTotales = 0;
		this.conexcionesActuales = 0;
		this.historial = "";
	}

	// SETTERS Y GETTERS

	public void setHistorial(String historial) {
		this.historial = historial;
	}

	// OTROS METODOS
	public synchronized void anadirMensaje(String mensaje) {
		historial += "\n" + mensaje;
		// setHistorial(historial);

		for (int i = 0; i < conexcionesActuales; i++) {
			if (tablaDeConexiones == null) {
				DataOutputStream salida;
				try {
					salida = new DataOutputStream(tablaDeConexiones[i].getOutputStream());
					salida.writeUTF(mensaje);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public synchronized void anadirCliente(Socket conexion) {
		if (tablaDeConexiones == null) {
			tablaDeConexiones[conexionesTotales] = conexion;
		}
		conexionesTotales++;
		conexcionesActuales++;
		try {
			DataOutputStream salida = new DataOutputStream(conexion.getOutputStream());
			salida.writeUTF(historial);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
