package kontakti.model;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;


public class RacuniModel {
    SimpleIntegerProperty id ;
    SimpleStringProperty datum ;
    SimpleStringProperty ime;
    SimpleFloatProperty ukupanIznos;


    public RacuniModel( int id, String datum, String ime, float ukupanIznos) {
        this.id = new SimpleIntegerProperty(id);
        this.datum = new SimpleStringProperty(datum);
        this.ime = new SimpleStringProperty(ime);
        this.ukupanIznos = new SimpleFloatProperty(ukupanIznos);
    }

    public int getId() {
        return id.get();
    }

    public String getDatum() {
        return datum.get();
    }

    public String getIme() {
        return ime.get();
    }

    public float getUkupanIznos() {
        return ukupanIznos.get();
    }

    public static ObservableList<RacuniModel> listaRacuna () {
        ObservableList<RacuniModel> lista = FXCollections.observableArrayList();
        Baza DB = new Baza();
        ResultSet rs = DB.select("SELECT R.id, R.datum, CONCAT(Z.ime,\" \", Z.prezime) as ime, R.ukupanIznos FROM racuni R " +
                "JOIN zaposlenik Z ON Z.id = R.idZaposlenik");
        try {
            while (rs.next()) {
                lista.add(new RacuniModel(
                        rs.getInt("id"),
                        rs.getString("datum"),
                        rs.getString("ime"),
                        rs.getFloat("ukupanIznos")
                ));
            }
        } catch (SQLException ex) {
            System.out.println("Nastala je greška prilikom iteriranja i vodje.");
        }
        return lista;
    }


}

