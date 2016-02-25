package prototypes;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import xmlsources.ExternalGenome;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.InputMismatchException;

/**
 * Creates a new XML file used for running a NASP job.
 *
 * @author Jean-Paul Labadie
 */
public class OutputParser {

    private DocumentBuilderFactory docFactory;
    private DocumentBuilder docBuilder;
    private Document output;
    private Element mainRootElement;

    public OutputParser(){

        docFactory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = docFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        output = docBuilder.newDocument();

        mainRootElement = output.createElement("NaspInputData");
        output.appendChild(mainRootElement);
    }

    /*
    Creates a new Element Node in the XML DOM with the supplied inputs. 
    Note that this is represented in memory, but not yet written to the 
    disk. Elements and Attributes are defined as Classes based on the 
    NASPInputSchema.xsd
    */
    public void addElement(ExternalGenome in) throws InputMismatchException{
    }

    /*
    Writes the current XML DOM to the disk using the filename given 
    in the default output directory. This finished XML represents 
    the entirety of the NASP tool job request, and can be sent to 
    a remote service running NASP to begin a new job.
    */
    public void createOutputXML(){

        Transformer transformer;
        DOMSource source = new DOMSource(output);
        StreamResult console = new StreamResult(new File("output.xml"));

        try {
            transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, console);
        } catch (TransformerConfigurationException e) {
            // the transformer could not be configured as desired
            e.printStackTrace();
        } catch (TransformerException e) {
            // the transformer failed when performing a transform
            e.printStackTrace();
        }
    }
}
