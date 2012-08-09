/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.remotediarylogic;

import com.mycompany.eellibrary.EELtransformer;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.*;
import javax.validation.bootstrap.GenericBootstrap;
import javax.validation.spi.ValidationProvider;
import org.antlr.runtime.RecognitionException;



/**
 *
 * @author Administrator
 */
/**
class ValidatedEntity<T>
{
    private T object;

    public T getObject()
    {
        return object;
    }

    private static Validator v = Validation.buildDefaultValidatorFactory().getValidator();
    
    public ValidatedEntity(T object)
    {
        Set violations = v.validate(object);
        if ( !violations.isEmpty() )
            throw new javax.validation.ConstraintViolationException(violations);
        this.object = object;
    }
    
    public void setProperty(String name, Object val)
    {
        v.validateValue(object.getClass(), name, val);
        object.getClass().getField(name).set(object, val);
    }
    
}
**/
public abstract class AbstractFacade<T> implements AbstractFacadeInterface<T>
{
    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass)
    {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity)
    {
        getEntityManager().persist(entity);
    }

    public void edit(T entity)
    {
        getEntityManager().merge(entity);
    }

    public void remove(T entity)
    {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id)
    {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> byEELQuery(String query)
    {
        javax.persistence.criteria.CriteriaQuery cq;
        try
        {
            cq = EELtransformer.<T>eelToCriteriaQuery(query, entityClass, getEntityManager().getCriteriaBuilder());
        } catch (RecognitionException ex)
        {
            Logger.getLogger(AbstractFacade.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Failed to parse queery string",ex);
        }

        TypedQuery tq = getEntityManager().createQuery(cq);
        Logger.getLogger(AbstractFacade.class.getName()).log(Level.SEVERE, tq.unwrap(org.hibernate.Query.class).getQueryString());
        return tq.getResultList();
    }

    public List<T> findAll()
    {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int from, int to)
    {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(to - from);
        q.setFirstResult(from);
        return q.getResultList();
    }

    public int count()
    {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
}
