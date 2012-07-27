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
public class CalendarEventNotice{
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
    private String Name;

    /**
     * Get the value of Name
     *
     * @return the value of Name
     */
    public String getName() {
        return Name;
    }

    /**
     * Set the value of Name
     *
     * @param Name new value of Name
     */
    public void setName(String Name) {
        this.Name = Name;
    }
    private String Content;

    /**
     * Get the value of Content
     *
     * @return the value of Content
     */
    public String getContent() {
        return Content;
    }

    /**
     * Set the value of Content
     *
     * @param Content new value of Content
     */
    public void setContent(String Content) {
        this.Content = Content;
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
