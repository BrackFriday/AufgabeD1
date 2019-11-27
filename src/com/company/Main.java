package com.company;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class Main {
    public static void main(String[] args) throws SAXException {

        Scanner scanner = new Scanner(System.in);

        PersonenContentHandler person = new PersonenContentHandler();
        try {
            // XMLReader erzeugen
            XMLReader xmlReader = XMLReaderFactory.createXMLReader();

            // Pfad zur XML Datei
            FileReader reader = new FileReader("/Users/lukas/Documents/HOST/LP Software/Aufgabenblatt 3/AufgabeD1/Personen.xml");
            InputSource inputSource = new InputSource(reader);


            // DTD kann optional übergeben werden
             inputSource.setSystemId("/Users/lukas/Documents/HOST/LP Software/Aufgabenblatt 3/AufgabeD1/src/com/company/personen.dtd");
            // White Spaces??


            // PersonenContentHandler wird übergeben
            xmlReader.setContentHandler(new PersonenContentHandler());

            PersonenContentHandler personenContentHandler = new PersonenContentHandler();


            System.out.println("Bitte Auswahl treffen:");
            System.out.println("Person hinzufügen [1]");
            System.out.println("Liste ausgeben [2]");
            String ergebnis = scanner.nextLine();
            switch (ergebnis) {
                case "1":
                    personenContentHandler.addPerson();
                    break;
                case "2":
                    // Parsen wird gestartet
                    xmlReader.parse(inputSource);
                    break;
                default:
                    System.out.println("Eingabe war inkorrekt! Bitte 1 oder 2 eingeben.");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}

