package ramqfileconverter;

import java.io.File;
import javax.xml.XMLConstants;
import javax.xml.validation.SchemaFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import java.io.IOException;
import org.xml.sax.SAXException;
import javax.xml.validation.Validator;
import org.w3c.dom.Document;
import javax.xml.transform.dom.DOMSource;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXNotRecognizedException;

/**
 * <p>Title: RAMQFileConverter</p>
 *
 * <p>Description: Application to convert RAMQ reclamation files to XML</p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: Sebastien Vezina</p>
 *
 * @author Sebastien Vezina: vezinase@gmail.com
 * @version 1.1
 */
public class XMLValidator {

    final private String controlFileSchemaFileName = "FICH_CTRL.xsd";
    final private String DPFileSchemaFileName = "DP_MED.xsd";
    final private String SCHEMA_NS_URI = XMLConstants.W3C_XML_SCHEMA_NS_URI;

    private Validator controlFileSchemaValidator;
    private Validator DPFileSchemaValidator;

    private Schema DPFileSchema;

    public XMLValidator() throws SAXNotSupportedException,
            SAXNotRecognizedException, SAXException {

        Schema schema;
        SchemaFactory factory = SchemaFactory.newInstance(SCHEMA_NS_URI);
        factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, Boolean.TRUE);

        schema = factory.newSchema(new File(controlFileSchemaFileName));
        controlFileSchemaValidator = schema.newValidator();

        schema = factory.newSchema(new File(DPFileSchemaFileName));
        DPFileSchemaValidator = schema.newValidator();
    }

    public void validateXMLControlFileDoc(Document doc) throws IOException,
            SAXException {
        //throw new SAXException();
        controlFileSchemaValidator.validate(new DOMSource(doc));
    }

    public void validateXMLDPFile(Document doc) throws IOException,
            SAXException {
        //throw new SAXException();
        DPFileSchemaValidator.validate(new DOMSource(doc));
    }
}
