package bencode.type;

import bencode.parser.Parser;

import java.io.InputStream;

/**
 * Created by Dmitrenko on 28.04.2015.
 * Special stub types for parsing logic
 */
public enum StubType implements BType<Void> {
    /**
     * end marker
     */
    END;

    @Override
    public Void value() {
        throw new UnsupportedOperationException("Type is None");
    }

    @Override
    public void decode(Parser p, InputStream is) {
    }

    @Override
    public String encode() {
        throw new UnsupportedOperationException("Type is None");
    }
}
