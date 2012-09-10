/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.remotediarylogic.changesets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@XmlRootElement
public abstract class BaseAbstractChangeSet<T>
{
    protected Map<String, Object> properties = new HashMap<>();

    public Map<String, Object> getProperties()
    {
        return properties;
    }

    public void setProperties(Map<String, Object> properties)
    {
        this.properties = properties;
    }

    public abstract T getNewEntityFor(EntityManager em);
    public abstract void projectChangesOnto(T entity, EntityManager em);
    
}
