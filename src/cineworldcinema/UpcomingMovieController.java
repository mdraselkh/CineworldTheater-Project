package cineworldcinema;

import java.io.File;
import java.io.FileNotFoundException;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class UpcomingMovieController implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            display();
        } catch (Exception ex) {

        }
    }

    void display() throws SQLException, FileNotFoundException, IOException {

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
            if (rs.getString("Status").equals("Upcoming")) {

                InputStream is = rs.getBinaryStream("Poster");
                OutputStream os = new FileOutputStream(new File("photo.jpg"));

                byte[] content = new byte[1024];
                int size = 0;

                while ((size = is.read(content)) != -1) {

                    os.write(content, 0, size);
                }

                image[count] = new Image("file:photo.jpg");
                movieName[count] = rs.getString("MovieName");
                g[count]=rs.getString(("Genre"));

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
}
