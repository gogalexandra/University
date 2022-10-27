package Model.Values;

import Model.Types.StringType;
import Model.Types.Type;

public class StringValue implements Value{

    String val;

    public StringValue(){
        this.val = "";
    }

    public StringValue(String v){
        this.val = v;
    }

    public String getVal(){
        return this.val;
    }

    @Override
    public Type getType() {
        return new StringType();
    }

    @Override
    public Value deepCopy() {
        return new StringValue(this.val);
    }

    @Override
    public boolean equals(Object another){
        return another instanceof StringValue;
    }

    @Override
    public String toString() {
        return this.val;
    }
}
