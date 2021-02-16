package ChatPSP;

import java.io.*;
import java.net.*;


/** aceptar conexiones y lanzar hilos */
public class AppServidor {
  static final int PUERTO = 4444;
  static final int MAXCONEX = 10;

  public static void main(String[] args) throws IOException {
    // Creamos el servidor

    try (ServerSocket servidor = new ServerSocket(PUERTO)) {
      System.out.println("Iniciando el servidor en el puerto  " + PUERTO + " ...");

      ComunHilos comunHilos = new ComunHilos(MAXCONEX);

      while (true) {
        // Esperamos a la primera petición de conexión que venga y la aceptamos
        Socket socketTcp = servidor.accept();

        AtiendeCliente atiendeCliente = new AtiendeCliente(socketTcp, comunHilos);

        atiendeCliente.start();

        // Obtenemos los canales de entrada y de salida de datos
        System.out.println("El cliente se ha conectado al server ");
        DataInputStream entrada = new DataInputStream(socketTcp.getInputStream());
        DataOutputStream salida = new DataOutputStream(socketTcp.getOutputStream());

        // Leemos un mensaje y devolvemos el mismo mensaje
        String mensajeDelCliente = entrada.readUTF();
        System.out.println("Recibido mensaje del cliente: " + mensajeDelCliente);
        salida.writeUTF("Tú si que eres " + mensajeDelCliente);

       
      }

    } catch (Exception e) {
      // TODO: handle exception
    }

  }

}