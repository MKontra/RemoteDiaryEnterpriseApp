/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.remotediarylogic.EJB;

import com.mycompany.remotediarylogic.DTO.DiaryUserDto;
import com.mycompany.remotediarymodel.DiaryUser;
import javax.ejb.Local;

/**
 *
 * @author Administrator
 */
@Local
public interface DiaryUserBeanLocal extends AbstractEJBEntityFacadeInterface<DiaryUser, DiaryUserDto>
{
    
}
