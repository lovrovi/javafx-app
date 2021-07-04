package kontakti.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ZaposleniciModel {

    SimpleIntegerProperty id;
    SimpleStringProperty ime;
    SimpleStringProperty prezime;
    SimpleStringProperty lozinka;
    SimpleIntegerProperty isAdmin;

    public ZaposleniciModel(Integer id, String ime, String prezime, String
            lozinka, Integer isAdmin) {
        this.id = new SimpleIntegerProperty (id);
        this.ime = new SimpleStringProperty(ime);
        this.prezime = new SimpleStringProperty(prezime);
        this.lozinka = new SimpleStringProperty(lozinka);
        this.isAdmin = new SimpleIntegerProperty(isAdmin);

    }
    public Integer getId() {
        return id.get();

    }
    public String getIme () {
        return ime.get();
    }

    public String getPrezime () {
        return  prezime.get();
    }

    public String getLozinka () {
        return lozinka.get();
    }

    public int getIsAdmin() {
        return isAdmin.get();
    }

    public static ObservableList<ZaposleniciModel> listaZaposlenika() {
        ObservableList<ZaposleniciModel> lista = FXCollections.observableArrayList();
        Baza DB = new Baza();
        ResultSet rs = DB.select("SELECT * FROM zaposlenik WHERE isEmployed = 1");
        try {
        while (rs.next()) {
            lista.add(new ZaposleniciModel(
                rs.getInt("id"),
                rs.getString("ime"),
                rs.getString("prezime"),
                rs.getString("lozinka"),
                rs.getInt("isAdmin")));
        }
        } catch (SQLException ex) {
            System.out.println("Nastala je greška prilikom iteriranja.");
        }
        return lista;
    }

    public void createZaposlenik(){
        try {
            String sql = "INSERT INTO zaposlenik VALUES (null, ?, ?, ?, ?, 1)";
            Baza DB = new Baza();
            PreparedStatement upit = DB.exec (sql);
            upit.setString(1, this.ime.getValue());
            upit.setString(2, this.prezime.getValue());
            upit.setString(3, this.lozinka.getValue());
            upit.setInt(4, this.isAdmin.getValue());
            upit.executeUpdate();

        } catch (SQLException ex) {
            System.out.print(ex);
        }
    }
    public void updateZaposlenik(){
        try {
            String sql = "UPDATE zaposlenik  SET ime = ?, prezime = ?, lozinka = ?, isAdmin = ? WHERE id = ?";
            Baza DB = new Baza();
            PreparedStatement upit = DB.exec (sql);
            upit.setString(1, this.ime.getValue());
            upit.setString(2, this.prezime.getValue());
            upit.setString(3, this.lozinka.getValue());
            upit.setInt(4, this.isAdmin.getValue());
            upit.setInt(5, this.id.getValue());

            upit.executeUpdate();
        }catch (SQLException ex){
            System.out.print(ex);
        }
    }

    public void deleteZaposlenik(){
        try {
            String sql = "UPDATE zaposlenik SET isEmployed = 0 WHERE id = ?";
            Baza DB = new Baza();
            PreparedStatement upit = DB.exec (sql);
            upit.setInt(1, this.id.getValue());

            upit.executeUpdate();
        }catch (SQLException ex){
            System.out.print(ex);
        }
    }
}
