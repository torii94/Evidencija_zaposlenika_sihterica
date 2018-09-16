/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evidencija_zaposlenika_sihterica.Controller;

import Services.BolovanjeService;
import Services.FirmaService;
import Services.GodisnjiService;
import Services.KorisnikService;
import Services.LoginService;
import Services.PlacaService;
import Services.PrisutnostService;
import evidencija_zaposlenika_sihterica.ControlledScreen;
import evidencija_zaposlenika_sihterica.Model.Bolovanje;
import evidencija_zaposlenika_sihterica.Model.Firma;
import evidencija_zaposlenika_sihterica.Model.Godisnji;
import evidencija_zaposlenika_sihterica.Model.Korisnik;
import evidencija_zaposlenika_sihterica.Model.Placa;
import evidencija_zaposlenika_sihterica.Model.Prisutnost;
import evidencija_zaposlenika_sihterica.ScreensController;
import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javax.swing.JFileChooser;

/**
 * FXML Controller class
 *
 * @author toni
 */
public class KorisnikInformacijeController implements Initializable , ControlledScreen{

    ScreensController myController;
    Alert alert = new Alert(AlertType.CONFIRMATION);
    Alert potvrda = new Alert(AlertType.INFORMATION);
    Alert greska = new Alert(AlertType.ERROR);
    
    public Korisnik odabraniKorisnik;
    private Pane slika_pane;
    @FXML
    private StackPane stack_pane;
    @FXML
    private ImageView slika_korisnika;
    private Label opisPoslaLbl;
    @FXML
    private Label brojsatiLbl;
    private Label datumRodjenjaLbl;
    private Label adresaLbl;
    private Label spolLbl;
    private Label mailLbl;
    @FXML
    private TextField adresaTF;
    @FXML
    private TextField spolTF;
    private TextField datumRodjenjaTF;
    @FXML
    private TextField emailTF;
    @FXML
    private TextField obrazovanjeTF;
    @FXML
    private TextField imeTF;
    @FXML
    private TextField prezimeTF;
    @FXML
    private TextArea opisPoslaTA;
    @FXML
    private Button spasiBtn;
    @FXML
    private DatePicker DatumRodjenjaDE;
    @FXML
    private Label ime_prezimeLbl1;
    @FXML
    private Label placaLbl1;
    @FXML
    private TextField PlacaTF;
    @FXML
    private TextField BonusTF;
    @FXML
    private CheckBox VlasnikCE;
    @FXML
    private CheckBox ZaposlenikCE;
    @FXML
    private TextField TelefonTF;
    @FXML
    private ComboBox<Firma> FirmaCombo;
    @FXML
    private Label DaniNaGodisnjemLbl;
    @FXML
    private Label DaniNaBolovanjuLbl;
    @FXML
    private TextField K_imeTF;
    @FXML
    private TextField LozinkaTF;
    @FXML
    private Button odlazakBtn;

     public void setKorisnik(Korisnik korisnik) {
        
        odabraniKorisnik = korisnik;
        Prisutnost prisutnost = PrisutnostService.prisutnostService.DajZaposlenikBrojSatiMjesec(odabraniKorisnik.getId(),9);
        Godisnji godisnji = GodisnjiService.godisnjiService.DajZaposlenikBrojDanaGodisnji(odabraniKorisnik.getId());
        Bolovanje bolovanje=BolovanjeService.bolovanjeService.DajZaposlenikBrojDanaBolovanje(odabraniKorisnik.getId());
        Placa placa= PlacaService.placaService.izBazePremaId(odabraniKorisnik.getId());

        if(odabraniKorisnik.getIme() != null)this.imeTF.setText(odabraniKorisnik.getIme());
        if(odabraniKorisnik.getPrezime()!= null)this.prezimeTF.setText(odabraniKorisnik.getPrezime());
        if(odabraniKorisnik.getAdresa()!= null)this.adresaTF.setText(odabraniKorisnik.getAdresa());          
        if(odabraniKorisnik.getDatumRodjenja()!= null)this.DatumRodjenjaDE.setValue(odabraniKorisnik.getDatumRodjenja().toLocalDate());
        if(odabraniKorisnik.getEmail()!= null)this.emailTF.setText(odabraniKorisnik.getEmail());
        if(odabraniKorisnik.getObrazovanje()!= null)this.obrazovanjeTF.setText(odabraniKorisnik.getObrazovanje());
        if(odabraniKorisnik.getOpis_posla()!= null)this.opisPoslaTA.setText(odabraniKorisnik.getOpis_posla());
        if(odabraniKorisnik.getSpol()!= null)this.spolTF.setText(odabraniKorisnik.getSpol());           
        if(odabraniKorisnik.getTelefon()!= null)this.TelefonTF.setText(odabraniKorisnik.getTelefon());            

        this.brojsatiLbl.setText(String.valueOf(prisutnost.getBrojSatiDan()));
        this.DaniNaGodisnjemLbl.setText(String.valueOf(godisnji.getDaniNaGodisnjem()));
        this.DaniNaBolovanjuLbl.setText(String.valueOf(bolovanje.getDaniNaBolovanju()));
        this.PlacaTF.setText(String.valueOf(placa.getIznosMjesecnePlace())+" $");
        this.BonusTF.setText(String.valueOf(placa.getGodisnjiBonus())+" $");
        if(odabraniKorisnik.getK_ime()!= null)this.K_imeTF.setText(odabraniKorisnik.getK_ime());
        if(odabraniKorisnik.getLozinka()!= null)this.LozinkaTF.setText(odabraniKorisnik.getLozinka());
        if(odabraniKorisnik.getTip()!= null){
            if(odabraniKorisnik.getTip().equals("Zaposlenik")){
                this.ZaposlenikCE.setSelected(true);
            }else{this.VlasnikCE.setSelected(true);}
        }

        this.slika_korisnika = new ImageView(odabraniKorisnik.getSlika() ); 
        this.slika_korisnika.setFitHeight(150);
        this.slika_korisnika.setFitWidth(150);
        this.stack_pane.getChildren().add(slika_korisnika);
        this.FirmaCombo.getSelectionModel().select(odabraniKorisnik.getFirma().getID()-1);
     }

    /**
     * Initializes the controller class.
     */
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<Firma> firme= (ObservableList<Firma>) FirmaService.firmaService.sveIzBaze();
        FirmaCombo.setItems(firme);
    }    
    
   
    @FXML
    private void promjeniSliku(MouseEvent event) {
        
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Img", "*.jpg"));
        File datoteka = fc.showOpenDialog(null);
        Image binarnaSlika = new Image(datoteka.toURI().toString());
        this.slika_korisnika.setImage(binarnaSlika);
      
    }

    @FXML
    private void spasiPrisutnost(MouseEvent event) {
        
        Prisutnost p = new Prisutnost();
        
        potvrda.setTitle("Dolazak");
        potvrda.setHeaderText("Rezultat:");
        potvrda.setContentText("Korisnik je uspješno prijavljen na poslu!");

        greska.setTitle("Greska");
        greska.setHeaderText("Rezultat:");
        greska.setContentText("Korisnik se prvo treba dodati da bi se postavila prisutnost!");

        if(odabraniKorisnik != null){
            LocalDateTime timePoint = LocalDateTime.now();
            LocalTime time=LocalTime.now();

            int hours = timePoint.getHour();
            int minutes = timePoint.getMinute();
            LocalTime start = LocalTime.of(hours, minutes);

            Time dolazak = java.sql.Time.valueOf(start);
            p.setKorisnikID(odabraniKorisnik);
            p.setBrojSatiDan(0);
            p.setDolazak(dolazak);
            p.setAktivan(true);
            // p.setOdlazak(0);
            p.setDan(timePoint.getDayOfMonth());
            p.setMjesec(timePoint.getMonthValue());

            PrisutnostService.prisutnostService.spasi(p); 
            potvrda.show();
        } else {
            greska.show();
        }        
    }

    @FXML
    private void odlazak(MouseEvent event) {
        Prisutnost p = new Prisutnost();
        
        potvrda.setTitle("Odlazak");
        potvrda.setHeaderText("Rezultat:");
        potvrda.setContentText("Korisnik je uspješno odjavljen sa posla!");
        
        greska.setTitle("Greska");
        greska.setHeaderText("Rezultat:");
        greska.setContentText("Korisnik se prvo treba dodati da bi se postavila prisutnost!");
        
         if(odabraniKorisnik != null){
            LocalDateTime timePoint = LocalDateTime.now();
            Prisutnost kor = PrisutnostService.prisutnostService.DajZposlenika(odabraniKorisnik.getId(), timePoint.getDayOfMonth(), timePoint.getMonthValue());

            int hours = timePoint.getHour();
            int minutes = timePoint.getMinute();

            Time dolazak=kor.getDolazak();
            Time odlazak = java.sql.Time.valueOf(LocalTime.of(hours, minutes));

            p.setKorisnikID(odabraniKorisnik);
            p.setAktivan(false);
            p.setOdlazak(odlazak);
            p.setDan(timePoint.getDayOfMonth());

            long difference = odlazak.getTime() - dolazak.getTime(); 
            long diffHours = difference / (60 * 60 * 1000) % 24;
            System.out.print(diffHours);
            p.setBrojSatiDan((int) diffHours);

            PrisutnostService.prisutnostService.uredi(p); 
            potvrda.show();
         } else {
            greska.show();
        }      
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @FXML
    private void UrediKorisnika(MouseEvent event) {
        Korisnik kor = new Korisnik();
        
         greska.setTitle("Nije uspijelo!");
         greska.setHeaderText("Greska:");
         greska.setContentText("Korisnik se mora dodati da bi se mogao urediti");
        
        if(odabraniKorisnik != null){
            kor.setId(odabraniKorisnik.getId());
            kor.setIme(imeTF.getText());
            kor.setPrezime(prezimeTF.getText());
            kor.setEmail(emailTF.getText());
            kor.setSpol(spolTF.getText());
            kor.setSlika(slika_korisnika.getImage());
            try{
                LocalDate isoDate = DatumRodjenjaDE.getValue();        
                Date datumrod = java.sql.Date.valueOf(isoDate);
                kor.setDatumRodjenja(datumrod);
            }catch(NullPointerException r){}
            kor.setAdresa(adresaTF.getText());
            kor.setTelefon(TelefonTF.getText());
            kor.setObrazovanje(obrazovanjeTF.getText());
            kor.setOpis_posla(opisPoslaTA.getText());
            kor.setK_ime(K_imeTF.getText());
            kor.setLozinka(LozinkaTF.getText());
            kor.setFirma(FirmaCombo.getValue());
            System.out.println(FirmaCombo.getValue());
            if(ZaposlenikCE.isSelected()){
                kor.setTip("Zaposlenik");
            } else if(VlasnikCE.isSelected()){
                 kor.setTip("Vlasnik");
            }

            potvrda.setTitle("Test");
            potvrda.setHeaderText("Rezultat:");
            potvrda.setContentText("Korisnik je uspješno uredjen!");

            alert.setTitle("Uredjivanje korisnika");
            alert.setHeaderText("Potvrda o uredjivanju korisnika");
            alert.setContentText("Da li želite urediti korisnika "+odabraniKorisnik.getIme()+" "+odabraniKorisnik.getPrezime()+"?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                 KorisnikService.korisnikService.uredi(kor);                
                 potvrda.show();
            } else if (result.get() == ButtonType.CANCEL){
                return;
            }
        } else {
            greska.show();
        }      

    }

    @FXML
    private void DodajKorisnika(MouseEvent event) {
        Korisnik kor = new Korisnik();
        
        greska.setTitle("Nije uspijelo!");
        greska.setHeaderText("Greska:");
        greska.setContentText("Nne mozete dodati postojeceg korisnika");

        if(odabraniKorisnik != null){
            greska.show();
        }else {
        
            kor.setIme(imeTF.getText());
            kor.setPrezime(prezimeTF.getText());
            kor.setEmail(emailTF.getText());
            kor.setSpol(spolTF.getText());       
            kor.setSlika(slika_korisnika.getImage());
            try{
                LocalDate isoDate = DatumRodjenjaDE.getValue();        
                Date datumrod = java.sql.Date.valueOf(isoDate);      
                kor.setDatumRodjenja(datumrod);
            }catch(NullPointerException es) {}
            kor.setAdresa(adresaTF.getText());
            kor.setTelefon(TelefonTF.getText());
            kor.setObrazovanje(obrazovanjeTF.getText());
            kor.setOpis_posla(opisPoslaTA.getText());
            kor.setK_ime(K_imeTF.getText());
            kor.setLozinka(LozinkaTF.getText());       
            kor.setFirma(FirmaCombo.getValue());
            if(ZaposlenikCE.isSelected()){
                kor.setTip("Zaposlenik");
            } else if(VlasnikCE.isSelected()){
                 kor.setTip("Vlasnik");
            }

            potvrda.setTitle("Test");
            potvrda.setHeaderText("Rezultat:");
            potvrda.setContentText("Korisnik je uspješno dodan!");

            alert.setTitle("Dodavanje korisnika");
            alert.setHeaderText("Potvrda o dodavanju korisnika");
            alert.setContentText("Da li želite dodati korisnika "+imeTF.getText()+" "+prezimeTF.getText()+"?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                 KorisnikService.korisnikService.spasi(kor);
                 potvrda.show();
            } else if (result.get() == ButtonType.CANCEL){
                return;
            }
        }
    }

    @FXML
    private void IzbrsiKorisnika(MouseEvent event) {
      
        greska.setTitle("Nije uspijelo!");
        greska.setHeaderText("Greska:");
        greska.setContentText("Korisnik ne postoji");

        if(odabraniKorisnik != null){
              
            potvrda.setTitle("Test");
            potvrda.setHeaderText("Rezultat:");
            potvrda.setContentText("Korisnik je uspješno obrisan!");

            alert.setTitle("Brisanje korisnika");
            alert.setHeaderText("Potvrda o brisanju korisnika");
            alert.setContentText("Da li želite obrisati korisnika "+odabraniKorisnik.getIme()+" "+odabraniKorisnik.getPrezime()+"?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                  KorisnikService.korisnikService.brisi(odabraniKorisnik);                
                 potvrda.show();
            } else if (result.get() == ButtonType.CANCEL){
                return;
              }
        }else{
            greska.show();
        }
        
    }

    @FXML
    private void ObrisiOdabrano(MouseEvent event) {
    }

    @FXML
    private void PromjeniPlacu(MouseEvent event) {
        Placa p= new Placa();
        
        Float placa= Float.parseFloat(PlacaTF.getText());
        p.setKorisnikID(odabraniKorisnik);
        p.setIznosMjesecnePlace(placa);
        p.setIznosDnevnePlace(placa/30);
        p.setIznosGodisnjePlace(placa*12);
        
        potvrda.setTitle("Test");
        potvrda.setHeaderText("Rezultat:");
        potvrda.setContentText("Placa je uspješno uredjena!");
 
        alert.setTitle("Promjena place");
        alert.setHeaderText("Potvrda o promjeni place");
        alert.setContentText("Da li želite promjenuti placu za zaposlenika "+odabraniKorisnik.getIme()+" "+odabraniKorisnik.getPrezime()+"?");
        
        greska.setTitle("Nije uspijelo!");
        greska.setHeaderText("Greska:");
        greska.setContentText("Greska prilikom dodavanja place");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
             PlacaService.placaService.uredi(p);
             potvrda.show();
        } else if (result.get() == ButtonType.CANCEL){
            return;
        }else{
            greska.show();
        }
        
       
    }

    @FXML
    private void PostaviBonus(MouseEvent event) {
        Placa p= new Placa();
        Float bonus= Float.parseFloat(BonusTF.getText());
        p.setKorisnikID(odabraniKorisnik);
        p.setGodisnjiBonus(bonus);
        
        potvrda.setTitle("Test");
        potvrda.setHeaderText("Rezultat:");
        potvrda.setContentText("Bonus je uspjesno promjenut!");
 
        alert.setTitle("Postavjanje bonusa");
        alert.setHeaderText("Potvrda o promjeni bonusa");
        alert.setContentText("Da li želite promjenuti bonus za zaposlenika "+odabraniKorisnik.getIme()+" "+odabraniKorisnik.getPrezime()+"?");
        
        greska.setTitle("Nije uspijelo!");
        greska.setHeaderText("Greska:");
        greska.setContentText("Greska prilikom dodavanja bonusa");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
              PlacaService.placaService.godisnjiBonus(p);
             potvrda.show();
        } else if (result.get() == ButtonType.CANCEL){
            return;
        }else{
            greska.show();
        }
      
    }

    @FXML
    private void PostaviPlacu(MouseEvent event) {
        Placa p= new Placa();
        Float placa= Float.parseFloat(PlacaTF.getText());
        p.setKorisnikID(odabraniKorisnik);
        p.setIznosMjesecnePlace(placa);
        p.setIznosDnevnePlace(placa/30);
        p.setIznosGodisnjePlace(placa*12);
        
        
        potvrda.setTitle("Test");
        potvrda.setHeaderText("Rezultat:");
        potvrda.setContentText("Placa je uspješno dodana!");
 
        alert.setTitle("Dodavanje place");
        alert.setHeaderText("Potvrda o dodavanju place");
        alert.setContentText("Da li želite dodati placu za zaposlenika "+odabraniKorisnik.getIme()+" "+odabraniKorisnik.getPrezime()+"?");
        
        greska.setTitle("Nije uspijelo!");
        greska.setHeaderText("Greska:");
        greska.setContentText("Greska prilikom dodavanja place");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
             PlacaService.placaService.spasi(p);
             potvrda.show();
        } else if (result.get() == ButtonType.CANCEL){
            return;
        }else{
            greska.show();
        }
        
        
    }  
}
