/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmerclientserver;



import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Thoma
 */
public class Server1 {
     static Socket s;
 
    static DataInputStream din;
    static DataOutputStream dout;


     public static void main(String[] args) {
         String[][] data = {{":0:9,17,13,10,14,8,12,16,14,15,14,13,13",":1:50,38,41,37,47,50,42,44,43,49,43,43,37",":2:37,21,27,30,31,20,26,35,37,35,33,28,33",":3:72,75,68,77,67,71,60,76,72,73,72,74,72"},
             {":0:9,17,13,10,14,8,12,16,14,15,14,13,13",":1:50,38,41,37,47,50,42,44,43,49,43,43,37",":2:37,21,27,30,31,20,26,35,37,35,33,28,33",":3:72,75,68,77,67,71,60,76,72,73,72,74,72"},
             {":0:9,17,13,10,14,8,12,16,14,15,14,13,13",":1:50,38,41,37,47,50,42,44,43,49,43,43,37",":2:37,21,27,30,31,20,26,35,37,35,33,28,33",":3:72,75,68,77,67,71,60,76,72,73,72,74,72"},
             {":0:9,17,13,10,14,8,12,16,14,15,14,13,13",":1:50,38,41,37,47,50,42,44,43,49,43,43,37",":2:37,21,27,30,31,20,26,35,37,35,33,28,33",":3:72,75,68,77,67,71,60,76,72,73,72,74,72"},
         };
       String Message ;
        String ClientMessages = "";
        String ClientData = "";
        char VariableX;
        char VariableY;
        char Station;
        char temp;
        int Number1;
        int Number2;
        int Number3;
        int Index = 5;
        int Counter = 0;
        int Variable = 0;
        String Data = "";

        while (true){
               Message = "";

              
               
          try{
             
            ServerSocket ss = new ServerSocket(1201);
            Socket s = ss.accept();
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            /*ServerGUI ServerVisable = new ServerGUI();
            ServerVisable.setVisible(true);*/
            char typeLogin = 1;
            char typeStation = 0;
            while (!Message.equals("exit")){
 
                Message = din.readUTF();
                String[] newMessage = Message.split(" ");
                
                System.out.println(Message); // first print
                if(Message.charAt(0) == typeStation){
                    String stationInfo = newMessage[1];
                    // read in -> String = login or station + " " + Station number + SelectedX + SelectedY
                    //char MessageType = Message.charAt(0);

                    char MessageType = stationInfo.charAt(0);
                    Data = Character.toString(MessageType);
                    /*if (Data.equals(":"))
                    {
                    Counter = Character.getNumericValue(Message.charAt(1));
                    Index = Index +1;
                    while (Character.toString(Message.charAt(Index)).equals(":")==false)
                    {
                    
                    temp = Message.charAt(Index);
                    Data = Character.toString(temp);
                    data[Counter][Variable] = data[Counter][Variable] + Data;
                    Index = Index +1;
                    
                    }
                    Index = Index +2;
                    Variable = Variable +1;
                    
                    }*/
                    
                    
                    
                    //else{
                    Station = stationInfo.charAt(0);
                    VariableX = stationInfo.charAt(1);
                    VariableY = stationInfo.charAt(2);
                    if (VariableX==VariableY){
                        ClientMessages = "Could not load Error: Same Variabel";
                        dout.writeUTF(ClientMessages);
                        din.close();
                        
                    }else{
                        ClientMessages = "Making Connection";
                        //UNDER CONSTRUSTION
                        
                        dout.writeUTF(ClientMessages);
                        Number1 = Character.getNumericValue(VariableX);
                        Number2 = Character.getNumericValue(VariableY);
                        Number3 = Character.getNumericValue(Station);
                        ClientData=  data[Number3][Number1] + data[Number3][Number2];
                        System.out.println(ClientData); // 2nd print
                        dout.writeUTF(ClientData);
                        din.close();
                    }   } else {
                }
                if(Message.charAt(0) == typeLogin){
                    // message from server is from login screen
                        // check file for login info
                        // message is == "1" " " "name" " " "pass"
                        //String username = Message[1];
                        List<String> Userdata = new ArrayList<>();
                    try{
                        Path path = Paths.get("Admin.txt");
                            try (Scanner scanner = new Scanner(path)) {
                                System.out.println("Read text file using Scanner");
                                while(scanner.hasNextLine()){
                                    String line = scanner.nextLine();
                                    Userdata.add(line);
                                    
                                }   }
                        System.out.println("Data collected");
                    }
                    catch(IOException e){
                        System.out.println("Error opening file");
                    }
                    int size = Userdata.size()-1;
                    boolean Present = false;
                    while (size >= 1){
                        System.out.println(Userdata.get(size-1));
                        System.out.println(newMessage[1]);
                        if ((Userdata.get(size).equals(newMessage[2]))&&(Userdata.get(size-1).equals(newMessage[1]))){
                            Present = true;
                            System.out.println("User found");
                        }
                        size = size -2;
                    }
        try{
            String msgout;
        if (Present == false){
            System.out.println("Not User");
            msgout = "0"; // user not found
            
        }else{
            msgout = "1"; // user found
        }
        dout.writeUTF(msgout);
        
        }catch(IOException e){
            //handle exception here
        }                       
                }
            //}
          }
          }
    catch(IOException e){
        // Exception handling
    }
          

     }

     }
   
    }


