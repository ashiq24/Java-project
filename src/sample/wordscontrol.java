package sample;

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
import java.util.Scanner;

/**
 * Created by Ashiq on 5/12/2016.
 */

public class wordscontrol {
    public static File file;
    public static ArrayList<Node > words;
    public static ArrayList<Node > knownwords;
    public static ArrayList<Node> kwords;
    public static wordscontrol controler;
    public static  int pos;
    public static Node node;
    public Label endup;
    public Label notify;
    public static int size;
    public Button word;
    public Button home;
    public Button Yes;
    public Button next;
    public ProgressBar progress;
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
           System.out.println(e);
       }
        size=words.size();
       kwords=new ArrayList<>();
        knownwords=new ArrayList<>();
      File f=new File("D:\\GRE word practice\\known.txt");
        try
        {
            fscan2=new Scanner(f);
            while(fscan2.hasNextLine())
            {
                String w;
                String m;
                String s;
                w=fscan2.nextLine();
                m=fscan2.nextLine();
                s=fscan2.nextLine();
                Node n=new Node(w,m,s);
                kwords.add(n);
            }
        }catch (Exception e)
        {

        }

        knownwords.addAll(kwords);
        System.out.println(knownwords.size());
        knownwords.retainAll(words);
        System.out.println(knownwords.size());
        words.removeAll(knownwords);
        kwords.removeAll(knownwords);
        pos=0;
        control.notify.setText(knownwords.size()+" Words out of "+size+ " is completed");
        control.progress.setProgress((knownwords.size()) * 1.0 / size);
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
    public  void startaction(ActionEvent event)
    {
        if(words.size()==0)
        {
            endup.setText("THIS SECTION IS COMPLETED :-)");
        }
        else if( words.size()-1==pos)
        {
            pos=0;
            node=words.get(pos);
            word.setText(words.get(pos).word.toUpperCase());
            meaning.setText(words.get(pos).mean);
            sentence.setText(words.get(pos).senten);
            pos++;
        }
        else {
            node=words.get(pos);
            word.setText(words.get(pos).word.toUpperCase());
            meaning.setText(words.get(pos).mean);
            sentence.setText(words.get(pos).senten);
            pos++;
        }
    }
    public  void nextaction(ActionEvent event)
    {
        if(words.size()==0)
        {
            endup.setText("THIS SECTION IS COMPLETED :-)");
        }
        else if( words.size()-1<=pos)
        {
            pos=0;
            node=words.get(pos);
            word.setText(words.get(pos).word.toUpperCase());
            meaning.setText(words.get(pos).mean);
            sentence.setText(words.get(pos).senten);
            pos++;
        }
        else {
            node=words.get(pos);
            word.setText(words.get(pos).word.toUpperCase());
            meaning.setText(words.get(pos).mean);
            sentence.setText(words.get(pos).senten);
            pos++;
        }

    }
    public void yesaction(ActionEvent event)
    {
        if(words.size()!=0) {
            knownwords.add(node);
            words.remove(node);
            pos--;
            notify.setText((knownwords.size()) + " Words out of " + size + " is completed");
            progress.setProgress((knownwords.size()) * 1.0 / size);
        }
        if(words.size()==0)
        {
            endup.setText("THIS SECTION IS COMPLETED :-)");
        }
        else if( words.size()-1<pos)
        {
            pos=0;
            node=words.get(pos);
            word.setText(words.get(pos).word.toUpperCase());
            meaning.setText(words.get(pos).mean);
            sentence.setText(words.get(pos).senten);
            pos++;
        }
        else {
            node=words.get(pos);
            word.setText(words.get(pos).word.toUpperCase());
            meaning.setText(words.get(pos).mean);
            sentence.setText(words.get(pos).senten);
            pos++;
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
        FXMLLoader fxml=new FXMLLoader();
        fxml.setLocation(getClass().getResource("startpage.fxml"));
        Parent home=fxml.load();
        Scene homescene=new Scene(home,650,600);
        Stage window=(Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        window.setTitle("HOME");
        window.setScene(homescene);
        window.show();


    }

}
