/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evidencija_zaposlenika_sihterica.Model;

/**
 *
 * @author toni
 */
public class Placa {
    private int ID;
    private Korisnik KorisnikID;
    private float IznosGodisnjePlace;
    private float IznosMjesecnePlace;
    private float IznosDnevnePlace;
    private float GodisnjiBonus;

    public Placa(int ID, Korisnik KorisnikID, float IznosGodisnjePlace, float IznosMjesecnePlace, float IznosDnevnePlace, float GodisnjiBonus) {
        this.ID = ID;
        this.KorisnikID = KorisnikID;
        this.IznosGodisnjePlace = IznosGodisnjePlace;
        this.IznosMjesecnePlace = IznosMjesecnePlace;
        this.IznosDnevnePlace = IznosDnevnePlace;
        this.GodisnjiBonus = GodisnjiBonus;
    }

   

    
    
    public Placa(){};

    

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Korisnik getKorisnikID() {
        return KorisnikID;
    }

    public void setKorisnikID(Korisnik KorisnikID) {
        this.KorisnikID = KorisnikID;
    }

   

    public float getIznosGodisnjePlace() {
        return IznosGodisnjePlace;
    }

    public void setIznosGodisnjePlace(float IznosGodisnjePlace) {
        this.IznosGodisnjePlace = IznosGodisnjePlace;
    }

    public float getIznosMjesecnePlace() {
        return IznosMjesecnePlace;
    }

    public void setIznosMjesecnePlace(float IznosMjesecnePlace) {
        this.IznosMjesecnePlace = IznosMjesecnePlace;
    }

    public float getIznosDnevnePlace() {
        return IznosDnevnePlace;
    }

    public void setIznosDnevnePlace(float IznosDnevnePlace) {
        this.IznosDnevnePlace = IznosDnevnePlace;
    }

    public float getGodisnjiBonus() {
        return GodisnjiBonus;
    }

    public void setGodisnjiBonus(float GodisnjiBonus) {
        this.GodisnjiBonus = GodisnjiBonus;
    }
    
    
}
