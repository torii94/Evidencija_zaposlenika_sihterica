/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import static evidencija_zaposlenika_sihterica.Model.Baza.DB;
import evidencija_zaposlenika_sihterica.Model.Korisnik;
import evidencija_zaposlenika_sihterica.Model.Prisutnost;
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
public class PrisutnostService implements model <Prisutnost>{

    public static final PrisutnostService prisutnostService=new PrisutnostService();

   
    @Override
    public Prisutnost spasi(Prisutnost prisutnost) {
        try {           
            PreparedStatement upit = DB.prepare ("INSERT INTO prisutnost VALUES(null, ?, ?, ?,?,?,?,?) ");
            Korisnik k= prisutnost.getKorisnikID();
            
            upit.setInt(1,k.getId() );
            upit.setBoolean(2,prisutnost.isAktivan());
            upit.setInt(3, prisutnost.getBrojSatiDan());
          
            upit.setTime(4, prisutnost.getDolazak());
            upit.setTime(5, prisutnost.getOdlazak());
            upit.setInt(6, prisutnost.getDan());
            upit.setInt(7, prisutnost.getMjesec());
            upit.executeUpdate();
            ResultSet rs = upit.getGeneratedKeys();
            if (rs.next()){
                prisutnost.setID(rs.getInt(1));
            }
            return prisutnost;
        } catch (SQLException ex) {
            System.out.println("Greška prilikom izvršavanja upita: " + ex.getMessage());
            return null;
        }    
    }

    @Override  
    public Prisutnost uredi(Prisutnost prisutnost) {
            try {         
            PreparedStatement upitP = DB.prepare ("UPDATE prisutnost SET prisutan=?,broj_radnih_sati_Dan=?,odlazak=? WHERE dan=? and korisnik_idZaposlenik=?");
            upitP.setBoolean(1, prisutnost.isAktivan());
            upitP.setInt(2, prisutnost.getBrojSatiDan());
            upitP.setTime(3, prisutnost.getOdlazak());
            upitP.setInt(4, prisutnost.getDan());
            Korisnik k= prisutnost.getKorisnikID();
            upitP.setInt(5, k.getId());
            upitP.executeUpdate();
            
            return prisutnost;
        } catch (SQLException ex) {
            System.out.println("Greška prilikom izvršavanja upita: " + ex.getMessage());
            return null;     
        } 
    }

    @Override
    public boolean brisi(Prisutnost object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Prisutnost> sveIzBaze() {
        try {
            ObservableList <Prisutnost> prisutnosti = FXCollections.observableArrayList();
            ResultSet rs=DB.select("SELECT korisnik.id,korisnik.Ime,korisnik.Prezime, korisnik.Slika, prisutnost.broj_radnih_sati_Dan FROM prisutnost INNER JOIN korisnik on prisutnost.korisnik_idZaposlenik=korisnik.id group by prisutnost.korisnik_idZaposlenik ");
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
                                      
                Prisutnost p = new Prisutnost();
                Korisnik k= new Korisnik();
                k.setId(rs.getInt(1));
                k.setIme(rs.getString(2));
                k.setPrezime(rs.getString(3));           
                k.setSlika(fxSlika);
                p.setKorisnikID(k);     
                p.setBrojSatiDan(rs.getInt(5));

                prisutnosti.add(p);
            }
            return prisutnosti;
           
        } catch (SQLException ex) {
            System.out.println("Greška prilikom izvršavanja upita: " + ex.getMessage());
            return null;
        }
    }
    
    public List<Prisutnost> CijelaSihtarica(int firma) {
        try {
            ObservableList <Prisutnost> prisutnosti = FXCollections.observableArrayList();
            //ResultSet rs=DB.select("SELECT korisnik.id,korisnik.Ime,korisnik.Prezime, korisnik.Slika, prisutnost.broj_radnih_sati_Dan FROM prisutnost INNER JOIN korisnik on prisutnost.korisnik_idZaposlenik=korisnik.id group by prisutnost.korisnik_idZaposlenik ");
            
             PreparedStatement upit = DB.prepare ("SELECT korisnik.id,korisnik.Ime,korisnik.Prezime, korisnik.Slika, prisutnost.broj_radnih_sati_Dan FROM prisutnost INNER JOIN korisnik on prisutnost.korisnik_idZaposlenik=korisnik.id WHERE korisnik.Firma_id=? and korisnik.Tip_Korisnika='Zaposlenik'  group by prisutnost.korisnik_idZaposlenik");
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
                                      
                Prisutnost p = new Prisutnost();
                Korisnik k= new Korisnik();
                k.setId(rs.getInt(1));
                k.setIme(rs.getString(2));
                k.setPrezime(rs.getString(3));           
                k.setSlika(fxSlika);
                p.setKorisnikID(k);     
                p.setBrojSatiDan(rs.getInt(5));

                prisutnosti.add(p);
            }
            return prisutnosti;
           
        } catch (SQLException ex) {
            System.out.println("Greška prilikom izvršavanja upita: " + ex.getMessage());
            return null;
        }
    }

    
    public Prisutnost DajZposlenika(int id, int dan, int mjesec) {
        Prisutnost p = new Prisutnost();
        try{
            PreparedStatement upit = DB.prepare ("SELECT dolazak,dan FROM prisutnost WHERE korisnik_idZaposlenik=? and dan=? and mjesec=? ");
           
            upit.setInt(1, id);
            upit.setInt(2, dan);
            upit.setInt(3, mjesec);

            ResultSet rs = upit.executeQuery();
            if (rs.next()){     
                p.setDolazak(rs.getTime(1));
                p.setDan(rs.getInt(2));
            }
                                 
        } catch (SQLException ex) {
            System.out.println("Greška prilikom izvršavanja upita: " + ex.getMessage());
            return null;
        }
        return p;
   
    }
      
    public ObservableList<Prisutnost> top3BrojSati(int firma) {
        try {
            ObservableList <Prisutnost> prisutnosti = FXCollections.observableArrayList();
           // ResultSet rs=DB.select("SELECT korisnik.Ime,korisnik.Prezime, korisnik.Slika, SUM(prisutnost.broj_radnih_sati_Dan) as sati FROM prisutnost INNER JOIN korisnik on prisutnost.korisnik_idZaposlenik=korisnik.id GROUP BY prisutnost.korisnik_idZaposlenik order by sati desc ");
            
             PreparedStatement upit = DB.prepare ("SELECT korisnik.Ime,korisnik.Prezime, korisnik.Slika, SUM(prisutnost.broj_radnih_sati_Dan) as sati FROM prisutnost INNER JOIN korisnik on prisutnost.korisnik_idZaposlenik=korisnik.id Where korisnik.Firma_id=? and korisnik.Tip_Korisnika='Zaposlenik' GROUP BY prisutnost.korisnik_idZaposlenik order by sati desc");
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
                                      
                Prisutnost p = new Prisutnost();
                Korisnik k= new Korisnik();      
                k.setIme(rs.getString(1));
                k.setPrezime(rs.getString(2));
                k.setSlika(fxSlika);
                p.setKorisnikID(k);     
                p.setBrojSatiDan(rs.getInt(4));
                
                prisutnosti.add(p);
            }
            return prisutnosti;
           
        } catch (SQLException ex) {
            System.out.println("Greška prilikom izvršavanja upita: " + ex.getMessage());
            return null;
        }
       
    }
    public int GetDay(int k, int dan) {
        int broj=0;
         
        try {
            PreparedStatement upit = DB.prepare ("SELECT broj_radnih_sati_Dan FROM prisutnost WHERE korisnik_idZaposlenik=? and dan=?");
            upit.setInt(1, k);
            upit.setInt(2, dan);
           
            ResultSet rs = upit.executeQuery();
            if (rs.next()){              
               broj= rs.getInt(1);               
        }} catch (SQLException ex) {
            System.out.println("Greška prilikom izvršavanja upita: " + ex.getMessage());
            return 0;
        }
        return broj;
    }
    
    public List<Prisutnost> GetOnlineZaposlenici(int firma) {

        try {
            ObservableList <Prisutnost> prisutnosti = FXCollections.observableArrayList();            
            //ResultSet rs=DB.select("SELECT korisnik.id,korisnik.Ime,korisnik.Prezime, korisnik.Slika, prisutnost.broj_radnih_sati_Dan, prisutnost.dolazak FROM prisutnost INNER JOIN korisnik on prisutnost.korisnik_idZaposlenik=korisnik.id  where prisutnost.prisutan=1 and korisnik.firma_id=? ");
             PreparedStatement upit = DB.prepare ("SELECT korisnik.id,korisnik.Ime,korisnik.Prezime, korisnik.Slika, prisutnost.broj_radnih_sati_Dan, prisutnost.dolazak FROM prisutnost INNER JOIN korisnik on prisutnost.korisnik_idZaposlenik=korisnik.id  where prisutnost.prisutan=1 and korisnik.firma_id=?");
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
                                      
                Prisutnost p = new Prisutnost();
                Korisnik k= new Korisnik();
                k.setId(rs.getInt(1));
                k.setIme(rs.getString(2));
                k.setPrezime(rs.getString(3));           
                k.setSlika(fxSlika);
                p.setKorisnikID(k);     
                p.setBrojSatiDan(rs.getInt(5));
                p.setDolazak(rs.getTime(6));
         
                prisutnosti.add(p);
            }
            return prisutnosti;
           
        } catch (SQLException ex) {
            System.out.println("Greška prilikom izvršavanja upita: " + ex.getMessage());
            return null;
        }
    }

    @Override
    public Prisutnost izBazePremaId(int id) {
        Prisutnost p = new Prisutnost();
        try{
            PreparedStatement upit = DB.prepare ("SELECT korisnik.id,korisnik.Ime,korisnik.Prezime FROM prisutnost INNER JOIN korisnik on prisutnost.korisnik_idZaposlenik=korisnik.id WHERE korisnik_idZaposlenik=? ");           
            upit.setInt(1, id);
            ResultSet rs = upit.executeQuery();
            if (rs.next()){
                Korisnik k= new Korisnik();
                k.setId(rs.getInt(1));
                k.setIme(rs.getString(2));
                k.setPrezime(rs.getString(3));           
                p.setKorisnikID(k);     
            }
                                 
        } catch (SQLException ex) {
            System.out.println("Greška prilikom izvršavanja upita: " + ex.getMessage());
            return null;
        }
        return p;    
    
    }
      
    public Prisutnost DajZaposlenikBrojSatiMjesec(int id, int mjesec) {
        Prisutnost p = new Prisutnost();
        
        try{
            PreparedStatement upit = DB.prepare ("SELECT SUM(broj_radnih_sati_Dan) FROM prisutnost WHERE korisnik_idZaposlenik=? and mjesec=? ");          
            upit.setInt(1, id);
            upit.setInt(2, mjesec);
           

            ResultSet rs = upit.executeQuery();
            if (rs.next()){                        
                p.setBrojSatiDan(rs.getInt(1));               
            }
                                 
        } catch (SQLException ex) {
            System.out.println("Greška prilikom izvršavanja upita: " + ex.getMessage());
            return null;
        }
        return p;  
    }
    
}
