package ChatPSP;

import java.io.*;
import java.net.Socket;

public class AppCliente {
  // VARIABLES
  static final String ip = "localhost";
  static final int puerto = 5555;

  public static void main(String[] args) throws IOException {
    // VARIABLES
    Socket miSocket = new Socket(ip, puerto);
    DataOutputStream salida = new DataOutputStream(miSocket.getOutputStream());
    AtiendeServidor hiloCliente = new AtiendeServidor(miSocket);
    String mensajeQueEscribeElCliente = "";
    String nombreUsuario = "";

    hiloCliente.start();
    while (!mensajeQueEscribeElCliente.equals("*")) {
      if (nombreUsuario.equals("")) {
        System.out.println("Nombre de usuario");
        nombreUsuario = Leer.pedirCadena();
        salida.writeUTF(mensajeQueEscribeElCliente);
      } else {
        mensajeQueEscribeElCliente = Leer.pedirCadena();
        salida.writeUTF(nombreUsuario + ": " + mensajeQueEscribeElCliente);
      }
    }
    System.exit(0);
  }
}
