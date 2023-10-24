package cineworldcinema;

import static cineworldcinema.SignInController.adminUserName;
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
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class AdminController implements Initializable {

    @FXML
    private BorderPane borderPane;

    @FXML
    private Label showAdminUserName;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showAdminUserName.setText(adminUserName);

        try {
            Pane view = new FXMLLoader().load(getClass().getResource("/FXMLs/MoviesInfo.fxml"));
            borderPane.setCenter(view);
        } catch (Exception ex) {
            System.out.println("can't load");
        }
    }

    @FXML
    private void ManageMovieAction(ActionEvent event) throws IOException {

        Pane view = new FXMLLoader().load(getClass().getResource("/FXMLs/MoviesInfo.fxml"));
        borderPane.setCenter(view);
    }

    @FXML
    private void ManageUserAction(ActionEvent event) throws IOException {

        Pane view = new FXMLLoader().load(getClass().getResource("/FXMLs/UsersList.fxml"));
        borderPane.setCenter(view);

    }

    @FXML
    private void adminLogOut(ActionEvent event) throws IOException {
        int returnValue = JOptionPane.showConfirmDialog(null, "Are You Sure?", "Want to exit?", JOptionPane.YES_NO_OPTION);
        System.out.println(returnValue);

        if (returnValue == JOptionPane.YES_OPTION) {
            Parent signIn = FXMLLoader.load(getClass().getResource("/FXMLs/signIn.fxml"));

            Stage SignInWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

            SignInWindow.setScene(new Scene(signIn));
            SignInWindow.setTitle("Sign In");
            SignInWindow.show();
        }

    }

    @FXML
    private void manageAdmin(ActionEvent event) throws IOException {
        Pane view = new FXMLLoader().load(getClass().getResource("/FXMLs/AdminsList.fxml"));
        borderPane.setCenter(view);
    }

    @FXML
    private void adminEditProfile(ActionEvent event) throws IOException {
        Pane view = new FXMLLoader().load(getClass().getResource("/FXMLs/AdminUpdate.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(view));
        stage.show();
    }

    @FXML
    private void HistoryAction(ActionEvent event) throws IOException {
        Pane view = new FXMLLoader().load(getClass().getResource("/FXMLs/BookingHistory.fxml"));
        borderPane.setCenter(view);
    }
}
