/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Martijn
 */
public class InputHandler implements Runnable {
    private static final int PORT_NUMBER = 7789;
    
    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(7789);
            while (true) {
                Socket s = ss.accept();
                Parser p = new Parser(s);
                p.run();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
}
