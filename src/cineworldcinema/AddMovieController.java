package cineworldcinema;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;

public class AddMovieController implements Initializable {

    @FXML
    private AnchorPane addMoviePane;
    @FXML
    private JFXTextField movieName;
    @FXML
    private JFXTextField directorName;
    @FXML
    private JFXTextArea castName;
    @FXML
    private JFXTextField genre;
    @FXML
    private JFXRadioButton showing;
    @FXML
    private ToggleGroup status;
    @FXML
    private JFXRadioButton upcoming;
    @FXML
    private JFXTextArea description;
    @FXML
    private JFXDatePicker releaseDate;
    @FXML
    private ImageView backImage;
    @FXML
    private ImageView moviePoster;
    @FXML
    private JFXTextField duration;
    @FXML
    private JFXTextField trailerLink;

    ByteArrayOutputStream bos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    private void submitAction(ActionEvent event) {

        try {
            RadioButton selectedButton = (RadioButton) status.getSelectedToggle();
            String st = selectedButton.getText();
            String date = releaseDate.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            String nullString = "";

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cineworld cinema", "root", "");
            String query = "insert into movies (MovieName,Director,Cast,Duration,Genre,Status,Trailer,"
                    + "ReleaseDate,Description,Poster,StartDate,EndDate,Screen1,Screen2,Screen3,ShowTime1,"
                    + "ShowTime2,ShowTime3,Price) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement pst = con.prepareStatement(query);

            pst.setString(1, movieName.getText());
            pst.setString(2, directorName.getText());
            pst.setString(3, castName.getText());
            pst.setString(4, duration.getText());
            pst.setString(5, genre.getText());
            pst.setString(6, st);
            pst.setString(7, trailerLink.getText());
            pst.setString(8, date);
            pst.setString(9, description.getText());
            pst.setBytes(10, bos.toByteArray());
            pst.setString(11, nullString);
            pst.setString(12, nullString);
            pst.setString(13, nullString);
            pst.setString(14, nullString);
            pst.setString(15, nullString);
            pst.setString(16, nullString);
            pst.setString(17, nullString);
            pst.setString(18, nullString);
            pst.setString(19, nullString);

            pst.execute();
            System.out.println("database insertion successful");

            int value = JOptionPane.showConfirmDialog(null, "Movie Add Complete", "New Movie", JOptionPane.OK_CANCEL_OPTION);
            if (value == JOptionPane.OK_OPTION) {
                addMoviePane.getScene().getWindow().hide();
            }
            con.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void uploadPosterAction(ActionEvent event) throws FileNotFoundException, IOException {
        backImage.setImage(null);

        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter ext1 = new FileChooser.ExtensionFilter("JPG files(*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter ext2 = new FileChooser.ExtensionFilter("PNG files(*.png)", "*.PNG");
        fc.getExtensionFilters().addAll(ext1, ext2);
        File file = fc.showOpenDialog(null);

        FileInputStream img = new FileInputStream(file);

        bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        for (int readNum; (readNum = img.read(buf)) != -1;) {
            bos.write(buf, 0, readNum);
        }

        Image poster = new Image(file.toURI().toString());
        moviePoster.setImage(poster);
        img.close();
    }

}
