/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.remotediarymodel;

import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.*;

/**
 *
 * @author Administrator
 */
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class DiaryGroup implements IIdAble
{
    @Id
    @XmlID
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
    
    @ManyToMany
    @JoinTable
    private List<DiaryUser> Members = new java.util.ArrayList<>();

    /**
     * Get the value of Members
     *
     * @return the value of Members
     */
    public List<DiaryUser> getMembers() {
        return Members;
    }

    /**
     * Set the value of Members
     *
     * @param Members new value of Members
     */
    public void setMembers(List<DiaryUser> Members) {
        this.Members = Members;
    }
    
    public void addToMembers(DiaryUser du)
    {
        if (!Members.contains(du))
        {
            du.addGroupNonChecking(this);
            Members.add(du);
        }
    }

    public void removeFromMembers(DiaryUser du)
    {
        du.removeGroupNonChecking(this);
        Members.remove(du);
    } 

    void removeUserNonChecking(DiaryUser aThis)
    {
        Members.remove(aThis);
    }

    void addUserNonChecking(DiaryUser aThis)
    {
        Members.add(aThis);
   
    }   
    
    public DiaryGroup() {
    }
}
