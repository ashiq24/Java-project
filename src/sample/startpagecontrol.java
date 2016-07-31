package sample;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import Loadserver.Clientmain;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Created by Ashiq on 5/12/2016.
 */
public class startpagecontrol {
    public static  String s;
    public static   Button exit;
    public static void sets(String m)
    {
        s=m;
    }
    public void easyaction(ActionEvent enent)throws Exception
    {
        wordscontrol control;
        wordscontrol.file=new File("D:\\GRE word practice\\easywords.txt");
        FXMLLoader fxml=new FXMLLoader();
        fxml.setLocation(getClass().getResource("wordprac.fxml"));
        Parent homescene = fxml.load();
        Scene startpage=new Scene (homescene,650,650);
        Stage homesateg= (Stage) ((Node) enent.getSource()).getScene().getWindow();
        control=(wordscontrol) fxml.getController();
        //control.Lwords=new ArrayList<>();
        wordscontrol.loadthings(control);
        homesateg.setScene(startpage);
        homesateg.show();

    }
    public void mediumaction(ActionEvent enent) throws IOException {
        wordscontrol control;
        wordscontrol.file=new File("D:\\GRE word practice\\easywords2.txt");
        FXMLLoader fxml=new FXMLLoader();
        fxml.setLocation(getClass().getResource("wordprac.fxml"));
        Parent homescene = fxml.load();
        Scene startpage=new Scene (homescene,650,650);
        Stage homesateg= (Stage) ((Node) enent.getSource()).getScene().getWindow();
        control=(wordscontrol) fxml.getController();
        //control.Lwords=new ArrayList<>();
        wordscontrol.loadthings(control);
        homesateg.setScene(startpage);
        homesateg.show();
    }
    public void hardaction(ActionEvent enent)
    {

    }

    public void testaction(ActionEvent enent)
    {

    }
    public void exitaction(ActionEvent enent) throws IOException {
        Clientmain cm=new Clientmain(9000);
        if(!Clientmain.Nonet)
        {
            System.out.println(wordscontrol.Lwords.size());
            cm.Upadte(wordscontrol.Lwords);

        }
        Stage homesateg= (Stage) ((Node) enent.getSource()).getScene().getWindow();
        homesateg.close();


    }


}
