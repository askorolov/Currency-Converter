/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.convertor;



/**
 *
 * @author aleksandr
 */

import javax.xml.soap.*;
import java.io.*;


public class Update {
    
     
public float usdRate() throws Exception {
        // Create SOAP Connection
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();

        // Send SOAP Message to SOAP Server
        String url = "http://www.cbr.ru/DailyInfoWebServ/DailyInfo.asmx";
        SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(), url);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        soapResponse.writeTo(out);
        String strMsg = new String(out.toByteArray());
        float usdRate=Float.parseFloat(strMsg.substring((strMsg.indexOf("curs")+5),strMsg.indexOf("</curs>")));
        soapConnection.close();
        return usdRate;
    }


    public float eurRate() throws Exception {
        // Create SOAP Connection
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();

        // Send SOAP Message to SOAP Server
        String url = "http://www.cbr.ru/DailyInfoWebServ/DailyInfo.asmx";
        SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(), url);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        soapResponse.writeTo(out);
        String strMsg = new String(out.toByteArray());
        strMsg=strMsg.substring(strMsg.indexOf("</curs>")+5);
        float eurRate=Float.parseFloat(strMsg.substring((strMsg.indexOf("curs")+5),strMsg.indexOf("</curs>")));
        
        
        
        soapConnection.close();
        return eurRate;
    }




    private static SOAPMessage createSOAPRequest() throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String serverURI = "http://web.cbr.ru/";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("example", serverURI);

        

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("AllDataInfoXML", "example");
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("On_date", "example");
        
        

        


     MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", serverURI  + "AllDataInfoXML");

        soapMessage.saveChanges();




        return soapMessage;
    }

}  
