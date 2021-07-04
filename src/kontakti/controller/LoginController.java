
package kontakti.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import kontakti.model.LogiraniKorisnikModel;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    Label statusLbl;
    @FXML
    TextField kimeTxt;
    @FXML
    PasswordField lozinkaTxt;
    
    public void prijavise (ActionEvent e) {
        String kime = kimeTxt.getText();
        String lozinka = lozinkaTxt.getText();
        
        if (kime.equals("") || lozinka.equals("")) {
            statusLbl.setText("Morate unijeti sve vrijednosti!");
        } else {
            LogiraniKorisnikModel user = LogiraniKorisnikModel.logiraj(kime, lozinka);
            if (user.isLogged) {
                statusLbl.setTextFill(Color.GREEN);
                statusLbl.setText("Uspješno ste se prijavili");
                if (user.isAdmin == 1){
                    goToLocation.goToLocation(e, "Proizvodi", 0);
                }
                else{
                    goToLocation.goToLocation(e, "Blagajna", user.userId);
                }



            } else {
                statusLbl.setText("Korisnicki podatci nisu ispravni!");
            }
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
}
