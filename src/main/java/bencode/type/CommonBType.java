package bencode.type;

/**
 * Created by Dmitrenko on 28.04.2015.
 * Class which represent common behavior for all types
 */
public abstract class CommonBType<Value> implements BType {
    Value value;

    public void decodePreconditions() {
        if (value != null) {
            throw new IllegalStateException("Type already decoded");
        }
    }

    public void encodePreconditions() {
        if (value == null) {
            throw new IllegalStateException("Type not decoded yet");
        }
    }

    void setValue(Value value) {
        this.value = value;
    }
}
