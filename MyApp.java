import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class MyApp {
    private static String livePathname = "/Users/Aimee/Downloads/EHR_Work/Development/forms4health-form-definitions";
    private String f4hPathname = "/Users/Aimee/Downloads/EHR_Work/Development/ltht-form-definitions";
    public FormDirectory liveDir = new FormDirectory(livePathname);
    public FormDirectory f4hDir = new FormDirectory(f4hPathname);
    private ArrayList<Block> listOfBlockObjects = new ArrayList<>();
    private ArrayList<Form> listOfFormObjects = new ArrayList<>();

    public void createBlockObjects() {

        for (File block : f4hDir.getBlockList()) {
            if (block.getName().startsWith(".")) {
                continue;
            }
            listOfBlockObjects.add(new Block(block.getName()));
        }

    }

    public void createFormObjects() {

        for (File form : f4hDir.getFormsList()) {
            if (form.getName().startsWith(".")) {
                continue;
            }
            listOfFormObjects.add(new Form(form.getName()));
        }
    }

    public void setupFormObjects(){
        for(Form form:listOfFormObjects){
            if(!form.equals(null)){
                form.setFormVersions(f4hPathname);
                form.findBlocksInForm(f4hPathname, listOfBlockObjects);
                form.setLiveStatus(liveDir);
        }
        }
    }

    public void printInfo(){
        for(Block b: listOfBlockObjects){
            b.printInfo();
        }
    }
    public static void main(String[] args) throws ParserConfigurationException, SAXException {
      
        MyApp app = new MyApp();

        app.createBlockObjects();
        app.createFormObjects();
        app.setupFormObjects();
        app.printInfo();
       
    }
  
}