package com.mycompany.remotediarylogic.DTO;

import java.util.ArrayList;
import java.util.Collection;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mycompany.remotediarymodel.*;

@XmlRootElement
public class CalendarEventDto
{
//field definition
//@XmlElement
    private Long Id ;

//accessors
    public Long getId()
    {
        return Id; 
    }

    public void setId(Long Id)
    {
        this.Id = Id;
    }    

//field definition
//@XmlElement
    private String Name ;

//accessors
    public String getName()
    {
        return Name; 
    }

    public void setName(String Name)
    {
        this.Name = Name;
    }    

//field definition
//@XmlElement
    private String Description ;

//accessors
    public String getDescription()
    {
        return Description; 
    }

    public void setDescription(String Description)
    {
        this.Description = Description;
    }    

//field definition
//@XmlElement
    private Calendar owningCalendar ;

//accessors
    public Calendar getOwningCalendar()
    {
        return owningCalendar; 
    }

    public void setOwningCalendar(Calendar owningCalendar)
    {
        this.owningCalendar = owningCalendar;
    }    

//field definition
//@XmlElement
    private Collection<Long> Alerts ;

//accessors
    public Collection<Long> getAlerts()
    {
        return Alerts; 
    }

    public void setAlerts(Collection<Long> Alerts)
    {
        this.Alerts = Alerts;
    }    

//field definition
//@XmlElement
    private Collection<Long> Notices ;

//accessors
    public Collection<Long> getNotices()
    {
        return Notices; 
    }

    public void setNotices(Collection<Long> Notices)
    {
        this.Notices = Notices;
    }    


    public CalendarEventDto(CalendarEvent _CalendarEvent)
    {
    this.Id = _CalendarEvent.getId();

    this.Name = _CalendarEvent.getName();

    this.Description = _CalendarEvent.getDescription();

    this.owningCalendar = _CalendarEvent.getOwningCalendar();

    for (IIdAble i : _CalendarEvent.getAlerts())
    {
        this.Alerts.add( (Long) i.getId());
    }

    for (IIdAble i : _CalendarEvent.getNotices())
    {
        this.Notices.add( (Long) i.getId());
    }

    }

}