/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.remotediary.restapi;

import com.mycompany.remotediarylogic.DTO.AbstractDto;
import com.mycompany.remotediarylogic.changesets.BaseAbstractChangeSet;
import com.mycompany.remotediarylogic.EJB.AbstractEJBEntityFacadeInterface;
import com.mycompany.remotediarymodel.DiaryUser;
import java.lang.reflect.Type;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 *
 * @author Administrator
 */
public abstract class AbstractResource<T, TDto extends AbstractDto<T>, TChangeSet extends BaseAbstractChangeSet<T>>
{

    @Context
    private UriInfo context;
    @Context
    private HttpHeaders headers;
    @Context
    private Request req;

    public abstract AbstractEJBEntityFacadeInterface getFacade();

    public abstract Type getResType();

    @POST
    @Consumes(
    {
        "application/xml", "application/json"
    })
    public void create(TChangeSet entity)
    {
        Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Creating request {0}",entity.toString());
        getFacade().create(entity); 
    }

    @PUT
    @Path("{id}")
    @Consumes(
    {
        "application/xml", "application/json"
    })
    public void edit(@PathParam("id") long id, TChangeSet entity)
    {
        getFacade().edit(id, entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(long id)
    {
        getFacade().remove(id);
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
        return Response.ok( (TDto)getFacade().find(id)).build();
    }

    @GET
    @Produces(
    {
        "application/xml", "application/json"
    })
    public List<TDto> findAll()
    {

        String where = context.getQueryParameters().getFirst("where");
        if (where != null && !where.isEmpty())
        {
            return getFacade().findByEELQuery(where);
        }
        return getFacade().findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces(
    {
        "application/xml", "application/json"
    })
    public List<TDto> findRange(@PathParam("from") long from,
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
