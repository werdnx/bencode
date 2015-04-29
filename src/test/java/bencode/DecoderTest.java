package bencode;

import bencode.parser.StreamParser;
import bencode.type.*;
import javafx.util.Pair;
import junit.framework.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Dmitrenko on 28.04.2015.
 */
public class DecoderTest {

    @Test
    public void dictionary() throws IOException {
        Pair<BString, BType<?>> pair1 = new Pair<>(TypeFactory.bString("bar"), TypeFactory.bString("spam"));
        Pair<BString, BType<?>> pair2 = new Pair<>(TypeFactory.bString("foo"), TypeFactory.bInt(42));
        org.junit.Assert.assertEquals("d3:bar4:spam3:fooi42ee", TypeFactory.bDictionary(pair2, pair1).encode());

        StreamParser parser = new StreamParser();
        List<BType<?>> list = parser.parse(new ByteArrayInputStream("d3:bar4:spam3:fooi42ee".getBytes()));
        Assert.assertEquals(1, list.size());
        Assert.assertEquals(BDictionary.class, list.get(0).getClass());
        Assert.assertEquals(2, ((BDictionary)list.get(0)).value().size());

        Assert.assertEquals(BString.class, ((BDictionary)list.get(0)).value().get(1).getKey().getClass());
        Assert.assertEquals(BInt.class, ((BDictionary) list.get(0)).value().get(1).getValue().getClass());

        Assert.assertEquals(BString.class, ((BDictionary)list.get(0)).value().get(0).getKey().getClass());
        Assert.assertEquals(BString.class, ((BDictionary) list.get(0)).value().get(0).getValue().getClass());

        Assert.assertEquals("foo", ((BDictionary)list.get(0)).value().get(1).getKey().value());
        Assert.assertEquals(42, ((BDictionary)list.get(0)).value().get(1).getValue().value());

        Assert.assertEquals("bar", ((BDictionary)list.get(0)).value().get(0).getKey().value());
        Assert.assertEquals("spam", ((BDictionary)list.get(0)).value().get(0).getValue().value());
    }

    @Test
    public void array() throws IOException {
        StreamParser parser = new StreamParser();
        List<BType<?>> list = parser.parse(new ByteArrayInputStream("l4:spami42ee".getBytes()));
        Assert.assertEquals(1, list.size());
        Assert.assertEquals(BArray.class, list.get(0).getClass());
        Assert.assertEquals(2, ((BArray)list.get(0)).value().size());
        Assert.assertEquals(BString.class, ((BArray) list.get(0)).value().get(0).getClass());
        Assert.assertEquals(BInt.class, ((BArray)list.get(0)).value().get(1).getClass());
        Assert.assertEquals("spam", ((BArray)list.get(0)).value().get(0).value());
        Assert.assertEquals(42, ((BArray)list.get(0)).value().get(1).value());
    }

    @Test
    public void string() throws IOException {
        StreamParser parser = new StreamParser();
        List<BType<?>> list = parser.parse(new ByteArrayInputStream("6:string".getBytes()));
        Assert.assertEquals(1, list.size());
        Assert.assertEquals(BString.class, list.get(0).getClass());
        Assert.assertEquals("string", list.get(0).value());
    }

    @Test
    public void parseIntWithMinus() throws IOException {
        StreamParser parser = new StreamParser();
        List<BType<?>> list = parser.parse(new ByteArrayInputStream("i-15e".getBytes()));
        Assert.assertEquals(1, list.size());
        Assert.assertEquals(BInt.class, list.get(0).getClass());
        Assert.assertEquals(-15, list.get(0).value());
    }

    @Test
    public void parseInt() throws IOException {
        StreamParser parser = new StreamParser();
        List<BType<?>> list = parser.parse(new ByteArrayInputStream("i15e".getBytes()));
        Assert.assertEquals(1, list.size());
        Assert.assertEquals(BInt.class, list.get(0).getClass());
        Assert.assertEquals(15, list.get(0).value());
    }
}
