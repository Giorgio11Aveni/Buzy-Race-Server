package com.buzyrace;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author aveni & miligi
 */
public class Gestore_File extends Thread{

    private final Socket s;
    private final DataInputStream dis;
    private final DataOutputStream dos;
    String username;
    String password;
    private String usernameTrue;
    private String passwordTrue;
    int livelli;
    int punteggio1;
    int punteggio2;
    int punteggio3;
    int punteggioArcade;

    private String[] punt;
    private int puntWriter;
    private int idLivello;


    //Costruttore
    public Gestore_File(Socket s, DataInputStream dis, DataOutputStream dos) {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
    }


    public void newSaveFile() throws IOException {
        
        String path = "C:\\Users\\aveni\\IdeaProjects\\BuzyRaceCS\\Server\\src\\SaveFile\\" + username + ".txt";
        
        try{
           
            File file = new File(path);
            if(!file.exists()) {
                file.createNewFile();
            }
            else{
                System.out.println("L'username non è disponibile.\n");
            }
            
        }catch(IOException e ){
            
            e.printStackTrace();
    
        }

    }

    public boolean checkFile(){

        String path = "C:\\Users\\aveni\\IdeaProjects\\BuzyRaceCS\\Server\\src\\SaveFile\\" + username + ".txt";

        File file = new File(path);
        if(file.exists()) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public void initializeFile(){

        String path = "C:\\Users\\aveni\\IdeaProjects\\BuzyRaceCS\\Server\\src\\SaveFile\\" + username + ".txt";

        try{
            
            File file = new File (path);
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i<2; i++){

                bw.write("null\n");
            }
            
            bw.flush();
            bw.close();
        }
        catch(IOException e){
            
            e.printStackTrace();   
        }
    }
    
    public void readFile() throws FileNotFoundException{

        String path = "C:\\Users\\aveni\\IdeaProjects\\BuzyRaceCS\\Server\\src\\SaveFile\\" + username + ".txt";

        File file = new File(path);
        Scanner scan = new Scanner(file);
        
        int linea = 1;
        
        while(scan.hasNextLine()){
            
            switch (linea){

                case 1:

                    usernameTrue = scan.nextLine();
                    break;

                case 2:

                    passwordTrue = scan.nextLine();
                    break;
                
                case 3:
                    
                    livelli = Integer.parseInt(scan.nextLine());
                    break;
                    
                case 4:
                    
                    punteggio1 = Integer.parseInt(scan.nextLine());
                    break;
                    
                case 5:
                    
                    punteggio2 = Integer.parseInt(scan.nextLine());
                    break;
                    
                case 6:
                    
                    punteggio3 = Integer.parseInt(scan.nextLine());
                    break;

                case 7:

                    punteggioArcade = Integer.parseInt(scan.nextLine());
                    break;
            }
            
            linea++;
        }    
    }
    
    public void aggiornaPunteggio(int a) throws FileNotFoundException{

        this.readFile();

        switch (a){
            
            case 1:
                
                if (this.livelli <= 1){
                    
                    this.livelli = 1;
   
                } 
                
                if (this.punteggio1 < Integer.parseInt(punt[0])){
                    
                    writeFile(a);
               
                }else{

                    puntWriter = this.punteggio1;
                    writeFile(a);
                    
                }
                break;
                
            case 2:
                
                if (this.livelli <= 2){
                    
                    this.livelli = 2;
   
                }
                
                if (this.punteggio2 < Integer.parseInt(punt[0])){
                    
                    writeFile(a);
               
                }else{

                    puntWriter = this.punteggio2;
                    writeFile(a);
                    
                }
                break;

                
        }
    }
    
    public void writeFile(int id){

        String path = "C:\\Users\\aveni\\IdeaProjects\\BuzyRaceCS\\Server\\src\\SaveFile\\" + username + ".txt";

        try{
            
            File file = new File (path);
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            
               switch (id){
                   
                   case 1:
                       
                       bw.write(username + "\n" + password + "\n" + this.livelli + "\n" + puntWriter + "\n" + this.punteggio2 + "\n" + this.punteggio3 + "\n" + this.punteggioArcade);
                       bw.flush();
                       bw.close();
                       break;
                    
                   case 2:
                       
                       bw.write(username + "\n" + password + "\n" + this.livelli + "\n" + this.punteggio1 + "\n" +  puntWriter + "\n" + this.punteggio3 + "\n" + this.punteggioArcade);
                       bw.flush();
                       bw.close();
                       break;
                       
                   case 3:
                       
                       bw.write(username + "\n" + password + "\n" + this.livelli + "\n" +  this.punteggio1 + "\n" + this.punteggio2 + "\n" + puntWriter + "\n" + this.punteggioArcade);
                       bw.flush();
                       bw.close();
                       break;

                   case 4:

                       bw.write(username + "\n" + password + "\n" + this.livelli + "\n" + this.punteggio1 + "\n" + this.punteggio2 + "\n" + this.punteggio3 + "\n" + puntWriter);
                       bw.flush();
                       bw.close();
                       break;
               }

        }
        catch(IOException e){
            
            e.printStackTrace();   
        }
    }

    public void writeLogin(){

        String path = "C:\\Users\\aveni\\IdeaProjects\\BuzyRaceCS\\Server\\src\\SaveFile\\" + username + ".txt";

        try {

            File file = new File(path);
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(username + "\n" + password);
            bw.flush();
            bw.close();

        }
        catch (IOException e){

            e.printStackTrace();

        }

    }

    public int getLivelli() {
        return livelli;
    }

    public void setLivelli(int livelli) {
        this.livelli = livelli;
    }

    public int getPunteggio1() {
        return punteggio1;
    }

    public void setPunteggio1(int punteggio1) {
        this.punteggio1 = punteggio1;
    }

    public int getPunteggio2() {
        return punteggio2;
    }

    public void setPunteggio2(int punteggio2) {
        this.punteggio2 = punteggio2;
    }

    public int getPunteggio3() {
        return punteggio3;
    }

    public void setPunteggio3(int punteggio3) {
        this.punteggio3 = punteggio3;
    }


    @Override
    public void run() {
        String received;
        String toreturn;
        while (true) {

            try {

                // Ask user what he wants
                //dos.writeUTF("What do you want?[Registrazione | Login]..\n" +
                        //"Digita [Exit] per chiudere la connessione.");

                // receive the answer from client
                received = dis.readUTF();

                /*if (received.equals("Exit")) {
                    System.out.println("Client " + this.s + " sends exit...");
                    System.out.println("Closing this connection.");
                    this.s.close();
                    System.out.println("Connection closed");
                    break;
                }

                 */


                // write on output stream based on the
                // answer from the client
                switch (received) {

                    case "Registrazione":
                        /*
                        toreturn = "Per registrarti, inserisci username e password separati da uno spazio.";
                        dos.writeUTF(toreturn);
                         */

                        //REGISTRAZIONE

                        handleLogin();

                        //Se l'username è disponibile
                        if (!checkFile()) {
                            handleFile(username);
                        }
                        //Se l'username è già in uso
                        else {
                            while (checkFile()) {
                                toreturn = "L'username non è disponibile.\nInserisci nuovi dati:";
                                dos.writeUTF(toreturn);

                                //Prendo i nuovi username e password e li posiziono nei rispettivi contenitori
                                handleLogin();

                                if (!checkFile()) {
                                    handleFile(username);
                                    break;
                                }
                            }
                        }

                        break;

                    case "Login":
                        /*
                        toreturn = "Per accedere, inserisci username e password separati da uno spazio.";
                        dos.writeUTF(toreturn);
                         */

                        //LOGIN

                        handleLogin();

                        //Ricerca username nel sistema

                        if (checkFile()) {
                            readFile();
                            if (usernameTrue.equals(username) && passwordTrue.equals(password)) {
                                toreturn = "Benvenuto " + username + "\n";
                                dos.writeUTF(toreturn);
                            }
                            //Se le credenziali non sono corrette
                            else {
                                while (!usernameTrue.equals(username) || !passwordTrue.equals(password)) {
                                    toreturn = "Le credenziali sono sbagliate. Riprova:";
                                    dos.writeUTF(toreturn);

                                    //Prendo i nuovi username e password e li posiziono nei rispettivi contenitori

                                    handleLogin();

                                    if (usernameTrue.equals(username) && passwordTrue.equals(password)) {
                                        toreturn = "Benvenuto " + username + "\n";
                                        dos.writeUTF(toreturn);
                                        break;
                                    }

                                }
                            }
                        } else {
                            toreturn = "Non esiste nessun account con questo username.";
                            dos.writeUTF(toreturn);
                        }

                        break;

                    default:
                        dos.writeUTF("Invalid input");
                        break;
                }


            } catch (IOException e) {
                e.printStackTrace();
            }

            //Alla fine di un livello, ricevo punteggio e ID livello e aggiorno i file

            while (true) {
                String punteggio = null;
                try {
                    punteggio = dis.readUTF();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                punt = punteggio.split("\n");
                idLivello = Integer.parseInt(punt[1]);
                try {
                    aggiornaPunteggio(idLivello);
                    writeFile(idLivello);

                    //Invio al Client i nuovi punteggi aggiornati

                    readFile();
                    String newPunt = livelli + "\n" + punteggio1 + "\n" + punteggio2 + "\n" + punteggio3 + "\n" + punteggioArcade;
                    dos.writeUTF(newPunt);

                } catch (IOException e) {
                    e.printStackTrace();
                }


                break;
            }

            try {
                // closing resources
                this.dis.close();
                this.dos.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void handleFile(String username) throws IOException {
        newSaveFile();
        initializeFile();
        writeLogin();
        String toreturn = "Registrazione avvenuta con successo!\n";
        dos.writeUTF(toreturn);
    }

    public void handleLogin() throws IOException {
            String line = null;
            try {
                line = dis.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[] data = line.split(" ");

        username = data[0];
        password = data[1];
    }
}

 
