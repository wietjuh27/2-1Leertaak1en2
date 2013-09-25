/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.sql.SQLException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import model.Station;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Martijn Doornik
 */
public class Parser extends DefaultHandler implements Runnable {
    
    private String tag;
    private Station station;
    private Socket s;

    /**
     * Create new parser, which can been started to parse the XML documents,
     * deliviered by the imputstream
     *
     * @param socket
     */
    public Parser(Socket socket) {
        s = socket;
    }
    
    @Override
    public void run() {
        try {
            InputStream is = s.getInputStream();
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();
            sp.parse(is, this);
            
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
        tag = qName;
    }
    
    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        try {
            String tmp = new String(ch, start, length);
            switch (tag) {
                case "STN":
                    station = new Station(tmp);
                    break;
                case "DATE":
                    station.setDate(tmp);
                    break;
                case "TIME":
                    station.setTime(tmp);
                    break;
                case "DEWP":
                    station.setDewPoint(tmp);
                    break;
                case "STP":
                    station.setSTP(tmp);
                    break;
                case "SLP":
                    station.setSLP(tmp);
                    break;
                case "VISIB":
                    station.setVisibilty(tmp);
                    break;
                case "WDSP":
                    station.setWindSpeed(tmp);
                    break;
                case "PRCP":
                    station.setRainFall(tmp);
                    break;
                case "SNDP":
                    station.setSnowFall(tmp);
                    break;
                case "FRSHTT":
                    station.setEvent(tmp);
                    break;
                case "CLDC":
                    station.setOvercast(tmp);
                    break;
                case "WNDDIR":
                    station.setWindDirection(tmp);
                    break;
                case "MEASUREMENT":
                    try {
                        ConnectionController.addToDB(station);
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
            } //end of switch
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
        } //end of try-catch block
    } //end of method "characters"
} // end of class