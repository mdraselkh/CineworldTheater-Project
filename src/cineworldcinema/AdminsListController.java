package cineworldcinema;

import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class AdminsListController implements Initializable {

    @FXML
    private TableView<adminList> adminTable;
    @FXML
    private TableColumn<adminList, String> serial;
    @FXML
    private TableColumn<adminList, String> Aname;
    @FXML
    private TableColumn<adminList, String> uName;
    @FXML
    private TableColumn<adminList, String> Aemail;

    ObservableList<adminList> admList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            show();
        } catch (SQLException ex) {

        }
    }

    void show() throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cineworld cinema", "root", "");

        String query = "select *from admins";

        Statement stm = con.createStatement();

        ResultSet rs = stm.executeQuery(query);

        while (rs.next()) {

            admList.add(new adminList(rs.getInt("ID"), rs.getString("name"), rs.getString("userName"), rs.getString("email")));
        }

        serial.setCellValueFactory(new PropertyValueFactory<>("Serial"));
        Aname.setCellValueFactory(new PropertyValueFactory<>("name"));
        uName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        Aemail.setCellValueFactory(new PropertyValueFactory<>("mail"));

        adminTable.setItems(admList);
        con.close();
    }

    @FXML
    private void addAdmin(ActionEvent event) throws IOException, SQLException {
        Pane view = new FXMLLoader().load(getClass().getResource("/FXMLs/addAdmin.fxml"));

        Stage stage = new Stage();
        stage.setScene(new Scene(view));
        stage.show();

    }

    @FXML
    private void RemoveAdmin(ActionEvent event) throws SQLException {
        if (adminTable.getSelectionModel().getSelectedItem() != null) {

            adminList data = adminTable.getSelectionModel().getSelectedItem();
            int id = data.getSerial();

            int returnValue = JOptionPane.showConfirmDialog(null, "Are You Sure?", "Want to Remove?", JOptionPane.YES_NO_OPTION);
            System.out.println(returnValue);

            if (returnValue == JOptionPane.YES_OPTION) {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cineworld cinema", "root", "");

                String query = "delete from admins where ID=?";

                PreparedStatement pstm = con.prepareStatement(query);
                pstm.setInt(1, id);
                pstm.execute();
                con.close();
                adminTable.getItems().remove(data);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please Select a Admin");
        }
    }

    @FXML
    private void refreshTable(ActionEvent event) {
        adminTable.getItems().clear();
        try {
            show();
        } catch (SQLException ex) {

        }
    }

}
