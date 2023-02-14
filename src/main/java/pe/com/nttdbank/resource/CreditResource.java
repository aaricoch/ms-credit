package pe.com.nttdbank.resource;

import java.util.List;

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
import jakarta.ws.rs.core.Response.Status;
import pe.com.nttdbank.dto.CreditDto;
import pe.com.nttdbank.service.CreditService;

@Path("/ms-credit/credit")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CreditResource {

    @Inject
    CreditService creditService;

    @GET
    public Response getAll() {
        List<CreditDto> credits = creditService.getAll();
        return Response.ok(credits).build();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") Long Id) {
        CreditDto creditDto = creditService.getById(Id);
        if (creditDto == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.ok(creditDto).build();
    }

    @POST
    public Response create(CreditDto creditDto) {
        if (creditService.Create(creditDto)) {
            return Response.ok().status(Status.CREATED).build();
        }
        return Response.status(Status.CONFLICT).build();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Long Id, CreditDto creditDto) {
        if (creditService.Edit(Id, creditDto)) {
            return Response.noContent().build();
        }
        return Response.ok().status(Status.CONFLICT).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long Id) {
        if (creditService.Delete(Id)) {
            return Response.noContent().build();
        }
        return Response.ok().status(Status.CONFLICT).build();
    }
}
