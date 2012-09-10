package com.mycompany.remotediarylogic.DTO;

import java.util.ArrayList;
import java.util.Collection;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mycompany.remotediarymodel.*;

@XmlRootElement
public class CalendarEventNoticeDto
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
    private String Content ;

//accessors
    public String getContent()
    {
        return Content; 
    }

    public void setContent(String Content)
    {
        this.Content = Content;
    }    

//field definition
//@XmlElement
    private CalendarEvent owningEvent ;

//accessors
    public CalendarEvent getOwningEvent()
    {
        return owningEvent; 
    }

    public void setOwningEvent(CalendarEvent owningEvent)
    {
        this.owningEvent = owningEvent;
    }    


    public CalendarEventNoticeDto(CalendarEventNotice _CalendarEventNotice)
    {
    this.Id = _CalendarEventNotice.getId();

    this.Name = _CalendarEventNotice.getName();

    this.Content = _CalendarEventNotice.getContent();

    this.owningEvent = _CalendarEventNotice.getOwningEvent();

    }

}