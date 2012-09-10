package com.mycompany.remotediarylogic.EJB;

import com.mycompany.eellibrary.EELtransformer;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.antlr.runtime.RecognitionException;

import com.mycompany.remotediarylogic.changesets.BaseAbstractChangeSet;
import com.mycompany.remotediarylogic.DTO.AbstractDto;
import com.mycompany.remotediarylogic.DTO.BaseAbstractDtoFactory;

/**
 *
 * @author Administrator
 */
public abstract class AbstractEJBEntityFacadeBase<T,TDto extends AbstractDto<T>> implements AbstractEJBEntityFacadeInterface<T,TDto>
{

    @Override
    public void create(BaseAbstractChangeSet<T> rel)
    {
        T entity = rel.getNewEntityFor(getEntityManager());
        getEntityManager().persist(entity);
    }

    @Override
    public void edit(Object id, BaseAbstractChangeSet<T> rel)
    {
        T toEdit = getEntityManager().find(entityClass, id);
        rel.projectChangesOnto(toEdit, getEntityManager());
    }

    @Override
    public TDto find(Object id)
    {
        T toEdit = getEntityManager().find(entityClass, id);
        return getDtoFactory().getDtoForEntity(toEdit);
    }

    @Override
    public List<TDto> findAll()
    {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getDtoFactory().projectIntoDtos(getEntityManager().createQuery(cq).getResultList());
    }

    @Override
    public List<TDto> findByEELQuery(String query)
    {
        javax.persistence.criteria.CriteriaQuery cq;
        try
        {
            cq = EELtransformer.<T>eelToCriteriaQuery(query, entityClass, getEntityManager().getCriteriaBuilder());
        } catch (RecognitionException ex)
        {
            Logger.getLogger(AbstractEJBEntityFacadeBase.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Failed to parse queery string",ex);
        }

        TypedQuery tq = getEntityManager().createQuery(cq);
        //Logger.getLogger(AbstractFacade.class.getName()).log(Level.SEVERE, tq.unwrap(org.hibernate.Query.class).getQueryString());
        return getDtoFactory().projectIntoDtos(tq.getResultList());
    }

    @Override
    public List<TDto> findRange(int from, int to)
    {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(to - from);
        q.setFirstResult(from);
        return getDtoFactory().projectIntoDtos(q.getResultList());
    }

    @Override
    public void remove(Object id)
    {
        getEntityManager().remove(getEntityManager().find(entityClass, id));
    }

    @Override
    public void removeByEELQuery(String query)
    {
        javax.persistence.criteria.CriteriaQuery cq;
        try
        {
            cq = EELtransformer.<T>eelToCriteriaQuery(query, entityClass, getEntityManager().getCriteriaBuilder());
        } catch (RecognitionException ex)
        {
            Logger.getLogger(AbstractEJBEntityFacadeBase.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Failed to parse queery string",ex);
        }

        TypedQuery tq = getEntityManager().createQuery(cq);
        //Logger.getLogger(AbstractFacade.class.getName()).log(Level.SEVERE, tq.unwrap(org.hibernate.Query.class).getQueryString());
        for ( Object e: tq.getResultList())
        {
            getEntityManager().remove(e);
        }
    }

    @Override
    public int count()
    {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }


    private Class<T> entityClass;

    public AbstractEJBEntityFacadeBase(Class<T> entityClass)
    {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();
    protected abstract BaseAbstractDtoFactory<T, TDto> getDtoFactory();

    
}
