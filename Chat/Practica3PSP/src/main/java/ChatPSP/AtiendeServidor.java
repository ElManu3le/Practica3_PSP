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

    
}
