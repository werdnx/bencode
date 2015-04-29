package bencode;

import bencode.type.*;
import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Dmitrenko on 29.04.2015.
 */
public class EncoderTest {

    @Test
    public void complexArray() {
        BInt bInt = TypeFactory.bInt(-123);
        BString bString = TypeFactory.bString("string");
        BArray bArray = TypeFactory.bArray(bInt, bString);
        BDictionary bDictionary = TypeFactory.bDictionary(new Pair<>(bString, bArray));

        Assert.assertEquals("ld6:stringli-123e6:stringeei-123e6:stringli-123e6:stringee", TypeFactory.bArray(bDictionary, bInt, bString, bArray).encode());
    }

    @Test
    public void complexDictionary() {
        BInt bInt = TypeFactory.bInt(-50);
        BString bString = TypeFactory.bString("str");
        BString bString2 = TypeFactory.bString("str2");
        BArray bArray = TypeFactory.bArray(bInt, bString);
        BDictionary bDictionary1 = TypeFactory.bDictionary(new Pair<>(bString, bArray));

        BDictionary bDictionary2 = TypeFactory.bDictionary(new Pair<>(bString, bDictionary1), new Pair<>(bString2, bInt));
        Assert.assertEquals("d3:strd3:strli-50e3:stree4:str2i-50ee", bDictionary2.encode());
    }

    @Test
    public void dictionary() {
        Pair<BString, BType<?>> pair1 = new Pair<>(TypeFactory.bString("bar"), TypeFactory.bString("spam"));
        Pair<BString, BType<?>> pair2 = new Pair<>(TypeFactory.bString("foo"), TypeFactory.bInt(42));
        Assert.assertEquals("d3:bar4:spam3:fooi42ee", TypeFactory.bDictionary(pair2, pair1).encode());
        try {
            Assert.assertEquals("i-120e", TypeFactory.bDictionary(null).encode());
            Assert.assertTrue(false);
        } catch (IllegalStateException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void array() {
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
    public void intWithMinus() {
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
    public void bInt() {
        Assert.assertEquals("i0e", TypeFactory.bInt(0).encode());
        Assert.assertEquals("i154e", TypeFactory.bInt(154).encode());
        Assert.assertEquals(("i" + Integer.MAX_VALUE + "e"), TypeFactory.bInt(Integer.MAX_VALUE).encode());
    }

    @Test
    public void string() {
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
