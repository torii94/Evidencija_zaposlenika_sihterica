/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import static evidencija_zaposlenika_sihterica.Model.Baza.DB;
import evidencija_zaposlenika_sihterica.Model.Godisnji;
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
public class GodisnjiService implements model <Godisnji>{

    public static final GodisnjiService godisnjiService=new GodisnjiService();

    @Override
    public Godisnji spasi(Godisnji godisnji) {
        try {          
            PreparedStatement upit = DB.prepare ("INSERT INTO godisnji VALUES(null, ?, ?, ?,?,?,?)");
            Korisnik k= godisnji.getKorisnikID();
            upit.setInt(1,k.getId() );
            upit.setBoolean(2,godisnji.isAktivan());
            upit.setInt(3, godisnji.getDaniNaGodisnjem());
            upit.setDate(4, godisnji.getOdlazak());
            upit.setDate(5, godisnji.getDolazak());
            Korisnik zamjena= godisnji.getZamjena();
            upit.setInt(6, zamjena.getId());
            
            upit.executeUpdate();
            ResultSet rs = upit.getGeneratedKeys();
            if (rs.next()){
                godisnji.setID(rs.getInt(1));
            }
            
            return godisnji;
        } catch (SQLException ex) {
            System.out.println("Greška prilikom izvršavanja upita: " + ex.getMessage());
            return null;
        }
        }

    @Override
    public Godisnji uredi(Godisnji godisnji) {
         try {            
            PreparedStatement upitP = DB.prepare ("UPDATE godisnji SET aktivanGodisnji=?,daniNaGodisnjem=?,dolazak=? WHERE korisnik_idZaposlenik=? and aktivanGodisnji=true");
            upitP.setBoolean(1, godisnji.isAktivan());
            upitP.setInt(2, godisnji.getDaniNaGodisnjem());
            upitP.setDate(3, godisnji.getDolazak());
            Korisnik k= godisnji.getKorisnikID();
            upitP.setInt(4, k.getId());
             
            upitP.executeUpdate();
            return godisnji;
        } catch (SQLException ex) {
            System.out.println("Greška prilikom izvršavanja upita: " + ex.getMessage());
            return null;
        } 
    }

    @Override
    public boolean brisi(Godisnji object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Godisnji> sveIzBaze() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
   public Godisnji DajDatumOdlaskaNaGodisnji(int id){
        Godisnji p = new Godisnji();      
        try{
            PreparedStatement upit = DB.prepare ("SELECT odlazak FROM godisnji WHERE korisnik_idZaposlenik=? and aktivanGodisnji=1  ");           
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
    public ObservableList<Godisnji> GetGodisnji(Korisnik korisnik) {
    
        try {
            ObservableList <Godisnji> godisnji = FXCollections.observableArrayList();
            PreparedStatement upit = DB.prepare ("SELECT  daniNaGodisnjem, odlazak, dolazak, zamjena FROM godisnji WHERE korisnik_idZaposlenik=? ");
           
            upit.setInt(1, korisnik.getId());
            ResultSet rs = upit.executeQuery();
            if (rs.next()){
               Godisnji g = new Godisnji();
                g.setDaniNaGodisnjem(rs.getInt(1));
                g.setOdlazak(rs.getDate(2));
                g.setDolazak(rs.getDate(3));
                Korisnik k= new Korisnik();
                k.setId(rs.getInt(4));
                g.setZamjena(k);
                g.setKorisnikID(korisnik);    
                
                godisnji.add(g);
            }
            
            return godisnji; 
                   
        } catch (SQLException ex) {
            System.out.println("Greška prilikom izvršavanja upita: " + ex.getMessage());
            return null;
        }
    }
      

    @Override
    public Godisnji izBazePremaId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public ObservableList<Godisnji> top3Godisnji(int firma) {
        try {
            ObservableList <Godisnji> godisnji = FXCollections.observableArrayList();
            //ResultSet rs = DB.select("SELECT Sum(godisnji.daniNaGodisnjem) as dani, korisnik.Ime, korisnik.Prezime, korisnik.Slika FROM godisnji inner join korisnik on godisnji.korisnik_idZaposlenik=korisnik.id GROUP BY korisnik_idZaposlenik order by dani DESC");
            
             PreparedStatement upit = DB.prepare ("SELECT Sum(godisnji.daniNaGodisnjem) as dani, korisnik.Ime, korisnik.Prezime, korisnik.Slika FROM godisnji inner join korisnik on godisnji.korisnik_idZaposlenik=korisnik.id WHERE korisnik.Firma_id=? and korisnik.Tip_Korisnika='Zaposlenik' GROUP BY korisnik_idZaposlenik order by dani DESC");
             upit.setInt(1, firma);
             
             ResultSet rs = upit.executeQuery();
            
            
            while (rs.next()){ 

                Image fxSlika = null;
                try {
                    BufferedImage bImage = ImageIO.read(rs.getBinaryStream(4));
                    fxSlika = SwingFXUtils.toFXImage(bImage, null);
                } catch (NullPointerException ex) {
                    fxSlika = null;
                } catch (IOException ex) {
                    Logger.getLogger(KorisnikService.class.getName()).log(Level.SEVERE, null, ex);
                }
                                      
                Godisnji g = new Godisnji();
                g.setDaniNaGodisnjem(rs.getInt(1));
                Korisnik k= new Korisnik();      
                k.setIme(rs.getString(2));
                k.setPrezime(rs.getString(3));
                k.setSlika(fxSlika);
                g.setKorisnikID(k);     
            
                 godisnji.add(g);
 
            }
            return godisnji;
           
        } catch (SQLException ex) {
            System.out.println("Greška prilikom izvršavanja upita: " + ex.getMessage());
            return null;
        }  
    }
    
    public ObservableList<Godisnji> SviGodisnjiOdmori(int firma) {
        try {
            ObservableList <Godisnji> _godisnji = FXCollections.observableArrayList();
           // ResultSet rs = DB.select("SELECT godisnji.*, korisnik.Ime, korisnik.Prezime, korisnik.Slika FROM godisnji inner join korisnik on godisnji.korisnik_idZaposlenik=korisnik.id ");
            
            
            PreparedStatement upit = DB.prepare ("SELECT godisnji.*, korisnik.Ime, korisnik.Prezime, korisnik.Slika FROM godisnji inner join korisnik on godisnji.korisnik_idZaposlenik=korisnik.id WHERE korisnik.Firma_id=? and korisnik.Tip_Korisnika='Zaposlenik'  ");
            upit.setInt(1, firma);
             
            ResultSet rs = upit.executeQuery();
            
            
            while (rs.next()){ 

                Image fxSlika = null;
                try {
                    BufferedImage bImage = ImageIO.read(rs.getBinaryStream(10));
                    fxSlika = SwingFXUtils.toFXImage(bImage, null);
                } catch (NullPointerException ex) {
                    fxSlika = null;
                } catch (IOException ex) {
                    Logger.getLogger(KorisnikService.class.getName()).log(Level.SEVERE, null, ex);
                }
                                      
                Godisnji godisnji = new Godisnji();
                godisnji.setID(rs.getInt(1));
                Korisnik k= new Korisnik();      
                k.setIme(rs.getString(8));
                k.setPrezime(rs.getString(9));
                k.setSlika(fxSlika);
                godisnji.setKorisnikID(k);     
                godisnji.setAktivan(rs.getBoolean(3));
                godisnji.setDaniNaGodisnjem(rs.getInt(4));
                godisnji.setOdlazak(rs.getDate(5));
                godisnji.setDolazak(rs.getDate(6));
          
                _godisnji.add(godisnji);

            }
            return _godisnji;
           
        } catch (SQLException ex) {
            System.out.println("Greška prilikom izvršavanja upita: " + ex.getMessage());
            return null;
        }   
    }
      public ObservableList<Godisnji> GetAktivanGodisnji(int firma) {
        try {
             ObservableList <Godisnji> _godisnji = FXCollections.observableArrayList();
            //ResultSet rs = DB.select("SELECT god.*, kor.Ime, kor.Prezime, kor.Slika, zap.Ime, zap.Prezime, zap.Slika FROM godisnji as god inner join korisnik as kor on god.korisnik_idZaposlenik=kor.id inner join korisnik as zap on god.zamjena=zap.id where god.aktivanGodisnji=true ");
            
            PreparedStatement upit = DB.prepare ("SELECT god.*, kor.Ime, kor.Prezime, kor.Slika, zap.Ime, zap.Prezime, zap.Slika FROM godisnji as god inner join korisnik as kor on god.korisnik_idZaposlenik=kor.id inner join korisnik as zap on god.zamjena=zap.id where kor.Firma_id=? and god.aktivanGodisnji=true and kor.Tip_Korisnika='Zaposlenik' ");
            upit.setInt(1, firma);
             
            ResultSet rs = upit.executeQuery();
            
            while (rs.next()){ 
                Image fxSlika = null;
                try {
                    BufferedImage bImage = ImageIO.read(rs.getBinaryStream(10));
                    fxSlika = SwingFXUtils.toFXImage(bImage, null);
                } catch (NullPointerException ex) {
                    fxSlika = null;
                } catch (IOException ex) {
                    Logger.getLogger(KorisnikService.class.getName()).log(Level.SEVERE, null, ex);
                }
                Image fx2Slika = null;
                try {
                    BufferedImage bImage = ImageIO.read(rs.getBinaryStream(13));
                    fx2Slika = SwingFXUtils.toFXImage(bImage, null);
                } catch (NullPointerException ex) {
                    fx2Slika = null;
                } catch (IOException ex) {
                    Logger.getLogger(KorisnikService.class.getName()).log(Level.SEVERE, null, ex);
                }
                                      
                Godisnji g = new Godisnji();
                g.setID(rs.getInt(1));
                Korisnik k= new Korisnik();      
                k.setIme(rs.getString(8));
                k.setPrezime(rs.getString(9));
                k.setSlika(fxSlika);
                g.setKorisnikID(k);     
                g.setAktivan(rs.getBoolean(3));
                g.setDaniNaGodisnjem(rs.getInt(4));
                g.setOdlazak(rs.getDate(5));
                g.setDolazak(rs.getDate(6));
                Korisnik zamjena = new Korisnik();
                zamjena.setIme(rs.getString(11));
                zamjena.setPrezime(rs.getString(12));
                zamjena.setSlika(fx2Slika);
                g.setZamjena(zamjena);
                
                _godisnji.add(g);
             
            }
            
            return _godisnji;
         
        } catch (SQLException ex) {
            System.out.println("Greška prilikom izvršavanja upita: " + ex.getMessage());
            return null;
        }      
    }
       public Godisnji DajZaposlenikBrojDanaGodisnji(int id) {
        Godisnji p = new Godisnji();
        
        try{
            PreparedStatement upit = DB.prepare ("SELECT SUM(daniNaGodisnjem) FROM godisnji WHERE korisnik_idZaposlenik=?  ");           
            upit.setInt(1, id);
           
            ResultSet rs = upit.executeQuery();
            if (rs.next()){                        
                p.setDaniNaGodisnjem(rs.getInt(1));                           
            }
                                 
        } catch (SQLException ex) {
            System.out.println("Greška prilikom izvršavanja upita: " + ex.getMessage());
            return null;
        }
        return p; 
    }
    


     
}
