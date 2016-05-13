package sample;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.scene.Node;
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
    public void easyaction(ActionEvent enent)
    {
        wordscontrol.file=new File("D:\\GRE word practice\\easywords.txt");
        wordscontrol.loadthings();


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
