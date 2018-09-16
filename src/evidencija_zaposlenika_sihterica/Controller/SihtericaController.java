/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evidencija_zaposlenika_sihterica.Controller;

import Services.KorisnikService;
import Services.LoginService;
import Services.PrisutnostService;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import evidencija_zaposlenika_sihterica.ControlledScreen;
import evidencija_zaposlenika_sihterica.Evidencija_zaposlenika_sihterica;
import evidencija_zaposlenika_sihterica.Model.Korisnik;
import evidencija_zaposlenika_sihterica.Model.Prisutnost;
import evidencija_zaposlenika_sihterica.ScreensController;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author toni
 */
public class SihtericaController implements Initializable ,ControlledScreen{
    ScreensController myController;
    
    public Prisutnost prisutnost= new Prisutnost();

    @FXML
    private FontAwesomeIconView otvoriGodisnjuBtn;
    @FXML
    private TilePane tile_pane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GetZaposlenici();
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

    @FXML
    private void OtvoriSihtaricu(MouseEvent event) {
        myController.loadScreen(Evidencija_zaposlenika_sihterica.screen6ID, Evidencija_zaposlenika_sihterica.screen6File);
        myController.setScreen(Evidencija_zaposlenika_sihterica.screen6ID);
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }
    
    private void GetZaposlenici(){
         
    int firma=LoginService.logiraniKorisnik().getFirma().getID();
    ObservableList<Korisnik> korisnici= KorisnikService.korisnikService.dajZaposlenike(firma);
           
    tile_pane.setVgap(20);
    tile_pane.setHgap(-25);
      
    for(Korisnik korisnik : korisnici){
        
        Text t = new Text();
        t.setText(korisnik.getIme()+" "+korisnik.getPrezime());        
        
        t.setFont(Font.font ("Arial", FontWeight.BOLD,  11));
        t.setFill(Color.BLACK);
     
        ImageView slika = new ImageView(korisnik.getSlika() ); 
        slika.setFitHeight(50);
        slika.setFitWidth(50);
       
        Button btn= new Button();
        btn.setText("Sihtarica");
        btn.alignmentProperty();
        HBox hbox= new HBox();
        VBox vbox = new VBox();
        hbox.setSpacing(10);
                 
         btn.setOnMouseClicked((event) -> {           
            prisutnost=PrisutnostService.prisutnostService.izBazePremaId(korisnik.getId());
            PopuniSihtericu(2);         
        });
         
        hbox.setPrefWidth(150);
        hbox.setMinHeight(75);
        hbox.setMinHeight(75);      
        vbox.setMaxWidth(75);     
        hbox.setAlignment(Pos.CENTER_LEFT);
        vbox.setAlignment(Pos.CENTER_RIGHT);
        hbox.getChildren().addAll(slika,t);
        vbox.getChildren().addAll(btn);
                    
        tile_pane.getChildren().addAll(hbox,vbox); 

    }
}

    @FXML
    private void otvoriSihtericuZaSve(MouseEvent event) {
        PopuniSihtericu(1);
    }
    
    private void PopuniSihtericu(int mode){
        JFileChooser dialog = new JFileChooser();
        dialog.setSelectedFile(new File("Sihterica.pdf"));
        int dialogResult = dialog.showSaveDialog(null);
        if (dialogResult==JFileChooser.APPROVE_OPTION){
            String filePath = dialog.getSelectedFile().getPath();

            try {

               Document myDocument = new Document();
               PdfWriter myWriter = PdfWriter.getInstance(myDocument, new FileOutputStream(filePath ));
               PdfPTable table = new PdfPTable(32);
               myDocument.open();


               float[] columnWidths = new float[] {15,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4};
               table.setWidths(columnWidths);

               table.setWidthPercentage(100);


                myDocument.add(new Paragraph("Zaposlenici sihterica",FontFactory.getFont(FontFactory.TIMES_ROMAN,20,java.awt.Font.BOLD )));
                myDocument.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));

                LocalDateTime timePoint = LocalDateTime.now();

                myDocument.add(new Paragraph(timePoint.getDayOfMonth()+"."+timePoint.getMonthValue()+"."+timePoint.getYear()+"    "+timePoint.getHour()+":"+timePoint.getMinute()+" h"  , FontFactory.getFont(FontFactory.TIMES_ROMAN,15,java.awt.Font.BOLD ) ));
                myDocument.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));

                table.addCell(new PdfPCell(new Paragraph("Ime i prezime",FontFactory.getFont(FontFactory.TIMES_ROMAN,9,java.awt.Font.PLAIN))));
                for(int i = 1; i <32; i++)
                {
                      table.addCell(new PdfPCell(new Paragraph(i+".7",FontFactory.getFont(FontFactory.TIMES_BOLDITALIC,7,java.awt.Font.PLAIN))));
                }

                if(mode == 1){
                int firma=LoginService.logiraniKorisnik().getFirma().getID();
                ObservableList<Prisutnost> prisutnosti= (ObservableList<Prisutnost>) PrisutnostService.prisutnostService.CijelaSihtarica(firma);
                    for(Prisutnost p:prisutnosti){             
                        table.addCell(new PdfPCell(new Paragraph(p.getKorisnikID().getIme()+" "+p.getKorisnikID().getPrezime(),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.PLAIN))));            
                        for(int i = 1; i< 32; i++){
                        table.addCell(new PdfPCell(new Paragraph(String.valueOf(PrisutnostService.prisutnostService.GetDay(p.getKorisnikID().getId(), i)),FontFactory.getFont(FontFactory.TIMES_ROMAN,6,java.awt.Font.PLAIN))));
                        }
                    }
                }

                if(mode == 2){
                  table.addCell(new PdfPCell(new Paragraph(prisutnost.getKorisnikID().getIme()+" "+prisutnost.getKorisnikID().getPrezime(),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.PLAIN))));
                       for(int i = 1; i< 32; i++){
                           table.addCell(new PdfPCell(new Paragraph(String.valueOf(PrisutnostService.prisutnostService.GetDay(prisutnost.getKorisnikID().getId(), i)),FontFactory.getFont(FontFactory.TIMES_ROMAN,7,java.awt.Font.PLAIN))));
                       }
                }

                myDocument.add(table);
                myDocument.add(new Paragraph("--------------------------------------------------------------------------------------------------------------------"));
                myDocument.close();  
                JOptionPane.showMessageDialog(null,"Sihterica uspjesno kreirana");

            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,e);        
            }
    //     finally {
    //            
    //            try{
    ////                rs.close();
    ////                pst.close();
    ////                
    //            }
    //            catch(Exception e){
    //            JOptionPane.showMessageDialog(null,e);
    //         
    //            }
    //     }     
        }
    }

}