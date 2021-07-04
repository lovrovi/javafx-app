package kontakti.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VrsteProizvoda {
    SimpleIntegerProperty id;
    SimpleStringProperty vrsta;

    public VrsteProizvoda(int id, String vrsta) {
        this.id = new SimpleIntegerProperty(id);
        this.vrsta = new SimpleStringProperty(vrsta);
    }

    public int getId() {
        return id.get();
    }

    public String getVrsta() {
        return vrsta.get();
    }

    public static ObservableList<VrsteProizvoda> listaVrsta () {
        ObservableList<VrsteProizvoda> lista = FXCollections.observableArrayList();
        Baza DB = new Baza();
        ResultSet rs = DB.select("SELECT * FROM vrstaproizvoda");
        try {
            while (rs.next()) {
                lista.add(new VrsteProizvoda(
                        rs.getInt("id"),
                        rs.getString("vrsta")
                ));
            }
        } catch (SQLException ex) {
            System.out.println("Nastala je greška prilikom iteriranja.");
        }
        return lista;
    }

}
