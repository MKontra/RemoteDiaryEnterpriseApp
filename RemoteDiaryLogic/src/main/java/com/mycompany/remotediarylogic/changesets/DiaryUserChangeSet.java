/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.remotediarylogic.changesets;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.xml.bind.annotation.XmlRootElement;

import com.mycompany.remotediarymodel.*;

/**
 *
 * @author Administrator
 */
@XmlRootElement
public class DiaryUserChangeSet extends BaseAbstractChangeSet<DiaryUser>
{
    protected Collection<RelationChange<Long>> Groups = new ArrayList<>();

    //accessors
    public Collection<RelationChange<Long>> getGroups()
    {
        return Groups; 
    }
    public void setGroups(Collection<RelationChange<Long>> Groups)
    {
        this.Groups = Groups;
    }   

    public DiaryUserChangeSet()
    {
    }
    
    @Override
    public DiaryUser getNewEntityFor(EntityManager em)
    {
        DiaryUser newEnt = new DiaryUser();
       
        newEnt.setId( (Long) properties.get("Id"));
        newEnt.setName( (String) properties.get("Name"));
        newEnt.setPasswordSHash( (byte[]) properties.get("PasswordSHash"));
        for ( RelationChange rc : Groups )
        {
            if ( rc.getOp() == RelationChange.Operation.REMOVE ) continue;
                DiaryGroup find = em.find(DiaryGroup.class, rc.getToRef());
                newEnt.addToGroups( find );
        }

 
        return newEnt;
    }

    @Override
    public void projectChangesOnto(DiaryUser entity, EntityManager em)
    {   
        if ( properties.containsKey("Id") )
            entity.setId((Long)properties.get("Id"));
        if ( properties.containsKey("Name") )
            entity.setName((String)properties.get("Name"));
        if ( properties.containsKey("PasswordSHash") )
            entity.setPasswordSHash((byte[])properties.get("PasswordSHash"));
        for ( RelationChange rc : Groups )
        {
            if ( rc.getOp() == RelationChange.Operation.ADD )
            {
                DiaryGroup find = em.find(DiaryGroup.class, rc.getToRef());
                entity.addToGroups( find );
            } else if ( rc.getOp() == RelationChange.Operation.REMOVE )
            {
                DiaryGroup find = em.find(DiaryGroup.class, rc.getToRef());
                entity.removeFromGroups( find );
            }   
        }

    }
    
}
