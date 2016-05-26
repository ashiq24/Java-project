package clients;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Created by Ashiq on 5/26/2016.
 */
public class clientreceiver implements Runnable{
    DataInputStream din;
    String ans;
    Thread t;
    clientreceiver(DataInputStream d)
    {
        din=d;
        ans="ooo";
        t=new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        try {
            ans=din.readUTF();
            System.out.println(ans);
            loginclient.order=ans;
            loginclient.d=true;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
