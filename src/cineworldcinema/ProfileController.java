/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cineworldcinema;


import static cineworldcinema.database.profile;
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ProfileController implements Initializable {
  @FXML
    private JFXButton btnEdit;

    @FXML
    private JFXButton btnRefresh;
    @FXML
    private Label fullName;
    @FXML
    private Label email;
    @FXML
    private Pane rootpane;
    @FXML
    private Label password;
    


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      try {
          showProfile();
      } catch (SQLException ex) {
          System.out.println("e");;
      }
    }  
    
    void showProfile() throws SQLException
    {
        
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cineworld cinema", "root", "");

        String query = "select *from users";

        Statement stm = con.createStatement();

        ResultSet rs = stm.executeQuery(query);
        while(rs.next())
        {
            if(rs.getInt("serial")==profile)
            {
                
                fullName.setText(rs.getString("name"));
                email.setText(rs.getString("email"));
                password.setText(rs.getString("password"));
            }
        }
        
         rs.close();
        
      con.close();
    }
    
    @FXML
    void RefreshAction(ActionEvent event) {
      try {
          showProfile();
      } catch (SQLException ex) {
          System.out.println("e");
      }
    }
    
    
      @FXML
    void EditAction(ActionEvent event) throws IOException {
         try{
        Parent EditPane=FXMLLoader.load(getClass().getResource("/FXMLs/EditProfile.fxml")); 
        Scene scene =new Scene(EditPane);
        
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Edit Window");
        stage.show();
        
       
        }catch(Exception e){
         System.out.println("can't load");
        }
    
 
    }
}

