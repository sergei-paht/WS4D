package sample.Controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class metodTestir {
    String a = "";
    public String add(String tfNameM,String tfNameAct,String tfDays, String comboDate, String tfDay,String tfH,
                      String tfMin, String selectModer, String selectJury1, String selectJury2,String selectJury3,
                      String selectJury4, String selectJury5, String selectWinner){
        if (!(tfH.isEmpty() || tfMin.isEmpty() || tfNameAct.isEmpty() || tfNameM.isEmpty()
                || comboDate.isEmpty() || selectModer.isEmpty() || selectWinner.isEmpty() || selectJury1.isEmpty()
                || selectJury2.isEmpty() || selectJury3.isEmpty() || selectJury4.isEmpty() || selectJury5.isEmpty())){
            return a = "Пустые данные";
        }
        if ((5<Integer.parseInt(tfH) && Integer.parseInt(tfH)<24) && (0<=Integer.parseInt(tfMin) && Integer.parseInt(tfMin)<=59)){
            return a = "Неверное время";
        }
        if (0<Integer.parseInt(tfDays) && 0<Integer.parseInt(tfDay)){
            return a = "Неверные дни";
        }
        else {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/4_course",
                        "root", "Qwerty123456")){
                    PreparedStatement statement = conn.prepareStatement
                            ("INSERT into act(НаименованиеМероприятия, ДатаНачала, Дни, Активность, День, ВремяНачала," +
                                    "Модератор, Жюри1, Жюри2 ,Жюри3 ,Жюри4 ,Жюри5, Победитель) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    statement.setString(1, tfNameM);
                    statement.setString(2, comboDate);
                    statement.setString(3, tfDays);
                    statement.setString(4, tfNameAct);
                    statement.setString(5, tfDay);
                    statement.setString(6, (tfH+":"+ tfMin));
                    statement.setString(7, selectModer);
                    statement.setString(8, selectJury1);
                    statement.setString(9, selectJury2);
                    statement.setString(10, selectJury3);
                    statement.setString(11, selectJury4);
                    statement.setString(12, selectJury5);
                    statement.setString(13, selectWinner);
                    statement.executeUpdate();
                }}
            catch (Exception e) {
                System.out.println("Ошибка с заполнением");
            }
            return a = "ок";}}
}
