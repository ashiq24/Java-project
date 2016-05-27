package sample;

import Loadserver.Clientmain;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    public Label warning;
    public TextField name;
    public TextField pass;
    public TextField name2;
    public TextField pass2;
    public Button login1;
    public Button login2;
    public Button login3;
    public Button recordlogin;
    public static ArrayList<sample.Node>Loadthings;

    public void login1(ActionEvent e)
    {
        name.setVisible(true);
        pass.setVisible(true);
        login1.setVisible(false);
        login2.setVisible(true);

    }
    public void loginaction(ActionEvent e) throws Exception {
        File f = new File("D:\\GRE word practice\\user.txt");
        Scanner s = new Scanner(f);
        String Nam, Pas;
        if (!s.hasNext()) {
            warning.setText("Please creat an account!!!");
        }
        else
        {
            Nam = s.nextLine();
            Pas = s.nextLine();
            String nam, pas;
            nam = name.getText();
            pas = pass.getText();
            if (nam.equals(Nam) && Pas.equals(pas)) {
                Main.Uname=nam;
               Parent homescene = FXMLLoader.load(getClass().getResource("startpage.fxml"));
                Scene startpage = new Scene(homescene, 650, 600);
                Stage homesateg = (Stage) ((Node) e.getSource()).getScene().getWindow();
                homesateg.setScene(startpage);
                homesateg.show();
            }
            else
            {
                warning.setText("use correct username and password...");
            }
        }
    }
    public void newaccaction(ActionEvent e) throws Exception
    {
        Parent homescene = FXMLLoader.load(getClass().getResource("/login/newaccount.fxml"));
        Scene startpage = new Scene(homescene, 650, 600);
        Stage homesateg = (Stage) ((Node) e.getSource()).getScene().getWindow();
        homesateg.setScene(startpage);
        homesateg.show();

    }
    public void recordlogin(ActionEvent e)
    {
        recordlogin.setVisible(false);
        login3.setVisible(true);
        name2.setVisible(true);
        pass2.setVisible(true);
    }
    public  void  login3action(ActionEvent a) throws IOException {
        Clientmain cm=new Clientmain();
        String name=name2.getText().trim();
        String pass=pass2.getText().trim();
        if(cm.Nonet)
        {
            warning.setText("connetion is not avaiable");
        }
        else {
            String ans = cm.validate(name, pass);
            if (ans.equals("Match")) {
                File f=new File("D:\\GRE word practice\\user.txt");
                PrintWriter p=new PrintWriter(f);
                p.println(name);
                p.println(pass);
                p.flush();
                p.close();

            } else {
                warning.setText("no match found");
            }
        }


    }
}
