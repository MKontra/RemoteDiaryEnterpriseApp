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
public class CalendarEventAlertChangeSet extends BaseAbstractChangeSet<CalendarEventAlert>
{

    public CalendarEventAlertChangeSet()
    {
    }
    
    @Override
    public CalendarEventAlert getNewEntityFor(EntityManager em)
    {
        CalendarEventAlert newEnt = new CalendarEventAlert();
       
        newEnt.setId( (Long) properties.get("Id"));
        newEnt.setOwningEvent( (CalendarEvent) properties.get("owningEvent"));
 
        return newEnt;
    }

    @Override
    public void projectChangesOnto(CalendarEventAlert entity, EntityManager em)
    {   
        if ( properties.containsKey("Id") )
            entity.setId((Long)properties.get("Id"));
        if ( properties.containsKey("owningEvent") )
            entity.setOwningEvent((CalendarEvent)properties.get("owningEvent"));
    }
    
}
