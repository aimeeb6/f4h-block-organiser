import java.io.BufferedWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.FileWriter;
import java.io.IOException;
public class MyApp {
    private static String livePathname = "C:\\Users\\aimee\\Documents\\EHR Work\\Development\\airelogics\\ltht-form-definitions";
    private String f4hPathname = "C:\\Users\\aimee\\Documents\\EHR Work\\Development\\forms4health-form-definitions";
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
        //creates block objects form the f4h repository ans stores in list

    }

    public void createFormObjects() {

        for (File form : f4hDir.getFormsList()) {
            if (form.getName().startsWith(".")) {
                continue;
            }
            listOfFormObjects.add(new Form(form.getName()));
            //creates form objects form the f4h repository ans stores in list
        }
    }

    public void setupFormObjects() {
        //sets up info for all forms 
        for (Form form : listOfFormObjects) {
            if (!form.equals(null)) {
                form.setFormVersions(f4hPathname);
                form.findBlocksInForm(f4hPathname, listOfBlockObjects);
                form.setLiveStatus(liveDir);
            }
        }
    }

    public void setListofBlockObjects(ArrayList<Block> list){
        listOfBlockObjects = list;
        //setter for the sorted list of block objects
    }
    
    public static Comparator<Block> nameComparator = new Comparator<Block>() {                 
        public int compare(Block b1, Block b2) {             
          return (int) (b1.getName().compareTo(b2.getName())); 
          //compares block names for sorting         
        }     
      };        

      public ArrayList<Block> getSortedBlockByName() {
          //sorts block list alphabetically
          Collections.sort(listOfBlockObjects, MyApp.nameComparator);
          return listOfBlockObjects;
      }

      public void printAllBlockInfo() throws IOException {
          BufferedWriter writer = new BufferedWriter(new FileWriter("allBlockInfo.txt"));
          for (Block b : listOfBlockObjects) {
              writer.write(b.printInfo());
              writer.newLine();
              for (Form f : b.getFormList()) {
                  writer.write(f.getName());
                  writer.write(f.printLiveStatus());
                  writer.newLine();
              }
              writer.newLine();
          }
          writer.close();
      }

      public void printBlocksWithNoUse() throws IOException {
          BufferedWriter writer = new BufferedWriter(new FileWriter("BlocksWithNoUse.txt"));
          for (Block b : listOfBlockObjects) {
              if (b.getFormList().size() == 0) {
                  writer.write(b.printInfo());
                  writer.newLine();
                  writer.newLine();
              }
              
          }
          writer.close();
      }

    public void printBlocksNotLive() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("blocksNotLive.txt"));
        blockloop:
        for (Block b : listOfBlockObjects) {
            for(Form f: b.getFormList()){
                if(f.getLiveStatus() == true){
                    continue blockloop;
                    //checks if any forms are live, skips the block loop if so
                }
            }
            for (Form f : b.getFormList()) {
                //prints info
                writer.write(b.printInfo());
                writer.newLine();
                writer.write(f.getName());
                writer.write(f.printLiveStatus());
                writer.newLine();
                writer.newLine();
            }
            
        }
        writer.close();


        }


      public static void main(String[] args) throws IOException {

          MyApp app = new MyApp();

          app.createBlockObjects();
          app.createFormObjects();
          app.setupFormObjects();
          app.setBlockObjects(app.getSortedBlockByName());
         app.printAllBlockInfo();
         // app.printBlocksWithNoUse();
         // app.printBlocksNotLive();
       
    }
  
}

