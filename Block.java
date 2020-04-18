import java.util.*;

public class Block {
    private String blockName; 
    private ArrayList<Form> formList = new ArrayList<>();

    public Block(String name){
        blockName = name; 
    }

    public void addToFormList(Form form){
        if(!formList.contains(form)){
            formList.add(form);
    }
}

    public String getName(){
        return blockName;
    }

    public void printInfo(){ 
        if(formList.size() < 1){
            System.out.println("The" + blockName + " is not used in any forms");
        }
        System.out.println(blockName + " is used in these forms ");
        for(Form form:formList){
            System.out.println(form.getName());
        //needs more work for emtpy forms lists
        }
    }

}