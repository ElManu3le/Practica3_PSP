package ChatPSP;

import java.io.*;
import java.net.Socket;

public class AppCliente {
  
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
