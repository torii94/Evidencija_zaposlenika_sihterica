/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evidencija_zaposlenika_sihterica.Controller;

import Services.GodisnjiService;
import Services.KorisnikService;
import Services.LoginService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import evidencija_zaposlenika_sihterica.ControlledScreen;
import evidencija_zaposlenika_sihterica.Evidencija_zaposlenika_sihterica;
import evidencija_zaposlenika_sihterica.Model.Godisnji;
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
public class GodisnjiController implements Initializable,ControlledScreen {

    ScreensController myController;
    
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    Alert potvrda = new Alert(Alert.AlertType.INFORMATION);
    Alert greska = new Alert(Alert.AlertType.ERROR);
    int firma=LoginService.logiraniKorisnik().getFirma().getID();
    @FXML
    private Pane bolovanje_pane1;
    @FXML
    private ScrollPane sp1;
    @FXML
    private TilePane aktivniGodisnjiTile;
    @FXML
    private ScrollPane sp11;
    @FXML
    private TilePane evidencijaGodisnjegTile;
    @FXML
    private ComboBox<Korisnik> PovratakSaGodisnjegCombo;
    @FXML
    private DatePicker DolazakDE;
    @FXML
    private Button SpremiPovratakBtn;
    @FXML
    private ComboBox<Korisnik> OdlazakNaGodisnjiCombo;
    @FXML
    private DatePicker OdlazakDE;
    @FXML
    private Button SpremiOdlazakBtn;
    @FXML
    private ComboBox<Korisnik> ZamjenaCombo;
    @FXML
    private FontAwesomeIconView otvoriGodisnjuBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

        ObservableList<Korisnik> korisnici= KorisnikService.korisnikService.dajZaposlenike(firma);
        OdlazakNaGodisnjiCombo.setItems(korisnici);
        PovratakSaGodisnjegCombo.setItems(korisnici);
        ZamjenaCombo.setItems(korisnici);
        PopuniGodisnji();
    }    

    private void PopuniGodisnji(){
        int firma=LoginService.logiraniKorisnik().getFirma().getID();

        ObservableList<Godisnji> sviGodisnji= GodisnjiService.godisnjiService.SviGodisnjiOdmori(firma);
        ObservableList<Godisnji> aktivniGodisnji= GodisnjiService.godisnjiService.GetAktivanGodisnji(firma);
           
        aktivniGodisnjiTile.setVgap(10);
        for(Godisnji b:aktivniGodisnji){             
            Text t = new Text();
            Text spacehelp= new Text();
            spacehelp.setText("     ");
            t.setText(b.getKorisnikID().getIme()+" "+b.getKorisnikID().getPrezime());   
            Text tzamjena = new Text();
            tzamjena.setText(b.getZamjena().getIme()+" "+b.getZamjena().getPrezime());   
            
            t.setFont(Font.font ("Arial", FontWeight.BOLD,  11));
            t.setFill(Color.BLACK);
            tzamjena.setFont(Font.font ("Arial", FontWeight.BOLD,  11));
            tzamjena.setFill(Color.BLACK);
            
            Text d = new Text();
            d.setText(b.getOdlazak().toString());        
            
            d.setFont(Font.font ("Arial", FontWeight.BOLD,  12));
            d.setFill(Color.BLACK);
         
            ImageView slika = new ImageView(b.getKorisnikID().getSlika() ); 
            ImageView zamjenaSlika = new ImageView(b.getZamjena().getSlika() ); 
            slika.setFitHeight(40);
            slika.setFitWidth(40);
            zamjenaSlika.setFitHeight(40);
            zamjenaSlika.setFitWidth(40);
            StackPane stack = new StackPane();
            stack.setPrefSize(60, 80);
            
            HBox hbox= new HBox();
            HBox h= new HBox();
            HBox hb= new HBox();
            HBox hboxzamjena= new HBox();
            hbox.setAlignment(Pos.CENTER_LEFT);
            h.setAlignment(Pos.CENTER_LEFT);
            hboxzamjena.setAlignment(Pos.CENTER_LEFT);
            hboxzamjena.setAlignment(Pos.CENTER_LEFT);
            
            hbox.setPrefWidth(200);
            
            VBox vbox = new VBox();
            hbox.setSpacing(5);
            h.getChildren().add(d);
            hbox.getChildren().addAll(slika,t);
                      
            hboxzamjena.getChildren().addAll(zamjenaSlika,spacehelp,tzamjena);
            hb.getChildren().addAll(hbox,h,spacehelp,hboxzamjena);
                      
            aktivniGodisnjiTile.getChildren().add(hb);
        }
         
        evidencijaGodisnjegTile.setVgap(10);
        for(Godisnji bol:sviGodisnji){
             
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
            
            hbox.setSpacing(5);
            h.getChildren().add(d);
            hbox.getChildren().addAll(slika,t);
            hb.getChildren().addAll(hbox,h);
                      
            evidencijaGodisnjegTile.getChildren().add(hb);            
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
    private void OtvoriGodisnji(MouseEvent event) {
        myController.loadScreen(Evidencija_zaposlenika_sihterica.screen5ID, Evidencija_zaposlenika_sihterica.screen5File);
        myController.setScreen(Evidencija_zaposlenika_sihterica.screen5ID);
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @FXML
    private void OtvoriSihtaricu(MouseEvent event) {
        myController.loadScreen(Evidencija_zaposlenika_sihterica.screen6ID, Evidencija_zaposlenika_sihterica.screen6File);
        myController.setScreen(Evidencija_zaposlenika_sihterica.screen6ID);
    }

    @FXML
    private void Povratak(MouseEvent event) {
       
         potvrda.setTitle("Test");
        potvrda.setHeaderText("Rezultat:");
        potvrda.setContentText("Povratak sa godisnjeg je uspješno postavljen!");
 
        greska.setTitle("Nije uspijelo!");
        greska.setHeaderText("Greska:");
        greska.setContentText("Greska prilikom povratka sa godisnjeg");
        
        if( DolazakDE.getValue() != null && PovratakSaGodisnjegCombo.getValue() != null  ){  
                               
            Godisnji god=GodisnjiService.godisnjiService.DajDatumOdlaskaNaGodisnji(PovratakSaGodisnjegCombo.getValue().getId());

            Godisnji g= new Godisnji();
            System.out.println(god.getOdlazak());
            LocalDate isoDate = DolazakDE.getValue();        
            long p2 = ChronoUnit.DAYS.between( god.getOdlazak().toLocalDate(),isoDate);        
            Date date = java.sql.Date.valueOf(isoDate);
            g.setKorisnikID(PovratakSaGodisnjegCombo.getValue());
            g.setAktivan(false);
            g.setDaniNaGodisnjem((int)p2);
            g.setDolazak(date);

            GodisnjiService.godisnjiService.uredi(g);
            potvrda.show();
            evidencijaGodisnjegTile.getChildren().clear();
            aktivniGodisnjiTile.getChildren().clear();
            PopuniGodisnji();   
         
        }else{
            greska.show();
        }
    }

    @FXML
    private void OdlazakNaGodisnji(MouseEvent event) {
        
        potvrda.setTitle("Test");
        potvrda.setHeaderText("Rezultat:");
        potvrda.setContentText("Ggodisnji uspješno postavljen!");
 
        greska.setTitle("Nije uspijelo!");
        greska.setHeaderText("Greska:");
        greska.setContentText("Greska prilikom postavljanja godisnjeg");
        
        if( OdlazakDE.getValue() != null && OdlazakNaGodisnjiCombo.getValue() != null && ZamjenaCombo.getValue() != null ){  
                   
            Godisnji g= new Godisnji();
            ObservableList<Korisnik> korisnici= KorisnikService.korisnikService.dajZaposlenike(firma);

            LocalDate isoDate = OdlazakDE.getValue();
            Date date = java.sql.Date.valueOf(isoDate);

            g.setKorisnikID(OdlazakNaGodisnjiCombo.getValue());
            g.setAktivan(true);
            g.setOdlazak(date);
            g.setZamjena(ZamjenaCombo.getValue());

            GodisnjiService.godisnjiService.spasi(g);   
            potvrda.show();
            evidencijaGodisnjegTile.getChildren().clear();
            aktivniGodisnjiTile.getChildren().clear();
            PopuniGodisnji();
        }else {
            greska.show();
        }
    }
    
}
