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
            //adds the form that contains this block to list, if block doesn't exit
    }
}

    public String getName(){
        return blockName;
    }

    public String printInfo(){ 
        if(formList.size() == 0){
            return("The " + blockName + " is not used in any forms");
        }else{
            return(blockName + " is used in a version of theses forms ");
        }
}

public ArrayList<Form> getFormList(){
    return formList;
}
}