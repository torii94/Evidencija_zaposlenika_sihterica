/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evidencija_zaposlenika_sihterica.Model;

import java.sql.Date;

/**
 *
 * @author toni
 */
public class Bolovanje extends ImePrezime  {
    private int ID;
    private Korisnik KorisnikID;
    private boolean Aktivan;
    private int DaniNaBolovanju;
    private Date Odlazak;
    private Date Dolazak;

    public Bolovanje(int ID, Korisnik KorisnikID, boolean Aktivan, int DaniNaBolovanju, Date Odlazak, Date Dolazak) {
        this.ID = ID;
        this.KorisnikID = KorisnikID;
        this.Aktivan = Aktivan;
        this.DaniNaBolovanju = DaniNaBolovanju;
        this.Odlazak = Odlazak;
        this.Dolazak = Dolazak;
    }

  
    
    public Bolovanje(){};

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

    public int getDaniNaBolovanju() {
        return DaniNaBolovanju;
    }

    public void setDaniNaBolovanju(int DaniNaBolovanju) {
        this.DaniNaBolovanju = DaniNaBolovanju;
    }

    public Date getOdlazak() {
        return Odlazak;
    }

    public void setOdlazak(Date Odlazak) {
        this.Odlazak = Odlazak;
    }

    public Date getDolazak() {
        return Dolazak;
    }

    public void setDolazak(Date Dolazak) {
        this.Dolazak = Dolazak;
    }

    public Korisnik getKorisnikID() {
        return KorisnikID;
    }

    public void setKorisnikID(Korisnik KorisnikID) {
        this.KorisnikID = KorisnikID;
    }

    @Override
    public String ImeIPrezime() {
        return KorisnikID.ImeIPrezime();
    }
    
    
    
}
