/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import static evidencija_zaposlenika_sihterica.Model.Baza.DB;
import evidencija_zaposlenika_sihterica.Model.Korisnik;
import evidencija_zaposlenika_sihterica.Model.Placa;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
//import model.model;
import evidencija_zaposlenika_sihterica.Interfaces.model;

/**
 *
 * @author toni
 */
public class PlacaService implements model <Placa> {

    public static final PlacaService placaService=new PlacaService();

    @Override
    public Placa spasi(Placa placa) {
        try {            
            PreparedStatement upit = DB.prepare ("INSERT INTO placa VALUES(null, ?,?,?,?,?)");
            Korisnik k= placa.getKorisnikID();          
            upit.setInt(1,k.getId() );                
            upit.setFloat(2, placa.getIznosGodisnjePlace());
            upit.setFloat(3, placa.getIznosMjesecnePlace());
            upit.setFloat(4, placa.getIznosDnevnePlace());
            upit.setFloat(5, placa.getGodisnjiBonus());
            
            upit.executeUpdate();            
            ResultSet rs = upit.getGeneratedKeys();
            if (rs.next()){
                placa.setID(rs.getInt(1));
            }
            return placa;
        } catch (SQLException ex) {
            System.out.println("Greška prilikom izvršavanja upita: " + ex.getMessage());
            return null;
        }
    }

    @Override
    public Placa uredi(Placa placa) {
        try {            
            PreparedStatement upitP = DB.prepare ("UPDATE placa SET iznosGodisnjePlace=?,iznosMjesecnePlace=?,iznosDnevnePlace=? WHERE  korisnik_idZaposlenik=?");
            upitP.setFloat(1, placa.getIznosGodisnjePlace());
            upitP.setFloat(2, placa.getIznosMjesecnePlace());
            upitP.setFloat(3, placa.getIznosDnevnePlace());
            Korisnik k= placa.getKorisnikID();
            upitP.setInt(4, k.getId());
            upitP.executeUpdate();
            
            return placa;
        } catch (SQLException ex) {
            System.out.println("Greška prilikom izvršavanja upita: " + ex.getMessage());
            return null;    
        }     
}

    public Placa godisnjiBonus(Placa placa) {
        try {            
            PreparedStatement upitP = DB.prepare ("UPDATE placa SET godisnjiBonus=? WHERE  korisnik_idZaposlenik=?");
            upitP.setFloat(1, placa.getGodisnjiBonus());
            
            Korisnik k= placa.getKorisnikID();
            upitP.setInt(2, k.getId());
            upitP.executeUpdate();
            
            return placa;
        } catch (SQLException ex) {
            System.out.println("Greška prilikom izvršavanja upita: " + ex.getMessage());
            return null;  
        } 
}
    @Override
    public boolean brisi(Placa object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Placa> sveIzBaze() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Placa izBazePremaId(int id) {
        Placa p = new Placa();    
        try{
        PreparedStatement upit = DB.prepare ("SELECT iznosGodisnjePlace,iznosMjesecnePlace, godisnjiBonus FROM placa WHERE korisnik_idZaposlenik=?  ");           
        upit.setInt(1, id);
           
        ResultSet rs = upit.executeQuery();
        if (rs.next()){                        
            p.setIznosGodisnjePlace(rs.getFloat(1));
            p.setIznosMjesecnePlace(rs.getFloat(2));
            p.setGodisnjiBonus(rs.getFloat(3));
        }
                                 
        } catch (SQLException ex) {
            System.out.println("Greška prilikom izvršavanja upita: " + ex.getMessage());
            return null;
        }
        return p;
   
    }    
    
    
    public ObservableList<Placa> top3Place(int firma) {
        try {
            ObservableList <Placa> place = FXCollections.observableArrayList();                         
           // ResultSet rs = DB.select("SELECT placa.*, korisnik.Ime, korisnik.Prezime,korisnik.Slika FROM placa inner join korisnik on placa.korisnik_idZaposlenik=korisnik.id order by placa.iznosMjesecnePlace DESC");
              
             PreparedStatement upit = DB.prepare ("SELECT placa.*, korisnik.Ime, korisnik.Prezime,korisnik.Slika FROM placa inner join korisnik on placa.korisnik_idZaposlenik=korisnik.id where korisnik.Firma_id=? and korisnik.Tip_Korisnika='Zaposlenik' order by placa.iznosMjesecnePlace DESC");
             upit.setInt(1, firma);
             
             ResultSet rs = upit.executeQuery();
            
            
            while (rs.next()){   
                  
                Image fxSlika = null;
                try {
                    BufferedImage bImage = ImageIO.read(rs.getBinaryStream(9));
                    fxSlika = SwingFXUtils.toFXImage(bImage, null);
                } catch (NullPointerException ex) {
                    fxSlika = null;
                } catch (IOException ex) {
                    Logger.getLogger(KorisnikService.class.getName()).log(Level.SEVERE, null, ex);
                }              
                      
                Placa p = new Placa();
                p.setID(rs.getInt(1));
                Korisnik k= new Korisnik();      
                k.setIme(rs.getString(7));
                k.setPrezime(rs.getString(8));
                k.setSlika(fxSlika);
                p.setKorisnikID(k);     
                p.setIznosGodisnjePlace(rs.getFloat(3));
                p.setIznosMjesecnePlace(rs.getFloat(4));

                p.setIznosDnevnePlace(rs.getFloat(5));
                p.setGodisnjiBonus(rs.getFloat(6));

                place.add(p);              
            }
            return place;
           
          
        } catch (SQLException ex) {
            System.out.println("Greška prilikom izvršavanja upita: " + ex.getMessage());
            return null;
        }      
    }   
}
