package Loadserver;

import sample.Controller;
import sample.Node;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Ashiq on 5/27/2016.
 */
public class Clientreciver implements Runnable{
    DataInputStream din;
    String s="s";
    Thread t;
    Clientreciver(DataInputStream din)
    {
        this.din=din;
        Controller.Loadthings=new ArrayList<>();
        t=new Thread(this);
        t.start();
    }
    @Override
    public void run() {
        try {
            s=din.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(s);
        while(!s.equals("stop"))
        {
            try {

                if(s.equals("nomatch"))
                {
                    Clientmain.ans="no match";
                }
                else if(s.equals("Match"))
                {
                    Clientmain.ans="Match";
                }
                else {
                    String[] s1 = s.split("#");
                    Controller.Loadthings.add(new Node(s1[0], s1[1], s1[2]));
                }
                s=din.readUTF();
                System.out.println(s);
            } catch (IOException e) {
                e.printStackTrace();
            }

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
