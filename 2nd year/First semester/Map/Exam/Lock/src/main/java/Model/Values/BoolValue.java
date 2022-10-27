package Model.Values;

import Model.Types.BoolType;
import Model.Types.Type;

public class BoolValue implements Value{

    boolean val;

    public BoolValue(){
        this.val = false;
    }

    public BoolValue(boolean v){
        this.val = v;
    }

    public boolean getVal(){
        return this.val;
    }

    @Override
    public Type getType() {
        return new BoolType();
    }

    @Override
    public Value deepCopy() {
        return new BoolValue(this.val);
    }

    @Override
    public boolean equals(Object another){
        return another instanceof BoolValue;
    }

    @Override
    public String toString() {
        return Boolean.toString(this.val);
    }
}
