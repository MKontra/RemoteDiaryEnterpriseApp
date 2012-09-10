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
public class DiaryGroupChangeSet extends BaseAbstractChangeSet<DiaryGroup>
{
    protected Collection<RelationChange<Long>> Members = new ArrayList<>();

    //accessors
    public Collection<RelationChange<Long>> getMembers()
    {
        return Members; 
    }
    public void setMembers(Collection<RelationChange<Long>> Members)
    {
        this.Members = Members;
    }   

    public DiaryGroupChangeSet()
    {
    }
    
    @Override
    public DiaryGroup getNewEntityFor(EntityManager em)
    {
        DiaryGroup newEnt = new DiaryGroup();
       
        newEnt.setId( (Long) properties.get("Id"));
        newEnt.setName( (String) properties.get("Name"));
        for ( RelationChange rc : Members )
        {
            if ( rc.getOp() == RelationChange.Operation.REMOVE ) continue;
                DiaryUser find = em.find(DiaryUser.class, rc.getToRef());
                newEnt.addToMembers( find );
        }

 
        return newEnt;
    }

    @Override
    public void projectChangesOnto(DiaryGroup entity, EntityManager em)
    {   
        if ( properties.containsKey("Id") )
            entity.setId((Long)properties.get("Id"));
        if ( properties.containsKey("Name") )
            entity.setName((String)properties.get("Name"));
        for ( RelationChange rc : Members )
        {
            if ( rc.getOp() == RelationChange.Operation.ADD )
            {
                DiaryUser find = em.find(DiaryUser.class, rc.getToRef());
                entity.addToMembers( find );
            } else if ( rc.getOp() == RelationChange.Operation.REMOVE )
            {
                DiaryUser find = em.find(DiaryUser.class, rc.getToRef());
                entity.removeFromMembers( find );
            }   
        }

    }
    
}
