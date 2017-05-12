/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.ttu.projekt.sportsseries;

import ee.ttu.idu0075._2017.ws.sportsseries.AddEventRequest;
import ee.ttu.idu0075._2017.ws.sportsseries.EventType;
import ee.ttu.idu0075._2017.ws.sportsseries.GetEventListRequest;
import ee.ttu.idu0075._2017.ws.sportsseries.GetEventListResponse;
import ee.ttu.idu0075._2017.ws.sportsseries.GetEventRequest;
import ee.ttu.idu0075._2017.ws.sportsseries.GetSeriesListRequest;
import ee.ttu.idu0075._2017.ws.sportsseries.GetSeriesListResponse;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

/**
 * REST Web Service
 *
 * @author anonapsep
 */
@Path("event")
public class EventResource {

     @Context
    private UriInfo context;
    
    public EventResource() {
    }

    @GET
    @Produces("application/json")
    public GetEventListResponse getEventList(@QueryParam("token") String token) {
        SportsServiceWebService ws = new SportsServiceWebService();
        GetEventListRequest request = new GetEventListRequest();
        request.setToken(token);
        return ws.getEventList(request);
    }
    
    @GET
    @Path("{id : \\d+}") //supports digits only
    @Produces("application/json")
    public EventType getEvent(@PathParam("id") String id,
            @QueryParam("token") String token) {
        SportsServiceWebService ws = new SportsServiceWebService();
        GetEventRequest request = new GetEventRequest();
        request.setId(BigInteger.valueOf(Integer.parseInt(id)));
        request.setToken(token);
        return ws.getEvent(request);
    }
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public EventType addEvent(EventType content, 
            @QueryParam("token") String token) {
        SportsServiceWebService ws = new SportsServiceWebService();
        AddEventRequest request = new AddEventRequest();
        request.setName(content.getName());
        request.setToken(token);
        request.setPlace(content.getPlace());
        request.setDate(content.getDate());
        request.setTypeOfSport(content.getTypeOfSport());
        return ws.addEvent(request);
    }
}

