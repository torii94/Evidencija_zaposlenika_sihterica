/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import static evidencija_zaposlenika_sihterica.Model.Baza.DB;
import evidencija_zaposlenika_sihterica.Model.Firma;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import model.model;
import evidencija_zaposlenika_sihterica.Interfaces.model;


/**
 *
 * @author toni
 */
public class FirmaService implements model <Firma> { 
    
    public static final FirmaService firmaService=new FirmaService();


    @Override
    public Firma spasi(Firma object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Firma uredi(Firma object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean brisi(Firma object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Firma> sveIzBaze() {
         try {
            ObservableList <Firma> firme = FXCollections.observableArrayList();
            ResultSet rs = DB.select("SELECT * from firma");
            while (rs.next()){                
                Firma f=new Firma();                
                f.setID(rs.getInt(1));
                f.setNaziv(rs.getString(2));
                f.setAadresa(rs.getString(3));
                
                firme.add(f);
            }
            
            return firme;
        } catch (SQLException ex) {
            System.out.println("Greška prilikom izvršavanja upita: " + ex.getMessage());
            return null;
        }
    }

    @Override
    public Firma izBazePremaId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
