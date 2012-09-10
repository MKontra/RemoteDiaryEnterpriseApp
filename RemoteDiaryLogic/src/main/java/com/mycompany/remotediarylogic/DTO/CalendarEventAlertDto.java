package com.mycompany.remotediarylogic.DTO;

import java.util.ArrayList;
import java.util.Collection;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mycompany.remotediarymodel.*;

@XmlRootElement
public class CalendarEventAlertDto
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


    public CalendarEventAlertDto(CalendarEventAlert _CalendarEventAlert)
    {
    this.Id = _CalendarEventAlert.getId();

    this.owningEvent = _CalendarEventAlert.getOwningEvent();

    }

}