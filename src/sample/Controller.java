package sample;

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
import java.util.Scanner;

public class Controller {
    public Label warning;
    public TextField name;
    public TextField pass;
    public Button login1;
    public Button login2;


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
}
