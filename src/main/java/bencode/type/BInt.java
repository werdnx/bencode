package bencode.type;

import bencode.parser.Parser;
import bencode.util.BEncodeUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Dmitrenko on 28.04.2015.
 * Integer abstraction
 */
public class BInt extends CommonBType<Integer> {

    BInt() {
    }

    @Override
    public Integer value() {
        return value;
    }

    @Override
    public void decode(Parser p, InputStream is) throws IOException {
        decodePreconditions();
        value = Integer.valueOf(BEncodeUtils.readDataUntilToken(is, BEncodeUtils.END));
    }

    @Override
    public String encode() {
        encodePreconditions();
        return String.valueOf(BEncodeUtils.INT) + value + BEncodeUtils.END;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
