import java.io.File;
import java.util.ArrayList;

public class MyApp {
    private static String livePathname = "/Users/Aimee/Downloads/EHR_Work/Development/forms4health-form-definitions";
    private String f4hPathname = "/Users/Aimee/Downloads/EHR_Work/Development/ltht-form-definitions";
    public FormDirectory liveDir = new FormDirectory(livePathname);
    public FormDirectory f4hDir = new FormDirectory(f4hPathname);
    private ArrayList<Block> listOfBlockObjects = new ArrayList<>();

    public void createBlockObjects() {

        for (File block : f4hDir.getBlockList()) {
            listOfBlockObjects.add(new Block(block.getName()));
        }
        listOfBlockObjects.get(5).printInfo();
    }

    public static void main(String[] args){
        MyApp setup = new MyApp();

        setup.createBlockObjects();

    }
  
}