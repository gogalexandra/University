package View;

import Controller.Controller;

import java.io.IOException;

public class RunExample extends Command{
    private Controller ctr;
    public RunExample(String key, String desc,Controller ctr){
        super(key, desc);
        this.ctr=ctr;
    }
    @Override
    public void execute() throws IOException {
        try{
            ctr.allStep(); }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
