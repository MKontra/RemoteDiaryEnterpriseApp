package com.mycompany.remotediarylogic.DTO;

import java.util.ArrayList;
import java.util.Collection;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mycompany.remotediarymodel.*;

@XmlRootElement
public class DiaryUserDto extends AbstractDto<DiaryUser>
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
    private String Name ;

//accessors
    public String getName()
    {
        return Name; 
    }

    public void setName(String Name)
    {
        this.Name = Name;
    }    

//field definition
//@XmlElement
    private byte[] PasswordSHash ;

//accessors
    public byte[] getPasswordSHash()
    {
        return PasswordSHash; 
    }

    public void setPasswordSHash(byte[] PasswordSHash)
    {
        this.PasswordSHash = PasswordSHash;
    }    

//field definition
//@XmlElement
    private Collection<Long> Groups = new ArrayList<>() ;

//accessors
    public Collection<Long> getGroups()
    {
        return Groups; 
    }

    public void setGroups(Collection<Long> Groups)
    {
        this.Groups = Groups;
    }

    public DiaryUserDto()
    {
    }


    public DiaryUserDto(DiaryUser _DiaryUser)
    {
    this.Id = _DiaryUser.getId();

    this.Name = _DiaryUser.getName();

    this.PasswordSHash = _DiaryUser.getPasswordSHash();

    for (IIdAble i : _DiaryUser.getGroups())
    {
        this.Groups.add( (Long) i.getId());
    }

    }

}