/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evidencija_zaposlenika_sihterica.Model;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;

/**
 *
 * @author toni
 */
public class Prisutnost {
    private int ID;
    private Korisnik KorisnikID;
    private boolean Aktivan;
    private int BrojSatiDan;
  
    private Time Dolazak;
    private Time Odlazak;
    private int Dan;
    private int Mjesec;

    public Prisutnost(int ID, Korisnik KorisnikID, boolean Aktivan, int BrojSatiDan, Time Dolazak, Time Odlazak, int Dan, int Mjesec) {
        this.ID = ID;
        this.KorisnikID = KorisnikID;
        this.Aktivan = Aktivan;
        this.BrojSatiDan = BrojSatiDan;
        this.Dolazak = Dolazak;
        this.Odlazak = Odlazak;
        this.Dan = Dan;
        this.Mjesec = Mjesec;
    }

    public Time getDolazak() {
        return Dolazak;
    }

    public void setDolazak(Time Dolazak) {
        this.Dolazak = Dolazak;
    }

    public Time getOdlazak() {
        return Odlazak;
    }

    public void setOdlazak(Time Odlazak) {
        this.Odlazak = Odlazak;
    }

  

    
    public Prisutnost(){}

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

   
    public boolean isAktivan() {
        return Aktivan;
    }

    public void setAktivan(boolean Aktivan) {
        this.Aktivan = Aktivan;
    }

    public int getBrojSatiDan() {
        return BrojSatiDan;
    }

    public void setBrojSatiDan(int BrojSatiDan) {
        this.BrojSatiDan = BrojSatiDan;
    }


  

    public int getDan() {
        return Dan;
    }

    public void setDan(int Dan) {
        this.Dan = Dan;
    }

    public int getMjesec() {
        return Mjesec;
    }

    public void setMjesec(int Mjesec) {
        this.Mjesec = Mjesec;
    }

    public Korisnik getKorisnikID() {
        return KorisnikID;
    }

    public void setKorisnikID(Korisnik KorisnikID) {
        this.KorisnikID = KorisnikID;
    }

   
    
    
}
