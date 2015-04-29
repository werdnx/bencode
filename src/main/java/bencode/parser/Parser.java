package bencode.parser;

import bencode.type.BType;

import java.io.IOException;
import java.util.List;

/**
 * Created by Dmitrenko on 28.04.2015.
 * Abstraction for parsers with different sources
 */
public interface Parser<Source> {
    /**
     *
     * @param is data source
     * @return Parsed objects
     * @throws IOException
     */
    BType<?> parseNext(Source is) throws IOException;

    List<BType<?>> parse(Source is) throws IOException;
}
