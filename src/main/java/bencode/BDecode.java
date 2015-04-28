package bencode;

/**
 * Created by Dmitrenko on 28.04.2015.
 * Created by Dmitrenko on 28.04.2015.
 * <p>
 * Created by Dmitrenko on 28.04.2015.
 * <expression> ::= <type>|<expression>
 * <type> ::= <str>|<int>|<arr>|<map>
 * <str> ::= <size>:<data>
 * <int> ::= i<data>e
 * <arr> ::= l<type>+e
 * <map> ::= d<entry>+e
 * <entry> ::= <str><type>
 */
public interface BDecode<I, O> {
    O decode(I data);
}
