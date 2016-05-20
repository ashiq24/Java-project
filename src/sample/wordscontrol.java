package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Ashiq on 5/12/2016.
 */

public class wordscontrol {
    public static File file;
    public static ArrayList<Node > words;
    public static ArrayList<Node > knownwords;
    public static Node []wordarray;
    public static  int pos;
    public Label endup;
    public Button word;
    public Button home;
    public Button next;
    public Button formore;
    public TextArea meaning;
    public TextArea sentence;
    public static void loadthings(wordscontrol control){
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
        pos=0;

        if( words.size()==pos)
        {
            control.endup.setText("THIS PART IS COMPLETED");
        }
        else {
            control.word.setText(words.get(pos).word);
            control.meaning.setText(words.get(pos).mean);
            control.sentence.setText(words.get(pos).senten);
            pos++;

        }

    }
    public  void startaction(ActionEvent event)
    {
        if( words.size()-1==pos)
        {
            endup.setText("THIS SECTION IS COMPLETED :-)");
        }
        else {
            word.setText(words.get(pos).word.toUpperCase());
            meaning.setText(words.get(pos).mean);
            sentence.setText(words.get(pos).senten);
            pos++;
            meaning.setEditable(false);
            sentence.setEditable(false);

        }
    }
    public  void nextaction(ActionEvent event)
    {
        if( words.size()-1==pos)
        {
            endup.setText("THIS SECTION IS COMPLETED :-)");
        }
        else {
            word.setText(words.get(pos).word.toUpperCase());
            meaning.setText(words.get(pos).mean);
            sentence.setText(words.get(pos).senten);
            pos++;
            meaning.setEditable(false);
            sentence.setEditable(false);

        }

    }

}
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