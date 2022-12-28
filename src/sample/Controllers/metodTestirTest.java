package sample.Controllers;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class metodTestirTest {

    metodTestir test = new metodTestir();
    private junit.framework.Assert Assert;

    @Test
    void add1() {
        String result1 = test.add("вжп", "f","1","12", "1","6","50", "f"
                ,"f","f", "f","","f", "f");
        Assert.assertEquals("Пустые данные", result1);}
    @Test
    void add2() {
        String result2 = test.add("f", "","1","12", "1","6","50", "f"
                ,"f","f", "f","f","f", "f");
        Assert.assertEquals("Пустые данные", result2);}
    @Test
    void add3() {
        String result3 = test.add("f", "f","1","12", "1","6","50", ""
                ,"f","f", "f","f","f", "f");
        Assert.assertEquals("Пустые данные", result3);}
    @Test
    void add4() {
        String result4 = test.add("f", "f","1","12", "1","6","50", "f"
                ,"f","f", "f","f","f", "");
        Assert.assertEquals("Пустые данные", result4);}
    @Test
    void add5() {
        String result5 = test.add("f", "f","1","12", "1","6","50", "f"
                ,"","f", "f","f","f", "f");
        Assert.assertEquals("Пустые данные", result5);}
    @Test
    void add6() {
        String result6 = test.add("f", "f","1","12", "1","3","50", "f"
                ,"f","f", "f","f","f", "f");
        Assert.assertEquals("Неверное время", result6);}
    @Test
    void add7() {
        String result7 = test.add("f", "f","1","12", "1","6","90", "f"
                ,"f","f", "f","f","f", "f");
        Assert.assertEquals("Неверное время", result7);}
    @Test
    void add8() {
        String result8 = test.add("f", "f","0","12", "1","6","50", "f"
                ,"f","f", "f","f","f", "f");
        Assert.assertEquals("Неверные дни", result8);}
    @Test
    void add9() {
        String result9 = test.add("f", "f","1","12", "0","6","50", "f"
                ,"f","f", "f","f","f", "f");
        Assert.assertEquals("Неверные дни", result9);}
    @Test
    void add10() {
        String result10 = test.add("f", "f","1","12", "1","6","50", "f"
                ,"f","f", "f","f","f", "f");
        Assert.assertEquals("ок", result10);

    }
}