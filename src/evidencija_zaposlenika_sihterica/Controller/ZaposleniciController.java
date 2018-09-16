/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evidencija_zaposlenika_sihterica.Controller;

import Services.KorisnikService;
import Services.LoginService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import evidencija_zaposlenika_sihterica.ControlledScreen;
import evidencija_zaposlenika_sihterica.Evidencija_zaposlenika_sihterica;
import evidencija_zaposlenika_sihterica.Model.Korisnik;
import evidencija_zaposlenika_sihterica.ScreensController;
import evidencija_zaposlenika_sihterica.Utils;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author toni
 */
public class ZaposleniciController implements Initializable,ControlledScreen {

    ScreensController myController;
    Korisnik prjavljeni;
    @FXML
    private Pane zaposlenici_pane;
    @FXML
    private TilePane emp_tile;
    Korisnik odabraniKorisnik;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GetZaposlenici();
    }    
    
    public void prijavljeniKorisnik(Korisnik korisnik) {
        prjavljeni = korisnik;
    }
    private void GetZaposlenici(){
        int firma_id=LoginService.logiraniKorisnik().getFirma().getID();
        //ObservableList<Korisnik> korisnici= KorisnikService.korisnikService.sveIzBaze();
        ObservableList<Korisnik> korisnici= KorisnikService.korisnikService.dajZaposlenike(firma_id);
        emp_tile.setVgap(20);
        emp_tile.setHgap(-25);

        for(Korisnik korisnik : korisnici){

            Text t = new Text();
            t.setText(korisnik.getIme()+" "+korisnik.getPrezime());        
            t.setFont(Font.font ("Arial", FontWeight.BOLD,  11));
            t.setFill(Color.BLACK);

            ImageView slika = new ImageView(korisnik.getSlika() ); 
            slika.setFitHeight(50);
            slika.setFitWidth(50);
           
            Button btn= new Button();
            btn.setText("Profil");
            btn.alignmentProperty();
            HBox hbox= new HBox();
            VBox vbox = new VBox();
            hbox.setSpacing(10);

            btn.setOnMouseClicked((event) -> {   
                odabraniKorisnik=KorisnikService.korisnikService.izBazePremaId(korisnik.getId());
                FXMLLoader loader = new FXMLLoader(Utils.getClass("KorisnikInformacije"));

                Parent sceneMain;
                try
                {
                    sceneMain = loader.load();
                    KorisnikInformacijeController controller = loader.<KorisnikInformacijeController>getController();
                    controller.setKorisnik(odabraniKorisnik);
                    Stage window = new Stage();
                    window.setScene(new Scene(sceneMain));
                    window.show(); 
                }   catch (IOException ex) {
                        Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
                    }                        
            });

            hbox.setPrefWidth(150);
            hbox.setMinHeight(75);
            hbox.setMinHeight(75);
            vbox.setMaxWidth(50);
            hbox.setAlignment(Pos.CENTER_LEFT);
            vbox.setAlignment(Pos.CENTER_RIGHT);
            hbox.getChildren().addAll(slika,t);
            vbox.getChildren().addAll(btn);
            
            emp_tile.getChildren().addAll(hbox,vbox); 
                      
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
    private void OtvoriBolovanje(MouseEvent event) {
        myController.loadScreen(Evidencija_zaposlenika_sihterica.screen4ID, Evidencija_zaposlenika_sihterica.screen4File);
        myController.setScreen(Evidencija_zaposlenika_sihterica.screen4ID);
    }

    @FXML
    private void OtvoriGodisnji(MouseEvent event) {
        myController.loadScreen(Evidencija_zaposlenika_sihterica.screen5ID, Evidencija_zaposlenika_sihterica.screen5File);
        myController.setScreen(Evidencija_zaposlenika_sihterica.screen5ID);
    }

    @FXML
    private void DodajNovogZaposlenika(MouseEvent event) {

        Stage window = new Stage();           
        window.show();                 
        Utils.prikazi(window, "KorisnikInformacije");
    }
    
}
