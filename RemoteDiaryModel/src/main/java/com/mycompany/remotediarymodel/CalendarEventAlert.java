/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.remotediarymodel;

import javax.persistence.*;

/**
 *
 * @author Administrator
 */
@Entity
public class CalendarEventAlert{
    @Id
    @GeneratedValue(strategy= GenerationType.TABLE)
    private Long Id;

    public Long getId()
    {
        return Id;
    }

    public void setId(Long Id)
    {
        this.Id = Id;
    }   
    
    @ManyToOne
    private CalendarEvent owningEvent;

    public CalendarEvent getOwningEvent()
    {
        return owningEvent;
    }

    public void setOwningEvent(CalendarEvent owningEvent)
    {
        this.owningEvent = owningEvent;
    }
    
    
    
}
