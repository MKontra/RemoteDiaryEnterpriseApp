/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.remotediarymodel;

import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Administrator
 */
@Entity
public class Calendar
{

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long Id;

    public Long getId()
    {
        return Id;
    }

    public void setId(Long Id)
    {
        this.Id = Id;
    }
    
    @OneToOne
    private DiaryUser Owner;

    /**
     * Get the value of Owner
     *
     * @return the value of Owner
     */
    public DiaryUser getOwner()
    {
        return Owner;
    }

    /**
     * Set the value of Owner
     *
     * @param Owner new value of Owner
     */
    public void setOwner(DiaryUser Owner)
    {
        this.Owner = Owner;
    }
    private String Name;

    /**
     * Get the value of Name
     *
     * @return the value of Name
     */
    public String getName()
    {
        return Name;
    }

    /**
     * Set the value of Name
     *
     * @param Name new value of Name
     */
    public void setName(String Name)
    {
        this.Name = Name;
    }
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owningCalendar")
    private List<CalendarEvent> Events;

    /**
     * Get the value of Events
     *
     * @return the value of Events
     */
    public List<CalendarEvent> getEvents()
    {
        return Events;
    }

    public void addEvent(CalendarEvent e)
    {
        if (!Events.contains(e))
        {
            e.setOwningCalendar(this);
            Events.add(e);
        }
    }

    public void removeEvent(CalendarEvent e)
    {
        Events.remove(e);
    }
    
    /**
     * Set the value of Events
     *
     * @param Events new value of Events
     */
    public void setEvents(List<CalendarEvent> Events)
    {
        this.Events = Events;
    }

    public Calendar()
    {
        Events = new java.util.ArrayList<>();
    }
}
