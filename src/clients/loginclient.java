package clients;

import login.logicontrol;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


/**
 * Created by Ashiq on 5/24/2016.
 */
public class loginclient {
    Socket client;
    String name;
    String pass;
    DataInputStream din;
    DataOutputStream dout;
    public static boolean d=false;
    public static  String  order;
    public loginclient(){
        try {
            client=new Socket("Localhost",8000);
            din=new DataInputStream(client.getInputStream());
            dout=new DataOutputStream(client.getOutputStream());
            new clientreceiver(din);
        } catch (IOException e) {
            logicontrol.nointernet=true;

        }
    }
    public void takeelem( String N, String P)
    {
        name=N;
        pass=P;
    }
    public void passit() throws IOException {
        String s=name.trim()+"#"+pass.trim();
        dout.writeUTF(s);
        dout.flush();

    }
    public String condition()
    {
        while(!d)
        {

        }
        return order;

    }
    public  void close() throws IOException {
        din.close();
        dout.close();
    }


}
