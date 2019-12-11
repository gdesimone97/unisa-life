/**
 * Sprint #1
 * User Story: (13) As a player I would like more languages
 * Task: (7) Structure of the language package
 * Team Members: Alfonso De Masi, Giuseppe De Simone
 */
package language;

import language.exceptions.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 * Concrete class that performs string queries in the given XML File. It should
 * be hidden behind a CacheFileTextFinder according to the Proxy pattern.
 *
 * @author alfon
 */
public class ConcreteFileTextFinder extends FileTextFinder {

    private static ConcreteFileTextFinder instance = null;
    private XPath querier = null;
    private DocumentBuilder documentBuilder = null;
    private final String MESSAGE_TAG = "message";

    /**
     * Static method to get a ConcreteFileTextFinder object, according to
     * Singleton pattern.
     *
     * @return an instance of ConcreteFileTextFinder
     * @throws FileNotSetException if the file name wasn't previously set
     */
    public static synchronized ConcreteFileTextFinder getConcreteFileTextFinder() throws TextFinderException {
        if (FileTextFinder.getFileName() == null) {
            throw new FileNotSetException();
        }
        if (ConcreteFileTextFinder.instance == null) {
            ConcreteFileTextFinder.instance = new ConcreteFileTextFinder();
        }
        return instance;
    }

    /**
     * Private constructor, it builds up a DocumentBuilder and a XPath object
     *
     * @throws XMLFileException
     */
    private ConcreteFileTextFinder() throws XMLFileException {
        try {
            this.documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            throw new XMLFileException();
        }
        this.querier = XPathFactory.newInstance().newXPath();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getString(Information obj) throws TextFinderException {
        String exp;
        try {
            exp = this.computeExpression(obj);
        } catch (InvalidObjectInformationException ex) {
            throw new StringNotFoundException();
        }
        return this.getString(exp);
    }

    /**
     * Method to instantiate a Document object in order to be used by a XML
     * parser.
     *
     * @param inputFile the File pointing to the xml file.
     * @return a Document object
     * @throws XMLFileException if error occours opening the file
     */
    private Document createDocument(FileInputStream inputFile) throws XMLFileException {
        Document doc = null;
        try {
            doc = this.documentBuilder.parse(inputFile);
        } catch (SAXException | IOException ex) {
            throw new XMLFileException();
        }
        doc.getDocumentElement().normalize();
        return doc;
    }

    /**
     * As getString(Information obj), it performs the query and searches for the
     * list of strings to be returned. This actually works taking an expression
     * directly.
     *
     * @param exp The expression of the query
     * @return the desired list of strings
     * @throws TextFinderException
     */
    protected List<String> getString(String exp) throws TextFinderException {

        List<String> returnList = new ArrayList<>();
        FileInputStream inputFile;
        try {
            inputFile = new FileInputStream(FileTextFinder.getFileName());
        } catch (FileNotFoundException ex) {
            throw new TextFinderException();
        }

        Document doc = this.createDocument(inputFile);
        NodeList nodeList = null;
        try {
            nodeList = (NodeList) this.querier.compile(exp).evaluate(doc, XPathConstants.NODESET);
        } catch (XPathExpressionException ex) {
            throw new StringNotFoundException();
        }
        // nodeList contains all the nodes that matches the query
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node currentNode = nodeList.item(i);
            if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
                // each node of the list has a set of messages, which are also nodes
                Element currentElement = (Element) currentNode;
                NodeList msgNodeList = currentElement.getElementsByTagName(MESSAGE_TAG);
                for (int j = 0; j < msgNodeList.getLength(); j++) {
                    returnList.add(msgNodeList.item(j).getTextContent());
                }
            }
        }
        if (returnList.size() <= 0) {
            throw new StringNotFoundException();
        }
        try {
            this.documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            throw new XMLFileException();
        }
        return returnList;
    }
}
