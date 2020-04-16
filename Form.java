import java.util.*;
//this code is programmed under the assumption that all LIVE forms are also on F4H

public class Form {
    private String formName;
    private ArrayList<Block> blockList = new ArrayList<>(); //list of all of the blocks in every version of the form 
    private boolean isFormLive; 

    public Form(String name){
        formName = name; 
    }

    public String getName() {
        return formName; 
    }

    public ArrayList<Block> getBlocks(){
        return blockList; 
    }

    public void listBlocks(){

    }

    public void setLiveStatus(){
        isFormLive = true;

    }

    public boolean getLiveStatus(){
        return isFormLive;

    }

    public static void main(String[] args){
        //start with hardcoded directories 




    }


}