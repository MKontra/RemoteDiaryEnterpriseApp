package com.mycompany.remotediarylogic.DTO;

import java.util.ArrayList;
import java.util.Collection;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mycompany.remotediarymodel.*;

@XmlRootElement
public class DiaryGroupDto
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
    private Collection<Long> Members ;

//accessors
    public Collection<Long> getMembers()
    {
        return Members; 
    }

    public void setMembers(Collection<Long> Members)
    {
        this.Members = Members;
    }    


    public DiaryGroupDto(DiaryGroup _DiaryGroup)
    {
    this.Id = _DiaryGroup.getId();

    this.Name = _DiaryGroup.getName();

    for (IIdAble i : _DiaryGroup.getMembers())
    {
        this.Members.add( (Long) i.getId());
    }

    }

}