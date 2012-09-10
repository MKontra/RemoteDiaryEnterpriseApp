/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.remotediarylogic.changesets;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.xml.bind.annotation.XmlRootElement;

import com.mycompany.remotediarymodel.*;

/**
 *
 * @author Administrator
 */
@XmlRootElement
public class CalendarChangeSet extends BaseAbstractChangeSet<Calendar>
{
    protected Collection<RelationChange<Long>> Events = new ArrayList<>();

    //accessors
    public Collection<RelationChange<Long>> getEvents()
    {
        return Events; 
    }
    public void setEvents(Collection<RelationChange<Long>> Events)
    {
        this.Events = Events;
    }   

    public CalendarChangeSet()
    {
    }
    
    @Override
    public Calendar getNewEntityFor(EntityManager em)
    {
        Calendar newEnt = new Calendar();
       
        newEnt.setId( (Long) properties.get("Id"));
        {
            DiaryUser find = em.find(DiaryUser.class, (Long) properties.get("Owner"));
            newEnt.setOwner( find );
        }
        newEnt.setName( (String) properties.get("Name"));
        for ( RelationChange rc : Events )
        {
            if ( rc.getOp() == RelationChange.Operation.REMOVE ) continue;
                CalendarEvent find = em.find(CalendarEvent.class, rc.getToRef());
                newEnt.addToEvents( find );
        }

 
        return newEnt;
    }

    @Override
    public void projectChangesOnto(Calendar entity, EntityManager em)
    {   
        if ( properties.containsKey("Id") )
            entity.setId((Long)properties.get("Id"));
        if ( properties.containsKey("Owner") )
        {
            DiaryUser find = em.find(DiaryUser.class, (Long) properties.get("Owner"));
            entity.setOwner( find );
        }
        if ( properties.containsKey("Name") )
            entity.setName((String)properties.get("Name"));
        for ( RelationChange rc : Events )
        {
            if ( rc.getOp() == RelationChange.Operation.ADD )
            {
                CalendarEvent find = em.find(CalendarEvent.class, rc.getToRef());
                entity.addToEvents( find );
            } else if ( rc.getOp() == RelationChange.Operation.REMOVE )
            {
                CalendarEvent find = em.find(CalendarEvent.class, rc.getToRef());
                entity.removeFromEvents( find );
            }   
        }

    }
    
}
