package cineworldcinema;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SignInController implements Initializable {

    @FXML
    private Button signUp;
    @FXML
    private JFXTextField inputMail;
    @FXML
    private JFXPasswordField inputPassword;
    @FXML
    private JFXButton loginButton;
    @FXML
    private JFXButton aminLogin;
    @FXML
    private JFXTextField adminNameInput;
    @FXML
    private JFXPasswordField adminPassInput;

    static String adminUserName;
    static int updateIndex;
    static boolean guest = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void signUpAction(ActionEvent event) {
        try {
            Parent signUp = FXMLLoader.load(getClass().getResource("/FXMLs/signUp.fxml"));

            Scene scn = new Scene(signUp);

            Stage signWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

            signWindow.setScene(scn);
            signWindow.setTitle("Sign Up");
            signWindow.show();

        } catch (Exception ex) {
            System.out.println("Can't load");
        }

    }

    @FXML
    private void loginAction(ActionEvent event) {

        database db = new database();
        String mail = inputMail.getText();
        String pass = inputPassword.getText();
        boolean match = db.getUserData(mail, pass);

        if (match) {

            try {
                Parent Home = FXMLLoader.load(getClass().getResource("/FXMLs/Home.fxml"));

                Stage home = (Stage) ((Node) event.getSource()).getScene().getWindow();

                home.setScene(new Scene(Home));
                home.setTitle("Welcome");
                home.show();
            } catch (IOException ex) {
                System.out.println("Can't load");
            }
        } else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Invalid Input");
            a.show();
            inputMail.clear();
            inputPassword.clear();
        }
    }

    @FXML
    private void adminLoginAction(ActionEvent event) throws SQLException {

        boolean match = false;

        String inputName = adminNameInput.getText();
        String inputPass = adminPassInput.getText();

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cineworld cinema", "root", "");

        String query = "select *from admins";

        Statement stm = con.createStatement();

        ResultSet rs = stm.executeQuery(query);

        while (rs.next()) {
            if ((rs.getString("userName").equals(inputName)) && (rs.getString("password").equals(inputPass))) {
                match = true;
                adminUserName = rs.getString("userName");
                updateIndex = rs.getInt("ID");
            }
        }
        if (match) {
            try {
                Parent admin = FXMLLoader.load(getClass().getResource("/FXMLs/Admin.fxml"));

                Scene scn = new Scene(admin);

                Stage adminWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

                adminWindow.setScene(scn);
                adminWindow.setTitle("Admin DashBoard");
                adminWindow.show();

            } catch (Exception ex) {
                System.out.println("Can't load");
            }
        } else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Invalid Input");
            a.show();
            adminNameInput.clear();
            adminPassInput.clear();

        }

    }

    @FXML
    private void GuestLogIn(ActionEvent event) {
        guest = true;
        try {
            Parent Home = FXMLLoader.load(getClass().getResource("/FXMLs/GuestLogIn.fxml"));

            Stage home = (Stage) ((Node) event.getSource()).getScene().getWindow();

            home.setScene(new Scene(Home));
            home.setTitle("Welcome");
            home.show();
        } catch (IOException ex) {
            System.out.println("Can't load");
        }
    }
}
