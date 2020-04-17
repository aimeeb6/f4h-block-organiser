import java.io.File;
import java.util.Arrays;

public class Directory {
    private String pathname;
    private File[] listOfForms;
    private File[] listOfBlocks;
    

    public Directory(String directoryPathName){
        if (directoryPathName.endsWith("form-definitions")){
            pathname = directoryPathName;
            setFileLists();
        }else{
            System.out.println("The pathname should end at /forms4health-form-definitions or /ltht-form-definitions ");
            System.exit(0);
        }
        
    }

    public void setFileLists(){
        File forms = new File(pathname + "/CompositeForms");
        listOfForms = forms.listFiles();

        File blocks = new File(pathname + "/FormSections");
        listOfBlocks = blocks.listFiles();


    }

    public File[] getFormsList(){
        return listOfForms;
    }

    public File[] getBlockList(){
        return listOfBlocks;
    }

    public static void main(String[] args){
        
        Directory liveDir = new Directory("/Users/Aimee/Downloads/EHR_Work/Development/forms4health-form-definitions");
        System.out.println(Arrays.toString(liveDir.getBlockList().getName()));

    }


}