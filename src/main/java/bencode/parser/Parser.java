package bencode.parser;

import bencode.type.BType;

import java.io.IOException;
import java.util.List;

/**
 * Created by Dmitrenko on 28.04.2015.
 */
public interface Parser<Source> {
    BType<?> parseNext(Source is) throws IOException;

    List<BType<?>> parse(Source is) throws IOException;
}
