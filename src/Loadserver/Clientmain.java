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
public class Clientmain {
    Socket client;
    public static Boolean Nonet=false;
    public static String ans="g";
    DataInputStream din;
    DataOutputStream dout;
    Clientreciver crec;
    public Clientmain()
    {
        try {
            client=new Socket("Localhost",9000);
            din=new DataInputStream(client.getInputStream());
            dout=new DataOutputStream(client.getOutputStream());
        } catch (IOException e) {
           Nonet=true;
        }
    }
    public void Upadte(ArrayList<Node> Alist) throws IOException {
        if(Uname.equals("!")){}
        else
        {
            dout.writeUTF("# "+Uname);
            dout.flush();
            for(Node n:Alist)
            {
                String s=n.word+"#"+n.mean+"#"+n.senten;
                dout.writeUTF(s);
                dout.flush();
            }
            dout.writeUTF("done");
            dout.flush();
        }
    }
    public String validate(String name, String pass) throws IOException {
        String s=name+" "+pass;
        new Clientreciver(din);
        dout.writeUTF(s);
        dout.flush();
        while(ans.equals("g"))
        {
            System.out.println("-"+ans);
        }
        if(ans.equals("nomatch"))
        {
            close();
        }
        System.out.println(ans);
        return ans;
    }
    void close() throws IOException {
        din.close();
        dout.close();
    }



}

