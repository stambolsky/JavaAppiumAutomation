import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass{

    @Test
    public void testGetLocalNumber() {
        int number = 14;
        Assert.assertTrue("Не верное число - " +getLocalNumber()+ ", должно быть - "+ number,
                getLocalNumber() == number);
    }
}
