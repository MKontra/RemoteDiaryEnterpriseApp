/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.remotediarymodel;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Null;

/**
 *
 * @author Administrator
 */
@Entity
public class CalendarEvent implements IIdAble
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

    
    private String eventType;

    /**
     * Get the value of eventType
     *
     * @return the value of eventType
     */
    public String getEventType()
    {
        return eventType;
    }

    /**
     * Set the value of eventType
     *
     * @param eventType new value of eventType
     */
    public void setEventType(String eventType)
    {
        this.eventType = eventType;
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
    private Calendar owningCalendar;

    public Calendar getOwningCalendar()
    {
        return owningCalendar;
    }

    public void setOwningCalendar(Calendar owningCalendar)
    {
        this.owningCalendar = owningCalendar;
    }
    
   
    
    
    @OneToMany(cascade= CascadeType.ALL,mappedBy="owningEvent")
    private List<CalendarEventAlert> Alerts = new ArrayList<>();

    
    public void addToAlerts(CalendarEventAlert cea)
    {
        if ( !Alerts.contains(cea) )
        {
            cea.setOwningEvent(this);
            Alerts.add(cea);
        }
    }
    
    public void removeFromAlerts(CalendarEventAlert cea)
    {
        cea.setOwningEvent(null);
        Alerts.remove(cea);
    }
    
    /**
     * Get the value of Alerts
     *
     * @return the value of Alerts
     */
    public List<CalendarEventAlert> getAlerts() {
        return Alerts;
    }

    /**
     * Set the value of Alerts
     *
     * @param Alerts new value of Alerts
     */
    public void setAlerts(List<CalendarEventAlert> Alerts) {
        this.Alerts = Alerts;
    }
    
    @OneToMany(mappedBy="owningEvent")
    private List<CalendarEventNotice> Notices = new ArrayList<>();

    /**
     * Get the value of Notices
     *
     * @return the value of Notices
     */
    public List<CalendarEventNotice> getNotices() {
        return Notices;
    }

    /**
     * Set the value of Notices
     *
     * @param Notices new value of Notices
     */
    public void setNotices(List<CalendarEventNotice> Notices) {
        this.Notices = Notices;
    }
    
    public void addToNotices(CalendarEventNotice cea)
    {
        if ( !Notices.contains(cea) )
        {
            cea.setOwningEvent(this);
            Notices.add(cea);
        }
    }
    
    public void removeFromNotices(CalendarEventNotice cea)
    {
        cea.setOwningEvent(null);
        Notices.remove(cea);
    }

    public CalendarEvent() {
    }

}
