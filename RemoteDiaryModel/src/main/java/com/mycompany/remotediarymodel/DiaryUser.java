/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.remotediarymodel;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.*;

/**
 *
 * @author Administrator
 */
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class DiaryUser
{

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @XmlElement
    private Long Id;

    public Long getId()
    {
        return Id;
    }

    public void setId(Long Id)
    {
        this.Id = Id;
    }
    @XmlElement
    @NotNull
    @Size(min=4, max=20)
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
    @XmlElement
    private byte[] PasswordSHash;

    /**
     * Get the value of PasswordSHash
     *
     * @return the value of PasswordSHash
     */
    public byte[] getPasswordSHash()
    {
        return PasswordSHash;
    }

    /**
     * Set the value of PasswordSHash
     *
     * @param PasswordSHash new value of PasswordSHash
     */
    public void setPasswordSHash(byte[] PasswordSHash)
    {
        this.PasswordSHash = PasswordSHash;
    }
    
    @ManyToMany(mappedBy = "Members")
    @XmlTransient
    private List<DiaryGroup> Groups;

    public void addToGroup(DiaryGroup dg)
    {
        if (!Groups.contains(dg))
        {
            dg.addUserNonChecking(this);
            Groups.add(dg);
        }
    }

    public void removeFromGroup(DiaryGroup dg)
    {
        dg.removeUserNonChecking(this);
        Groups.remove(dg);
    }

    public List<DiaryGroup> getGroups()
    {
        return Groups;
    }

    public void setGroups(List<DiaryGroup> groups)
    {
        this.Groups = groups;
    }

    public DiaryUser()
    {
        this.Groups = new ArrayList<DiaryGroup>();
    }

    
    
    
    @Override
    public String toString()
    {
        return "DiaryUser{" + "Id=" + Id + ", Name=" + Name + ", PasswordSHash=" + PasswordSHash + '}';
    }
    
    
}
