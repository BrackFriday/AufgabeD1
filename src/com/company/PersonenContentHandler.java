package com.company;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;

public class PersonenContentHandler implements ContentHandler {

    private ArrayList<Person> allePersonen = new ArrayList<Person>();
    private String currentValue;
    private Person person;

    // Aktuelle Zeichen die gelesen werden, werden in eine Zwischenvariable
    // gespeichert
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        currentValue = new String(ch, start, length);
    }

    // Methode wird aufgerufen wenn der Parser zu einem Start-Tag kommt
    public void startElement(String uri, String localName, String qName,
                             Attributes atts) throws SAXException {
        if (localName.equals("person")) {
            // Neue Person erzeugen
            person = new Person();

            // Attribut id wird in einen Integer umgewandelt und dann zu der
            // jeweiligen Person gesetzt
            person.setId(Integer.parseInt(atts.getValue("id")));
        }
    }

    // Methode wird aufgerufen wenn der Parser zu einem End-Tag kommt
    public void endElement(String uri, String localName, String qName)
            throws SAXException {

        // Name setzen
        if (localName.equals("name")) {
            person.setName(currentValue);
        }

        // Vorname setzen
        if (localName.equals("vorname")) {
            person.setVorname(currentValue);
        }

        // Datum parsen und setzen
        if (localName.equals("geburtsdatum")) {
            SimpleDateFormat datumsformat = new SimpleDateFormat("dd.MM.yyyy");
            try {
                Date date = datumsformat.parse(currentValue);
                person.setGeburtsdatum(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        // Postleitzahl setzen
        if (localName.equals("postleitzahl")) {
            person.setPostleitzahl(currentValue);
        }

        // Ort setzen
        if (localName.equals("ort")) {
            person.setOrt(currentValue);
        }

        //hobby setzen
        if (localName.equals("hobby")){
            person.setHobby(currentValue);
        }

        //lieblingsgericht setzen
        if (localName.equals("lieblingsgericht")){
            person.setLieblingsgericht(currentValue);
        }

        //lieblingsband setzen
        if (localName.equals("lieblingsband")){
            person.setLieblingsband(currentValue);
        }

        // Person in Personenliste abspeichern falls Person End-Tag erreicht
        // wurde.
        if (localName.equals("person")) {
            allePersonen.add(person);
            System.out.println(person);
        }
    }

    public void addPerson() throws IOException, SAXException, ParserConfigurationException, TransformerException, ParseException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newDefaultInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse("/Users/lukas/Documents/HOST/LP Software/Aufgabenblatt 3/AufgabeD1/Personen.xml");
        org.w3c.dom.Element root = document.getDocumentElement();


        Collection<Person> personen = new ArrayList<Person>();
        personen.add(new Person());

        Scanner scanner = new Scanner(System.in);

      //  for (Person person1 : personen){

            org.w3c.dom.Element newPerson = document.createElement("person");

            System.out.println("ID:");
            org.w3c.dom.Attr id = document.createAttribute("id");
            id.setValue(scanner.nextLine());
            newPerson.setAttributeNode(id);

            System.out.println("Name:");
            org.w3c.dom.Element name = document.createElement("name");
            name.appendChild(document.createTextNode(scanner.nextLine()));
            newPerson.appendChild(name);

            System.out.println("Vorname:");
            org.w3c.dom.Element vorname = document.createElement("vorname");
            vorname.appendChild(document.createTextNode(scanner.nextLine()));
            newPerson.appendChild(vorname);

            System.out.println("Geburtsdatum:");
            org.w3c.dom.Element geburtsdatum = document.createElement("geburtsdatum");
             SimpleDateFormat datumsformat = new SimpleDateFormat("dd.MM.yyyy");
             String date = scanner.nextLine();
             Date datum = datumsformat.parse(date);
             try{
                if(datumsformat.parse(date).equals(datum)) {
                    geburtsdatum.appendChild(document.createTextNode(date));
                    newPerson.appendChild(geburtsdatum);
                } else{
                    System.out.println("Datum ist falsch");
                }
             } catch (ParseException e){
                 e.printStackTrace();
             }
        System.out.println("Postleitzahl:");
            org.w3c.dom.Element postleitzahl = document.createElement("postleitzahl");
            postleitzahl.appendChild(document.createTextNode(scanner.nextLine()));
            newPerson.appendChild(postleitzahl);

            System.out.println("Ort:");
            org.w3c.dom.Element ort = document.createElement("ort");
            ort.appendChild(document.createTextNode(scanner.nextLine()));
            newPerson.appendChild(ort);

            System.out.println("Hobby:");
            org.w3c.dom.Element hobby = document.createElement("hobby");
            hobby.appendChild(document.createTextNode(scanner.nextLine()));
            newPerson.appendChild(hobby);

            System.out.println("Lieblingsgericht:");
            org.w3c.dom.Element lieblingsgericht = document.createElement("lieblingsgericht");
            lieblingsgericht.appendChild(document.createTextNode(scanner.nextLine()));
            newPerson.appendChild(lieblingsgericht);

            System.out.println("Lieblingsband:");
            Element lieblingsband = document.createElement("lieblingsband");
            lieblingsband.appendChild(document.createTextNode(scanner.nextLine()));
            newPerson.appendChild(lieblingsband);

            root.appendChild(newPerson);
    //    }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        StreamResult result = new StreamResult("/Users/lukas/Documents/HOST/LP Software/Aufgabenblatt 3/AufgabeD1/Personen");
        DOMSource source = new DOMSource(document);
        transformer.transform(source, result);



    }



    public void endDocument() throws SAXException {}
    public void endPrefixMapping(String prefix) throws SAXException {}
    public void ignorableWhitespace(char[] ch, int start, int length)
            throws SAXException {}
    public void processingInstruction(String target, String data)
            throws SAXException {}
    public void setDocumentLocator(Locator locator) {  }
    public void skippedEntity(String name) throws SAXException {}
    public void startDocument() throws SAXException {}
    public void startPrefixMapping(String prefix, String uri)
            throws SAXException {}
}