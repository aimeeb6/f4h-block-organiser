import java.util.*;

public class Block {
    private String blockName; 
    private ArrayList<Form> formList = new ArrayList<>();

    public Block(String name){
        blockName = name; 
    }

    public void addToFormList(Form form){
        formList.add(form);
    }

    public void printInfo(){
        System.out.println(blockName + "is used in these forms" + formList);
        //needs more work for emtpy forms lists
    }

}