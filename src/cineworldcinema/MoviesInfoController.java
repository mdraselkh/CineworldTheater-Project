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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class MoviesInfoController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private TableView<MovieList> movieTable;
    @FXML
    private TableColumn<MovieList, String> Serial;
    @FXML
    private TableColumn<MovieList, String> Name;
    @FXML
    private TableColumn<MovieList, String> Director;
    @FXML
    private TableColumn<MovieList, String> Status;

    ObservableList<MovieList> mList = FXCollections.observableArrayList();

    static int movieSerial;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        show();
    }

    @FXML
    private void refreshAction(ActionEvent event) {
        movieTable.getItems().clear();
        show();
    }

    @FXML
    private void addMovieAction(ActionEvent event) throws IOException {
        Pane view = new FXMLLoader().load(getClass().getResource("/FXMLs/AddMovie.fxml"));

        Stage stage = new Stage();
        stage.setScene(new Scene(view));
        stage.show();

    }

    @FXML
    private void editStatusAction(ActionEvent event) throws IOException {

        if (movieTable.getSelectionModel().getSelectedItem() != null) {

            MovieList data = movieTable.getSelectionModel().getSelectedItem();
            movieSerial = data.getSerial();

            Pane view = new FXMLLoader().load(getClass().getResource("/FXMLs/AllocateMovie.fxml"));

            Stage stage = new Stage();
            stage.setScene(new Scene(view));
            stage.show();

        } else {
            JOptionPane.showMessageDialog(null, "Please Select a Movie");
        }

    }

    void show() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cineworld cinema", "root", "");

            String query = "select *from movies";

            Statement stm = con.createStatement();

            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {

                mList.add(new MovieList(rs.getInt("Serial"), rs.getString("MovieName"), rs.getString("Director"), rs.getString("Status")));
            }

            Serial.setCellValueFactory(new PropertyValueFactory<>("Serial"));
            Name.setCellValueFactory(new PropertyValueFactory<>("name"));
            Director.setCellValueFactory(new PropertyValueFactory<>("directorName"));
            Status.setCellValueFactory(new PropertyValueFactory<>("status"));

            movieTable.setItems(mList);
            con.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void removeMovie(ActionEvent event) throws SQLException {
        if (movieTable.getSelectionModel().getSelectedItem() != null) {

            MovieList data = movieTable.getSelectionModel().getSelectedItem();
            int id = data.getSerial();

            int returnValue = JOptionPane.showConfirmDialog(null, "Are You Sure?", "Want to Remove?", JOptionPane.YES_NO_OPTION);
            System.out.println(returnValue);

            if (returnValue == JOptionPane.YES_OPTION) {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cineworld cinema", "root", "");

                String query = "delete from movies where Serial=?";

                PreparedStatement pstm = con.prepareStatement(query);
                pstm.setInt(1, id);
                pstm.execute();
                con.close();
                movieTable.getItems().remove(data);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please Select a Movie");
        }

    }

}
