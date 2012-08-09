/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.remotediary.restapi;

import com.mycompany.remotediarylogic.AbstractFacadeInterface;
import com.mycompany.remotediarylogic.DiaryUserFacadeLocal;
import com.mycompany.remotediarymodel.DiaryUser;
import java.lang.reflect.Type;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.enterprise.context.RequestScoped;

/**
 * REST Web Service
 *
 * @author Administrator
 */
@Path("user")
@RequestScoped
public class UserResource extends AbstractResource<DiaryUser>
{

    @EJB
    private DiaryUserFacadeLocal dufl;

    @Override
    public AbstractFacadeInterface getFacade()
    {
        return dufl;
    }

    @Override
    public Type getResType()
    {
        return DiaryUser.class;
    }

}
