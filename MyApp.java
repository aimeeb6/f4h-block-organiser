import java.io.BufferedWriter;
import java.io.File;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

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

    public void setupFormObjects() {
        for (Form form : listOfFormObjects) {
            if (!form.equals(null)) {
                form.setFormVersions(f4hPathname);
                form.findBlocksInForm(f4hPathname, listOfBlockObjects);
                form.setLiveStatus(liveDir);
            }
        }
    }

    public void printAllBlockInfo() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("blockInfo.txt"));
        for(Block b: listOfBlockObjects){
            writer.write(b.printInfo());
            writer.newLine();
            for(Form f: b.getFormList()){
                writer.write(f.getName());
                writer.newLine();
            }
            writer.newLine();
            writer.newLine();
        }
        writer.close();
    }

    public void printBlocksWithNoUse() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("blockInfo.txt"));
        for(Block b: listOfBlockObjects){
            if(b.getFormList().size() == 0){
                writer.write(b.printInfo());
                writer.newLine();
            }
        }
        writer.close();
    }


    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
      
        MyApp app = new MyApp();

        app.createBlockObjects();
        app.createFormObjects();
        app.setupFormObjects();
        app.printBlocksWithNoUse();
       
    }
  
}