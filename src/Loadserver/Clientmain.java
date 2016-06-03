package Loadserver;


import sample.Main;
import sample.Node;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import static sample.Main.Uname;
import static sample.wordscontrol.node;

/**
 * Created by Ashiq on 5/24/2016.
 */
public class Clientmain implements Runnable{
    Socket client;
    public static Boolean Nonet=false;
    public static ArrayList<Node> Alist=new ArrayList<>();
    public static String ans="g";
    DataInputStream din;
    DataOutputStream dout;
    Clientreciver crec;
    Thread update;
    public Clientmain(int n)
    {
        try {
            client=new Socket("Localhost",n);
            din=new DataInputStream(client.getInputStream());
            dout=new DataOutputStream(client.getOutputStream());

        } catch (IOException e) {
           Nonet=true;
        }
    }
    public void Upadte(ArrayList<Node> Alist) throws IOException {
        Clientmain.Alist.addAll(Alist);
        update=new Thread(this);
        update.start();

    }
    public String validate(String name, String pass) throws IOException {
        String s=name+" "+pass;
        crec=new Clientreciver(client,this, s);

        while(ans.equals("g"))
        {
            System.out.println("-"+ans);
        }
        if(ans.equals("nomatch"))
        {
        }
        System.out.println(ans);
        return ans;
    }
     void close() throws IOException {
        din.close();
        dout.close();
         client.close();
    }


    @Override
    public void run() {
        if(false){
            try {
                close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            try {
                System.out.println("dvodjvdjvkdkvndk");
                dout.writeUTF("# " + Uname);
                System.out.println(Uname);
                dout.flush();
                for (Node n : Alist) {
                    String s = n.word + "#" + n.mean + "#" + n.senten;
                    dout.writeUTF(s);
                    System.out.println(s);
                    dout.flush();
                }
                dout.writeUTF("done");
                dout.flush();
                close();
            }
            catch (Exception e)
            {
                System.out.println(e.fillInStackTrace());
            }
        }
    }
}

