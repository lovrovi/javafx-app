/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontakti.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Daniel
 */
public class LogiraniKorisnikModel {
    public boolean isLogged;
    public int isAdmin;
    public int userId;

    public LogiraniKorisnikModel(boolean isLogged, int isAdmin, int userId){
        this.isLogged = isLogged;
        this.isAdmin = isAdmin;
        this.userId = userId;
    }


    public static LogiraniKorisnikModel logiraj (String kime, String lozinka) {
        Baza db = new Baza();
        PreparedStatement ps = db.exec("SELECT * FROM zaposlenik WHERE ime =? AND "
                + "lozinka=? AND isEmployed = 1");
        try {
            ps.setString(1, kime);
            ps.setString(2, lozinka);
            ResultSet rs = ps.executeQuery();
            return new LogiraniKorisnikModel(rs.next(), rs.getInt("isAdmin"), rs.getInt("id"));
        } catch (SQLException ex) {
            System.out.println("Nastala je greška: "+ex.getMessage());
            return new LogiraniKorisnikModel(false, 0, 0);
        }
    }
}
