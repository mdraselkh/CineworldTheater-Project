package cineworldcinema;

import static cineworldcinema.database.UserName;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import static java.lang.System.exit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class HomeController implements Initializable {

    @FXML
    private JFXButton btnUpcomingMovie;
    @FXML
    private JFXButton btnMovieScreen;
    @FXML
    private JFXButton btnAboutUs;
 
   
    @FXML
    private BorderPane btnBorderPane;
    @FXML
    private JFXButton btnHome;
    @FXML
    private JFXButton btnContactUs;
    @FXML
    private Label profilerName;
    @FXML
    private JFXButton btnlogout1;
    @FXML
    private JFXButton btnProfile;
    @FXML
    private JFXButton btnHistory;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            open();
        } catch (IOException ex) {

        }
        profilerName.setText(UserName);
        
    }

    private void close(MouseEvent event) {
        exit(0);
    }

    void open() throws IOException {

        AnchorPane Pane = FXMLLoader.load(getClass().getResource("/FXMLs/Movies.fxml"));
        btnBorderPane.setCenter(Pane);
    }

    @FXML
    void Home(ActionEvent event) throws IOException {

        open();
    }

    @FXML
    private void UpcomingMovie(ActionEvent event) throws IOException {
        AnchorPane Pane = FXMLLoader.load(getClass().getResource("/FXMLs/UpcomingMovie.fxml"));
        btnBorderPane.setCenter(Pane);
    }

    @FXML
    void AboutAction(ActionEvent event) throws IOException {
        AnchorPane Pane = FXMLLoader.load(getClass().getResource("/FXMLs/AboutUs.fxml"));
        btnBorderPane.setCenter(Pane);
    }


    @FXML
    void ProfileAction(ActionEvent event) throws IOException {
        AnchorPane Pane = FXMLLoader.load(getClass().getResource("/FXMLs/Profile.fxml"));
        btnBorderPane.setCenter(Pane);
    }

    @FXML
    public void LogoutAction(ActionEvent event) throws IOException {

        int returnValue = JOptionPane.showConfirmDialog(null, "Want to exit?", "Are You Sure?", JOptionPane.YES_NO_OPTION);
        System.out.println(returnValue);

        if (returnValue == JOptionPane.YES_OPTION) {

            btnlogout1.getScene().getWindow().hide();

            Parent signIn = FXMLLoader.load(getClass().getResource("/FXMLs/signIn.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setScene(new Scene(signIn));
            primaryStage.setTitle("Sign In");
            primaryStage.show();
        }
    }

    @FXML
    private void movieScreenAction(ActionEvent event) throws IOException {
          AnchorPane Pane = FXMLLoader.load(getClass().getResource("/FXMLs/movieScreen.fxml"));
        btnBorderPane.setCenter(Pane);
    }


    @FXML
    private void Contact(ActionEvent event) throws IOException {
         AnchorPane Pane = FXMLLoader.load(getClass().getResource("/FXMLs/Contact.fxml"));
        btnBorderPane.setCenter(Pane);
    }

    @FXML
    private void HistoryAction(ActionEvent event) throws IOException {
        AnchorPane Pane = FXMLLoader.load(getClass().getResource("/FXMLs/History.fxml"));
        btnBorderPane.setCenter(Pane); 
    }

}
