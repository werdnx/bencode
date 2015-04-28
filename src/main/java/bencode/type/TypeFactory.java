package bencode.type;

import bencode.util.BEncodeUtils;
import javafx.util.Pair;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitrenko on 28.04.2015.
 */
public class TypeFactory {
    public static BType<?> createType(InputStream is) throws IOException {
        char c = (char) is.read();
        if (c == BEncodeUtils.INT) {
            return new BInt();
        } else if (c == BEncodeUtils.ARRAY) {
            return new BArray();
        } else if (c == BEncodeUtils.DICTIONARY) {
            return new BDictionary();
        } else if (Character.isDigit(c)) {
            BString bString = new BString();
            bString.setLength(stringLength(c, is));
            return bString;
        } else if (c == BEncodeUtils.END) {
            return StubType.END;
        } else if ((int) c == -1) {
            return StubType.END;
        } else {
            throw new IllegalStateException("Unknown b type " + c + " left bytes " + is.available());
        }
    }

    private static int stringLength(char firstDigit, InputStream is) throws IOException {
        String restLength = BEncodeUtils.readDataUntilToken(is, BEncodeUtils.STRING_SPLITTER);
        return Integer.valueOf(firstDigit + restLength);
    }

    public static BInt bInt(Integer i) {
        BInt bInt = new BInt();
        bInt.setValue(i);
        return bInt;
    }

    public static BString bString(String s) {
        BString bString = new BString();
        bString.setLength(s.length());
        bString.setValue(s);
        return bString;
    }

    public static BArray bArray(BType<?>... values) {
        BArray bArray = new BArray();
        ArrayList<BType<?>> list = new ArrayList<>(values.length);
        for (BType<?> value : values) {
            list.add(value);
        }
        bArray.setValue(list);
        return bArray;
    }

    public static BDictionary bDictionary(Pair<BString, BType<?>>... pairs) {
        BDictionary bDictionary = new BDictionary();
        List<Pair<BString, BType<?>>> value = new ArrayList<>();
        for (Pair<BString, BType<?>> pair : pairs) {
            value.add(pair);
        }
        return bDictionary;
    }
}
