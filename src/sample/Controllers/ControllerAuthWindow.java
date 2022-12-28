package sample.Controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.DataBase;
import sample.Main;
import sample.POJO.User;

public class ControllerAuthWindow {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button enterButton;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button caphaButton;

    @FXML
    private TextField caphaField;
    public static int idOrg = 0;

    @FXML
    void initialize() {
        String simCode = "qwertyuiopasdfghjkzxcvbnmQWERTYUOASDFGHJKLZXCVBNM1234567890!@#$%^&*>?";
        Random random = new Random();
        char sim;
        String codes = "";
        int index;
        for (int i = 0; i < 4; i++){
            index = random.nextInt(simCode.length());
            sim = simCode.charAt(index);
            codes += sim;}

        String finalCodes = codes;
        caphaButton.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Сообщение");
            alert.setHeaderText(finalCodes);
            alert.showAndWait();

        });
        enterButton.setOnAction(event -> {
            String textLogin = loginField.getText().trim();
            String textPassword = passwordField.getText().trim();
            String textCapha = caphaField.getText().trim();
            if (!textLogin.equals("") & !textPassword.equals("") & !textCapha.equals("")){
                if (textCapha.equals(finalCodes)){
                    try {
                        loginUser(textLogin,textPassword);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText("Неправилный кот!");
                    alert.setContentText("Повторите попитку позже!");
                    alert.showAndWait();
                }
            }else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Поля не заполнены!");
                alert.setContentText("Заполните все поля!");
                alert.showAndWait();
            }

        });


    }

    private void loginUser(String textLogin, String textPassword) throws SQLException {
        try {
            dbConnection = getDbConnection();
            ResultSet resSet1 = dbConnection.createStatement().executeQuery("SELECT * FROM jury");
            while (resSet1.next()) {
                if (resSet1.getString("телефон").equals(textLogin) &&
                        resSet1.getString("Пароль").equals(textPassword)){
                    enterButton.getScene().getWindow().hide();
                    Main.OpenIcon("/sample/Windows/juriWindow.fxml");}
                break;


            }
            ResultSet resSet2 = dbConnection.createStatement().executeQuery("SELECT * FROM moder");
            while (resSet2.next()) {
                if (resSet2.getString("телефон").equals(textLogin) &&
                        resSet2.getString("Пароль").equals(textPassword)){
                    enterButton.getScene().getWindow().hide();
                Main.OpenIcon("/sample/Windows/moderWindow.fxml");}
                break;


            }

            ResultSet resSet3 = dbConnection.createStatement().executeQuery("SELECT * FROM organizers");
            while (resSet3.next()) {
                if (resSet3.getString("телефон").equals(textLogin) &&
                        resSet3.getString("Пароль").equals(textPassword)){
                    idOrg = resSet3.getInt("№");
                    enterButton.getScene().getWindow().hide();
                    Main.OpenIcon("/sample/Windows/organizator.fxml");}
                break;


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