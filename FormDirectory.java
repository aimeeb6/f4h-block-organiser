import java.io.File;

public class FormDirectory {
    private String pathname;
    private File[] listOfForms;
    private File[] listOfBlocks;
    

    public FormDirectory(String directoryPathName){
        if (directoryPathName.endsWith("form-definitions")){
            pathname = directoryPathName;
            setFileLists();
        }else{
            System.out.println("The pathname should end at /forms4health-form-definitions or /ltht-form-definitions ");
            System.exit(0);
        }
        //validates repo pathways
        
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


}