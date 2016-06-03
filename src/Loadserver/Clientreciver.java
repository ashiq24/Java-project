package Loadserver;

import sample.Controller;
import sample.Node;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Ashiq on 5/27/2016.
 */
public class Clientreciver implements Runnable{
    DataInputStream din;
    DataOutputStream dout;
    Socket soket;
    Clientmain cm;
    String s="s";
    String S;
    Thread t;
    Clientreciver(Socket soc, Clientmain cm, String s) throws IOException {
        soket=soc;
        din=new DataInputStream(soc.getInputStream());
        dout=new DataOutputStream(soc.getOutputStream());
        S=new String(s);
        dout.writeUTF(S);
        dout.flush();
        Controller.Loadthings=new ArrayList<>();
        this.cm=cm;
        t=new Thread(this);

        t.start();
    }
    @Override
    public void run() {

        while(!s.equals("stop"))
        {
            try {
                s=din.readUTF();

                if(s.equals("nomatch"))
                {
                    Clientmain.ans="no match";
                    break;
                }
                else if(s.equals("Match"))
                {
                    Clientmain.ans="Match";
                }
                else {
                    String[] s1 = s.split("#");
                    Controller.Loadthings.add(new Node(s1[0], s1[1], s1[2]));
                }

                System.out.println(s);
            } catch (IOException e) {
                e.printStackTrace();
            }
            catch (ArrayIndexOutOfBoundsException a)
            {

            }

        }
        try {
            cm.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File f=new File("D:\\GRE word practice\\known.txt");

        try {
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw=new BufferedWriter(fw);
            PrintWriter p=new PrintWriter(bw);
            for( Node x:Controller.Loadthings)
            {
                p.println(x.word);
                p.println(x.mean);
                p.println(x.senten);
            }
            p.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
