package cineworldcinema;

import static cineworldcinema.SignInController.guest;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MoviesController implements Initializable {

    static int movieid;
    int ID1 = 0, ID2 = 0, ID3 = 0, ID4 = 0, ID5 = 0, ID6 = 0, ID7 = 0, ID8 = 0, ID9 = 0;
    int[] id = {ID1, ID2, ID3, ID4, ID5, ID6, ID7, ID8, ID9};

    @FXML
    private ImageView img1;
    @FXML
    private Label name1;
    @FXML
    private Label genre1;
    @FXML
    private ImageView img2;
    @FXML
    private Label genre2;
    @FXML
    private ImageView img3;
    @FXML
    private Label name3;
    @FXML
    private Label genre3;
    @FXML
    private ImageView img11;
    @FXML
    private ImageView img12;
    @FXML
    private ImageView img13;
    @FXML
    private Label name11;
    @FXML
    private Label name12;
    @FXML
    private Label name13;
    @FXML
    private Label genre11;
    @FXML
    private Label genre12;
    @FXML
    private Label genre13;
    @FXML
    private ImageView img111;
    @FXML
    private ImageView img112;
    @FXML
    private ImageView img113;
    @FXML
    private Label name111;
    @FXML
    private Label name112;
    @FXML
    private Label name113;
    @FXML
    private Label name2;
    @FXML
    private ImageView img4;
    @FXML
    private ImageView img5;
    @FXML
    private ImageView img6;
    @FXML
    private Label name4;
    @FXML
    private Label name5;
    @FXML
    private Label name6;
    @FXML
    private Label genre4;
    @FXML
    private Label genre5;
    @FXML
    private Label genre6;
    @FXML
    private ImageView img7;
    @FXML
    private ImageView img8;
    @FXML
    private ImageView img9;
    @FXML
    private Label name7;
    @FXML
    private Label name8;
    @FXML
    private Label name9;
    @FXML
    private Label genre7;
    @FXML
    private Label genre8;
    @FXML
    private Label genre9;
    @FXML
    private JFXButton bookbtn1;
    @FXML
    private JFXButton bookbtn2;
    @FXML
    private JFXButton bookbtn3;
    @FXML
    private JFXButton bookbtn4;
    @FXML
    private JFXButton bookbtn5;
    @FXML
    private JFXButton bookbtn6;
    @FXML
    private JFXButton bookbtn8;
    @FXML
    private JFXButton bookbtn7;
    @FXML
    private JFXButton bookbtn9;
    private Object movieTable;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private JFXButton viewbtn1;
    @FXML
    private JFXButton viewbtn2;
    @FXML
    private JFXButton viewbtn3;
    @FXML
    private JFXButton viewbtn4;
    @FXML
    private JFXButton viewbtn5;
    @FXML
    private JFXButton viewbtn6;
    @FXML
    private JFXButton viewbtn8;
    @FXML
    private JFXButton viewbtn7;
    @FXML
    private JFXButton viewbtn9;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            show();
        } catch (Exception ex) {

        }
    }

    void show() throws SQLException, IOException {

        ImageView[] poster = {img1, img2, img3, img4, img5, img6, img7, img8, img9};
        Label[] name = {name1, name2, name3, name4, name5, name6, name7, name8, name9};
        Label[] genre = {genre1, genre2, genre3, genre4, genre5, genre6, genre7, genre8, genre9};

        Image image1 = null, image2 = null, image3 = null, image4 = null, image5 = null;
        Image image6 = null, image7 = null, image8 = null, image9 = null;
        Image[] image = {image1, image2, image3, image4, image5, image6, image7, image8, image9};

        String movieName1 = null, movieName2 = null, movieName3 = null, movieName4 = null, movieName5 = null,
                movieName6 = null, movieName7 = null, movieName8 = null, movieName9 = null;
        String[] movieName = {movieName1, movieName2, movieName3, movieName4, movieName5, movieName6,
            movieName7, movieName8, movieName9};

        String g1 = null, g2 = null, g3 = null, g4 = null, g5 = null, g6 = null, g7 = null, g8 = null, g9 = null;
        String[] g = {g1, g2, g3, g4, g5, g6, g7, g8, g9};

        int count = 0;

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cineworld cinema", "root", "");

        String query = "select *from movies";

        Statement stm = con.createStatement();

        ResultSet rs = stm.executeQuery(query);

        while (rs.next()) {
            if (rs.getString("Status").equals("Showing")) {

                InputStream is = rs.getBinaryStream("Poster");
                OutputStream os = new FileOutputStream(new File("photo.jpg"));

                byte[] content = new byte[1024];
                int size = 0;

                while ((size = is.read(content)) != -1) {

                    os.write(content, 0, size);
                }

                image[count] = new Image("file:photo.jpg");
                movieName[count] = rs.getString("MovieName");
                g[count] = rs.getString(("Genre"));
                id[count] = rs.getInt("Serial");
                if (count == 9) {
                    break;
                }
                count++;
            }

        }

        for (int i = 0; i <= 9; i++) {
            poster[i].setImage(image[i]);
            name[i].setText(movieName[i]);
            genre[i].setText(g[i]);

        }
    }

    void movieDetails() {
        try {
            Parent show1 = FXMLLoader.load(getClass().getResource("/FXMLs/movieshow1.fxml"));
            Scene scene = new Scene(show1);
            Stage stage = (Stage) rootPane.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Movie Window");
            stage.show();

        } catch (Exception e) {
            System.out.println("can't load");
        }
    }

    void bookTheShow() {

        if (guest) {
            Alert a = new Alert(AlertType.ERROR);
            a.setContentText("You Have To Log In To Book The Show");
            a.show();

        } else {
            try {
                Parent show1 = FXMLLoader.load(getClass().getResource("/FXMLs/SeatReserve.fxml"));
                Scene scene = new Scene(show1);
                Stage stage = (Stage) rootPane.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Movie Window");
                stage.show();

            } catch (Exception e) {
                System.out.println("can't load");
            }
        }

    }

    @FXML
    private void viewAction1(ActionEvent event) {
        movieid = id[0];
        movieDetails();
    }

    @FXML
    private void viewAction2(ActionEvent event) {
        movieid = id[1];
        movieDetails();
    }

    @FXML
    private void viewAction3(ActionEvent event) {
        movieid = id[2];
        movieDetails();
    }

    @FXML
    private void viewAction4(ActionEvent event) {
        movieid = id[3];
        movieDetails();
    }

    @FXML
    private void viewAction5(ActionEvent event) {
        movieid = id[4];
        movieDetails();
    }

    @FXML
    private void viewAction6(ActionEvent event) {
        movieid = id[5];
        movieDetails();
    }

    @FXML
    void viewAction7(ActionEvent event) {
        movieid = id[6];
        movieDetails();
    }

    @FXML
    void viewAction8(ActionEvent event) {
        movieid = id[7];
        movieDetails();
    }

    @FXML
    private void viewAction9(ActionEvent event) {
        movieid = id[8];
        movieDetails();
    }

    @FXML
    private void bookAction1(ActionEvent event) {
        movieid = id[0];
        bookTheShow();
    }

    @FXML
    private void bookAction2(ActionEvent event) {
        movieid = id[1];
        bookTheShow();
    }

    @FXML
    private void bookAction3(ActionEvent event) {
        movieid = id[2];
        bookTheShow();
    }

    @FXML
    private void bookAction4(ActionEvent event) {
        movieid = id[3];
        bookTheShow();
    }

    @FXML
    private void bookAction5(ActionEvent event) {
        movieid = id[4];
        bookTheShow();
    }

    @FXML
    private void bookAction6(ActionEvent event) {
        movieid = id[5];
        bookTheShow();
    }

    @FXML
    private void bookAction7(ActionEvent event) {
        movieid = id[6];
        bookTheShow();
    }

    @FXML
    private void bookAction8(ActionEvent event) {
        movieid = id[7];
        bookTheShow();
    }

    @FXML
    private void bookAction9(ActionEvent event) {
        movieid = id[8];
        bookTheShow();
    }
}
