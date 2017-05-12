/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.ttu.projekt.sportsseries;

import com.sun.xml.internal.ws.developer.SchemaValidation;
import ee.ttu.idu0075._2017.ws.sportsseries.EventType;
import ee.ttu.idu0075._2017.ws.sportsseries.GetEventListResponse;
import ee.ttu.idu0075._2017.ws.sportsseries.GetEventRequest;
import ee.ttu.idu0075._2017.ws.sportsseries.GetSeriesListResponse;
import ee.ttu.idu0075._2017.ws.sportsseries.GetSeriesRequest;
import ee.ttu.idu0075._2017.ws.sportsseries.SeriesEventListType;
import ee.ttu.idu0075._2017.ws.sportsseries.SeriesEventType;
import ee.ttu.idu0075._2017.ws.sportsseries.SeriesType;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.datatype.DatatypeConstants;

/**
 *
 * @author anonapsep
 */
@WebService(serviceName = "SeriesService", portName = "SeriesPort", endpointInterface = "ee.ttu.idu0075._2017.ws.sportsseries.SeriesPortType", targetNamespace = "http://www.ttu.ee/idu0075/2017/ws/sportsseries", wsdlLocation = "WEB-INF/wsdl/SportsServiceWebService/SportsSeriesService.wsdl")
@SchemaValidation public class SportsServiceWebService {
    
    static int nextEventId = 1;
    static int nextSeriesId = 1;
    
    static List<EventType> eventList = new ArrayList<EventType>();
    static List<SeriesType> seriesList = new ArrayList<SeriesType>();
   
    
    

    public ee.ttu.idu0075._2017.ws.sportsseries.EventType getEvent(ee.ttu.idu0075._2017.ws.sportsseries.GetEventRequest parameter) {
        EventType et = null;
        if (parameter.getToken().equalsIgnoreCase("salasona")) {
            for (int i = 0; i < eventList.size(); i++) {
                if (eventList.get(i).getId().equals(parameter.getId())){
                    et = eventList.get(i);
                }
                    
                
            }
        }
        return et;
    }
    

    public ee.ttu.idu0075._2017.ws.sportsseries.EventType addEvent(ee.ttu.idu0075._2017.ws.sportsseries.AddEventRequest parameter) {
        EventType et = null;  
        if (parameter.getToken().equalsIgnoreCase("salasona")) {
            et = new EventType();
            et.setId(BigInteger.valueOf(nextEventId++));
            et.setName(parameter.getName());
            et.setPlace(parameter.getPlace());
            et.setDate(parameter.getDate());
            et.setTypeOfSport(parameter.getTypeOfSport());
            
            eventList.add(et);
            
            
        }
        return et;
    }

    public ee.ttu.idu0075._2017.ws.sportsseries.GetEventListResponse getEventList(ee.ttu.idu0075._2017.ws.sportsseries.GetEventListRequest parameter) {
        GetEventListResponse response = new GetEventListResponse();
        if (parameter.getToken().equalsIgnoreCase("salasona")) {
            for (EventType eventType : eventList) { 
                response.getEvent().add(eventType);
            }
        }
        return response;
    }

    public ee.ttu.idu0075._2017.ws.sportsseries.SeriesType getSeries(ee.ttu.idu0075._2017.ws.sportsseries.GetSeriesRequest parameter) {
        SeriesType st = null;
        if (parameter.getToken().equalsIgnoreCase("salasona")) {
            for (int i = 0; i < seriesList.size(); i++) {
                if (seriesList.get(i).getId().equals(parameter.getId())) {
                    st = seriesList.get(i);
                }

            }    
        }
        return st;
    }

    public ee.ttu.idu0075._2017.ws.sportsseries.SeriesType addSeries(ee.ttu.idu0075._2017.ws.sportsseries.AddSeriesRequest parameter) {
       SeriesType st = null;  
        if (parameter.getToken().equalsIgnoreCase("salasona")) {
            st = new SeriesType();
            st.setId(BigInteger.valueOf(nextEventId++));
            st.setSeriesName(parameter.getSeriesName());
            st.setTypeOfSport(parameter.getTypeOfSport());
            st.setSeriesStartDate(parameter.getSeriesStartDate());
            st.setSeriesEndDate(parameter.getSeriesEndDate());
            st.setNumOfEvents(parameter.getNumOfEvents());
            
            seriesList.add(st);
            
            
        }
        return st;
    }

    public ee.ttu.idu0075._2017.ws.sportsseries.GetSeriesListResponse getSeriesList(ee.ttu.idu0075._2017.ws.sportsseries.GetSeriesListRequest parameter) {
        GetSeriesListResponse response = new GetSeriesListResponse();
        if (parameter.getToken().equalsIgnoreCase("salasona")) {
            for (SeriesType seriesType : seriesList) {
                if ( (parameter.getStartDate() != null && parameter.getStartDate().compare(seriesType.getSeriesStartDate())== DatatypeConstants.LESSER)
                    && (parameter.getEndDate() != null && parameter.getEndDate().compare(seriesType.getSeriesEndDate())== DatatypeConstants.GREATER)    
                        ){
                    response.getSeries().add(seriesType);
                }
            }
        }
        return response;
    }

    public ee.ttu.idu0075._2017.ws.sportsseries.SeriesEventListType getSeriesEventList(ee.ttu.idu0075._2017.ws.sportsseries.GetSeriesEventListRequest parameter) {
        SeriesEventListType seriesEventList = null;
        if (parameter.getToken().equalsIgnoreCase("salasona")) {
            for (int i = 0; i < seriesList.size(); i++) {
                if (seriesList.get(i).getId().equals(parameter.getSeriesId())) {
                    seriesEventList = seriesList.get(i).getSeriesEventList();
                }

            }    
        }
        return seriesEventList;
    
    }
    

    public ee.ttu.idu0075._2017.ws.sportsseries.SeriesEventType addSeriesEvent(ee.ttu.idu0075._2017.ws.sportsseries.AddSeriesEventRequest parameter) {
        SeriesEventType seriesEvent = new SeriesEventType();
        if (parameter.getToken().equalsIgnoreCase("salasona")){
            GetEventRequest eventRequest = new GetEventRequest();
            GetSeriesRequest seriesRequest = new GetSeriesRequest();
            eventRequest.setId(parameter.getEventId());
            eventRequest.setToken(parameter.getToken());
            seriesRequest.setId(parameter.getSeriesId());
            seriesRequest.setToken(parameter.getToken());
            
            seriesEvent.setEvent(getEvent(eventRequest));
            seriesEvent.setSeries(getSeries(seriesRequest));
            
            for (int i = 0; i < seriesList.size(); i++) {
                if (seriesList.get(i).getId().equals(parameter.getSeriesId())) {
                    seriesList.get(i).getSeriesEventList().getSeriesEvent().add(seriesEvent);
                    return seriesEvent;
                }

            } 
   
        }
         
        
        return null;
        
    }
        
     
}
    

