package com.mycompany.remotediarylogic.DTO;

import java.util.ArrayList;
import java.util.Collection;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mycompany.remotediarymodel.*;

@XmlRootElement
public class CalendarDto
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
    private Long Owner ;

//accessors
    public Long getOwner()
    {
        return Owner; 
    }

    public void setOwner(Long Owner)
    {
        this.Owner = Owner;
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
    private Collection<Long> Events ;

//accessors
    public Collection<Long> getEvents()
    {
        return Events; 
    }

    public void setEvents(Collection<Long> Events)
    {
        this.Events = Events;
    }    


    public CalendarDto(Calendar _Calendar)
    {
    this.Id = _Calendar.getId();

    this.Owner = _Calendar.getOwner().getId();

    this.Name = _Calendar.getName();

    for (IIdAble i : _Calendar.getEvents())
    {
        this.Events.add( (Long) i.getId());
    }

    }

}