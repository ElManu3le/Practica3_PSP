package ChatPSP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class AppCliente {
/** */

  //Si introducimos un puerto o ip no valida, no nos escuchara la peticion
  static final String IP = "localhost";
  static final int PUERTO = 4444;

  public static void main(String[] args) throws IOException {

    /** Hacemos un try para comprobar que hicimos la conexion con el servidor */
    try (Socket socketTcp = new Socket(IP, PUERTO)) {

      DataInputStream entradAServer = new DataInputStream(socketTcp.getInputStream());
      DataOutputStream salidAServer = new DataOutputStream(socketTcp.getOutputStream());

      String mensaje;
 while ((mensaje = Leer.pedirCadena()) != "*") {

        salidAServer.writeUTF(mensaje);
        String mensajeDelServidor = entradAServer.readUTF();
        System.out.
     println("Recibido mensaje del servidor: " + mensajeDelServidor);
      } 

    } catch (Exception e) {
      System.err.println("error => " + e.getMessage());
    }

  }
}
