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
public class CalendarEventChangeSet extends BaseAbstractChangeSet<CalendarEvent>
{
    protected Collection<RelationChange<Long>> Alerts = new ArrayList<>();

    //accessors
    public Collection<RelationChange<Long>> getAlerts()
    {
        return Alerts; 
    }
    public void setAlerts(Collection<RelationChange<Long>> Alerts)
    {
        this.Alerts = Alerts;
    }   
    protected Collection<RelationChange<Long>> Notices = new ArrayList<>();

    //accessors
    public Collection<RelationChange<Long>> getNotices()
    {
        return Notices; 
    }
    public void setNotices(Collection<RelationChange<Long>> Notices)
    {
        this.Notices = Notices;
    }   

    public CalendarEventChangeSet()
    {
    }
    
    @Override
    public CalendarEvent getNewEntityFor(EntityManager em)
    {
        CalendarEvent newEnt = new CalendarEvent();
       
        newEnt.setId( (Long) properties.get("Id"));
        newEnt.setName( (String) properties.get("Name"));
        newEnt.setDescription( (String) properties.get("Description"));
        newEnt.setOwningCalendar( (Calendar) properties.get("owningCalendar"));
        for ( RelationChange rc : Alerts )
        {
            if ( rc.getOp() == RelationChange.Operation.REMOVE ) continue;
                CalendarEventAlert find = em.find(CalendarEventAlert.class, rc.getToRef());
                newEnt.addToAlerts( find );
        }

        for ( RelationChange rc : Notices )
        {
            if ( rc.getOp() == RelationChange.Operation.REMOVE ) continue;
                CalendarEventNotice find = em.find(CalendarEventNotice.class, rc.getToRef());
                newEnt.addToNotices( find );
        }

 
        return newEnt;
    }

    @Override
    public void projectChangesOnto(CalendarEvent entity, EntityManager em)
    {   
        if ( properties.containsKey("Id") )
            entity.setId((Long)properties.get("Id"));
        if ( properties.containsKey("Name") )
            entity.setName((String)properties.get("Name"));
        if ( properties.containsKey("Description") )
            entity.setDescription((String)properties.get("Description"));
        if ( properties.containsKey("owningCalendar") )
            entity.setOwningCalendar((Calendar)properties.get("owningCalendar"));
        for ( RelationChange rc : Alerts )
        {
            if ( rc.getOp() == RelationChange.Operation.ADD )
            {
                CalendarEventAlert find = em.find(CalendarEventAlert.class, rc.getToRef());
                entity.addToAlerts( find );
            } else if ( rc.getOp() == RelationChange.Operation.REMOVE )
            {
                CalendarEventAlert find = em.find(CalendarEventAlert.class, rc.getToRef());
                entity.removeFromAlerts( find );
            }   
        }

        for ( RelationChange rc : Notices )
        {
            if ( rc.getOp() == RelationChange.Operation.ADD )
            {
                CalendarEventNotice find = em.find(CalendarEventNotice.class, rc.getToRef());
                entity.addToNotices( find );
            } else if ( rc.getOp() == RelationChange.Operation.REMOVE )
            {
                CalendarEventNotice find = em.find(CalendarEventNotice.class, rc.getToRef());
                entity.removeFromNotices( find );
            }   
        }

    }
    
}
