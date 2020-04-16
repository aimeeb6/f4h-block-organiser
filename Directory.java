
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Directory {
    private String pathname;
    private File[] listOfFiles;

    public Directory(String directoryPathName){
        pathname = directoryPathName;
    }

    public void setFileList(){
        File folder = new File(pathname);
        try {
            Files.list(new File(pathname).toPath()).forEach(path -> {
                System.out.println(path);
            });
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void main(String[] args){
        
        Directory liveDir = new Directory("/Users/Aimee/Downloads/EHR_Work/Development/forms4health-form-definitions/CompositeForms");
        liveDir.setFileList();

    }


}