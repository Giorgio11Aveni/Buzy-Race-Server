package com.buzyrace;

import java.io.*;
import java.text.*;
import java.util.*;
import java.net.*;

//Classe Server
public class Server {

    public static void main(String[] args) throws IOException {

        //Il server ascolta sulla porta 7964
        ServerSocket ss = new ServerSocket(7964);

        //Attesa infinita per le richieste client

        while (true) {

            Socket s = null;

            try {

                s = ss.accept();

                System.out.println("Un nuovo client è connesso : " + s);

                // obtaining input and out streams
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                System.out.println("Assegno un nuovo thread al client");

                Thread t = new Gestore_File(s, dis, dos);

                t.start();

            }
            catch (Exception e) {
                s.close();
                e.printStackTrace();
            }
        }
    }
}
