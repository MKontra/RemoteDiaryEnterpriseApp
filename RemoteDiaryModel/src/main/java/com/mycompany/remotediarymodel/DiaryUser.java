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
public class DiaryUser implements IIdAble
{

    @Id
    @GeneratedValue
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
    private List<DiaryGroup> Groups = new java.util.ArrayList<>();

    public void addToGroups(DiaryGroup dg)
    {
        if (!Groups.contains(dg))
        {
            dg.addUserNonChecking(this);
            Groups.add(dg);
        }
    }

    public void removeFromGroups(DiaryGroup dg)
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

    @Override
    public String toString()
    {
        return "DiaryUser{" + "Id=" + Id + ", Name=" + Name + ", PasswordSHash=" + PasswordSHash + '}';
    }

    void addGroupNonChecking(DiaryGroup dg)
    {
            Groups.add(dg);
    }

    void removeGroupNonChecking(DiaryGroup dg)
    {
            Groups.remove(dg);
    }
    
    public DiaryUser()
    {
    }
}
