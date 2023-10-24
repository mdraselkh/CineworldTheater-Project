package cineworldcinema;

import static cineworldcinema.MoviesInfoController.movieSerial;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

public class AllocateMovieController implements Initializable {

    @FXML
    private Label nMovie;
    @FXML
    private ImageView pMovie;
    @FXML
    private JFXDatePicker startDate;
    @FXML
    private JFXDatePicker endDate;
    @FXML
    private JFXCheckBox screen1;
    @FXML
    private JFXCheckBox screen2;
    @FXML
    private JFXCheckBox screen3;
    @FXML
    private JFXCheckBox slot1;
    @FXML
    private JFXCheckBox slot2;
    @FXML
    private TextField price;
    @FXML
    private JFXCheckBox slot3;
    @FXML
    private AnchorPane rootPane;

    @Override

    public void initialize(URL url, ResourceBundle rb) {
        display();
    }

    void display() {
        Connection con;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cineworld cinema", "root", "");
            String query = "select *from movies where Serial=" + movieSerial;
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);

            if (rs.next()) {
                nMovie.setText((rs.getString("MovieName")));

                InputStream is = rs.getBinaryStream("Poster");
                OutputStream os = new FileOutputStream(new File("photo.jpg"));

                byte[] content = new byte[1024];
                int size = 0;

                while ((size = is.read(content)) != -1) {

                    os.write(content, 0, size);
                }
                Image image = new Image("file:photo.jpg");
                pMovie.setImage(image);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void saveAction(ActionEvent event) {

        String stDate = startDate.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String edDate = endDate.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String status = "Showing";
        String scrn1 = "";
        String scrn2 = "";
        String scrn3 = "";
        String slt1 = "";
        String slt2 = "";
        String slt3 = "";

        if (screen1.isSelected()) {
            scrn1 = screen1.getText();
        }
        if (screen2.isSelected()) {
            scrn2 = screen2.getText();
        }
        if (screen3.isSelected()) {
            scrn3 = screen3.getText();
        }
        if (slot1.isSelected()) {
            slt1 = slot1.getText();
        }
        if (slot2.isSelected()) {
            slt2 = slot2.getText();
        }
        if (slot3.isSelected()) {
            slt3 = slot3.getText();
        }

        Connection con;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cineworld cinema", "root", "");

            String query = "update movies set Status=?, StartDate=?, EndDate=?, Screen1=?, Screen2=?, Screen3=?,"
                    + " ShowTime1=?, ShowTime2=?, ShowTime3=?, Price=? where Serial=?";
            PreparedStatement pstm = con.prepareStatement(query);

            pstm.setString(1, status);
            pstm.setString(2, stDate);
            pstm.setString(3, edDate);
            pstm.setString(4, scrn1);
            pstm.setString(5, scrn2);
            pstm.setString(6, scrn3);
            pstm.setString(7, slt1);
            pstm.setString(8, slt2);
            pstm.setString(9, slt3);
            pstm.setString(10, price.getText());
            pstm.setInt(11, movieSerial);
            pstm.executeUpdate();
            con.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        int value = JOptionPane.showConfirmDialog(null, "Are You Sure", "Allocate Movie", JOptionPane.YES_NO_OPTION);

        if (value == JOptionPane.YES_OPTION) {
            rootPane.getScene().getWindow().hide();

        } else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Invalid Input");
            a.show();
        }

    }

}
