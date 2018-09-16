/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import static evidencija_zaposlenika_sihterica.Model.Baza.DB;
import evidencija_zaposlenika_sihterica.Model.Firma;
import evidencija_zaposlenika_sihterica.Model.Korisnik;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class KorisnikService implements model <Korisnik>{
    
public static final KorisnikService korisnikService=new KorisnikService();
    
    @Override
    public ObservableList<Korisnik> sveIzBaze() {
        try {
            ObservableList <Korisnik> korisnici = FXCollections.observableArrayList();
            ResultSet rs = DB.select("SELECT korisnik.* , firma.Naziv_Firme, firma.Adresa FROM korisnik inner join firma on korisnik.firma_id=firma.id where firma.id=3 and korisnik.Tip_Korisnika='Zaposlenik'");
            while (rs.next()){
                
                Image fxSlika = null;
                try {
                    BufferedImage bImage = ImageIO.read(rs.getBinaryStream(6));
                    fxSlika = SwingFXUtils.toFXImage(bImage, null);
                } catch (NullPointerException ex) {
                    fxSlika = null;
                } 
                Korisnik k= new Korisnik();
                Firma f=new Firma();
                
                k.setId(rs.getInt(1));
                k.setIme(rs.getString(2));
                k.setPrezime(rs.getString(3));
                k.setEmail(rs.getString(4));
                k.setSpol(rs.getString(5));
                k.setSlika(fxSlika);
                k.setDatumRodjenja(rs.getDate(7));
                k.setAdresa(rs.getString(8));
                k.setTelefon(rs.getString(9));
                k.setObrazovanje(rs.getString(10));
                k.setOpis_posla(rs.getString(11));
                k.setTip(rs.getString(14));
                f.setNaziv(rs.getString(16));
                f.setAadresa(rs.getString(17));
                k.setFirma(f);
                
                korisnici.add(k);
            }
            return korisnici;
        } catch (SQLException ex) {
            System.out.println("Greška prilikom izvršavanja upita: " + ex.getMessage());
            return null;
        }
        catch (IOException ex) {
            System.out.println("Greška prilikom dodavanja slike: " + ex.getMessage());
            return null;
        }
    }

    @Override
    public Korisnik spasi(Korisnik korisnik) {      
        try {
            
            ByteArrayOutputStream os;
            os = new ByteArrayOutputStream();
            ImageIO.write(SwingFXUtils.fromFXImage(korisnik.getSlika(), null),"jpg", os); 
            InputStream fis = new ByteArrayInputStream(os.toByteArray());
            
            PreparedStatement upit = DB.prepare ("INSERT INTO korisnik VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?)");
            
            upit.setString(1, korisnik.getIme());
            upit.setString(2, korisnik.getPrezime());
            upit.setString(3, korisnik.getEmail());
            upit.setString(4, korisnik.getSpol());
            upit.setBinaryStream(5, fis);
            upit.setDate(6, korisnik.getDatumRodjenja());
            upit.setString(7, korisnik.getAdresa());
            upit.setString(8, korisnik.getTelefon());
            upit.setString(9, korisnik.getObrazovanje());
            upit.setString(10, korisnik.getOpis_posla());
            upit.setString(11, korisnik.getK_ime());
            upit.setString(12, korisnik.getLozinka());
            upit.setString(13, korisnik.getTip());
            Firma firma= korisnik.getFirma();
            upit.setInt(14, firma.getID());            

            upit.executeUpdate();
            ResultSet rs = upit.getGeneratedKeys();
            if (rs.next()){
                korisnik.setId(rs.getInt(1));
            }
            return korisnik;
            
        } catch (SQLException ex) {
            System.out.println("Greška prilikom izvršavanja upita: " + ex.getMessage());
            return null;
        } catch (FileNotFoundException ex) {
            System.out.println("Greška prilikom čitanja datoteke: " + ex.getMessage());
            return null;
        } catch (IOException ex) {
            System.out.println("Greška prilikom čitanja datoteke: " + ex.getMessage());
            return null;
        }
    
    }

    @Override
    public Korisnik uredi(Korisnik korisnik) {
       // System.out.print(korisnik.getFirma());
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(SwingFXUtils.fromFXImage(korisnik.getSlika(), null), "jpg", os);
            InputStream fis = new ByteArrayInputStream(os.toByteArray());
            
            PreparedStatement upit = DB.prepare ("UPDATE korisnik SET Ime=?,Prezime=?, Email=?, Spol=?, Slika=?,Datum_Rodjenja=?, Adresa=?, Telefon=?, Obrazovanje=?, Opis_Posla=?, Korinsnicko_ime=?, Lozinka=?, Tip_Korisnika=?, Firma_id=? WHERE id=?");
            upit.setString(1, korisnik.getIme());
            upit.setString(2, korisnik.getPrezime());
            upit.setString(3, korisnik.getEmail());
            upit.setString(4, korisnik.getSpol());
            upit.setBinaryStream(5, fis);
            upit.setDate(6, korisnik.getDatumRodjenja());
            upit.setString(7, korisnik.getAdresa());
            upit.setString(8, korisnik.getTelefon());
            upit.setString(9, korisnik.getObrazovanje());
            upit.setString(10, korisnik.getOpis_posla());
            upit.setString(11, korisnik.getK_ime());
            upit.setString(12, korisnik.getLozinka());
            upit.setString(13, korisnik.getTip());    
            
            Firma f= korisnik.getFirma();
            upit.setInt(14,f.getID());
            upit.setInt(15, korisnik.getId());
           
            upit.executeUpdate();
            return korisnik;
                  
        } catch (SQLException ex) {
            System.out.println("Greška prilikom izvršavanja upita: " + ex.getMessage());
            return null;
        } catch (FileNotFoundException ex) {
            System.out.println("Greška prilikom dodavanja slike: " + ex.getMessage());
            return null;
        } catch (IOException ex) {
            System.out.println("Greška prilikom dodavanja slike: " + ex.getMessage());
            return null;
        }        
         
    }

    @Override
   public boolean brisi(Korisnik korisnik) {
        try {
            PreparedStatement upit = DB.prepare ("DELETE FROM korisnik WHERE id=?");
            upit.setInt(1, korisnik.getId());
            upit.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Greška prilikom izvršavanja upita: " + ex.getMessage());
            return false;
        }
    
    }
   
    @Override
    public Korisnik izBazePremaId(int id) {
        try {
            PreparedStatement upit = DB.prepare ("SELECT korisnik.*, firma.Naziv_Firme FROM korisnik inner join firma on korisnik.firma_id=firma.id WHERE korisnik.id=?");
            upit.setInt(1, id);
            ResultSet rs = upit.executeQuery();
            if (rs.next()){
                
                Image fxSlika = null;
                try {
                    BufferedImage bImage = ImageIO.read(rs.getBinaryStream(6));
                    fxSlika = SwingFXUtils.toFXImage(bImage, null);
                } catch (NullPointerException ex) {
                    fxSlika = null;
                } catch (IOException ex) {
                    Logger.getLogger(KorisnikService.class.getName()).log(Level.SEVERE, null, ex);
                }
                Firma f=new Firma();
                f.setID(rs.getInt(15));
                f.setNaziv(rs.getString(16));
                            
                return new Korisnik(
                    rs.getInt(1), 
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    fxSlika,
                    rs.getDate(7),
                    rs.getString(8),
                    rs.getString(9),
                    rs.getString(10),
                    rs.getString(11),
                    rs.getString(12),
                    rs.getString(13),
                    rs.getString(14),                      
                    f
                );
            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.out.println("Greška prilikom izvršavanja upita: " + ex.getMessage());
            return null;
        }
    }
     public ObservableList<Korisnik> dajZaposlenike(int firma) {
        try {
            ObservableList <Korisnik> korisnici = FXCollections.observableArrayList();
            PreparedStatement upit = DB.prepare("SELECT korisnik.* , firma.Naziv_Firme, firma.Adresa FROM korisnik inner join firma on korisnik.firma_id=firma.id where korisnik.Firma_id=? and korisnik.Tip_Korisnika='Zaposlenik'");
            upit.setInt(1, firma);
              
            ResultSet rs = upit.executeQuery();
            while (rs.next()){
                
                Image fxSlika = null;
                try {
                    BufferedImage bImage = ImageIO.read(rs.getBinaryStream(6));
                    fxSlika = SwingFXUtils.toFXImage(bImage, null);
                } catch (NullPointerException ex) {
                    fxSlika = null;
                } 
                Korisnik k= new Korisnik();
                Firma f=new Firma();
    
                k.setId(rs.getInt(1));
                k.setIme(rs.getString(2));
                k.setPrezime(rs.getString(3));
                k.setEmail(rs.getString(4));
                k.setSpol(rs.getString(5));
                k.setSlika(fxSlika);
                k.setDatumRodjenja(rs.getDate(7));
                k.setAdresa(rs.getString(8));
                k.setTelefon(rs.getString(9));
                k.setObrazovanje(rs.getString(10));
                k.setOpis_posla(rs.getString(11));
                k.setTip(rs.getString(12));
                f.setNaziv(rs.getString(16));
                f.setAadresa(rs.getString(17));
                k.setFirma(f);
             
               korisnici.add(k);
            }
            return korisnici;
        } catch (SQLException ex) {
            System.out.println("Greška prilikom izvršavanja upita: " + ex.getMessage());
            return null;
        }
        catch (IOException ex) {
            System.out.println("Greška prilikom dodavanja slike: " + ex.getMessage());
            return null;
        }
    }

}
