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
public class Firma {
    private int ID;
    private String Naziv;
    private String Aadresa;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNaziv() {
        return Naziv;
    }

    public void setNaziv(String Naziv) {
        this.Naziv = Naziv;
    }

    public String getAadresa() {
        return Aadresa;
    }

    public void setAadresa(String Aadresa) {
        this.Aadresa = Aadresa;
    }
    
    @Override
        public String toString() {
            return Naziv;
        }
}
