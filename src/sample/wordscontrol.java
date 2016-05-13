package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Ashiq on 5/12/2016.
 */
class Node
{
    String word;
    String mean;
    String senten;
    Node(String w,String m,String s)
    {
        word=w;
        mean=m;
        senten=s;
    }
}
public class wordscontrol {
    public static File file;
    public static ArrayList<Node > words;
    public static ArrayList<Node > knownwords;
    public static Node []wordarray;
    public static  int pos;
    Label endup;
    Button word;
    Button home,next,formore;
    TextField meaning, sentence;
    public static void loadthings(){
        Scanner fscan;
        words=new ArrayList<>() ;
       try
       {
            fscan=new Scanner(file);
           while(fscan.hasNextLine())
           {
               String w;
               String m;
               String s;
               w=fscan.nextLine();
               m=fscan.nextLine();
               s=fscan.nextLine();
               Node n=new Node(w,m,s);
               words.add(n);
           }
       }catch (FileNotFoundException e)
       {
           System.out.println(e);
       }
       knownwords=new ArrayList<>();
      File f=new File("D:\\GRE word practice\\known.txt");
        try
        {
            fscan=new Scanner(f);
            while(fscan.hasNextLine())
            {
                String w;
                String m;
                String s;
                w=fscan.nextLine();
                m=fscan.nextLine();
                s=fscan.nextLine();
                Node n=new Node(w,m,s);
                knownwords.add(n);
            }
        }catch (FileNotFoundException e)
        {
            System.out.println(e);
        }
        words.removeAll(knownwords);
        wordarray=(Node [])words.toArray();
        pos=0;

    }
    public  void startaction(ActionEvent event)
    {
        if( wordarray.length==0)
        {
            endup.setText("THIS PART IS COMPLETED");
        }
        else {
            word.setText(wordarray[pos].word);
            meaning.setText(wordarray[pos].mean);
            sentence.setText(wordarray[pos].senten);
            pos++;

        }
    }
    public  void nextaction(ActionEvent event)
    {
        if(pos==wordarray.length) {
            endup.setText("THIS PART IS COMPLETED");
        }

    }

}
