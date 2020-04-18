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
    private ArrayList<Block> blockList = new ArrayList<>(); // list of all of the blocks in every version of the form
    private boolean isFormLive;
    private ArrayList<String> blocksInForms = new ArrayList<>(); // l
    private File[] formVersions;

    public Form(String name) {
        formName = name;
    }

    public String getName() {
        return formName;
    }

    public ArrayList<Block> getBlocks() {
        return blockList;
    }

    public void setFormVersions(String pathname){
        File forms = new File(pathname + "/CompositeForms" + "/" + formName + "/");
        formVersions = forms.listFiles();
    }

    public void findBlocksInForm(String pathname){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            for(File version:formVersions ){
                if (version.getName().startsWith(".")) {
                    continue;
                }

            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(pathname + "/CompositeForms/" + formName + "/" + version.getName() + "/edit.xml");
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("nf:form-section");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;

                if(!blocksInForms.contains(eElement.getAttribute("template-form-reference"))){
                    blocksInForms.add(eElement.getAttribute("template-form-reference"));
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

    public void setLiveStatus(){
        isFormLive = true;

    }

    public boolean getLiveStatus(){

        return isFormLive;

    }

    public void setBlockArray(ArrayList<Block> listOfBlock){
        for(String block: blocksInForms){
            for(Block blockObject : listOfBlock){
                    if (block.equals(blockObject.getName())){
                        blockList.add(blockObject);
                        blockObject.addToFormList(this);
                        break;
                    }
            }
        }
    }
}