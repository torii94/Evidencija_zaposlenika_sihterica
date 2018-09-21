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
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import evidencija_zaposlenika_sihterica.ControlledScreen;
import evidencija_zaposlenika_sihterica.Evidencija_zaposlenika_sihterica;
import evidencija_zaposlenika_sihterica.Model.Bolovanje;
import evidencija_zaposlenika_sihterica.Model.Godisnji;
import evidencija_zaposlenika_sihterica.Model.Placa;
import evidencija_zaposlenika_sihterica.Model.Prisutnost;
import evidencija_zaposlenika_sihterica.ScreensController;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author toni
 */
public class StatistikaController implements Initializable,ControlledScreen {

    ScreensController myController;

    @FXML
    private Pane statistika_pane;
    @FXML
    private ImageView slikaSatiTOP1;
    @FXML
    private Label imePrezimeBrojSatiTOP1;
    @FXML
    private Label brojSatiTOP1;
    @FXML
    private ImageView slikaSatiTOP2;
    @FXML
    private Label imePrezimeBrojSatiTOP2;
    @FXML
    private Label brojSatiTOP2;
    @FXML
    private ImageView slikaSatiTOP3;
    @FXML
    private Label imePrezimeBrojSatiTOP3;
    @FXML
    private Label brojSatiTOP3;
    @FXML
    private ImageView slikaSatiLOS1;
    @FXML
    private Label imePrezimeBrojSatiLOS1;
    @FXML
    private Label brojSatiLOS1;
    @FXML
    private ImageView slikaSatiLOS2;
    @FXML
    private Label imePrezimeBrojSatiLOS2;
    @FXML
    private Label brojSatiLOS2;
    @FXML
    private ImageView slikaSatiLOS3;
    @FXML
    private Label imePrezimeBrojSatiLOS3;
    @FXML
    private Label brojSatiLOS3;
    @FXML
    private ImageView slikaPlacaTOP1;
    @FXML
    private Label imePrezimeIznosPlaceTOP1;
    @FXML
    private Label iznosPlaceTOP1;
    @FXML
    private ImageView slikaPlacaTOP2;
    @FXML
    private Label imePrezimeIznosPlaceTOP2;
    @FXML
    private Label iznosPlaceTOP2;
    @FXML
    private ImageView slikaPlacaTOP3;
    @FXML
    private Label imePrezimeIznosPlaceTOP3;
    @FXML
    private Label iznosPlaceTOP3;
    @FXML
    private ImageView slikaPlacaLOS1;
    @FXML
    private Label imePrezimeIznosPlaceLOS1;
    @FXML
    private Label iznosPlaceLOS1;
    @FXML
    private ImageView slikaPlacaLOS2;
    @FXML
    private Label imePrezimeIznosPlaceLOS2;
    @FXML
    private Label iznosPlaceLOS2;
    @FXML
    private ImageView slikaPlacaLOS3;
    @FXML
    private Label imePrezimeIznosPlaceLOS3;
    @FXML
    private Label iznosPlaceLOS3;
    @FXML
    private ImageView slikaGodisnjiTOP1;
    @FXML
    private Label imePrezimeGodisnjiTOP1;
    @FXML
    private Label daniGodisnjiTOP1;
    @FXML
    private ImageView slikaGodisnjiTOP2;
    @FXML
    private Label imePrezimeGodisnjiTOP2;
    @FXML
    private Label daniGodisnjiTOP2;
    @FXML
    private ImageView slikaGodisnjiTOP3;
    @FXML
    private Label imePrezimeGodisnjiTOP3;
    @FXML
    private Label daniGodisnjiTOP3;
    @FXML
    private ImageView slikaBolovanjeTOP1;
    @FXML
    private Label imePrezimeBolovanjeTOP1;
    @FXML
    private Label daniBolovanjeTOP1;
    @FXML
    private ImageView slikaBolovanjeTOP2;
    @FXML
    private Label imePrezimeBolovanjeTOP2;
    @FXML
    private Label daniBolovanjeTOP2;
    @FXML
    private ImageView slikaBolovanjeTOP3;
    @FXML
    private Label imePrezimeBolovanjeTOP3;
    @FXML
    private Label daniBolovanjeTOP3;
    @FXML
    private Label prosjekSatiLBL;
    @FXML
    private Label prosjekPlacaLBL;
    @FXML
    private FontAwesomeIconView otvoriGodisnjuBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        PopuniStatistiku();
    }    

    private void PopuniStatistiku(){
        
        int day = LocalDateTime.now().getDayOfMonth();
        int month = LocalDateTime.now().getMonthValue();
        LocalTime time = LocalTime.now();
        int firma=LoginService.logiraniKorisnik().getFirma().getID();
                     
        ObservableList<Prisutnost> prisutnosti= PrisutnostService.prisutnostService.top3BrojSati(firma);
                                                          
            imePrezimeBrojSatiTOP1.setText(prisutnosti.get(0).ImeIPrezime());
            slikaSatiTOP1.setImage(prisutnosti.get(0).getKorisnikID().getSlika());
            brojSatiTOP1.setText(String.valueOf(prisutnosti.get(0).getBrojSatiDan())+" "+"h");

            imePrezimeBrojSatiTOP2.setText(prisutnosti.get(1).ImeIPrezime());
            slikaSatiTOP2.setImage(prisutnosti.get(1).getKorisnikID().getSlika());
            brojSatiTOP2.setText(String.valueOf(prisutnosti.get(1).getBrojSatiDan())+" "+"h");

            imePrezimeBrojSatiTOP3.setText(prisutnosti.get(2).ImeIPrezime());
            slikaSatiTOP3.setImage(prisutnosti.get(2).getKorisnikID().getSlika());
            brojSatiTOP3.setText(String.valueOf(prisutnosti.get(2).getBrojSatiDan())+" "+"h");

            imePrezimeBrojSatiLOS1.setText(prisutnosti.get(prisutnosti.size()-1 ).ImeIPrezime());
            slikaSatiLOS1.setImage(prisutnosti.get(prisutnosti.size()-1 ).getKorisnikID().getSlika());
            brojSatiLOS1.setText(String.valueOf(prisutnosti.get(prisutnosti.size()-1 ).getBrojSatiDan())+" "+"h");

            imePrezimeBrojSatiLOS2.setText(prisutnosti.get(prisutnosti.size()-2 ).ImeIPrezime());
            slikaSatiLOS2.setImage(prisutnosti.get(prisutnosti.size()-2 ).getKorisnikID().getSlika());
            brojSatiLOS2.setText(String.valueOf(prisutnosti.get(prisutnosti.size()-2 ).getBrojSatiDan())+" "+"h");

            imePrezimeBrojSatiLOS3.setText(prisutnosti.get(prisutnosti.size()-3 ).ImeIPrezime());
            slikaSatiLOS3.setImage(prisutnosti.get(prisutnosti.size()-3 ).getKorisnikID().getSlika());
            brojSatiLOS3.setText(String.valueOf(prisutnosti.get(prisutnosti.size()-3 ).getBrojSatiDan())+" "+"h");

            int i =0;
            int sum=0;
            for (Prisutnost p : prisutnosti){
                i += 1;
                sum += p.getBrojSatiDan();
            }
            float prosjek = sum/i;
            prosjekSatiLBL.setText(String.valueOf(prosjek)+" "+"h" );

        ObservableList<Placa> place= PlacaService.placaService.top3Place(firma);
   
            imePrezimeIznosPlaceTOP1.setText(place.get(0).ImeIPrezime());
            slikaPlacaTOP1.setImage(place.get(0).getKorisnikID().getSlika());
            iznosPlaceTOP1.setText(String.valueOf(place.get(0).getIznosMjesecnePlace())+" "+"$");

            imePrezimeIznosPlaceTOP2.setText(place.get(1).ImeIPrezime());
            slikaPlacaTOP2.setImage(place.get(1).getKorisnikID().getSlika());
            iznosPlaceTOP2.setText(String.valueOf(place.get(1).getIznosMjesecnePlace())+" "+"$");

            imePrezimeIznosPlaceTOP3.setText(place.get(2).ImeIPrezime());
            slikaPlacaTOP3.setImage(place.get(2).getKorisnikID().getSlika());
            iznosPlaceTOP3.setText(String.valueOf(place.get(2).getIznosMjesecnePlace())+" "+"$");

            imePrezimeIznosPlaceLOS1.setText(place.get(place.size()-1).ImeIPrezime());
            slikaPlacaLOS1.setImage(place.get(place.size()-1).getKorisnikID().getSlika());
            iznosPlaceLOS1.setText(String.valueOf(place.get(place.size()-1).getIznosMjesecnePlace())+" "+"$");

            imePrezimeIznosPlaceLOS2.setText(place.get(place.size()-2).ImeIPrezime());
            slikaPlacaLOS2.setImage(place.get(place.size()-2).getKorisnikID().getSlika());
            iznosPlaceLOS2.setText(String.valueOf(place.get(place.size()-2).getIznosMjesecnePlace())+" "+"$");

            imePrezimeIznosPlaceLOS3.setText(place.get(place.size()-3).ImeIPrezime());
            slikaPlacaLOS3.setImage(place.get(place.size()-3).getKorisnikID().getSlika());
            iznosPlaceLOS3.setText(String.valueOf(place.get(place.size()-3).getIznosMjesecnePlace())+" "+"$");

            int a =0;
            int suma=0;
            for (Placa p : place){
                a += 1;
                suma += p.getIznosMjesecnePlace();
            }
            float prosjekPlace = suma/a;
            prosjekPlacaLBL.setText(String.valueOf(prosjekPlace)+" "+"$");

        ObservableList<Godisnji> godisnji= GodisnjiService.godisnjiService.top3Godisnji(firma);

            imePrezimeGodisnjiTOP1.setText(godisnji.get(0).ImeIPrezime());
            slikaGodisnjiTOP1.setImage(godisnji.get(0).getKorisnikID().getSlika());
            daniGodisnjiTOP1.setText(String.valueOf(godisnji.get(0).getDaniNaGodisnjem())+" "+"dana");

            imePrezimeGodisnjiTOP2.setText(godisnji.get(1).ImeIPrezime());
            slikaGodisnjiTOP2.setImage(godisnji.get(1).getKorisnikID().getSlika());    
            daniGodisnjiTOP2.setText(String.valueOf(godisnji.get(1).getDaniNaGodisnjem())+" "+"dana");

            imePrezimeGodisnjiTOP3.setText(godisnji.get(2).ImeIPrezime());
            slikaGodisnjiTOP3.setImage(godisnji.get(2).getKorisnikID().getSlika());
            daniGodisnjiTOP3.setText(String.valueOf(godisnji.get(2).getDaniNaGodisnjem())+" "+"dana");

        ObservableList<Bolovanje> bolovanje= BolovanjeService.bolovanjeService.top3Bolovanje(firma);
                      
            imePrezimeBolovanjeTOP1.setText(bolovanje.get(0).ImeIPrezime());
            slikaBolovanjeTOP1.setImage(bolovanje.get(0).getKorisnikID().getSlika());
            daniBolovanjeTOP1.setText(String.valueOf(bolovanje.get(0).getDaniNaBolovanju())+" "+"dana");

            imePrezimeBolovanjeTOP2.setText(bolovanje.get(1).ImeIPrezime());
            slikaBolovanjeTOP2.setImage(bolovanje.get(1).getKorisnikID().getSlika());
            daniBolovanjeTOP2.setText(String.valueOf(bolovanje.get(1).getDaniNaBolovanju())+" "+"dana");

            imePrezimeBolovanjeTOP3.setText(bolovanje.get(2).ImeIPrezime());
            slikaBolovanjeTOP3.setImage(bolovanje.get(2).getKorisnikID().getSlika());
            daniBolovanjeTOP3.setText(String.valueOf(bolovanje.get(2).getDaniNaBolovanju())+" "+"dana");

    }
    

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @FXML
    private void OtvoriMain(MouseEvent event) {
        myController.loadScreen(Evidencija_zaposlenika_sihterica.screen1ID, Evidencija_zaposlenika_sihterica.screen1File);
       myController.setScreen(Evidencija_zaposlenika_sihterica.screen1ID);
    }

    @FXML
    private void OtvoriStatistiku(MouseEvent event) {
        PopuniStatistiku();
        myController.loadScreen(Evidencija_zaposlenika_sihterica.screen3ID, Evidencija_zaposlenika_sihterica.screen3File);
        myController.setScreen(Evidencija_zaposlenika_sihterica.screen3ID);
    }

    @FXML
    private void OtvoriOnline(MouseEvent event) {
        myController.loadScreen(Evidencija_zaposlenika_sihterica.screen7ID, Evidencija_zaposlenika_sihterica.screen7File);
        myController.setScreen(Evidencija_zaposlenika_sihterica.screen7ID);
    }

    @FXML
    private void OtvoriZaposlenike(MouseEvent event) {
        myController.loadScreen(Evidencija_zaposlenika_sihterica.screen2ID, Evidencija_zaposlenika_sihterica.screen2File);
        myController.setScreen(Evidencija_zaposlenika_sihterica.screen2ID);
    }

    @FXML
    private void OtvoriSihtaricu(MouseEvent event) {
        myController.loadScreen(Evidencija_zaposlenika_sihterica.screen6ID, Evidencija_zaposlenika_sihterica.screen6File);
        myController.setScreen(Evidencija_zaposlenika_sihterica.screen6ID);
    }

    @FXML
    private void OtvoriBolovanje(MouseEvent event) {
        myController.loadScreen(Evidencija_zaposlenika_sihterica.screen4ID, Evidencija_zaposlenika_sihterica.screen4File);
        myController.setScreen(Evidencija_zaposlenika_sihterica.screen4ID);
    }

    @FXML
    private void OtvoriGodisnji(MouseEvent event) {
        myController.loadScreen(Evidencija_zaposlenika_sihterica.screen5ID, Evidencija_zaposlenika_sihterica.screen5File);
        myController.setScreen(Evidencija_zaposlenika_sihterica.screen5ID);
    }
    
}
