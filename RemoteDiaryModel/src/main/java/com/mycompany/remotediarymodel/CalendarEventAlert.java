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
public class CalendarEventAlert implements IIdAble
{
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
    private String Description;

    /**
     * Get the value of Description
     *
     * @return the value of Description
     */
    public String getDescription() {
        return Description;
    }

    /**
     * Set the value of Description
     *
     * @param Description new value of Description
     */
    public void setDescription(String Description) {
        this.Description = Description;
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
    

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Long occurDateTime;

    /**
     * Get the value of when
     *
     * @return the value of when
     */
    public Long getWhen()
    {
        return occurDateTime;
    }

    /**
     * Set the value of when
     *
     * @param when new value of when
     */
    public void setWhen(Long when)
    {
        this.occurDateTime = when;
    } 
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Long repeatInterval;

    /**
     * Get the value of repeatInterval
     *
     * @return the value of repeatInterval
     */
    public Long getRepeatInterval()
    {
        return repeatInterval;
    }

    /**
     * Set the value of repeatInterval
     *
     * @param repeatInterval new value of repeatInterval
     */
    public void setRepeatInterval(Long repeatInterval)
    {
        this.repeatInterval = repeatInterval;
    }

    public CalendarEventAlert()
    {
    }
    
}
