package sample;

import Loadserver.Clientmain;
import com.sun.deploy.uitoolkit.impl.fx.HostServicesFactory;
import com.sun.javafx.application.HostServicesDelegate;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Ashiq on 5/12/2016.
 */

public class wordscontrol {
    public static File file;
    public static ArrayList<Node > words;
    public static ArrayList<Node > knownwords;
    public static ArrayList<Node> kwords;
    public static  ArrayList<Node> Lwords=new ArrayList<>();
    public static ArrayList<Node> nextwords;
    public static ArrayList<Node> nextthis;
    public static wordscontrol controler;
    public static  int pos;
    public static Node node;
    public Label endup;
    public Label notify;
    public Label nextnoti;
    public static int size;
    public Button word;
    public Button home;
    public Button Yes;
    public Button next;
    public ProgressBar progress;
    public ProgressBar nextprogress;
    public Button formore;
    public TextArea meaning;
    public TextArea sentence;
    public static void loadthings(wordscontrol control){

        Scanner fscan,fscan2;
        controler=control;
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

       }
        size=words.size();
       kwords=new ArrayList<>();
        knownwords=new ArrayList<>();
      File f=new File("D:\\GRE word practice\\known.txt");
        try
        {
            fscan2=new Scanner(f);
            String w;
            String m;
            String s;
            while(fscan2.hasNext())
            {

                w=fscan2.nextLine();
                m=fscan2.nextLine();
                s=fscan2.nextLine();
                Node n=new Node(w,m,s);
                kwords.add(n);
            }
        }catch (Exception e)
        {
            System.out.println("ttttttttttttttttttttttttttttttttt");

        }


        ////// adding next words action.....
        nextwords=new ArrayList<>();
        f=new File("D:\\GRE word practice\\next.txt");
        try
        {
            fscan2=new Scanner(f);
            String w;
            String m;
            String s;
            while(fscan2.hasNext())
            {

                w=fscan2.nextLine();
                m=fscan2.nextLine();
                s=fscan2.nextLine();
                Node n=new Node(w,m,s);
                nextwords.add(n);
            }
        }catch (Exception e)
        {
            System.out.println("000000");

        }
        nextthis=new ArrayList<>(nextwords);
        System.out.println("------------"+nextwords.size());
        nextthis.retainAll(words);
        nextwords.removeAll(nextthis);
        //////
        knownwords.addAll(kwords);
        System.out.println(knownwords.size());
        knownwords.retainAll(words);
        System.out.println(knownwords.size());
        words.removeAll(knownwords);
        kwords.removeAll(knownwords);
        pos=0;
        control.notify.setText(knownwords.size()+" Words out of "+size+ " is completed");
        control.nextnoti.setText("You r reviewing "+nextthis.size()+" Words out of "+size);
        control.progress.setStyle("-fx-accent: blue;-fx-border-color:indigo;");
        control.nextprogress.setStyle("-fx-border-color:blue;-fx-accent: black;");
        control.progress.setProgress((knownwords.size()) * 1.0 / size);
        control.nextprogress.setProgress((nextthis.size()) * 1.0 / size);
        if( words.size()==pos)
        {
            control.endup.setText("THIS PART IS COMPLETED");
        }
        else {
            node=words.get(pos);
            control.word.setText(words.get(pos).word);
            control.meaning.setText(words.get(pos).mean);
            control.sentence.setText(words.get(pos).senten);
            pos++;

        }

    }


    public  void nextaction(ActionEvent event)
    {
        if(words.size()!=0) {
            if (!nextthis.contains(node)) {
                nextthis.add(node);
                System.out.println(node.word);
            } else {
                //System.out.println("==="+node.word);
            }
            nextnoti.setText("You r reviewing " + nextthis.size() + " Words out of " + size);
            nextprogress.setProgress((nextthis.size()) * 1.0 / size);
            probable();
        }
    }
    public void yesaction(ActionEvent event)
    {
        if(words.size()!=0) {
            words.remove(node);
            knownwords.add(node);
            nextthis.remove(node);
            Lwords.add(node);
            notify.setText(knownwords.size() + " Words out of " + size + " is completed");
            progress.setProgress((knownwords.size()) * 1.0 / size);
            nextnoti.setText("You r reviewing " + nextthis.size() + " Words out of " + size);
            nextprogress.setProgress((nextthis.size()) * 1.0 / size);
            probable();
        }
    }
    public void formoreaction(ActionEvent event) throws IOException, URISyntaxException {
        String [] s1=word.getText().toLowerCase().trim().split(" ");
        //System.out.println(s1);
        System.out.println(s1[0]);
        String s="http://www.dictionary.com/browse/"+s1[0]+"?s=t";
        System.out.println(s);
        java.awt.Desktop.getDesktop().browse(new URI(s));


    }
    public void homeaction( ActionEvent event) throws IOException {
       // File f=new File("D:\\GRE word practice\\known.txt");
        PrintWriter p=new PrintWriter("D:\\GRE word practice\\known.txt");
        p.close();
         p=new PrintWriter("D:\\GRE word practice\\known.txt");
        kwords.addAll(knownwords);
        for(Node x: kwords)
        {
            p.println(x.word);
            p.println(x.mean);
            p.println(x.senten);
        }
        p.close();
        ///// next words
        nextwords.addAll(nextthis);
        System.out.println("------------"+nextwords.size());
        p=new PrintWriter("D:\\GRE word practice\\next.txt");
        p.close();
        p=new PrintWriter("D:\\GRE word practice\\next.txt");
        for(Node x: nextwords)
        {
            p.println(x.word);
            p.println(x.mean);
            p.println(x.senten);
        }
        p.close();
        //////
        FXMLLoader fxml=new FXMLLoader();
        fxml.setLocation(getClass().getResource("startpage.fxml"));
        Parent home=fxml.load();
        Scene homescene=new Scene(home,650,600);
        Stage window=(Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        window.setTitle("HOME");
        window.setScene(homescene);
        window.show();
    }
    public void probable()
    {
        Random r=new Random();
        int i=r.nextInt(1000)%size;
        System.out.println(i+"  "+ nextthis.size());
        if(words.size()==0) endup.setText("THIS PART IS COMPLETED");
        else if(nextthis.size()==0 ||i>=nextthis.size())
        {
            pos++;
            System.out.println("??");
            if(pos>=words.size())
            {
                pos=0;
                node=words.get(pos);
                word.setText(words.get(pos).word.toUpperCase());
                meaning.setText(words.get(pos).mean);
                sentence.setText(words.get(pos).senten);
                pos++;
            }
            else
            {
                node=words.get(pos);
                word.setText(words.get(pos).word.toUpperCase());
                meaning.setText(words.get(pos).mean);
                sentence.setText(words.get(pos).senten);
                pos++;
            }
        }
        else if(nextthis.size()!=0)
        {
            int j=new Random().nextInt();
            if(j<0)j=j*-1;
            node=nextthis.get(j%nextthis.size());
            word.setText(node.word.toUpperCase());
            meaning.setText(node.mean);
            sentence.setText(node.senten);
        }

    }
}
