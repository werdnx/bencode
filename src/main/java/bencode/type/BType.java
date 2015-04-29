package bencode.type;

import bencode.parser.Parser;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Dmitrenko on 28.04.2015.
 * Common b type abstraction
 */
public interface BType<Value> {
    /**
     *
     * @return Keeping alue
     */
    Value value();

    /**
     *
     * @param p parser implementation which know how to parse source
     * @param is data source
     * @throws IOException
     */
    void decode(Parser p, InputStream is) throws IOException;

    /**
     * Convert object to bencode representation
     * @return bencode representation
     */
    String encode();
}
