package bencode.parser;

import bencode.type.BType;
import bencode.type.ServiceType;
import bencode.type.TypeFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Dmitrenko on 28.04.2015.
 */
public class StreamParser implements Parser<InputStream> {
    @Override
    public BType<?> parseNext(InputStream is) throws IOException {
        BType<?> type = TypeFactory.createType(is);
        type.decode(this, is);
        return type;
    }

    @Override
    public List<BType<?>> parse(InputStream is) throws IOException {
        BType<?> bType;
        List<BType<?>> result = new LinkedList<>();
        while (!(bType = parseNext(is)).equals(ServiceType.END)) {
            result.add(bType);
        }
        return result;
    }
}
