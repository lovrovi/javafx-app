package kontakti.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RacunDetails {
    SimpleIntegerProperty idRacun;
    SimpleStringProperty proizvod;
    SimpleIntegerProperty cijena;
    SimpleIntegerProperty kolicina;

    public RacunDetails (int idRacun, String proizvod, int cijena, int kolicina){
        this.idRacun = new SimpleIntegerProperty(idRacun);
        this.proizvod = new SimpleStringProperty(proizvod);
        this.cijena = new SimpleIntegerProperty(cijena);
        this.kolicina = new SimpleIntegerProperty(kolicina);
    }

    public int getIdRacun() {
        return idRacun.get();
    }

    public String getProizvod() {
        return proizvod.get();
    }

    public int getCijena() {
        return cijena.get();
    }

    public int getKolicina() {
        return kolicina.get();
    }

    public ObservableList<RacunDetails> detaljiRacuna() {
        ObservableList<RacunDetails> lista = FXCollections.observableArrayList();
        Baza DB = new Baza();
        PreparedStatement statement = DB.exec("SELECT P.naziv, P.cijena, PR.kolicina FROM proizvodi P JOIN proizvodi_racun PR ON P.id = PR.idProizvod WHERE PR.idRacun = ?");
        try {
            statement.setInt(1, this.idRacun.getValue());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                lista.add(new RacunDetails(
                        0,
                        rs.getString("naziv"),
                        rs.getInt("cijena"),
                        rs.getInt("kolicina")
                ));
            }

        }
        catch (SQLException ex){
            System.out.println("Nastala je greška prilikom iteriranja ovdje.");
        }

        return lista;
    }
}
