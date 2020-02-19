import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass {

    @Test
    public void testGetLocalNumber() {
        int number = 14;
        Assert.assertTrue("Не верное число - " +getLocalNumber()+ ", должно быть - "+ number,
                getLocalNumber() == number);
    }

    @Test
    public void testGetClassNumber() {
        int number = 45;
        Assert.assertTrue("Не верное число - " +getClassNumber()+ ", должно быть больше "+ number,
                getClassNumber() > number);
    }
}
