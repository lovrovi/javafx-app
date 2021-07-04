package kontakti.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import kontakti.model.BlagajnaModel;
import kontakti.model.ProizvodiModel;
import kontakti.model.Racun;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

public class BlagajnaController implements Initializable {

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
    TableView tblRacun;
    @FXML
    TableColumn col_racunPro;
    @FXML
    TableColumn col_racunKol;
    @FXML
    TableColumn col_racunCij;
    @FXML
    TableColumn col_racunId;
    @FXML
    TextField txt_ukupno;
    @FXML
    TextField txt_naziv;
    @FXML
    TextField txt_kolicina;
    @FXML
    TextField txt_cijena;
    @FXML
    TextField txt_id;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getProizvodi();
        txt_ukupno.setText("0");
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

    public void selectRow(MouseEvent e){
        try {
            ProizvodiModel selected = (ProizvodiModel) tblProizvodi.getSelectionModel().getSelectedItem();
            txt_id.setText("" + selected.getId());
            txt_naziv.setText("" + selected.getNaziv());
            txt_cijena.setText("" + selected.getCijena());
            txt_kolicina.setText("1");

        } catch (NullPointerException n){

            System.out.print(n + "ovdje");
        }

    }
    public void addToRacun(ActionEvent e){
        try {
            int id = Integer.parseInt(this.txt_id.getText());
            String naziv = this.txt_naziv.getText();
            int kolicina = Integer.parseInt(this.txt_kolicina.getText());
            int  cijena = Integer.parseInt(this.txt_cijena.getText()) * kolicina;
            Racun racun = new Racun(id, naziv, kolicina, cijena);

            col_racunId.setCellValueFactory(new PropertyValueFactory<Racun,
                    Integer>("idProizvod"));
            col_racunPro.setCellValueFactory(new PropertyValueFactory<Racun,
                    String>("Naziv"));
            col_racunKol.setCellValueFactory(new PropertyValueFactory<Racun,
                    Integer>("Kolicina"));
            col_racunCij.setCellValueFactory(new PropertyValueFactory<Racun,
                    Integer>("Cijena"));
            tblRacun.getItems().add(racun);

            int ukupno = Integer.parseInt(this.txt_ukupno.getText()) + cijena;
            txt_ukupno.setText(String.valueOf(ukupno));

        } catch (NullPointerException n){

            System.out.print(n + "ovdje");
        }
    }

    public void create(ActionEvent e){
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd_HH:mm:ss").format(Calendar.getInstance().getTime());
        Stage stages = (Stage) tblRacun.getScene().getWindow();
        String modelString = stages.getTitle();
        int userId = Integer.parseInt(modelString);
        int  ukupno = Integer.parseInt(this.txt_ukupno.getText());

        List<Integer> idProizvoda = new ArrayList<>();
        List<Integer> kolicinaProizvoda = new ArrayList<>();
        for (Object item : tblRacun.getItems()) {
            idProizvoda.add((Integer) col_racunId.getCellObservableValue(item).getValue());
            kolicinaProizvoda.add((Integer) col_racunKol.getCellObservableValue(item).getValue());
        }
        BlagajnaModel racun = new BlagajnaModel(idProizvoda, kolicinaProizvoda);
        BlagajnaModel BM = new BlagajnaModel (timeStamp, userId, ukupno);
        BM.createRacun();
        racun.createRacunProizvod();
        tblRacun.getItems().clear();
        txt_ukupno.setText(String.valueOf(0));

    }

    public void clear(ActionEvent e){
        tblRacun.getItems().clear();
        txt_ukupno.setText(String.valueOf(0));
    }



}
