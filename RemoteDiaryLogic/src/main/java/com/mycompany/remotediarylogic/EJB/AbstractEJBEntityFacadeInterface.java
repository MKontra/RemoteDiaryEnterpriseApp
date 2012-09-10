package com.mycompany.remotediarylogic.EJB;

import java.util.List;
import com.mycompany.remotediarylogic.changesets.BaseAbstractChangeSet;
import com.mycompany.remotediarylogic.DTO.AbstractDto;
/**
 *
 * @author Administrator
 */
public interface AbstractEJBEntityFacadeInterface<T,TDto extends AbstractDto<T>>
{
    void create(BaseAbstractChangeSet<T> rel);

    void edit(Object id, BaseAbstractChangeSet<T> rel);

    void remove(Object id);

    void removeByEELQuery(String query);

    TDto find(Object id);
    
    List<TDto> findByEELQuery(String query);

    List<TDto> findAll();

    List<TDto> findRange(int from, int to);

    int count();
}