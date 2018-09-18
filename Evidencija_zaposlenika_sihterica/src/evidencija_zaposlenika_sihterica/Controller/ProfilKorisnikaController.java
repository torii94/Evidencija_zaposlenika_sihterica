/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evidencija_zaposlenika_sihterica.Controller;

import Services.BolovanjeService;
import Services.GodisnjiService;
import Services.LoginService;
import Services.PlacaService;
import Services.PrisutnostService;
import evidencija_zaposlenika_sihterica.ControlledScreen;
import evidencija_zaposlenika_sihterica.Model.Bolovanje;
import evidencija_zaposlenika_sihterica.Model.Godisnji;
import evidencija_zaposlenika_sihterica.Model.Korisnik;
import evidencija_zaposlenika_sihterica.Model.Placa;
import evidencija_zaposlenika_sihterica.Model.Prisutnost;
import evidencija_zaposlenika_sihterica.ScreensController;
import java.net.URL;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author toni
 */
public class ProfilKorisnikaController implements Initializable,ControlledScreen {

    ScreensController myController;           
    public Korisnik profil =LoginService.logiraniKorisnik();

    @FXML
    private ImageView slika_korisnika;
    @FXML
    private Text imeTE;
    @FXML
    private Text prezimTE;
    @FXML
    private Text telTE;
    @FXML
    private Text adresaTE;
    @FXML
    private Text mailTE;
    @FXML
    private Text obrazovanjeTE;
    @FXML
    private Text opisposlaTE;
    private Text brojdanaGodisnjiTE;
    @FXML
    private Text brojDanaBolovanjeTE;
    @FXML
    private Text placaTE;
    @FXML
    private Text datum_rodTE;
    @FXML
    private StackPane stack_pane;
    @FXML
    private Text spolTE;
    @FXML
    private Text firmaTE;
    @FXML
    private Button spasiBtn;
    @FXML
    private Text brojSatiMjesec;
    @FXML
    private Text godisnjiTE;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PopuniProfil();
    }    

    private void PopuniProfil(){
               
        Prisutnost prisutnost = PrisutnostService.prisutnostService.DajZaposlenikBrojSatiMjesec(profil.getId(),9);
        Godisnji godisnji = GodisnjiService.godisnjiService.DajZaposlenikBrojDanaGodisnji(profil.getId());
        Bolovanje bolovanje=BolovanjeService.bolovanjeService.DajZaposlenikBrojDanaBolovanje(profil.getId());
        Placa placa= PlacaService.placaService.izBazePremaId(profil.getId());

        if(profil.getIme() != null)this.imeTE.setText(profil.getIme());
        if(profil.getPrezime()!= null)this.prezimTE.setText(profil.getPrezime());
        if(profil.getAdresa()!= null)this.adresaTE.setText(profil.getAdresa());          
        if(profil.getDatumRodjenja()!= null)this.datum_rodTE.setText(profil.getDatumRodjenja().toString());
        if(profil.getEmail()!= null)this.mailTE.setText(profil.getEmail());
        if(profil.getObrazovanje()!= null)this.obrazovanjeTE.setText(profil.getObrazovanje());
        if(profil.getOpis_posla()!= null)this.opisposlaTE.setText(profil.getOpis_posla());
        if(profil.getSpol()!= null)this.spolTE.setText(profil.getSpol());           
        if(profil.getTelefon()!= null)this.telTE.setText(profil.getTelefon());            

        this.brojSatiMjesec.setText(String.valueOf(prisutnost.getBrojSatiDan())); 
        this.godisnjiTE.setText(String.valueOf(godisnji.getDaniNaGodisnjem()));
        this.brojDanaBolovanjeTE.setText(String.valueOf(bolovanje.getDaniNaBolovanju()));
        this.placaTE.setText(String.valueOf(placa.getIznosGodisnjePlace())+" $");
       //this.BonusTF.setText(String.valueOf(placa.getGodisnjiBonus())+" $");

        this.firmaTE.setText(profil.getFirma().getNaziv());
        this.slika_korisnika = new ImageView(profil.getSlika() ); 
        this.slika_korisnika.setFitHeight(115);
        this.slika_korisnika.setFitWidth(127);
        this.stack_pane.getChildren().add(slika_korisnika);
    }
    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController=screenPage;
    }

    @FXML
    private void spasiPrisutnost(MouseEvent event) {
        Prisutnost p = new Prisutnost();
        
        LocalDateTime timePoint = LocalDateTime.now();
        LocalTime time=LocalTime.now();       
        int hours = timePoint.getHour();
        int minutes = timePoint.getMinute();
        LocalTime start = LocalTime.of(hours, minutes);
        Time dolazak = java.sql.Time.valueOf(start);
        p.setKorisnikID(profil);
        p.setBrojSatiDan(0);
        p.setDolazak(dolazak);
        p.setAktivan(true);
        p.setDan(timePoint.getDayOfMonth());
        p.setMjesec(timePoint.getMonthValue());
                 
        PrisutnostService.prisutnostService.spasi(p);      
    }

    @FXML
    private void odlazak(MouseEvent event) {
        Prisutnost p = new Prisutnost();
        LocalDateTime timePoint = LocalDateTime.now();
        Prisutnost kor = PrisutnostService.prisutnostService.DajZposlenika(profil.getId(), timePoint.getDayOfMonth(), timePoint.getMonthValue());
        
        int hours = timePoint.getHour();
        int minutes = timePoint.getMinute();
       
        Time dolazak=kor.getDolazak();
        Time odlazak = java.sql.Time.valueOf(LocalTime.of(hours, minutes));
        
        p.setKorisnikID(profil);
        p.setAktivan(false);
        p.setOdlazak(odlazak);
        p.setDan(timePoint.getDayOfMonth());
        
        long difference = odlazak.getTime() - dolazak.getTime(); 
        long diffHours = difference / (60 * 60 * 1000) % 24;
        p.setBrojSatiDan((int) diffHours);

        PrisutnostService.prisutnostService.uredi(p);   
    }
    
}
