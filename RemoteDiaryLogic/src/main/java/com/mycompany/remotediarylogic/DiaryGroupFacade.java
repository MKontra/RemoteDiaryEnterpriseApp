/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.remotediarylogic;

import com.mycompany.remotediarymodel.DiaryGroup;
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
public class DiaryGroupFacade extends AbstractFacade<DiaryGroup> implements DiaryGroupFacadeLocal
{
    @PersistenceContext(unitName = "remoteDiary")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public DiaryGroupFacade()
    {
        super(DiaryGroup.class);
    }
    
}
