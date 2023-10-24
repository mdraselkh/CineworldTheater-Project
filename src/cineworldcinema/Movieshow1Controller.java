package cineworldcinema;

import static cineworldcinema.MoviesController.movieid;
import static cineworldcinema.SignInController.guest;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Movieshow1Controller implements Initializable {

    ;
    @FXML
    private Label name;
    @FXML
    private Label genre;
    @FXML
    private Label dirname;
    @FXML
    private Label cast;
    @FXML
    private Label date;
    @FXML
    private Label description;
   
    @FXML
    private Label duration;
    @FXML
    private ImageView poster;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            showmovieinfo();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void showmovieinfo() throws SQLException, FileNotFoundException, IOException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cineworld cinema", "root", "");

        String query = "select *from movies";

        Statement stm = con.createStatement();

        ResultSet rs = stm.executeQuery(query);

        while (rs.next()) {
            if (rs.getInt("Serial") == movieid) {
                
                name.setText(rs.getString("MovieName"));
                genre.setText(rs.getString("Genre"));
                description.setText(rs.getString("Description"));
                dirname.setText(rs.getString("Director"));
                cast.setText(rs.getString("Cast"));
                date.setText(rs.getString("ReleaseDate"));
                duration.setText(rs.getString("Duration"));

                InputStream is = rs.getBinaryStream("Poster");
                OutputStream os = new FileOutputStream(new File("photo.jpg"));

                byte[] content = new byte[1024];
                int size = 0;

                while ((size = is.read(content)) != -1) {

                    os.write(content, 0, size);
                }
                Image image = new Image("file:photo.jpg");
                poster.setImage(image);
                break;
            }
        }

        con.close();
    }

    @FXML
    private void backAction(ActionEvent event) throws IOException {

        if (guest) {
            try {
                Parent Home = FXMLLoader.load(getClass().getResource("/FXMLs/GuestLogIn.fxml"));

                Stage home = (Stage) ((Node) event.getSource()).getScene().getWindow();

                home.setScene(new Scene(Home));
                home.setTitle("Welcome");
                home.show();
            } catch (IOException ex) {
                System.out.println("Can't load");
            }
        } else {
            try {
                Parent Home = FXMLLoader.load(getClass().getResource("/FXMLs/Home.fxml"));

                Stage home = (Stage) ((Node) event.getSource()).getScene().getWindow();

                home.setScene(new Scene(Home));
                home.setTitle("Welcome");
                home.show();
            } catch (IOException ex) {
                System.out.println("Can't load");
            }
        }

    }

}
