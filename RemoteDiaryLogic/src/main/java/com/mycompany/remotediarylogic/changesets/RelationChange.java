/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.remotediarylogic.changesets;

/**
 *
 * @author Administrator
 */
public class RelationChange<T>
{
    
    public static enum Operation { ADD, REMOVE };
    
    private Operation op;
    private T toRef;

    public RelationChange()
    {
    }

    public RelationChange(Operation op, T toRef)
    {
        this.op = op;
        this.toRef = toRef;
    }

    public void setOp(Operation op)
    {
        this.op = op;
    }


    public void setToRef(T toRef)
    {
        this.toRef = toRef;
    }

    public Operation getOp()
    {
        return op;
    }
    
    public Object getToRef()
    {
        return toRef;
    }
    
    
    
}
