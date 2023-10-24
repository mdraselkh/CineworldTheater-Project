package cineworldcinema;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class SignUpController implements Initializable {

    @FXML
    private Button singInButton;
    @FXML
    private JFXTextField nameTF;
    @FXML
    private JFXTextField mailTF;
    @FXML
    private JFXPasswordField passTF;
    @FXML
    private JFXButton signUpButton;
    @FXML
    private Label errName;
    @FXML
    private Label errPass;
    @FXML
    private Label errMail;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void signIn(ActionEvent event) {

        try {
            Parent signIn = FXMLLoader.load(getClass().getResource("/FXMLs/signIn.fxml"));

            Stage SingInWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            SingInWindow.setScene(new Scene(signIn));
            SingInWindow.show();

        } catch (Exception e) {
            System.out.println("Can't Load!!!");
        }

    }

    @FXML
    private void signUpAction(ActionEvent event) throws FileNotFoundException {
        database db = new database();

        String name = nameTF.getText();
        String mail = mailTF.getText();
        String pass = passTF.getText();

        boolean nameFormat = Pattern.matches("([^.\\s]([a-zA-Z\\.]){1,}\\s[a-zA-Z]{1,}\\s?([a-zA-Z]{1,})\\s?([a-zA-Z]{1,})?)", name);
        boolean mailFormat = Pattern.matches("[^.\\s][a-z0-9\\.\\_]{3,64}@([a-z]){2,50}.[a-z]{2,}", mail);
        boolean passFormat = Pattern.matches("[^\\s]{8,32}", pass);

        if (nameFormat && mailFormat && passFormat) {

            try {
                db.setUserData(name, mail, pass);
                nameTF.clear();
                mailTF.clear();
                passTF.clear();

                errName.setText("");
                errMail.setText("");
                errPass.setText("");

            } catch (Exception e) {

            }

        } else {

            if (!nameFormat) {
                errName.setText("Must contain only alphabets & space");

            }
            if (nameFormat) {
                errName.setText("");
            }

            if (!mailFormat) {
                errMail.setText("Please enter a valid email address");
            }
            if (mailFormat) {
                errMail.setText("");
            }
            if (!passFormat) {
                errPass.setText("Your password must be at least 8 characters long");
            }
            if (passFormat) {
                errPass.setText("");
            }
        }
    }
}
