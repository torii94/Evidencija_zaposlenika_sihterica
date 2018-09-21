/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evidencija_zaposlenika_sihterica.Controller;

import Services.LoginService;
import evidencija_zaposlenika_sihterica.ControlledScreen;
import evidencija_zaposlenika_sihterica.Evidencija_zaposlenika_sihterica;
import evidencija_zaposlenika_sihterica.Model.Korisnik;
import evidencija_zaposlenika_sihterica.ScreensController;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author toni
 */
public class LoginController implements Initializable ,ControlledScreen{
    ScreensController myController;
    ScreensController mainContainer = new ScreensController();

    public Korisnik kor;
    @FXML
    private TextField ime;
    @FXML
    private PasswordField lozinka;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Prijava(MouseEvent event) {
       
        String kor_ime = "kwellesley1h";
        String password = "KITRaVCg8VV";
////        
//        String kor_ime = "kkopke5";
//        String password = "caMUmwAv";

//
//        String kor_ime = "bspitaro1u";
//        String password = "FtrbIcCnsvLa";

//      String kor_ime = ime.getText();
//      String password = lozinka.getText();

        if (LoginService.login(kor_ime, password)){
            if(LoginService.logiraniKorisnik().getTip().equals("Vlasnik")){
                 myController.loadScreen(Evidencija_zaposlenika_sihterica.screen1ID, Evidencija_zaposlenika_sihterica.screen1File);
                 myController.setScreen(Evidencija_zaposlenika_sihterica.screen1ID);       
            }
            else if(LoginService.logiraniKorisnik().getTip().equals("Zaposlenik")){
                 myController.loadScreen(Evidencija_zaposlenika_sihterica.screen9ID, Evidencija_zaposlenika_sihterica.screen9File);
                 myController.setScreen(Evidencija_zaposlenika_sihterica.screen9ID);
            }          
        }else {
                 Alert greska = new Alert(Alert.AlertType.ERROR);
                 
                greska.setTitle("Nije uspijelo!");
                greska.setHeaderText("Greska:");
                greska.setContentText("Pogresni korisnicki podaci!");
                greska.show();

            }                    
    }
    
    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;  
    }

    
}
