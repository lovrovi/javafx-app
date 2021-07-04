package kontakti.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class goToLocation {
    public static void goToLocation (ActionEvent e, String s, int id) {
        Parent root;
        try {
            root = FXMLLoader.load(goToLocation.class.getClassLoader().getResource("kontakti/view/" + s + ".fxml"));
            Stage stage = new Stage();
            if (id == 0){
                stage.setTitle(s);
            }
            else {
                stage.setTitle(String.valueOf(id));
            }
            stage.setScene(new Scene(root));
            stage.show();
            //skrij prozor koji je ostao otvoren (login prozor)
            ((Node)(e.getSource())).getScene().getWindow().hide();
        } catch (IOException err) {
            err.printStackTrace();
        }
    }
}
