package kontakti.model;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProizvodiModel {
    SimpleIntegerProperty id;
    SimpleStringProperty naziv;
    SimpleIntegerProperty cijena;
    SimpleStringProperty vrsta;


    public ProizvodiModel(Integer id, String naziv, Integer cijena, String vrsta) {
        this.id = new SimpleIntegerProperty(id);
        this.naziv = new SimpleStringProperty(naziv);
        this.cijena = new SimpleIntegerProperty(cijena);
        this.vrsta = new SimpleStringProperty(vrsta);
    }

    public int getId() {
        return id.get();
    }

    public String getNaziv() {
        return naziv.get();
    }

    public int getCijena() {
        return cijena.get();
    }

    public String getVrsta() {
        return vrsta.get();
    }

    public static ObservableList<ProizvodiModel> listaProizvoda () {
        ObservableList<ProizvodiModel> lista = FXCollections.observableArrayList();
        Baza DB = new Baza();
        ResultSet rs = DB.select("SELECT P.id, P.naziv, P.cijena, V.vrsta FROM proizvodi P " +
                "JOIN vrstaproizvoda V on V.id = P.idVrsta WHERE P.isExist = 1");
        try {
            while (rs.next()) {
                lista.add(new ProizvodiModel(
                        rs.getInt("id"),
                        rs.getString("naziv"),
                        rs.getInt("cijena"),
                        rs.getString("vrsta")
                        ));
            }
        } catch (SQLException ex) {
            System.out.println("Nastala je greška prilikom iteriranja.");
        }
        return lista;
    }


    public void createProizvod(){
        try {
            String sql = "INSERT INTO proizvodi VALUES (null, ?, ?, (SELECT id FROM vrstaproizvoda WHERE vrsta = ?), 1)";
            Baza DB = new Baza();
            PreparedStatement upit = DB.exec (sql);
            upit.setString(1, this.naziv.getValue());
            upit.setInt(2, this.cijena.getValue());
            upit.setString(3, this.vrsta.getValue());
            upit.executeUpdate();

        } catch (SQLException ex) {
            System.out.print(ex);
        }
    }

    public void updateProizvod(){
        try {
            String sql = "UPDATE proizvodi  SET naziv = ?, cijena = ?, " +
                    "idVrsta = (SELECT id FROM vrstaproizvoda WHERE vrsta = ?) WHERE id = ?";
            Baza DB = new Baza();
            PreparedStatement upit = DB.exec (sql);
            upit.setString(1, this.naziv.getValue());
            upit.setInt(2, this.cijena.getValue());
            upit.setString(3, this.vrsta.getValue());
            upit.setInt(4, this.id.getValue());

            upit.executeUpdate();
        }catch (SQLException ex){
            System.out.print(ex);
        }
    }

    public void deleteProizvod(){
        try {
            String sql = "UPDATE proizvodi SET isExist = 0 WHERE id = ?";
            Baza DB = new Baza();
            PreparedStatement upit = DB.exec (sql);
            upit.setInt(1, this.id.getValue());

            upit.executeUpdate();
        }catch (SQLException ex){
            System.out.print(ex);
        }
    }


}
