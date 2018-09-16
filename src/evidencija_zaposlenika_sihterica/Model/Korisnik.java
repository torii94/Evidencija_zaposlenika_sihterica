/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evidencija_zaposlenika_sihterica.Model;

import java.sql.Date;
import javafx.scene.image.Image;

/**
 *
 * @author toni
 */


public class Korisnik {
    private int id;
    private String ime;
    private String prezime;
    private String email;
    private String spol;
    private Image slika;
    private Date datumRodjenja;
    private String adresa;   
    private String telefon;   
    private String Obrazovanje;
    private String Opis_posla;
    private String k_ime;
    private String lozinka;
    private String tip;
    
    private Firma firma;

    public Firma getFirma() {
        return firma;
    }

    public void setFirma(Firma firma) {
        this.firma = firma;
    }

    public Korisnik() {
    }

    public Korisnik(int id, String ime, String prezime, String email, String spol, Image slika, Date datumRodjenja, String adresa, String telefon, String Obrazovanje, String Opis_posla, String k_ime, String lozinka, String tip, Firma firma) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.spol = spol;
        this.slika = slika;
        this.datumRodjenja = datumRodjenja;
        this.adresa = adresa;
        this.telefon = telefon;
        this.Obrazovanje = Obrazovanje;
        this.Opis_posla = Opis_posla;
        this.k_ime = k_ime;
        this.lozinka = lozinka;
        this.tip = tip;
        this.firma = firma;
    }

     

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    
    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public Image getSlika() {
        return slika;
    }

    public void setSlika(Image slika) {
        this.slika = slika;
    }

    public Korisnik(String ime, String prezime){
        this.ime=ime;
        this.prezime=prezime;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSpol() {
        return spol;
    }

    public void setSpol(String spol) {
        this.spol = spol;
    }

    public String getK_ime() {
        return k_ime;
    }

    public void setK_ime(String k_ime) {
        this.k_ime = k_ime;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

   
    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

  
    public String getObrazovanje() {
        return Obrazovanje;
    }

    public void setObrazovanje(String Obrazovanje) {
        this.Obrazovanje = Obrazovanje;
    }

    public String getOpis_posla() {
        return Opis_posla;
    }

    public void setOpis_posla(String Opis_posla) {
        this.Opis_posla = Opis_posla;
    }
       @Override
        public String toString() {
            return ime+" "+prezime;
        }
   
    
}

