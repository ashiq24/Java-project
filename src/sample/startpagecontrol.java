package sample;
import java.io.File;
import java.io.IOException;

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
        Scene startpage=new Scene (homescene,650,600);
        Stage homesateg= (Stage) ((Node) enent.getSource()).getScene().getWindow();
        control=(wordscontrol) fxml.getController();
        wordscontrol.loadthings(control);
        homesateg.hide();
        homesateg.setScene(startpage);
        homesateg.show();

    }
    public void mediumaction(ActionEvent enent)
    {

    }
    public void hardaction(ActionEvent enent)
    {

    }

    public void testaction(ActionEvent enent)
    {

    }
    public void exitaction(ActionEvent enent)
    {
        Stage homesateg= (Stage) ((Node) enent.getSource()).getScene().getWindow();
        homesateg.close();


    }


}
