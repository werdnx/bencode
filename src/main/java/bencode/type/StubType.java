package bencode.type;

import bencode.parser.Parser;

import java.io.InputStream;

/**
 * Created by Dmitrenko on 28.04.2015.
 */
public enum StubType implements BType<Void> {
    END;

    @Override
    public Void value() {
        throw new UnsupportedOperationException("Type is None");
    }

    @Override
    public void decode(Parser p, InputStream is) {
        return;
    }

    @Override
    public char[] encode() {
        throw new UnsupportedOperationException("Type is None");
    }
}
