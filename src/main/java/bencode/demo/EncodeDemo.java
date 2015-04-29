package bencode.demo;

import bencode.type.*;
import javafx.util.Pair;

/**
 * Created by Dmitrenko on 29.04.2015.
 */
public class EncodeDemo {
    public static void main(String[] args) {
        BInt bInt = TypeFactory.bInt(-50);
        BString bString = TypeFactory.bString("str");
        BString bString2 = TypeFactory.bString("str2");
        BArray bArray = TypeFactory.bArray(bInt, bString);
        BDictionary bDictionary1 = TypeFactory.bDictionary(new Pair<>(bString, bArray));
        BDictionary bDictionary2 = TypeFactory.bDictionary(new Pair<>(bString, bDictionary1), new Pair<>(bString2, bInt));

        System.out.println(bDictionary2.encode());
    }
}
