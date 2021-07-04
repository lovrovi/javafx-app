package kontakti.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BlagajnaModel {
    SimpleStringProperty datum;
    SimpleIntegerProperty idZaposlenik;
    SimpleIntegerProperty ukupanIznos;
    List<Integer> idProizvoda;
    List<Integer> kolicina;



    public BlagajnaModel(String datum, int idZaposlenik, int ukupanIznos) {
        this.datum = new SimpleStringProperty(datum) ;
        this.idZaposlenik = new SimpleIntegerProperty( idZaposlenik);
        this.ukupanIznos = new SimpleIntegerProperty(ukupanIznos);
    }

    public BlagajnaModel(List<Integer> id, List<Integer> kolicina){
        this.idProizvoda = id;
        this.kolicina = kolicina;
    }

    public List<Integer> getIdProizvoda() {
        return idProizvoda;
    }

    public List<Integer> getKolicina() {
        return kolicina;
    }

    public String getDatum() {
        return datum.get();
    }

    public int getIdZaposlenik() {
        return idZaposlenik.get();
    }

    public int getUkupanIznos() {
        return ukupanIznos.get();
    }

    public void createRacun(){
        try {
            String sql = "INSERT INTO racuni VALUES (null, ?, ?, ?)";
            Baza DB = new Baza();
            PreparedStatement upit = DB.exec (sql);
            upit.setString(1, this.datum.getValue());
            upit.setInt(2, this.idZaposlenik.getValue());
            upit.setInt(3, this.ukupanIznos.getValue());
            upit.executeUpdate();

        } catch (SQLException ex) {
            System.out.print(ex);
        }
    }

    public void createRacunProizvod(){
        StringBuilder insert = new StringBuilder();
        for (int i = 0; i < this.idProizvoda.size(); i++){
            insert.append("(").append(this.idProizvoda.get(i)).append(", @racunID, ").append(this.kolicina.get(i)).append("),");
        }
        insert.deleteCharAt(insert.length()-1);

        try {

            String sql = "SET @racunID = (SELECT MAX(id) FROM racuni); INSERT INTO proizvodi_racun VALUES" + insert + ";";
            System.out.print(sql);
            Baza DB = new Baza();

            PreparedStatement upit = DB.exec (sql);

            upit.executeUpdate();

        } catch (SQLException ex) {
            System.out.print(ex);
        }
    }

}
