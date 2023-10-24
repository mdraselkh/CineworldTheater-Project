package cineworldcinema;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

public class UsersListController implements Initializable {

    @FXML
    private TableView<userList> userTable;
    @FXML
    private TableColumn<userList, String> serial;
    @FXML
    private TableColumn<userList, String> userName;
    @FXML
    private TableColumn<userList, String> userMail;
    ObservableList<userList> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            show();
        } catch (SQLException ex) {

        }
    }

    void show() throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cineworld cinema", "root", "");

        String query = "select *from users";

        Statement stm = con.createStatement();

        ResultSet rs = stm.executeQuery(query);

        while (rs.next()) {
            list.add(new userList(rs.getInt("Serial"), rs.getString("name"), rs.getString("email")));
        }

        serial.setCellValueFactory(new PropertyValueFactory<>("sn"));
        userName.setCellValueFactory(new PropertyValueFactory<>("name"));
        userMail.setCellValueFactory(new PropertyValueFactory<>("mail"));

        userTable.setItems(list);
    }

    @FXML
    private void removeUser(ActionEvent event) throws SQLException {

        if (userTable.getSelectionModel().getSelectedItem() != null) {

            userList data = userTable.getSelectionModel().getSelectedItem();
            int id = data.getSn();

            int returnValue = JOptionPane.showConfirmDialog(null, "Are You Sure?", "Want to Remove?", JOptionPane.YES_NO_OPTION);
            System.out.println(returnValue);

            if (returnValue == JOptionPane.YES_OPTION) {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cineworld cinema", "root", "");

                String query = "delete from users where Serial=?";

                PreparedStatement pstm = con.prepareStatement(query);
                pstm.setInt(1, id);
                pstm.execute();
                con.close();
                userTable.getItems().remove(data);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please Select a User");
        }

    }
}
