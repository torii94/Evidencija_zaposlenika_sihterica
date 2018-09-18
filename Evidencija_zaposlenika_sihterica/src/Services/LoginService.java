/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import evidencija_zaposlenika_sihterica.Model.Baza;
import evidencija_zaposlenika_sihterica.Model.Korisnik;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author toni
 */

    public class LoginService {
    
        private static KorisnikService korisnikService = new KorisnikService();   
        static Korisnik prijavljeni = null;        
        static Korisnik tip=null;
    
    public static boolean login (String ime, String lozinka) {
        try {
            PreparedStatement upit = Baza.DB.prepare("SELECT id,Korinsnicko_ime, lozinka, Tip_Korisnika FROM korisnik WHERE Korinsnicko_ime=? AND Lozinka=?");
            upit.setString(1, ime);
            upit.setString(2, lozinka);
            
            ResultSet rs = upit.executeQuery();
            if (rs.next()) {
                LoginService.prijavljeni = korisnikService.izBazePremaId(rs.getInt(1));
                return true;
                
            } else {
                return false;
            }
        } catch (SQLException ex) {
           System.out.println("Greska prilikom prijave: " + ex.getMessage());
           return false;
        }
    }
    
    
    public static Korisnik logiraniKorisnik () {
        return LoginService.prijavljeni;
    }
     public static Korisnik tipKorisnika () {
        return LoginService.tip;
    }
  
}
