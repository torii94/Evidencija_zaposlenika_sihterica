/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evidencija_zaposlenika_sihterica.Controller;

import Services.BolovanjeService;
import Services.KorisnikService;
import Services.LoginService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import evidencija_zaposlenika_sihterica.ControlledScreen;
import evidencija_zaposlenika_sihterica.Evidencija_zaposlenika_sihterica;
import evidencija_zaposlenika_sihterica.Model.Bolovanje;
import evidencija_zaposlenika_sihterica.Model.Korisnik;
import evidencija_zaposlenika_sihterica.ScreensController;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author toni
 */
public class BolovanjeController implements Initializable,ControlledScreen {

    ScreensController myController;
    
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    Alert potvrda = new Alert(Alert.AlertType.INFORMATION);
    Alert greska = new Alert(Alert.AlertType.ERROR);
    
    @FXML
    private Pane bolovanje_pane;
    @FXML
    private ScrollPane sp;
    @FXML
    private TilePane aktivnotile;
    @FXML
    private ScrollPane sp1;
    @FXML
    private TilePane evidencijaTile;
    @FXML
    private ComboBox<Korisnik> zaposlenikBolovnjePovratakCombo;
    @FXML
    private DatePicker datumPovratkaDE;
    @FXML
    private Button dolazakBolovanjeBtn;
    @FXML
    private ComboBox<Korisnik> zaposleniciCombo;
    @FXML
    private DatePicker datumOdlaskaDE;
    @FXML
    private Button odlazakNaBolovanjebtn;
    @FXML
    private FontAwesomeIconView otvoriGodisnjuBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int firma=LoginService.logiraniKorisnik().getFirma().getID();
         ObservableList<Korisnik> korisnici= KorisnikService.korisnikService.dajZaposlenike(firma);
         zaposleniciCombo.setItems(korisnici);
         zaposlenikBolovnjePovratakCombo.setItems(korisnici);
         PopuniBolovanje();
    }    
    
    private void PopuniBolovanje(){
        int firma=LoginService.logiraniKorisnik().getFirma().getID();
        ObservableList<Bolovanje> svabolovanja= BolovanjeService.bolovanjeService.GetAllBolovanje(firma);
        ObservableList<Bolovanje> bolovanja= BolovanjeService.bolovanjeService.GetAktivnoBolovanje(firma);
       
        aktivnotile.setVgap(10);
        for(Bolovanje b:bolovanja){           
            
            Text t = new Text();
            t.setText(b.getKorisnikID().getIme()+" "+b.getKorisnikID().getPrezime());                    
            t.setFont(Font.font ("Arial", FontWeight.BOLD,  12));
            t.setFill(Color.BLACK);
            
            Text d = new Text();
            d.setText(b.getOdlazak().toString());                   
            d.setFont(Font.font ("Arial", FontWeight.BOLD,  12));
            d.setFill(Color.BLACK);
         
            ImageView slika = new ImageView(b.getKorisnikID().getSlika() ); 
            slika.setFitHeight(50);
            slika.setFitWidth(50);
            StackPane stack = new StackPane();
            stack.setPrefSize(60, 80);
            
            Button btn= new Button();            
            btn.setText("Profil");
            btn.alignmentProperty();
            
            HBox hbox= new HBox();
            HBox h= new HBox();
            HBox hb= new HBox();

            hbox.setAlignment(Pos.CENTER_LEFT);
            h.setAlignment(Pos.CENTER_LEFT);    
            hbox.setPrefWidth(200);            
            VBox vbox = new VBox();
            hbox.setSpacing(5);
            
            h.getChildren().add(d);
            hbox.getChildren().addAll(slika,t);
            hb.getChildren().addAll(hbox,h);
                      
             aktivnotile.getChildren().add(hb);                       
        }
         
        
        evidencijaTile.setVgap(10);
        for(Bolovanje bol:svabolovanja){
             
            Text t = new Text();
            t.setText(bol.getKorisnikID().getIme()+" "+bol.getKorisnikID().getPrezime());                    
            t.setFont(Font.font ("Arial", FontWeight.BOLD,  12));
            t.setFill(Color.BLACK);
            
            Text d = new Text();
            try{
                d.setText(bol.getOdlazak().toString()+"                    "+bol.getDolazak().toString());        
            }catch(NullPointerException ex){}        
            d.setFont(Font.font ("Arial", FontWeight.BOLD,  12));
            d.setFill(Color.BLACK);
         
            ImageView slika = new ImageView(bol.getKorisnikID().getSlika() ); 
            slika.setFitHeight(50);
            slika.setFitWidth(50);
            StackPane stack = new StackPane();
            stack.setPrefSize(60, 80);

            Button btn= new Button();            
            btn.setText("Profil");
            btn.alignmentProperty();
            
            HBox hbox= new HBox();
            HBox h= new HBox();
            HBox hb= new HBox();

            hbox.setAlignment(Pos.CENTER_LEFT);
            h.setAlignment(Pos.CENTER_LEFT);
            
            hbox.setPrefWidth(200);
            
            VBox vbox = new VBox();
            hbox.setSpacing(5);
            h.getChildren().add(d);
            hbox.getChildren().addAll(slika,t);
            hb.getChildren().addAll(hbox,h);
                      
            evidencijaTile.getChildren().add(hb);           
        }         
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }



    @FXML
    private void DolazakSaBolovanja(MouseEvent event) {

        potvrda.setTitle("Test");
        potvrda.setHeaderText("Rezultat:");
        potvrda.setContentText("Povratak sa bolovanja je uspješno postavljen!");
 
        greska.setTitle("Nije uspijelo!");
        greska.setHeaderText("Greska:");
        greska.setContentText("Greska prilikom povratka sa bolovanja");
        
        Bolovanje bol=BolovanjeService.bolovanjeService.DajDatumOdlaskaNaBolovanje(zaposlenikBolovnjePovratakCombo.getValue().getId());
  
            Bolovanje b= new Bolovanje();
            LocalDate isoDate = datumPovratkaDE.getValue();        
            long p2 = ChronoUnit.DAYS.between( bol.getOdlazak().toLocalDate(),isoDate); 

            Date date = java.sql.Date.valueOf(isoDate);
            b.setKorisnikID(zaposlenikBolovnjePovratakCombo.getValue());
            b.setAktivan(false);
            b.setDaniNaBolovanju((int)p2);
            b.setDolazak(date);

            BolovanjeService.bolovanjeService.uredi(b);
            potvrda.show();
            evidencijaTile.getChildren().clear();
            aktivnotile.getChildren().clear();
            PopuniBolovanje();
        
        }
    

    @FXML
    private void OdlazakNaBolovanje(MouseEvent event) {
        Bolovanje b= new Bolovanje();
        
        potvrda.setTitle("Test");
        potvrda.setHeaderText("Rezultat:");
        potvrda.setContentText("Bolovanje je uspješno postavljeno!");
 
        greska.setTitle("Nije uspijelo!");
        greska.setHeaderText("Greska:");
        greska.setContentText("Greska prilikom postavjanja bolovanja");
        
        if( datumOdlaskaDE.getValue() != null && zaposleniciCombo.getValue() != null ){
            LocalDate isoDate = datumOdlaskaDE.getValue();
            Date date = java.sql.Date.valueOf(isoDate);

            b.setKorisnikID(zaposleniciCombo.getValue());
            b.setAktivan(true);
            b.setDaniNaBolovanju(0);
            b.setOdlazak(date);

            BolovanjeService.bolovanjeService.spasi(b);   
            potvrda.show();
            evidencijaTile.getChildren().clear();
            aktivnotile.getChildren().clear();
            PopuniBolovanje();
        }else{
            greska.show();
        }
    }
    
     @FXML
    private void OtvoriMain(MouseEvent event) {
        myController.loadScreen(Evidencija_zaposlenika_sihterica.screen1ID, Evidencija_zaposlenika_sihterica.screen1File);
        myController.setScreen(Evidencija_zaposlenika_sihterica.screen1ID);
    }

    @FXML
    private void OtvoriStatistiku(MouseEvent event) {
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
    private void OtvoriBolovanje(MouseEvent event) { 
        myController.loadScreen(Evidencija_zaposlenika_sihterica.screen4ID, Evidencija_zaposlenika_sihterica.screen4File);
        myController.setScreen(Evidencija_zaposlenika_sihterica.screen4ID);
    }

    @FXML
    private void OtvoriSihtaricu(MouseEvent event) {
        myController.loadScreen(Evidencija_zaposlenika_sihterica.screen6ID, Evidencija_zaposlenika_sihterica.screen6File);
        myController.setScreen(Evidencija_zaposlenika_sihterica.screen6ID);
                            
    }

    @FXML
    private void OtvoriGodisnji(MouseEvent event) {
        myController.loadScreen(Evidencija_zaposlenika_sihterica.screen5ID, Evidencija_zaposlenika_sihterica.screen5File);
        myController.setScreen(Evidencija_zaposlenika_sihterica.screen5ID);

    }
}
