package bencode.type;

import bencode.parser.Parser;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Dmitrenko on 28.04.2015.
 */
public interface BType<Value> {
    Value value();

    void decode(Parser p, InputStream is) throws IOException;

    char[] encode();
}
