package Model.Types;

import Model.Values.IntValue;
import Model.Values.Value;

public class IntType implements Type{

    @Override
    public Value defaultValue() {
        return new IntValue(0);
    }

    @Override
    public Type deepCopy() {
        return new IntType();
    }

    @Override
    public boolean equals(Object another){
        return another instanceof IntType;
    }

    @Override
    public String toString() {
        return "int";
    }
}
