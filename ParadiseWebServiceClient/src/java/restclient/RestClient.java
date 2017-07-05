/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restclient;

import ejb.Roomtypes;
import java.util.Collection;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Preet
 */
public class RestClient {
    public Client client;
    public Collection<Roomtypes> entity;
    
    public RestClient(){
        client = ClientBuilder.newClient();
    }
    
    public Collection<Roomtypes> getRoomTypes(){
        entity = client.target("http://localhost:8080/ParadiseHotel-war/")
                .path("webresources/service")
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<Collection<Roomtypes>>(){});
        return entity;
    }
    
}
