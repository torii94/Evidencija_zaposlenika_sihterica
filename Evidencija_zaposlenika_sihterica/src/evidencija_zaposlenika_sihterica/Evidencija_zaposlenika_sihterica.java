/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evidencija_zaposlenika_sihterica;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 * @author toni
 */
public class Evidencija_zaposlenika_sihterica extends Application {
      
    public static  String screenID = "View/Login.fxml";
    public static  String screenFile = "View/Login.fxml";
    public static  String screen1ID = "View/Menu.fxml";
    public static  String screen1File = "View/Menu.fxml";
    public static  String screen7ID = "View/PrisutniZaposlenici.fxml";
    public static  String screen7File = "View/PrisutniZaposlenici.fxml";
    public static String screen3ID = "View/Statistika.fxml";
    public static String screen3File = "View/Statistika.fxml";
    public static String screen2ID = "View/Zaposlenici";
    public static String screen2File = "View/Zaposlenici.fxml";
    public static String screen4ID = "View/Bolovanje.fxml";
    public static String screen4File = "View/Bolovanje.fxml";
    public static String screen5ID = "View/Godisnji.fxml";
    public static String screen5File = "View/Godisnji.fxml";
    public static String screen6ID = "View/Sihterica.fxml";
    public static String screen6File = "View/Sihterica.fxml";
    public static String screen8ID = "View/KorisnikInformacije.fxml";
    public static String screen8File = "View/KorisnikInformacije.fxml";
    public static String screen9ID = "View/ProfilKorisnika.fxml";
    public static String screen9File = "View/ProfilKorisnika.fxml";
    

    
    @Override
    public void start(Stage stage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("View/Menu.fxml"));
//        
//        Scene scene = new Scene(root);
//        
//        stage.setScene(scene);
//        stage.show();
        
        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(Evidencija_zaposlenika_sihterica.screenID,Evidencija_zaposlenika_sihterica.screenFile);
        mainContainer.loadScreen(Evidencija_zaposlenika_sihterica.screen1ID,Evidencija_zaposlenika_sihterica.screen1File);        
        mainContainer.setScreen(Evidencija_zaposlenika_sihterica.screenID);
        
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
