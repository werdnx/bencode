package bencode.type;

import bencode.parser.Parser;
import bencode.util.BEncodeUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Dmitrenko on 28.04.2015.
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
        while (!(bType = p.parseNext(is)).equals(ServiceType.END)) {
            value.add(bType);
        }
    }

    @Override
    public char[] encode() {
        encodePreconditions();
        return (BEncodeUtils.ARRAY + "" +
                value.stream().map(bType -> bType.encode()).toString().toCharArray() +
                BEncodeUtils.END)
                .toCharArray();
    }
}
