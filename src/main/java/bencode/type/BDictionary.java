package bencode.type;

import bencode.parser.Parser;
import bencode.util.BEncodeUtils;
import javafx.util.Pair;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by Dmitrenko on 28.04.2015.
 */
public class BDictionary extends CommonBType<List<Pair<BString, BType<?>>>> {
    BDictionary() {
    }

    @Override
    public List<Pair<BString, BType<?>>> value() {
        return value;
    }

    @Override
    public void decode(Parser p, InputStream is) throws IOException {
        decodePreconditions();
        value = new ArrayList<>();
        while (true) {
            BType<?> bKey = p.parseNext(is);
            BType<?> bValue = p.parseNext(is);
            if (bKey.equals(StubType.END)) {
                break;
            } else if (!(bKey instanceof BString)) {
                throw new IllegalStateException("Wrong dictionary format, string as key expected, left bytes " + is.available());
            } else if (bValue.equals(StubType.END)) {
                throw new IllegalStateException("Wrong dictionary format, value expected, left bytes " + is.available());
            } else {
                Pair pair = new Pair(bKey, bValue);
                value.add(pair);
            }
        }
    }

    @Override
    public String encode() {
        encodePreconditions();
        Stream<String> map = value.stream().map(pair -> pair.getKey().encode() + pair.getValue().encode());
        return new StringBuilder().append(BEncodeUtils.DICTIONARY)
                .append(value.stream().map(pair -> pair.getKey().encode() + pair.getValue().encode()).reduce((a, b) -> a + b).get())
                .append(BEncodeUtils.END).toString();
    }
}
