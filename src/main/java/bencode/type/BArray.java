package bencode.type;

import bencode.parser.Parser;
import bencode.util.BEncodeUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Dmitrenko on 28.04.2015.
 * Array abstraction
 */
public class BArray extends CommonBType<ArrayList<BType<?>>> {
    BArray() {
    }

    @Override
    public ArrayList<BType<?>> value() {
        return value;
    }

    @Override
    public void decode(Parser p, InputStream is) throws IOException {
        decodePreconditions();
        value = new ArrayList<>();
        BType<?> bType;
        while (!(bType = p.parseNext(is)).equals(StubType.END)) {
            value.add(bType);
        }
    }

    @Override
    public String encode() {
        encodePreconditions();
        return String.valueOf(BEncodeUtils.ARRAY) + value.stream().map(bType -> bType.encode()).reduce((a, b) -> a + b).get() + BEncodeUtils.END;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (BType<?> bType : value) {
            sb.append(bType.toString()).append(", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append(" ]");
        return sb.toString();
    }
}
