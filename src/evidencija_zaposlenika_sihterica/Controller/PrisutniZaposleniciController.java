/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evidencija_zaposlenika_sihterica.Controller;

import Services.LoginService;
import Services.PrisutnostService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import evidencija_zaposlenika_sihterica.ControlledScreen;
import evidencija_zaposlenika_sihterica.Evidencija_zaposlenika_sihterica;
import evidencija_zaposlenika_sihterica.Model.Prisutnost;
import evidencija_zaposlenika_sihterica.ScreensController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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
public class PrisutniZaposleniciController implements Initializable ,ControlledScreen{

    ScreensController myController;

    @FXML
    private Pane zaposlenici_pane;
    @FXML
    private TilePane emp_tile;
    @FXML
    private FontAwesomeIconView otvoriGodisnjuBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GetLiveZaposlenici();
    }    
    
    private void GetLiveZaposlenici(){
    
        int firma=LoginService.logiraniKorisnik().getFirma().getID();
        ObservableList<Prisutnost> korisnici= (ObservableList<Prisutnost>) PrisutnostService.prisutnostService.GetOnlineZaposlenici(firma);

        emp_tile.setVgap(20);
        emp_tile.setHgap(35);


        for(Prisutnost korisnik : korisnici){

            Text t = new Text();
            t.setText(korisnik.getKorisnikID().getIme()+"\n"+korisnik.getKorisnikID().getPrezime()+"\n"+korisnik.getDolazak()+" h");           
            t.setFont(Font.font ("Arial", FontWeight.BOLD,  13));
            t.setFill(Color.BLACK);

            ImageView slika = new ImageView(korisnik.getKorisnikID().getSlika() ); 
            slika.setFitHeight(100);
            slika.setFitWidth(100);

            HBox hbox= new HBox();
            VBox vbox = new VBox();
            vbox.setSpacing(5);

            hbox.setPrefWidth(150);
            hbox.setMinHeight(75);
            hbox.setMinHeight(75);               
            vbox.setMaxWidth(50);
            hbox.setAlignment(Pos.CENTER_LEFT);
            vbox.setAlignment(Pos.CENTER);

            hbox.getChildren().addAll(slika,t);
            vbox.getChildren().addAll(slika,t);

            emp_tile.getChildren().addAll(vbox); 

        }
    }

    @FXML
    private void OtvoriMain(MouseEvent event) {
        myController.loadScreen(Evidencija_zaposlenika_sihterica.screen1ID, Evidencija_zaposlenika_sihterica.screen1File);
        myController.setScreen(Evidencija_zaposlenika_sihterica.screen1ID);
    }

    @FXML
    private void OtvoriZaposlenike(MouseEvent event) {
        myController.loadScreen(Evidencija_zaposlenika_sihterica.screen2ID, Evidencija_zaposlenika_sihterica.screen2File);
        myController.setScreen(Evidencija_zaposlenika_sihterica.screen2ID);
    }

    @FXML
    private void OtvoriOnline(MouseEvent event) {
        myController.loadScreen(Evidencija_zaposlenika_sihterica.screen7ID, Evidencija_zaposlenika_sihterica.screen7File);
        myController.setScreen(Evidencija_zaposlenika_sihterica.screen7ID);
    }

    @FXML
    private void OtvoriStatistiku(MouseEvent event) {
        myController.loadScreen(Evidencija_zaposlenika_sihterica.screen3ID, Evidencija_zaposlenika_sihterica.screen3File);
        myController.setScreen(Evidencija_zaposlenika_sihterica.screen3ID);
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

    @Override
    public void setScreenParent(ScreensController screenPage) {
         myController = screenPage;
    }
    
}
