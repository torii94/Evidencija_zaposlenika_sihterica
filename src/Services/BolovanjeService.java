/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import static evidencija_zaposlenika_sihterica.Model.Baza.DB;
import evidencija_zaposlenika_sihterica.Model.Bolovanje;
import evidencija_zaposlenika_sihterica.Model.Korisnik;
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
public class BolovanjeService implements model <Bolovanje> {
    public static final BolovanjeService bolovanjeService=new BolovanjeService();

    @Override
        public Bolovanje spasi(Bolovanje bolovanje) {
        try {          
            PreparedStatement upit = DB.prepare ("INSERT INTO bolovanje VALUES(null, ?, ?, ?,?,?)");
            Korisnik k= bolovanje.getKorisnikID();
            upit.setInt(1,k.getId() );
            upit.setBoolean(2,bolovanje.isAktivan());
            upit.setInt(3, bolovanje.getDaniNaBolovanju());
            upit.setDate(4, bolovanje.getOdlazak());
            upit.setDate(5, bolovanje.getDolazak());
         
            upit.executeUpdate();

            ResultSet rs = upit.getGeneratedKeys();
            if (rs.next()){
                bolovanje.setID(rs.getInt(1));
            }
            
            return bolovanje;
            
        } catch (SQLException ex) {
            System.out.println("Greška prilikom izvršavanja upita: " + ex.getMessage());
            return null;
        }  
    }

    @Override    
    public Bolovanje uredi(Bolovanje bolovanje) {
        try {            
            PreparedStatement upitP = DB.prepare ("UPDATE bolovanje SET aktivnoBolovanje=?,daniNaBolovanju=?,dolazak=? WHERE korisnik_idZaposlenik=? and aktivnoBolovanje=true");
            upitP.setBoolean(1, bolovanje.isAktivan());
            upitP.setInt(2, bolovanje.getDaniNaBolovanju());
            upitP.setDate(3, bolovanje.getDolazak());
            Korisnik k= bolovanje.getKorisnikID();
            upitP.setInt(4, k.getId());
             
            upitP.executeUpdate();
            return bolovanje;
        } catch (SQLException ex) {
            System.out.println("Greška prilikom izvršavanja upita: " + ex.getMessage());
            return null;       
        } 
    }

    @Override
    public boolean brisi(Bolovanje object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Bolovanje> sveIzBaze() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Bolovanje izBazePremaId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     public ObservableList<Bolovanje> top3Bolovanje(int firma) {
        try {
            ObservableList <Bolovanje> bolovanje = FXCollections.observableArrayList();
            //ResultSet rs = DB.select("SELECT korisnik.Ime,korisnik.Prezime, korisnik.Slika, SUM(bolovanje.daniNaBolovanju) as dani FROM bolovanje INNER JOIN korisnik on bolovanje.korisnik_idZaposlenik=korisnik.id GROUP BY korisnik_idZaposlenik ORDER BY dani DESC");
           
             PreparedStatement upit = DB.prepare ("SELECT korisnik.Ime,korisnik.Prezime, korisnik.Slika, SUM(bolovanje.daniNaBolovanju) as dani FROM bolovanje INNER JOIN korisnik on bolovanje.korisnik_idZaposlenik=korisnik.id WHERE Firma_id=? and korisnik.Tip_Korisnika='Zaposlenik'  GROUP BY korisnik_idZaposlenik ORDER BY dani DESC");
             upit.setInt(1, firma);
             
             ResultSet rs = upit.executeQuery();
            
            
            while (rs.next()){ 

                Image fxSlika = null;
                try {
                    BufferedImage bImage = ImageIO.read(rs.getBinaryStream(3));
                    fxSlika = SwingFXUtils.toFXImage(bImage, null);
                } catch (NullPointerException ex) {
                    fxSlika = null;
                } catch (IOException ex) {
                    Logger.getLogger(KorisnikService.class.getName()).log(Level.SEVERE, null, ex);
                }

                Bolovanje b = new Bolovanje();
                b.setDaniNaBolovanju(rs.getInt(4));
                Korisnik k= new Korisnik();      
                k.setIme(rs.getString(1));
                k.setPrezime(rs.getString(2));
                k.setSlika(fxSlika);
                b.setKorisnikID(k);     

                bolovanje.add(b);    
            }

            return bolovanje;
           
        } catch (SQLException ex) {
            System.out.println("Greška prilikom izvršavanja upita: " + ex.getMessage());
            return null;
        }       
    }
    
//     public Bolovanje GetBolovanje(Korisnik korisnik){
//          
//         Bolovanje b = new Bolovanje();
//         try{
//          
//         PreparedStatement upit = DB.prepare ("SELECT  daniNaBolovanju, odlazak, dolazak,aktivnoBolovanje FROM bolovanje WHERE korisnik_idZaposlenik=? ");
//
//            upit.setInt(1, korisnik.getId());
//            ResultSet rs = upit.executeQuery();
//                if (rs.next()){
//                  
//                    b.setDaniNaBolovanju(rs.getInt(1));
//                    b.setOdlazak(rs.getDate(2));
//                    b.setDolazak(rs.getDate(3)); 
//                    b.setAktivan(rs.getBoolean(4));
//                    b.setKorisnikID(korisnik);     
//                                                      
//                }                                
//
//        } catch (SQLException ex) {
//            System.out.println("Greška prilikom izvršavanja upita: " + ex.getMessage());
//            return null;
//        }
//          return b; 
//     }
     
      public Bolovanje DajDatumOdlaskaNaBolovanje(int id){
        Bolovanje p = new Bolovanje();      
        try{
            PreparedStatement upit = DB.prepare ("SELECT odlazak FROM bolovanje WHERE korisnik_idZaposlenik=? and aktivnoBolovanje=1  ");           
            upit.setInt(1, id);
           
            ResultSet rs = upit.executeQuery();
            if (rs.next()){                        
               p.setOdlazak(rs.getDate(1));                           
            }
                                 
        } catch (SQLException ex) {
            System.out.println("Greška prilikom izvršavanja upita: " + ex.getMessage());
            return null;
        }
        return p;   
   }

     
    public ObservableList<Bolovanje> GetAllBolovanje(int firma) {
        try {
            ObservableList <Bolovanje> bolovanja = FXCollections.observableArrayList();
            //ResultSet rs = DB.select("SELECT bolovanje.*, korisnik.Ime, korisnik.Prezime, korisnik.Slika FROM bolovanje inner join korisnik on bolovanje.korisnik_idZaposlenik=korisnik.id ");
            
            PreparedStatement upit = DB.prepare ("SELECT bolovanje.*, korisnik.Ime, korisnik.Prezime, korisnik.Slika FROM bolovanje inner join korisnik on bolovanje.korisnik_idZaposlenik=korisnik.id where korisnik.Firma_id=? and korisnik.Tip_Korisnika='Zaposlenik'  ");
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

                Bolovanje b = new Bolovanje();
                b.setID(rs.getInt(1));
                Korisnik k= new Korisnik();      
                k.setIme(rs.getString(7));
                k.setPrezime(rs.getString(8));
                k.setSlika(fxSlika);
                b.setKorisnikID(k);     
                b.setAktivan(rs.getBoolean(3));
                b.setDaniNaBolovanju(rs.getInt(4));
                b.setOdlazak(rs.getDate(5));
                b.setDolazak(rs.getDate(6));

                bolovanja.add(b);              
            }            
            
            return bolovanja;

        } catch (SQLException ex) {
            System.out.println("Greška prilikom izvršavanja upita: " + ex.getMessage());
            return null;
        }     
    }
     
    public ObservableList<Bolovanje> GetAktivnoBolovanje(int firma) {
        try {
        ObservableList <Bolovanje> bolovanja = FXCollections.observableArrayList();
        //ResultSet rs = DB.select("SELECT bolovanje.*, korisnik.Ime, korisnik.Prezime, korisnik.Slika FROM bolovanje inner join korisnik on bolovanje.korisnik_idZaposlenik=korisnik.id where bolovanje.aktivnoBolovanje=true ");
        
         
            PreparedStatement upit = DB.prepare ("SELECT bolovanje.*, korisnik.Ime, korisnik.Prezime, korisnik.Slika FROM bolovanje inner join korisnik on bolovanje.korisnik_idZaposlenik=korisnik.id where korisnik.Firma_id=? and bolovanje.aktivnoBolovanje=true and korisnik.Tip_Korisnika='Zaposlenik' ");
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
                                      
            Bolovanje b = new Bolovanje();
            b.setID(rs.getInt(1));
            Korisnik k= new Korisnik();      
            k.setIme(rs.getString(7));
            k.setPrezime(rs.getString(8));
            k.setSlika(fxSlika);
            b.setKorisnikID(k);     
            b.setAktivan(rs.getBoolean(3));
            b.setDaniNaBolovanju(rs.getInt(4));
            b.setOdlazak(rs.getDate(5));
            b.setDolazak(rs.getDate(6));
 
            bolovanja.add(b);              
            }
        
        return bolovanja;
           
        } catch (SQLException ex) {
            System.out.println("Greška prilikom izvršavanja upita: " + ex.getMessage());
            return null;
        }      
    }
     
      public Bolovanje DajZaposlenikBrojDanaBolovanje(int id) {
        Bolovanje p = new Bolovanje();        
        try{
            PreparedStatement upit = DB.prepare ("SELECT SUM(daniNaBolovanju) FROM bolovanje WHERE korisnik_idZaposlenik=?  ");           
            upit.setInt(1, id);

            ResultSet rs = upit.executeQuery();
            if (rs.next()){                        
                p.setDaniNaBolovanju(rs.getInt(1));                           
            }

        } catch (SQLException ex) {
            System.out.println("Greška prilikom izvršavanja upita: " + ex.getMessage());
            return null;
        }
        return p;   
    }
}
