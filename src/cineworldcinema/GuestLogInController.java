
package cineworldcinema;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GuestLogInController implements Initializable {

    @FXML
    private AnchorPane borderPane;
    @FXML
    private BorderPane BorderPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            open();
        } catch (IOException ex) {

        }
    }    
    void open() throws IOException {

        AnchorPane Pane = FXMLLoader.load(getClass().getResource("/FXMLs/Movies.fxml"));
        BorderPane.setCenter(Pane);
    }

    @FXML
    private void Home(ActionEvent event) throws IOException {
        
         open();
    }

    @FXML
    private void UpcomingMovie(ActionEvent event) throws IOException {
        AnchorPane Pane = FXMLLoader.load(getClass().getResource("/FXMLs/UpcomingMovie.fxml"));
        BorderPane.setCenter(Pane);
    }

    @FXML
    private void movieScreenAction(ActionEvent event) throws IOException {
         AnchorPane Pane = FXMLLoader.load(getClass().getResource("/FXMLs/movieScreen.fxml"));
        BorderPane.setCenter(Pane);
    }

    @FXML
    private void AboutAction(ActionEvent event) throws IOException {
         AnchorPane Pane = FXMLLoader.load(getClass().getResource("/FXMLs/AboutUs.fxml"));
        BorderPane.setCenter(Pane);
    }

    @FXML
    private void Contact(ActionEvent event) throws IOException {
         AnchorPane Pane = FXMLLoader.load(getClass().getResource("/FXMLs/Contact.fxml"));
        BorderPane.setCenter(Pane);
    }

    @FXML
    private void ExitAction(ActionEvent event) {
         try {
            Parent Home = FXMLLoader.load(getClass().getResource("/FXMLs/signIn.fxml"));

            Stage home = (Stage) ((Node) event.getSource()).getScene().getWindow();

            home.setScene(new Scene(Home));
            home.setTitle("Welcome");
            home.show();
        } catch (IOException ex) {
            System.out.println("Can't load");
        }
    }
}
