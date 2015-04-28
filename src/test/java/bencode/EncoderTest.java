package bencode;

import bencode.type.BString;
import bencode.type.BType;
import bencode.type.TypeFactory;
import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by andrew on 29.04.2015.
 */
public class EncoderTest {

    @Test
    public void complexArrayTest() {

    }

    @Test
    public void complexDictionaryTest() {

    }

    @Test
    public void dictionaryTest() {
        Pair<BString, BType<?>> pair1 = new Pair<>(TypeFactory.bString("bar"), TypeFactory.bString("spam"));
        Pair<BString, BType<?>> pair2 = new Pair<>(TypeFactory.bString("foo"), TypeFactory.bInt(42));
        Assert.assertEquals("d3:bar4:spam3:fooi42ee", TypeFactory.bDictionary(pair1, pair2).encode());
    }

    @Test
    public void arrayTest() {
        Assert.assertEquals("l4:spami42ee", TypeFactory.bArray(TypeFactory.bString("spam"), TypeFactory.bInt(42)).encode());
        Assert.assertEquals("l4:spami42ee", TypeFactory.bArray(TypeFactory.bString("spam"), TypeFactory.bInt(42)).encode());
        try {
            Assert.assertEquals("i-120e", TypeFactory.bArray(null).encode());
            Assert.assertTrue(false);
        } catch (IllegalStateException e) {
            Assert.assertTrue(true);
        }
    }


    @Test
    public void intWithMinusTest() {
        Assert.assertEquals("i-42e", TypeFactory.bInt(-42).encode());
        Assert.assertEquals("i-120e", TypeFactory.bInt(-120).encode());
        Assert.assertEquals(("i" + Integer.MIN_VALUE + "e"), TypeFactory.bInt(Integer.MIN_VALUE).encode());
        try {
            Assert.assertEquals(TypeFactory.bInt(null).encode(), "i-120e");
            Assert.assertTrue(false);
        } catch (IllegalStateException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void intTest() {
        Assert.assertEquals("i0e", TypeFactory.bInt(0).encode());
        Assert.assertEquals("i154e", TypeFactory.bInt(154).encode());
        Assert.assertEquals(("i" + Integer.MAX_VALUE + "e"), TypeFactory.bInt(Integer.MAX_VALUE).encode());
    }

    @Test
    public void stringTest() {
        Assert.assertEquals("4:spam", TypeFactory.bString("spam").encode());
        Assert.assertEquals("17:bencodestringtest", TypeFactory.bString("bencodestringtest").encode());
        try {
            Assert.assertEquals("4:spam", TypeFactory.bString(null).encode());
            Assert.assertTrue(false);
        } catch (IllegalStateException e) {
            Assert.assertTrue(true);
        }
    }
}
