package ChatPSP;

import java.io.*;
import java.net.*;

public class AppServidor {
    static final int PUERTO = 4444;
  public static void main(String[] args) throws IOException {
    // Creamos el servidor
    ServerSocket servidor = new ServerSocket(PUERTO);
    System.out.println("Escuchando en el puerto " + PUERTO + " ...");

    // Esperamos a la primera petición de conexión que venga y la aceptamos
    Socket socketTcp = servidor.accept();

    // Obtenemos los canales de entrada de datos y de salida
    DataInputStream entrada = new DataInputStream(socketTcp.getInputStream());
    DataOutputStream salida = new DataOutputStream(socketTcp.getOutputStream());

    // Leemos un mensaje y devolvemos el mismo mensaje
    String mensajeDelCliente = entrada.readUTF();
    System.out.println("Recibido mensaje del cliente: " + mensajeDelCliente);
    salida.writeUTF("Tú si que eres " + mensajeDelCliente);

    // Cerramos conexión
    socketTcp.close();
    servidor.close();
  }

}