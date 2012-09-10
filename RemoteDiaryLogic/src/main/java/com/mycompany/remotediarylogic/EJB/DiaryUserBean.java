/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.remotediarylogic.EJB;

import com.mycompany.remotediarylogic.DTO.BaseAbstractDtoFactory;
import com.mycompany.remotediarylogic.DTO.DiaryUserDto;
import com.mycompany.remotediarymodel.DiaryUser;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Administrator
 */
@Stateless(name = "DiaryUserBean")
public class DiaryUserBean extends AbstractEJBEntityFacadeBase<DiaryUser, DiaryUserDto> implements DiaryUserBeanLocal
{

    @PersistenceContext(unitName = "remoteDiary")
    EntityManager em;
    BaseAbstractDtoFactory<DiaryUser, DiaryUserDto> dtoFact;

    public DiaryUserBean()
    {
        super(DiaryUser.class);
        dtoFact = new BaseAbstractDtoFactory<DiaryUser, DiaryUserDto>() {

            @Override
            public DiaryUserDto getDtoForEntity(DiaryUser obj)
            {
                return new DiaryUserDto(obj);
            }
        };
    }

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    @Override
    protected BaseAbstractDtoFactory<DiaryUser, DiaryUserDto> getDtoFactory()
    {
        return dtoFact;
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
