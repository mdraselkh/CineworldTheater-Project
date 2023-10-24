package cineworldcinema;

import static cineworldcinema.SeatReserveController.TICKET;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class TicketInfoController implements Initializable {

    @FXML
    private AnchorPane popUpPane;
    @FXML
    private Label number;
    @FXML
    private Label name;
    @FXML
    private Label movie;
    @FXML
    private Label date;
    @FXML
    private Label time;
    @FXML
    private Label screen;
    @FXML
    private Label seat;
    @FXML
    private Label price;
    private OutputStream os;
    private float initialize;
    private Object rs;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cineworld cinema", "root", "");
            Statement stm = con.createStatement();
            String query = "SELECT userName, movieName, Screen, ShowTime, ShowDate, SeatNo, TotalPrice from booking where TicketID=" + TICKET;
            ResultSet rs = stm.executeQuery(query);

            if (rs.next()) {
                number.setText(Integer.toString(TICKET));
                name.setText(rs.getString("userName"));
                movie.setText(rs.getString("movieName"));
                date.setText(rs.getString("ShowDate"));
                time.setText(rs.getString("ShowTime"));
                screen.setText(rs.getString("Screen"));
                seat.setText(rs.getString("SeatNo"));
                price.setText(Integer.toString(rs.getInt("TotalPrice")));
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void okayButton(ActionEvent event) throws DocumentException, FileNotFoundException {
        popUpPane.getScene().getWindow().hide();
    }

    @FXML
    private void printTicketAction(ActionEvent event) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("creatpdf.pdf"));
            System.out.println("success");
            document.open();

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cineworld cinema", "root", "");
            Statement stm = con.createStatement();
            String query = "SELECT userName, movieName, Screen, ShowTime, ShowDate, SeatNo, TotalPrice from booking where TicketID=" + TICKET;
            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {
                Paragraph para = new Paragraph(((rs.getString("userName") + "\n " + rs.getString("movieName")) + "\n " + rs.getString("ShowDate")) + "\n " + rs.getString("ShowTime") + " \n" + rs.getString("Screen") + "\n " + rs.getString("SeatNo"));
                document.add(para);
                document.close();
            }
            System.out.println("again success");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
