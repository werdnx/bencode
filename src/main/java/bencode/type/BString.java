package bencode.type;

import bencode.parser.Parser;
import bencode.util.BEncodeUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Dmitrenko on 28.04.2015.
 */
public class BString extends CommonBType<String> {

    BString() {
    }

    private int length;

    @Override
    public String value() {
        return value;
    }

    @Override
    public void decode(Parser p, InputStream is) throws IOException {
        decodePreconditions();
        char[] chars = new char[length];
        int b;
        for (int i = 0; i < length; i++) {
            b = is.read();
            if (b == -1) {
                throw new IllegalStateException("Unexpected stream end, last chars " + chars);
            } else {
                chars[i] = (char) b;
            }
        }
        value = new String(chars);
    }

    @Override
    public char[] encode() {
        encodePreconditions();
        return (value.length() + BEncodeUtils.STRING_SPLITTER + value).toCharArray();
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
