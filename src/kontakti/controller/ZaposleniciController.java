package kontakti.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import kontakti.model.ZaposleniciModel;

import java.net.URL;
import java.util.ResourceBundle;

public class ZaposleniciController implements Initializable {
    @FXML
    TableView tbl_zaposlenici;
    @FXML
    TableColumn col_id;
    @FXML
    TableColumn col_ime;
    @FXML
    TableColumn col_prezime;
    @FXML
    TableColumn col_lozinka;
    @FXML
    TableColumn col_isAdmin;

    @FXML
    TextField txt_ime;
    @FXML
    TextField txt_prezime;
    @FXML
    TextField txt_lozinka;
    @FXML
    TextField txt_id;
    @FXML
    CheckBox check_isAdmin;

    @Override
    public void initialize(URL url, ResourceBundle rb) { getZaposlenici(); }

    public void getZaposlenici(){
        ObservableList<ZaposleniciModel> data = ZaposleniciModel.listaZaposlenika();
        col_id.setCellValueFactory(new PropertyValueFactory<ZaposleniciModel,
                Integer>("Id"));
        col_ime.setCellValueFactory(new PropertyValueFactory<ZaposleniciModel,
                String>("Ime"));
        col_prezime.setCellValueFactory(new PropertyValueFactory<ZaposleniciModel,
                String>("Prezime"));
        col_lozinka.setCellValueFactory(new PropertyValueFactory<ZaposleniciModel,
                String>("Lozinka"));
        col_isAdmin.setCellValueFactory(new PropertyValueFactory<ZaposleniciModel,
                Integer>("isAdmin"));
        tbl_zaposlenici.setItems(data);
    }

    public void goToProizvodi (ActionEvent e) {
        goToLocation.goToLocation(e, "Proizvodi", 0);
    }
    public void goToRacuni (ActionEvent e) {
        goToLocation.goToLocation(e, "Racuni", 0);
    }

    public void createZaposlenik(ActionEvent e){
        String ime = this.txt_ime.getText();
        String prezime = this.txt_prezime.getText();
        String lozinka = this.txt_lozinka.getText();
        boolean isChecked = this.check_isAdmin.isSelected();
        int isAdmin = 0;
        if (isChecked){
            isAdmin = 1;
        }
        else {
            isAdmin = 0;
        }
        ZaposleniciModel ZM = new ZaposleniciModel (0, ime, prezime, lozinka, isAdmin);
        ZM.createZaposlenik();
        getZaposlenici();
    }

    @FXML
    public void selectRow(MouseEvent e){
        try {
            ZaposleniciModel selected = (ZaposleniciModel) tbl_zaposlenici.getSelectionModel().getSelectedItem();
            txt_id.setText("" + selected.getId());
            txt_ime.setText("" + selected.getIme());
            txt_prezime.setText("" + selected.getPrezime());
            txt_lozinka.setText("" + selected.getLozinka());
            check_isAdmin.setSelected(selected.getIsAdmin() == 1);
        } catch (NullPointerException n){
            clearSelected();
            System.out.print(n);
        }
    }

    public void updateZaposlenik(ActionEvent e){
        int id = Integer.parseInt(this.txt_id.getText());
        String ime = this.txt_ime.getText();
        String prezime = this.txt_prezime.getText();
        String lozinka = this.txt_lozinka.getText();
        int isAdmin;
        if(this.check_isAdmin.isSelected()){
            isAdmin = 1;
        }
        else{
            isAdmin = 0;
        }
        ZaposleniciModel ZM = new ZaposleniciModel (id, ime, prezime, lozinka, isAdmin);
        ZM.updateZaposlenik();
        getZaposlenici();
    }

    public void deleteZaposlenik(ActionEvent e){
        int id = Integer.parseInt(this.txt_id.getText());
        ZaposleniciModel ZP = new ZaposleniciModel (id, "ime", "prezime", "lozinka", 0);
        ZP.deleteZaposlenik();
        getZaposlenici();
        clearSelected();

    }

    public void clear(ActionEvent e){
        clearSelected();
    }

    public void clearSelected(){
        this.txt_id.clear();
        this.txt_ime.clear();
        this.txt_prezime.clear();
        this.txt_lozinka.clear();
        check_isAdmin.setSelected(false);
    }
}
