package bencode.demo;

import bencode.parser.Parser;
import bencode.parser.StreamParser;
import bencode.type.BType;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Dmitrenko on 29.04.2015.
 */
public class DecodeDemo {
    public static void main(String[] args) throws IOException {
        Parser<InputStream> parser = new StreamParser();
        List<BType<?>> list = parser.parse(new ByteArrayInputStream("i100e7:bencodeld6:stringli-123e6:stringeei-123e6:stringli-123e6:stringee".getBytes()));

        list.forEach(System.out::println);

    }
}
