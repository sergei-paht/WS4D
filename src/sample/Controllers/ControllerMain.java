package sample.Controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Main;
import sample.POJO.Events;

public class ControllerMain {
    private ObservableList<Events> events = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button authButton;

    @FXML
    private TableColumn<Events, String> DataEventsColum;

    @FXML
    private TableColumn<Events, String> NamEventsColum;
    @FXML
    private TableView<Events> tableEvents;

    @FXML
    void initialize() {
        authButton.setOnAction(event -> {
            authButton.getScene().getWindow().hide();
            Main.OpenIcon("/sample/Windows/authWindow.fxml");


        });

        initEvents();
        NamEventsColum.setCellValueFactory(new PropertyValueFactory<Events, String>("NamEventsColum"));
        DataEventsColum.setCellValueFactory(new PropertyValueFactory<Events, String>("DataEventsColum"));
        // заполняем таблицу данными
        tableEvents.setItems(events);


    }

    private void initEvents() {
        try {
            dbConnection = getDbConnection();
            ResultSet resSet = dbConnection.createStatement().executeQuery("SELECT * FROM events");
            while (resSet.next()) {
                events.add(new Events(resSet.getString("Событие"), resSet.getString("DATE")));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
    Connection dbConnection;
    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://localhost:3306/vs4k";
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, "root", "1234");
        return dbConnection;
    }


}
