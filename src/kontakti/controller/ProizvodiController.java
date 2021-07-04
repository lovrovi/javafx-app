package kontakti.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import kontakti.model.ProizvodiModel;
import kontakti.model.VrsteProizvoda;

import java.net.URL;
import java.util.ResourceBundle;

public class ProizvodiController implements Initializable {
    @FXML
    TableView tblProizvodi;
    @FXML
    TableColumn col_id;
    @FXML
    TableColumn col_naziv;
    @FXML
    TableColumn col_cijena;
    @FXML
    TableColumn col_vrsta;

    @FXML
    TextField txt_naziv;
    @FXML
    TextField txt_cijena;
    @FXML
    TextField txt_id;
    @FXML
    ChoiceBox select_vrsta;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getProizvodi();
        getVrste();
    }

    public void getProizvodi(){
        ObservableList<ProizvodiModel> data = ProizvodiModel.listaProizvoda();
        col_id.setCellValueFactory(new PropertyValueFactory<ProizvodiModel,
                Integer>("Id"));
        col_naziv.setCellValueFactory(new PropertyValueFactory<ProizvodiModel,
                String>("Naziv"));
        col_cijena.setCellValueFactory(new PropertyValueFactory<ProizvodiModel,
                Integer>("Cijena"));
        col_vrsta.setCellValueFactory(new PropertyValueFactory<ProizvodiModel,
                String>("Vrsta"));
        tblProizvodi.setItems(data);
    }

    public void getVrste(){
        ObservableList<VrsteProizvoda> data = VrsteProizvoda.listaVrsta();
        for(VrsteProizvoda vrsta : data){
            select_vrsta.getItems().add(vrsta.getVrsta());
        }

    }

    public void goToZaposlenici (ActionEvent e) { goToLocation.goToLocation(e, "Zaposlenici", 0); }
    public void goToRacuni (ActionEvent e) { goToLocation.goToLocation(e, "Racuni", 0); }

    public void create(ActionEvent e){
        String naziv = this.txt_naziv.getText();
        int  cijena = Integer.parseInt(this.txt_cijena.getText());
        String vrsta = (String) this.select_vrsta.getValue();
        ProizvodiModel PM = new ProizvodiModel (0, naziv, cijena, vrsta);
        PM.createProizvod();
        getProizvodi();
        clearSelected();
    }

    @FXML
    public void selectRow(MouseEvent e){
        try {
            ProizvodiModel selected = (ProizvodiModel) tblProizvodi.getSelectionModel().getSelectedItem();
            txt_id.setText("" + selected.getId());
            txt_naziv.setText("" + selected.getNaziv());
            txt_cijena.setText("" + selected.getCijena());
            select_vrsta.setValue(selected.getVrsta());
        } catch (NullPointerException n){
            clearSelected();
            System.out.print(n + "ovdje");
        }

    }

    public void update(ActionEvent e){
        int id = Integer.parseInt(this.txt_id.getText());
        String naziv = this.txt_naziv.getText();
        int  cijena = Integer.parseInt(this.txt_cijena.getText());
        String vrsta = (String) this.select_vrsta.getValue();
        ProizvodiModel PM = new ProizvodiModel (id, naziv, cijena, vrsta);
        PM.updateProizvod();
        getProizvodi();
    }

    public void delete(ActionEvent e){
        int id = Integer.parseInt(this.txt_id.getText());
        ProizvodiModel PM = new ProizvodiModel (id, "naziv", 0, "");
        PM.deleteProizvod();
        getProizvodi();
        clearSelected();
    }

    public void clear(ActionEvent e){
        clearSelected();
    }

    public void clearSelected(){
        this.txt_id.clear();
        this.txt_naziv.clear();
        this.txt_cijena.clear();
        this.select_vrsta.valueProperty().set(null);
    }
}
