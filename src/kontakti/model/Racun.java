package kontakti.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Racun {
    SimpleIntegerProperty idProizvod;
    SimpleStringProperty naziv;
    SimpleIntegerProperty kolicina;
    SimpleIntegerProperty cijena;

    public Racun(int idProizvod, String naziv, int kolicina, int cijena) {
        this.idProizvod = new SimpleIntegerProperty (idProizvod);
        this.naziv = new SimpleStringProperty(naziv);
        this.kolicina = new SimpleIntegerProperty(kolicina);
        this.cijena = new SimpleIntegerProperty(cijena);
    }

    public int getIdProizvod() {
        return idProizvod.get();
    }

    public String getNaziv() {
        return naziv.get();
    }

    public int getKolicina() {
        return kolicina.get();
    }

    public int getCijena() {
        return cijena.get();
    }

}
