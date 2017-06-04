/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.ttu.projekt.sportsseries;

import ee.ttu.idu0075._2017.ws.sportsseries.AddSeriesEventRequest;
import ee.ttu.idu0075._2017.ws.sportsseries.AddSeriesRequest;
import ee.ttu.idu0075._2017.ws.sportsseries.GetSeriesEventListRequest;
import ee.ttu.idu0075._2017.ws.sportsseries.GetSeriesListRequest;
import ee.ttu.idu0075._2017.ws.sportsseries.GetSeriesListResponse;
import ee.ttu.idu0075._2017.ws.sportsseries.GetSeriesRequest;
import ee.ttu.idu0075._2017.ws.sportsseries.SeriesEventListType;
import ee.ttu.idu0075._2017.ws.sportsseries.SeriesEventType;
import ee.ttu.idu0075._2017.ws.sportsseries.SeriesType;
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
@Path("series")
public class SeriesResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SeriesResource
     */
    public SeriesResource() {
    }

    /**
     * Retrieves representation of an instance of ee.ttu.projekt.sportsseries.SeriesResource
     * @param token
     
     * @param startDateString
     * @param endDateString
     * @return an instance of ee.ttu.idu0075._2017.ws.sportsseries.SeriesType
     */
    @GET
    @Produces("application/json")
    public GetSeriesListResponse getSeriesList(
            @QueryParam("token") String token,
            
            @QueryParam("start") String startDateString,
            @QueryParam("end") String endDateString) {
        try {
            SportsServiceWebService ws = new SportsServiceWebService();
            GetSeriesListRequest request = new GetSeriesListRequest();
            request.setToken(token);
            
            
            DateFormat formatter; 
            formatter = new SimpleDateFormat("dd-MM-yy");
            Date date = formatter.parse(startDateString);
            GregorianCalendar gregory = new GregorianCalendar();
            gregory.setTime(date);
            request.setStartDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory));
            date = formatter.parse(endDateString);
            gregory = new GregorianCalendar();
            gregory.setTime(date);
            request.setEndDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory));
            return ws.getSeriesList(request);
        } catch (ParseException | DatatypeConfigurationException ex) {
            Logger.getLogger(SeriesResource.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @GET
    @Path("{id : \\d+}") //support digit only
    @Produces("application/json")
    public SeriesType getSeries(@PathParam("id") String id,
            @QueryParam("token") String token) {
        SportsServiceWebService ws = new SportsServiceWebService();
        GetSeriesRequest request = new GetSeriesRequest();
        request.setId(BigInteger.valueOf(Integer.parseInt(id)));
        request.setToken(token);
        return ws.getSeries(request);
    }
    
    /**
     *
     * @param content
     * @param token
     * @return 
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public SeriesType addSeries(SeriesType content, 
                                @QueryParam("token") String token) {
        SportsServiceWebService ws = new SportsServiceWebService();
        AddSeriesRequest request = new AddSeriesRequest();
        request.setToken(token);
        request.setSeriesName(content.getSeriesName());
        request.setSeriesStartDate(content.getSeriesStartDate());
        request.setSeriesEndDate(content.getSeriesEndDate());
        request.setTypeOfSport(content.getTypeOfSport());
        request.setNumOfEvents(content.getNumOfEvents());
        request.setRequestId(content.getRequestId());
        return ws.addSeries(request);    
    }
    
    
    @GET
    @Path("{id : \\d+}/products") //support digit only
    @Produces("application/json")
    public SeriesEventListType getSeriesEventList(@PathParam("id") String id,
            @QueryParam("token") String token) {
        SportsServiceWebService ws = new SportsServiceWebService();
        GetSeriesEventListRequest request = new GetSeriesEventListRequest();
        request.setSeriesId(BigInteger.valueOf(Integer.parseInt(id)));
        request.setToken(token);
        return ws.getSeriesEventList(request);
    }
    
    /**
     *
     * @param content
     * @param token
     * @param id
     * @return 
     */
    @POST
    @Path("{id : \\d+}/products") //support digit only
    @Consumes("application/json")
    @Produces("application/json")
    public SeriesEventType addSeriesEvent(AddSeriesEventRequest content, 
                                @QueryParam("token") String token,
                                @PathParam("id") String id) {
        SportsServiceWebService ws = new SportsServiceWebService();
        AddSeriesEventRequest request = new AddSeriesEventRequest();
        request.setToken(token);
        request.setSeriesId(content.getSeriesId());
        request.setEventId(content.getEventId());
        return ws.addSeriesEvent(request);    
    }
}
