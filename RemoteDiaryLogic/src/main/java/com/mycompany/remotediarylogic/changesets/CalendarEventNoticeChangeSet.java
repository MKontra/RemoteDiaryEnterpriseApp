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
public class CalendarEventNoticeChangeSet extends BaseAbstractChangeSet<CalendarEventNotice>
{

    public CalendarEventNoticeChangeSet()
    {
    }
    
    @Override
    public CalendarEventNotice getNewEntityFor(EntityManager em)
    {
        CalendarEventNotice newEnt = new CalendarEventNotice();
       
        newEnt.setId( (Long) properties.get("Id"));
        newEnt.setName( (String) properties.get("Name"));
        newEnt.setContent( (String) properties.get("Content"));
        newEnt.setOwningEvent( (CalendarEvent) properties.get("owningEvent"));
 
        return newEnt;
    }

    @Override
    public void projectChangesOnto(CalendarEventNotice entity, EntityManager em)
    {   
        if ( properties.containsKey("Id") )
            entity.setId((Long)properties.get("Id"));
        if ( properties.containsKey("Name") )
            entity.setName((String)properties.get("Name"));
        if ( properties.containsKey("Content") )
            entity.setContent((String)properties.get("Content"));
        if ( properties.containsKey("owningEvent") )
            entity.setOwningEvent((CalendarEvent)properties.get("owningEvent"));
    }
    
}
