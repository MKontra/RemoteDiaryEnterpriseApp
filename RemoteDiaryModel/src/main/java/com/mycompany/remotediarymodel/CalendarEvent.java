/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.remotediarymodel;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Administrator
 */
@Entity
public class CalendarEvent{
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
    private List<CalendarEventAlert> Alerts;

    
    public void addAlert(CalendarEventAlert cea)
    {
        if ( !Alerts.contains(cea) )
        {
            cea.setOwningEvent(this);
            Alerts.add(cea);
        }
    }
    
    public void removeAlert(CalendarEventAlert cea)
    {
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
    private List<CalendarEventNotice> Notices;

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

    public CalendarEvent() {
        this.Alerts = new ArrayList<>();
        this.Notices = new ArrayList<>();
    }

}
