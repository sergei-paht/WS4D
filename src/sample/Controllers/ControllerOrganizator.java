package sample.Controllers;

        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.*;
        import javafx.scene.image.Image;
        import javafx.scene.image.ImageView;
        import javafx.scene.layout.Pane;
        import javafx.stage.Stage;
        import java.io.IOException;
        import java.sql.*;
        import java.util.Objects;

public class ControllerOrganizator {
    @FXML
    private CheckBox checkPas;
    @FXML private Pane paneMyProf;
    @FXML private Pane paneMerop;
    @FXML private Button btnMerop;
    @FXML private Button btnMyProf;
    @FXML private Button btnJury;
    @FXML private Button btnUchast;
    @FXML private Button btnOkProf;
    @FXML private Button btnOtmProf;
    @FXML private Button btnSaveM;
    @FXML private Button btnOtmM;
    @FXML private PasswordField tfPass;
    @FXML private PasswordField tfPass1;
    @FXML private TextField tfDays;
    @FXML private TextField tfDay;
    @FXML private TextField tfH;
    @FXML private TextField tfMin;
    @FXML private TextField tfNameM;
    @FXML private TextField tfNameAct;
    @FXML private Label lbError;
    @FXML private Label lbPrivetTime;
    @FXML private Label lbPrivetName;
    @FXML private Label lbFIO;
    @FXML private Label lbCountry;
    @FXML private Label lbNumber;
    @FXML private Label lbEmail;
    @FXML private Label lbID;
    @FXML private Label lbBdate;
    @FXML private Label lbPol;
    @FXML private Label lbErrorPas;
    @FXML private ImageView imageID;
    @FXML private ComboBox<String> comboMod;
    @FXML private ComboBox<String> comboJ1;
    @FXML private ComboBox<String> comboJ2;
    @FXML private ComboBox<String> comboJ3;
    @FXML private ComboBox<String> comboJ4;
    @FXML private ComboBox<String> comboJ5;
    @FXML private ComboBox<String> comboWin;
    @FXML private DatePicker comboDate;
    public String picID;

    private final ObservableList<String> moderatorData = FXCollections.observableArrayList();
    private final ObservableList<String> juryData = FXCollections.observableArrayList();
    private final ObservableList<String> winData = FXCollections.observableArrayList();
    private String selectModer = "";
    private String selectWinner = "";
    private String selectJury1 = "";
    private String selectJury2 = "";
    private String selectJury3 = "";
    private String selectJury4 = "";
    private String selectJury5 = "";


    @FXML
    void initialize() throws SQLException {
        showTime();
        moderCombo();
        juryCombo();
        winCombo();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/vs4k",
                    "root", "1234");
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT ?????? FROM organizers WHERE `???` = " + ControllerAuthWindow.idOrg);
            while (resultSet.next()) {
                lbPrivetName.setText(resultSet.getString("??????"));
            }
        } catch (Exception e) {
            System.out.println("???????????? ?? ????????????????????????");
        }

        btnOkProf.setOnAction(event -> {
            if(checkPas.isSelected() && !(tfPass.getText().isEmpty() || tfPass1.getText().isEmpty())
                    && Objects.equals(tfPass.getText(), tfPass1.getText())){
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                    try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/vs4k",
                            "root", "1234")) {
                        PreparedStatement statement = conn.prepareStatement
                                ("UPDATE organizers SET ???????????? = '" + tfPass.getText() + "' WHERE `???` = " + ControllerAuthWindow.idOrg);
                        statement.executeUpdate();
                    }
                } catch (Exception e) {
                    System.out.println("???????????? ?? ???????????????????? ????????????");
                }
                paneMyProf.setVisible(false);
                lbErrorPas.setText("");}
            else if (!(checkPas.isSelected())) {
                paneMyProf.setVisible(false);
                lbErrorPas.setText("");}
            else {lbErrorPas.setText("?????????????????? ????????????");}
        });

        btnMyProf.setOnAction(event -> {
            paneMyProf.setVisible(true);
            showMyInfo(ControllerAuthWindow.idOrg);
        });
        btnOtmProf.setOnAction(event -> {
            paneMyProf.setVisible(false);
            lbErrorPas.setText("");
        });

        btnMerop.setOnAction(event -> {
            paneMerop.setVisible(true);
        });
        btnOtmM.setOnAction(event -> {
            paneMerop.setVisible(false);
        });
        btnSaveM.setOnAction(event -> {
            if (!(tfH.getText().isEmpty() || tfMin.getText().isEmpty()
                    || tfNameAct.getText().isEmpty() || tfNameM.getText().isEmpty()) && (5<Integer.parseInt(tfH.getText())
                    && Integer.parseInt(tfH.getText())<24) && (0<=Integer.parseInt(tfMin.getText()) && Integer.parseInt(tfMin.getText())<=59)
                    && (0<Integer.parseInt(tfDays.getText()) && 0<Integer.parseInt(tfDay.getText()))) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                    try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/vs4k",
                            "root", "1234")){
                        PreparedStatement statement = conn.prepareStatement
                                ("INSERT into active(`???????????????????????? ??????????????????????`, `???????? ????????????`, ??????, ????????????????????, ????????, `?????????? ????????????`," +
                                        "??????????????????, `???????? 1`, `???????? 2` ,`???????? 3` ,`???????? 4` ,`???????? 5`, ????????????????????) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
                        statement.setString(1, tfNameM.getText());
                        statement.setString(2, String.valueOf(java.sql.Date.valueOf(comboDate.getValue())));
                        statement.setString(3, tfDays.getText());
                        statement.setString(4, tfNameAct.getText());
                        statement.setString(5, tfDay.getText());
                        statement.setString(6, (tfH.getText() +":"+ tfMin.getText()));
                        statement.setString(7, selectModer);
                        statement.setString(8, selectJury1);
                        statement.setString(9, selectJury2);
                        statement.setString(10, selectJury3);
                        statement.setString(11, selectJury4);
                        statement.setString(12, selectJury5);
                        statement.setString(13, selectWinner);
                        statement.executeUpdate();
                    }
                    paneMerop.setVisible(false);}
                catch (Exception e) {
                    System.out.println("???????????? ?? ??????????????????????");
                }}
            else {lbError.setText("??????????????????, ?????? ???? ???????? ?????????????????? ??????????");}
        });

    }

    private void showTime(){
        int time = Integer.parseInt(String.valueOf(java.time.LocalDateTime.now()).substring(11, 13));
        if (9<=time && time<=10) {
            lbPrivetTime.setText("???????????? ????????");
        }
        else if (11<=time && time<=17) {
            lbPrivetTime.setText("???????????? ????????");
        }
        else if (18<=time && time<=23) {
            lbPrivetTime.setText("???????????? ??????????");
        }
    }

    private void showMyInfo(int Code) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/vs4k",
                    "root", "1234");
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT  `???`, ??????, ??????????, `???????? ????????????????`, ????????????, ??????????????, ????????, ?????? FROM organizers WHERE `???` = " + Code);
            while (resultSet.next()) {
                lbFIO.setText(resultSet.getString("??????"));
                lbPol.setText(resultSet.getString("??????"));
                lbBdate.setText(resultSet.getString("???????? ????????????????"));
                lbID.setText(resultSet.getString("???"));
                lbCountry.setText(resultSet.getString("????????????"));
                lbNumber.setText(resultSet.getString("??????????????"));
                lbEmail.setText(resultSet.getString("??????????"));
                picID = resultSet.getString("????????");
                imageID.setImage(new Image("sample/Pictures/organizator/" + picID));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void moderCombo() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/vs4k",
                "root", "1234")) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT ?????? FROM moder");
            while (resultSet.next()){
                moderatorData.add(resultSet.getString("??????"));
            }
            comboMod.getItems().addAll( moderatorData);
            comboMod.setValue("??????????????????");
            comboMod.setOnAction(this::getModer);
        }catch (Exception e){
            System.out.println("???????????? ?? ?????????????? ????????????????????");
        }}
    public void juryCombo() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/vs4k",
                "root", "1234")) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT ?????? FROM jury");
            while (resultSet.next()){
                juryData.add(resultSet.getString("??????"));
            }
            comboJ1.getItems().addAll( juryData);
            comboJ1.setValue("???????? 1");
            comboJ1.setOnAction(this::getJ1);
            comboJ2.getItems().addAll( juryData);
            comboJ2.setValue("???????? 2");
            comboJ2.setOnAction(this::getJ2);
            comboJ3.getItems().addAll( juryData);
            comboJ3.setValue("???????? 3");
            comboJ3.setOnAction(this::getJ3);
            comboJ4.getItems().addAll( juryData);
            comboJ4.setValue("???????? 4");
            comboJ4.setOnAction(this::getJ4);
            comboJ5.getItems().addAll( juryData);
            comboJ5.setValue("???????? 5");
            comboJ5.setOnAction(this::getJ5);
        }catch (Exception e){
            System.out.println("???????????? ?? ?????????????? ????????");
        }}
    public void winCombo() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/vs4k",
                "root", "1234")) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT ?????? FROM participants");
            while (resultSet.next()){
                winData.add(resultSet.getString("??????"));
            }
            comboWin.getItems().addAll(winData);
            comboWin.setValue("????????????????????");
            comboWin.setOnAction(this::getWinner);
        }catch (Exception e){
            System.out.println("???????????? ?? ?????????????? ????????????????????");
        }}

    private void getJ1(javafx.event.ActionEvent actionEvent1) {
        selectJury1 = comboJ1.getValue();
    }
    private void getJ2(javafx.event.ActionEvent actionEvent1) {
        selectJury2 = comboJ2.getValue();
    }
    private void getJ3(javafx.event.ActionEvent actionEvent1) {
        selectJury3 = comboJ3.getValue();
    }
    private void getJ4(javafx.event.ActionEvent actionEvent1) {
        selectJury4 = comboJ4.getValue();
    }
    private void getJ5(javafx.event.ActionEvent actionEvent1) {
        selectJury5 = comboJ5.getValue();
    }
    private void getWinner(javafx.event.ActionEvent actionEvent1) {
        selectWinner = comboWin.getValue();
    }
    private void getModer(javafx.event.ActionEvent actionEvent1) {
        selectModer = comboMod.getValue();
    }

}
