/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evidencija_zaposlenika_sihterica.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author toni
 */
public class Konekcija {
     private String host;
    private String korisnik;
    private String lozinka;
    private String baza;
    
    protected Connection konekcija;

    public Konekcija(String host, String korisnik, String lozinka, String baza) {
        this.host = host;
        this.korisnik = korisnik;
        this.lozinka = lozinka;
        this.baza = baza;
        this.spoji();
    }

    public Konekcija() {
        this.host = "localhost";
        this.korisnik = "root";
        this.lozinka = "";
        this.baza = "evidencija_zaposlenika";
        this.spoji();
    }
    
    public void spoji () {
        try {
            Class.forName ("com.mysql.jdbc.Driver");
            try {
                this.konekcija = DriverManager.getConnection(
                        "jdbc:mysql://"+this.host+"/"
                                +this.baza
                                +"?user="+this.korisnik
                                +"&password="+this.lozinka
                );
            } catch (SQLException ex) {
                System.out.println("Nisam se uspio povezati, problem s korisnickim podacima.");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println ("Nisam pronasao klasu za povezivanje.");
        }
    }
    
    public void odspoji () {
        try {
            this.konekcija.close();
        } catch (SQLException ex) {
            System.out.println("Nisam se uspio odspojiti.");
        }
    }
    
}
