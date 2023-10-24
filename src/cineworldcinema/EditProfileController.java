package cineworldcinema;

import static cineworldcinema.database.profile;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

public class EditProfileController implements Initializable {

    @FXML
    private JFXButton btnsave;
    @FXML
    private JFXTextField NameTf;
    @FXML
    private JFXTextField EmailTf;
    @FXML
    private JFXPasswordField NpassTf;
    @FXML
    private AnchorPane editPane;
    @FXML
    private JFXButton btnCancel;

    @FXML
    void CancelAction(ActionEvent event) {
       btnCancel.getScene().getWindow().hide();
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @FXML
    private void SaveAction(ActionEvent event) throws SQLException {
        String name=NameTf.getText();
        String mail=EmailTf.getText();
        String newpass=NpassTf.getText();
        
        boolean nameFormat = Pattern.matches("([^.\\s]([a-zA-Z\\.]){1,}\\s[a-zA-Z]{1,}\\s?[a-zA-Z]{2,}\\s?([a-zA-Z]{1,})?)", name);
        boolean mailFormat = Pattern.matches("[^.\\s][a-z0-9\\.\\_]{3,64}@([a-z]){2,50}.[a-z]{2,}", mail);
        boolean passFormat = Pattern.matches("[\\S]{8,32}", newpass);
        
        if (nameFormat && mailFormat && passFormat) {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cineworld cinema", "root", "");

            String qu = "update users set name=?, email=?, password=? where serial=?";

            PreparedStatement pstm = con.prepareStatement(qu);

            pstm.setString(1, name);
            pstm.setString(2, mail);
            pstm.setString(3, newpass);
            pstm.setInt(4, profile);

            System.out.println("Information Updated");
            pstm.executeUpdate();
            con.close();

            int value = JOptionPane.showConfirmDialog(null, "Information Update Completed", "User", JOptionPane.YES_NO_OPTION);

            if (value == JOptionPane.YES_OPTION) {
                editPane.getScene().getWindow().hide();
            }
        } else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Invalid Input");
            a.show();
        }
    }
    
}
