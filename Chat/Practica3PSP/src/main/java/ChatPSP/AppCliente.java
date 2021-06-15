package ChatPSP;

import java.io.*;
import java.net.Socket;

public class AppCliente {
  /*
   * // VARIABLES static final String ip = "localhost"; static final int puerto =
   * 5555;
   * 
   * public static void main(String[] args) throws IOException { // VARIABLES
   * Socket miSocket = new Socket(ip, puerto); DataOutputStream salida = new
   * DataOutputStream(miSocket.getOutputStream()); AtiendeServidor hiloCliente =
   * new AtiendeServidor(miSocket); String mensajeQueEscribeElCliente = ""; String
   * nombreUsuario = "";
   * 
   * hiloCliente.start(); while (!mensajeQueEscribeElCliente.equals("*")) { if
   * (nombreUsuario.equals("")) { System.out.println("Nombre de usuario");
   * nombreUsuario = Leer.pedirCadena();
   * salida.writeUTF(mensajeQueEscribeElCliente); } else {
   * mensajeQueEscribeElCliente = Leer.pedirCadena();
   * salida.writeUTF(nombreUsuario + ": " + mensajeQueEscribeElCliente); } }
   * System.exit(0); }
   */
  public static void main(String[] args) throws IOException {
    try (Socket socket = new Socket("localhost", 4444)) {

      DataOutputStream output = new DataOutputStream(socket.getOutputStream());

      System.out.println("Nombre de usuario?");
      String usuario = Leer.pedirCadena();
      output.writeUTF(usuario);

      AtiendeServidor threadServer = new AtiendeServidor(socket);
      threadServer.start();

      String msg = Leer.pedirCadena();

      while (!msg.equals("*")) {
        output.writeUTF(msg);
        msg = Leer.pedirCadena();
      }

      output.writeUTF(msg);
      System.out.println("Se ha desconectado del chat");
    }

  }

}
