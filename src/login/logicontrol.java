package login;
import clients.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Ashiq on 5/24/2016.
 */
public class logicontrol {
    public static boolean nointernet=false;
    public Label warning;
    public TextField Name;
    public TextField Pass;
    public Button close;
    public Button creat;
    String s;
    public void creataction(ActionEvent event) throws IOException {
        loginclient lc=new loginclient();
        warning.setText("");
        if(nointernet )
        {
            warning.setText("Connection is not available.try later..:)");
        }
        else if(Name.getText().trim().equals("") ||Pass.getText().trim().equals(""))
        {
            warning.setText("Enter valid user name and password");

        }
        else
        {
            lc.takeelem(Name.getText().trim(),Pass.getText().trim());
            lc.passit();
            s=lc.condition();
            System.out.println(s);
            if(s.equals("success"))
            {
                try {
                    File f=new File("D:\\GRE word practice\\user.txt");
                    /*PrintWriter p=new PrintWriter(f);
                    p.close();*/
                    PrintWriter p1=new PrintWriter(f);
                    p1.println(Name.getText().trim());
                    p1.println(Pass.getText().trim());
                    p1.flush();
                    Main.Uname=new String(Name.getText().trim());
                    warning.setText("Done");
                    File f2=new File("D:\\GRE word practice\\known.txt");
                    PrintWriter p2=new PrintWriter(f2);
                    p2.close();
                    //Stage homesateg = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    //homesateg.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            else
            {
                warning.setText("The user name already in use");
            }
        }

    }
    public void returnaction( ActionEvent event) throws IOException {
        Parent homescene = FXMLLoader.load(getClass().getResource("/sample/sample.fxml"));
        Scene startpage = new Scene(homescene, 650, 600);
        Stage homesateg = (Stage) ((Node) event.getSource()).getScene().getWindow();
        homesateg.setScene(startpage);
        homesateg.show();
    }


}
