package dev.kaio.resource;

import dev.kaio.entity.Person;
import dev.kaio.service.PersonService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

    @Inject
    PersonService personService;

    @GET
    public List<Person> personList() {
        return personService.personList();
    }

    @POST
    public Response addPerson(Person person) {
        return Response.status(Response.Status.CREATED).entity(personService.addPerson(person)).build();
    }

    @GET
    @Path("/{id}")
    public Person findPersonById(@PathParam("id") Long id) {
        return personService.findById(id);
    }

    @PUT
    @Path("/{id}")
    public Response updatePerson(@PathParam("id") Long id, Person person) {
        return Response.status(Response.Status.OK).entity(personService.updatePerson(id, person)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletePerson(@PathParam("id") Long id) {
        personService.deletePerson(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
