/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.remotediarylogic;

import com.mycompany.remotediarymodel.DiaryUser;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Administrator
 */
@Stateless
@LocalBean
public class DiaryUserFacade extends AbstractFacade<DiaryUser> implements DiaryUserFacadeLocal
{
    @PersistenceContext(unitName = "remoteDiary")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public DiaryUserFacade()
    {
        super(DiaryUser.class);
    }
    
}
