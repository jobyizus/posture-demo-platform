package com.mycompany.resources;

import com.mycompany.api.Event;
import com.mycompany.core.EventRepository;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

@Path("events")
@Produces(MediaType.APPLICATION_JSON)
public class EventResource {

    private EventRepository repository;

    public EventResource(EventRepository repository) {
        this.repository = repository;
    }

    @GET
    public List<Event> allEvents() {
        return repository.findAll();
    }

    @GET
    @Path("{id}")
    public Event event(@PathParam("id") Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new WebApplicationException("Event not found", 404));
    }

    @POST
    public Event create(Event event) {
        return repository.save(event);
    }

    @PUT
    @Path("{id}")
    public Event update(@PathParam("id") Long id, Event event) {
        return repository.update(id, event)
                .orElseThrow(() ->
                        new WebApplicationException("Event not found", 404));
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        repository.delete(id);
        return Response.ok().build();
    }

}
