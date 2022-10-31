package Model.Values;

import Model.Types.*;

import java.sql.SQLIntegrityConstraintViolationException;

public class IntValue implements Value{

    int val;

    public IntValue(){
        this.val = 0;
    }

    public IntValue(int v){
        this.val = v;
    }

    public int getVal(){
        return this.val;
    }

    @Override
    public Type getType() {
        return new IntType();
    }

    @Override
    public Value deepCopy() {
        return new IntValue(this.val);
    }

    @Override
    public String toString() {
        return Integer.toString(this.val);
    }
}