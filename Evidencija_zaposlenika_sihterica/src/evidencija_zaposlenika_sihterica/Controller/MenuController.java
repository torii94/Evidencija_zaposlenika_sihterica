/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evidencija_zaposlenika_sihterica.Controller;
import Services.LoginService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import evidencija_zaposlenika_sihterica.ControlledScreen;
import evidencija_zaposlenika_sihterica.Evidencija_zaposlenika_sihterica;
import evidencija_zaposlenika_sihterica.Model.Korisnik;
import evidencija_zaposlenika_sihterica.ScreensController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

/**
 *
 * @author toni
 */
public class MenuController implements Initializable, ControlledScreen {

    ScreensController myController;
   
    public Korisnik odabraniKorisnik;
    public Korisnik prjavljeni;
    
    @FXML
    private Pane main_pane;
    @FXML
    private Button statistikaBTN;
    @FXML
    private Label ime_firme;
    @FXML
    private Label ime_prezimeLogirani;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Korisnik prijavljeni=LoginService.logiraniKorisnik();
        ime_firme.setText(prijavljeni.getFirma().getNaziv());
        ime_prezimeLogirani.setText(prijavljeni.getIme()+" "+prijavljeni.getPrezime());
    }
    
    

    @FXML
    private void otvoriStatistiku(javafx.scene.input.MouseEvent event) {
        myController.loadScreen(Evidencija_zaposlenika_sihterica.screen3ID, Evidencija_zaposlenika_sihterica.screen3File);
        myController.setScreen(Evidencija_zaposlenika_sihterica.screen3ID);
    }

//    
//   
//    private void otvori(javafx.scene.input.MouseEvent event) throws FileNotFoundException, DocumentException {
//        
//         
//	    JFileChooser dialog = new JFileChooser();
//            dialog.setSelectedFile(new File("Employees Report.pdf"));
//            int dialogResult = dialog.showSaveDialog(null);
//            if (dialogResult==JFileChooser.APPROVE_OPTION){
//            String filePath = dialog.getSelectedFile().getPath();
//           
//        try {
//            // TODO add your handling code here:
//            
//            
//            
//           
//           Document myDocument = new Document();
//           PdfWriter myWriter = PdfWriter.getInstance(myDocument, new FileOutputStream(filePath ));
//           PdfPTable table = new PdfPTable(31);
//           myDocument.open();
//           
//   
//           float[] columnWidths = new float[] {15,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3};
//           table.setWidths(columnWidths);
//
//           table.setWidthPercentage(100); //set table width to 100%
//           
//          
//          myDocument.add(new Paragraph("Zaposlenici sihterica",FontFactory.getFont(FontFactory.TIMES_BOLD,20,java.awt.Font.BOLD )));
//          myDocument.add(new Paragraph("----------------------------------------------------------------",FontFactory.getFont(FontFactory.TIMES_BOLD,5,java.awt.Font.BOLD )));
//
//          myDocument.add(new Paragraph(new java.util.Date().toString()));
//          myDocument.add(new Paragraph("----------------------------------------------------------------",FontFactory.getFont(FontFactory.TIMES_BOLD,5,java.awt.Font.BOLD )));
//
//       
//          table.addCell(new PdfPCell(new Paragraph("First Name",FontFactory.getFont(FontFactory.TIMES_ROMAN,10,java.awt.Font.BOLD))));
//          table.addCell(new PdfPCell(new Paragraph("P",FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.BOLD))));
//          table.addCell(new PdfPCell(new Paragraph("U",FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.BOLD))));
//          table.addCell(new PdfPCell(new Paragraph("S",FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.BOLD))));
//          table.addCell(new PdfPCell(new Paragraph("C",FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.BOLD))));
//          table.addCell(new PdfPCell(new Paragraph("P",FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.BOLD))));
//          table.addCell(new PdfPCell(new Paragraph("S",FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.BOLD))));
//          table.addCell(new PdfPCell(new Paragraph("N",FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.BOLD))));
//          table.addCell(new PdfPCell(new Paragraph("P",FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.BOLD))));
//          table.addCell(new PdfPCell(new Paragraph("U",FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.BOLD))));
//          table.addCell(new PdfPCell(new Paragraph("S",FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.BOLD))));
//          table.addCell(new PdfPCell(new Paragraph("C",FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.BOLD))));
//          table.addCell(new PdfPCell(new Paragraph("P",FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.BOLD))));
//          table.addCell(new PdfPCell(new Paragraph("S",FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.BOLD))));
//          table.addCell(new PdfPCell(new Paragraph("N",FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.BOLD))));
//          table.addCell(new PdfPCell(new Paragraph("P",FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.BOLD))));
//          table.addCell(new PdfPCell(new Paragraph("U",FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.BOLD))));
//          table.addCell(new PdfPCell(new Paragraph("S",FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.BOLD))));
//          table.addCell(new PdfPCell(new Paragraph("C",FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.BOLD))));
//          table.addCell(new PdfPCell(new Paragraph("P",FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.BOLD))));
//          table.addCell(new PdfPCell(new Paragraph("S",FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.BOLD))));
//          table.addCell(new PdfPCell(new Paragraph("N",FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.BOLD))));
//          table.addCell(new PdfPCell(new Paragraph("P",FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.BOLD))));
//          table.addCell(new PdfPCell(new Paragraph("U",FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.BOLD))));
//          table.addCell(new PdfPCell(new Paragraph("S",FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.BOLD))));
//          table.addCell(new PdfPCell(new Paragraph("C",FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.BOLD))));
//          table.addCell(new PdfPCell(new Paragraph("P",FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.BOLD))));
//          table.addCell(new PdfPCell(new Paragraph("S",FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.BOLD))));
//          table.addCell(new PdfPCell(new Paragraph("N",FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.BOLD))));
//          table.addCell(new PdfPCell(new Paragraph("P",FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.BOLD))));
//          table.addCell(new PdfPCell(new Paragraph("U",FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.BOLD))));
//          
//          
//          
//          
//          
//          
//          
//          
//          
//          
//          
//          
////          while(rs.next())
////            {
//           //ObservableList<Korisnik> korisnici= KorisnikService.korisnikService.sveIzBaze();
//
//           //for(Korisnik k:korisnici){
//           ObservableList<Prisutnost> prisutnosti= (ObservableList<Prisutnost>) PrisutnostService.prisutnostService.sveIzBaze();
//           for(Prisutnost p:prisutnosti){
////           
////           table.addCell(new PdfPCell(new Paragraph(String.valueOf(k.getId()),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.PLAIN))));
//             
//
//             table.addCell(new PdfPCell(new Paragraph(p.getKorisnikID().getIme(),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.PLAIN))));
//             table.addCell(new PdfPCell(new Paragraph(String.valueOf(PrisutnostService.prisutnostService.GetDay(p.getKorisnikID().getId(), 1)),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.PLAIN))));
//             table.addCell(new PdfPCell(new Paragraph(String.valueOf(PrisutnostService.prisutnostService.GetDay(p.getKorisnikID().getId(), 2)),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.PLAIN))));
//             table.addCell(new PdfPCell(new Paragraph(String.valueOf(PrisutnostService.prisutnostService.GetDay(p.getKorisnikID().getId(), 3)),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.PLAIN))));
//             table.addCell(new PdfPCell(new Paragraph(String.valueOf(PrisutnostService.prisutnostService.GetDay(p.getKorisnikID().getId(), 4)),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.PLAIN))));
//             table.addCell(new PdfPCell(new Paragraph(String.valueOf(PrisutnostService.prisutnostService.GetDay(p.getKorisnikID().getId(), 5)),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.PLAIN))));
//             table.addCell(new PdfPCell(new Paragraph(String.valueOf(PrisutnostService.prisutnostService.GetDay(p.getKorisnikID().getId(), 6)),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.PLAIN))));
//             table.addCell(new PdfPCell(new Paragraph(String.valueOf(PrisutnostService.prisutnostService.GetDay(p.getKorisnikID().getId(), 7)),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.PLAIN))));
//             table.addCell(new PdfPCell(new Paragraph(String.valueOf(PrisutnostService.prisutnostService.GetDay(p.getKorisnikID().getId(), 8)),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.PLAIN))));
//             table.addCell(new PdfPCell(new Paragraph(String.valueOf(PrisutnostService.prisutnostService.GetDay(p.getKorisnikID().getId(), 9)),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.PLAIN))));
//             table.addCell(new PdfPCell(new Paragraph(String.valueOf(PrisutnostService.prisutnostService.GetDay(p.getKorisnikID().getId(), 10)),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.PLAIN))));
//             table.addCell(new PdfPCell(new Paragraph(String.valueOf(PrisutnostService.prisutnostService.GetDay(p.getKorisnikID().getId(), 11)),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.PLAIN))));
//             table.addCell(new PdfPCell(new Paragraph(String.valueOf(PrisutnostService.prisutnostService.GetDay(p.getKorisnikID().getId(), 12)),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.PLAIN))));
//             table.addCell(new PdfPCell(new Paragraph(String.valueOf(PrisutnostService.prisutnostService.GetDay(p.getKorisnikID().getId(), 13)),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.PLAIN))));
//             table.addCell(new PdfPCell(new Paragraph(String.valueOf(PrisutnostService.prisutnostService.GetDay(p.getKorisnikID().getId(), 14)),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.PLAIN))));
//             table.addCell(new PdfPCell(new Paragraph(String.valueOf(PrisutnostService.prisutnostService.GetDay(p.getKorisnikID().getId(), 15)),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.PLAIN))));
//             table.addCell(new PdfPCell(new Paragraph(String.valueOf(PrisutnostService.prisutnostService.GetDay(p.getKorisnikID().getId(), 16)),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.PLAIN))));
//             table.addCell(new PdfPCell(new Paragraph(String.valueOf(PrisutnostService.prisutnostService.GetDay(p.getKorisnikID().getId(), 17)),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.PLAIN))));
//             table.addCell(new PdfPCell(new Paragraph(String.valueOf(PrisutnostService.prisutnostService.GetDay(p.getKorisnikID().getId(), 18)),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.PLAIN))));
//             table.addCell(new PdfPCell(new Paragraph(String.valueOf(PrisutnostService.prisutnostService.GetDay(p.getKorisnikID().getId(), 19)),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.PLAIN))));
//             table.addCell(new PdfPCell(new Paragraph(String.valueOf(PrisutnostService.prisutnostService.GetDay(p.getKorisnikID().getId(), 20)),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.PLAIN))));
//             table.addCell(new PdfPCell(new Paragraph(String.valueOf(PrisutnostService.prisutnostService.GetDay(p.getKorisnikID().getId(), 21)),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.PLAIN))));
//             table.addCell(new PdfPCell(new Paragraph(String.valueOf(PrisutnostService.prisutnostService.GetDay(p.getKorisnikID().getId(), 22)),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.PLAIN))));
//             table.addCell(new PdfPCell(new Paragraph(String.valueOf(PrisutnostService.prisutnostService.GetDay(p.getKorisnikID().getId(), 23)),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.PLAIN))));
//             table.addCell(new PdfPCell(new Paragraph(String.valueOf(PrisutnostService.prisutnostService.GetDay(p.getKorisnikID().getId(), 24)),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.PLAIN))));
//             table.addCell(new PdfPCell(new Paragraph(String.valueOf(PrisutnostService.prisutnostService.GetDay(p.getKorisnikID().getId(), 25)),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.PLAIN))));
//             table.addCell(new PdfPCell(new Paragraph(String.valueOf(PrisutnostService.prisutnostService.GetDay(p.getKorisnikID().getId(), 26)),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.PLAIN))));
//             table.addCell(new PdfPCell(new Paragraph(String.valueOf(PrisutnostService.prisutnostService.GetDay(p.getKorisnikID().getId(), 27)),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.PLAIN))));
//             table.addCell(new PdfPCell(new Paragraph(String.valueOf(PrisutnostService.prisutnostService.GetDay(p.getKorisnikID().getId(), 28)),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.PLAIN))));
//              table.addCell(new PdfPCell(new Paragraph(String.valueOf(PrisutnostService.prisutnostService.GetDay(p.getKorisnikID().getId(), 29)),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.PLAIN))));
//             table.addCell(new PdfPCell(new Paragraph(String.valueOf(PrisutnostService.prisutnostService.GetDay(p.getKorisnikID().getId(), 30)),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.PLAIN))));
////           table.addCell(new PdfPCell(new Paragraph(String.valueOf(k.getDatumRodjenja()),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.PLAIN))));
////           table.addCell(new PdfPCell(new Paragraph(k.getEmail(),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.PLAIN))));
////           table.addCell(new PdfPCell(new Paragraph(k.getAdresa(),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.PLAIN))));
////           table.addCell(new PdfPCell(new Paragraph(k.getAdresa(),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.PLAIN))));
////           table.addCell(new PdfPCell(new Paragraph(k.getIme(),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.PLAIN))));
////           //table.addCell(new PdfPCell(new Paragraph(rs.getString(9),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,Font.PLAIN))));
////
////           table.addCell(new PdfPCell(new Paragraph(k.getIme(),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.PLAIN))));
////           table.addCell(new PdfPCell(new Paragraph(k.getIme(),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.PLAIN))));
////           table.addCell(new PdfPCell(new Paragraph(k.getIme(),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.PLAIN))));
////           table.addCell(new PdfPCell(new Paragraph(k.getIme(),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,java.awt.Font.PLAIN))));
//////           table.addCell(new PdfPCell(new Paragraph(rs.getString(14),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,Font.PLAIN))));
//////           table.addCell(new PdfPCell(new Paragraph(rs.getString(15),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,Font.PLAIN))));
////              
//          }
//           
//           myDocument.add(table);
//           myDocument.add(new Paragraph("--------------------------------------------------------------------------------------------"));
//           myDocument.close();  
//           JOptionPane.showMessageDialog(null,"Report was successfully generated");
//            
//     }
//        catch(Exception e){
//            JOptionPane.showMessageDialog(null,e);
//         
//         
//     }
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
//   }
//    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @FXML
    private void OtvoriZaposlenike(javafx.scene.input.MouseEvent event) {   
        myController.loadScreen(Evidencija_zaposlenika_sihterica.screen2ID, Evidencija_zaposlenika_sihterica.screen2File);
        myController.setScreen(Evidencija_zaposlenika_sihterica.screen2ID);                          
    }

    @FXML
    private void OtvoriBolovanja(javafx.scene.input.MouseEvent event) {  
        myController.loadScreen(Evidencija_zaposlenika_sihterica.screen4ID, Evidencija_zaposlenika_sihterica.screen4File);
        myController.setScreen(Evidencija_zaposlenika_sihterica.screen4ID);       
    }

    @FXML
    private void otvoriGodisnji(javafx.scene.input.MouseEvent event) { 
        myController.loadScreen(Evidencija_zaposlenika_sihterica.screen5ID, Evidencija_zaposlenika_sihterica.screen5File);
        myController.setScreen(Evidencija_zaposlenika_sihterica.screen5ID);       
    }

    @FXML
    private void OtvoriSihtaricu(javafx.scene.input.MouseEvent event) {
        myController.loadScreen(Evidencija_zaposlenika_sihterica.screen6ID, Evidencija_zaposlenika_sihterica.screen6File);
        myController.setScreen(Evidencija_zaposlenika_sihterica.screen6ID);
    }

    @FXML
    private void otvoriAktivneZaposlenike(javafx.scene.input.MouseEvent event) {
        myController.loadScreen(Evidencija_zaposlenika_sihterica.screen7ID, Evidencija_zaposlenika_sihterica.screen7File);
        myController.setScreen(Evidencija_zaposlenika_sihterica.screen7ID);
    }
    
}
