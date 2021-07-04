package kontakti.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import kontakti.model.ProizvodiModel;
import kontakti.model.RacunDetails;
import kontakti.model.RacuniModel;
import kontakti.controller.goToLocation;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RacuniController implements Initializable{
    @FXML
    TableView tblRacuni;
    @FXML
    TableColumn col_datum;
    @FXML
    TableColumn col_ime;
    @FXML
    TableColumn col_ukupno;
    @FXML
    TableView tblDetails;
    @FXML
    TableColumn col_proizvod;
    @FXML
    TableColumn col_cijena;
    @FXML
    TableColumn col_kolicina;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<RacuniModel> data = RacuniModel.listaRacuna();
        col_datum.setCellValueFactory(new PropertyValueFactory<RacuniModel,
                String>("Datum"));
        col_ime.setCellValueFactory(new PropertyValueFactory<RacuniModel,
                String>("Ime"));
        col_ukupno.setCellValueFactory(new PropertyValueFactory<RacuniModel,
                Float>("UkupanIznos"));

        tblRacuni.setItems(data);
    }
    public void goToZaposlenici (ActionEvent e) {
        goToLocation.goToLocation(e, "Zaposlenici", 0);
    }
    public void goToProizvodi (ActionEvent e) {
        goToLocation.goToLocation(e, "Proizvodi", 0);
    }

    @FXML
    public void selectRow(MouseEvent e){
        try {
            RacuniModel selected = (RacuniModel) tblRacuni.getSelectionModel().getSelectedItem();
            int id = selected.getId();
            RacunDetails RD = new RacunDetails(id, "", 0, 0);
            ObservableList<RacunDetails> data = RD.detaljiRacuna();
            col_proizvod.setCellValueFactory(new PropertyValueFactory<RacunDetails,
                    String>("Proizvod"));
            col_cijena.setCellValueFactory(new PropertyValueFactory<RacunDetails,
                    Integer>("Cijena"));
            col_kolicina.setCellValueFactory(new PropertyValueFactory<RacunDetails,
                    Integer>("Kolicina"));

            tblDetails.setItems(data);


        } catch (NullPointerException n){
            //tblDetails.getItems().clear();
            System.out.print(n);
        }

    }
}
