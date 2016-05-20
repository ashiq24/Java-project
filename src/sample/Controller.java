package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller {
    public void stataction(ActionEvent e) throws Exception
    {
        Parent homescene = FXMLLoader.load(getClass().getResource("startpage.fxml"));
        Scene startpage=new Scene (homescene,650,600);
        Stage homesateg= (Stage) ((Node) e.getSource()).getScene().getWindow();
        homesateg.hide();
        homesateg.setScene(startpage);
        startpagecontrol.sets("bye");
        homesateg.show();
    }



}
