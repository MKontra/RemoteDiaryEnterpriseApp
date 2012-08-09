/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.remotediary.restapi;

import com.mycompany.remotediarylogic.AbstractFacade;
import com.mycompany.remotediarylogic.AbstractFacadeInterface;
import com.sun.jersey.spi.container.ContainerRequest;
import java.lang.reflect.Type;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 *
 * @author Administrator
 */
public abstract class AbstractResource<T>
{

    @Context
    private UriInfo context;
    @Context
    private HttpHeaders headers;
    @Context
    private Request req;

    public abstract AbstractFacadeInterface getFacade();

    public abstract Type getResType();

    @POST
    @Consumes(
    {
        "application/xml", "application/json"
    })
    public void create(T entity)
    {
        Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Creating request {0}",entity.toString());
        getFacade().create(entity); 
    }

    @PUT
    @Consumes(
    {
        "application/xml", "application/json"
    })
    public void edit(T entity)
    {
        getFacade().edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(T entity)
    {
        getFacade().remove(entity);
    }

    @GET
    @Path("{id}")
    @Produces(
    {
        "application/xml", "application/json"
    })
    public Response find(@PathParam("id") long id)
    {
        Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Processing request {0}", context.getRequestUri().toString());
        return Response.ok(new GenericEntity(getFacade().find(id), getResType())).build();
    }

    @GET
    @Produces(
    {
        "application/xml", "application/json"
    })
    public List<T> findAll()
    {

        String where = context.getQueryParameters().getFirst("where");
        if (where != null && !where.isEmpty())
        {
            return getFacade().byEELQuery(where);
        }
        return getFacade().findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces(
    {
        "application/xml", "application/json"
    })
    public List<T> findRange(@PathParam("from") long from,
                              @PathParam("to") long to )
    {
        return getFacade().findRange((int)from, (int)to);
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public Response count()
    {
        return Response.ok("" + getFacade().count()).build();
    }
}
