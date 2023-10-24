package cineworldcinema;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

public class AddAdminController implements Initializable {

    @FXML
    private JFXTextField adminName;
    @FXML
    private JFXTextField adminUserName;
    @FXML
    private JFXTextField adminMail;
    @FXML
    private JFXPasswordField adminPass;
    @FXML
    private AnchorPane rootPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void addAction(ActionEvent event) throws SQLException {

        String name = adminName.getText();
        String userName = adminUserName.getText();
        String mail = adminMail.getText();
        String pass = adminPass.getText();

        boolean nameFormat = Pattern.matches("([^.\\s]([a-zA-Z\\.]){1,}\\s[a-zA-Z]{1,}\\s?[a-zA-Z]{2,}\\s?([a-zA-Z]{1,})?)", name);
        boolean userNameFormat = Pattern.matches("[a-zA-Z_0-9]{5,}", userName);
        boolean mailFormat = Pattern.matches("[^.\\s][a-z0-9\\.\\_]{3,64}@([a-z]){2,50}.[a-z]{2,}", mail);
        boolean passFormat = Pattern.matches("[\\S]{8,32}", pass);

        if (nameFormat && userNameFormat && mailFormat && passFormat) {
            boolean found = false;
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cineworld cinema", "root", "");

            String query = "select *from admins";

            Statement stm = con.createStatement();

            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {
                if (rs.getString("email").equals(mail)) {
                    found = true;
                    break;
                }
            }
            if (found) {
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setContentText("Email already Used");
                a.show();
            } else {
                String qu = "insert into admins (name,userName,email,password) values(?,?,?,?)";

                PreparedStatement pstm = con.prepareStatement(qu);

                pstm.setString(1, name);
                pstm.setString(2, userName);
                pstm.setString(3, mail);
                pstm.setString(4, pass);

                pstm.executeUpdate();
                System.out.println("User information updated...");

                int value = JOptionPane.showConfirmDialog(null, "Admin Add Completed", "Admin", JOptionPane.YES_NO_OPTION);

                if (value == JOptionPane.YES_OPTION) {

                    rootPane.getScene().getWindow().hide();
                }
            }
            con.close();

        }

    }
}
