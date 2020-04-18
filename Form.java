import java.io.File;
import java.io.IOException;
import java.util.*;
//this code is programmed under the assumption that all LIVE forms are also on F4H
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Form {
    private String formName;
    private ArrayList<Block> blockList =  new ArrayList<>();
    private ArrayList<String> formVersions =  new ArrayList<>();
    private boolean isFormLive = false;
    

    public Form(String name) {
        formName = name;
    }

    public String getName() {
        return formName;
    }

    public ArrayList<Block> getBlocks() {
        return blockList;
    }

    public void setFormVersions(String f4hPathname){
        // F4Hpathname makes the pathname from the forms4health website
        File forms = new File(f4hPathname + "/CompositeForms" + "/" + formName + "/");
        File[] versions = forms.listFiles();
        
        for(File formV: versions){
            if (formV.getName().startsWith(".")) {
                continue;
            }
            formVersions.add(formV.getName());
        }
    }

    public void findBlocksInForm(String f4hPathname, ArrayList<Block> blockObjList){
        // F4Hpathname makes the pathname from the forms4health website
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            //loops through all of the form versions 
            for(String version:formVersions ){
                if (version.startsWith(".")) {
                    continue;
                    //this removes any unwanted files such as .git files and .DS_Store
                }

            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(f4hPathname + "/CompositeForms/" + formName + "/" + version + "/edit.xml");
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("nf:form-section");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;

                for(Block b: blockObjList){
                    if(b.getName().equals(eElement.getAttribute("template-form-reference"))){
                        blockList.add(b);
                        b.addToFormList(this);
                    }
            }
            }
            }
        }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    public void setLiveStatus(FormDirectory liveDir){

        for(File form:liveDir.getFormsList()){
            if(form.getName().equals(formName)){
                isFormLive = true;
            }
    }
}

    public boolean getLiveStatus(){
        return isFormLive;

    }
}