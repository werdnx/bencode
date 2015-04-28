package bencode.util;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Dmitrenko on 28.04.2015.
 */
public class BEncodeUtils {

    public static final char INT = 'i';
    public static final char ARRAY = 'l';
    public static final char DICTIONARY = 'd';
    public static final char STRING_SPLITTER = ':';
    public static final char END = 'e';


    public String encode(Object[] objects) {
        return null;
    }

    public Object[] decode(String bis) {
        return null;
    }

    public static String readDataUntilToken(InputStream is, char token) throws IOException {
        StringBuilder sb = new StringBuilder();
        char ch;
        while (true) {
            ch = (char) is.read();
            if (ch == token) {
                break;
            } else if ((int) ch == -1) {
                throw new IllegalStateException("Unexpected stream end, token " + token);
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}
