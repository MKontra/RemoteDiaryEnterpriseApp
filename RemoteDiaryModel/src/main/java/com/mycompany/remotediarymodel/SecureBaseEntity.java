/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.remotediarymodel;

import java.security.Principal;
import javax.persistence.Entity;
import javax.security.auth.Subject;

/**
 *
 * @author Administrator
 */
@Entity
public abstract class SecureBaseEntity
{

    private Long id;

    private Subject owner;
    
    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public Long getId()
    {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(Long id)
    {
        this.id = id;
    }

}
