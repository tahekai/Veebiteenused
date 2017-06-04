/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportsseriesclientapplication;

import ee.ttu.idu0075._2017.ws.sportsseries.EventType;
import ee.ttu.idu0075._2017.ws.sportsseries.GetEventRequest;
import java.math.BigInteger;
import java.util.Scanner;

/**
 *
 * @author anonapsep
 */
public class SportsSeriesClientApplication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner obj = new Scanner(System.in);
        System.out.println("Sisesta ürituse id: ");
        String id = obj.next();
        GetEventRequest parameter = new GetEventRequest();
        parameter.setToken("salasona");
        parameter.setId(new BigInteger(id));
        
        EventType event = getEvent(parameter);
            if(event == null){
                System.out.println("Sellise id-ga üritust ei ole");
            } else {
                System.out.println("Üritus: " + event.getName());
            }
        
    }

    private static EventType getEvent(ee.ttu.idu0075._2017.ws.sportsseries.GetEventRequest parameter) {
        ee.ttu.idu0075._2017.ws.sportsseries.SeriesService service = new ee.ttu.idu0075._2017.ws.sportsseries.SeriesService();
        ee.ttu.idu0075._2017.ws.sportsseries.SeriesPortType port = service.getSeriesPort();
        return port.getEvent(parameter);
    }
    
}
