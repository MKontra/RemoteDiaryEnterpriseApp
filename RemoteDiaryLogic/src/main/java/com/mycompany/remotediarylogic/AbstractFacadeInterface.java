/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.remotediarylogic;

import com.mycompany.remotediarymodel.DiaryGroup;
import java.util.List;

/**
 *
 * @author Administrator
 */
public interface AbstractFacadeInterface<T>
{
    void create(T obj);

    void edit(T obj);

    void remove(T obj);

    T find(Object id);
    
    List<T> byEELQuery(String query);

    List<T> findAll();

    List<T> findRange(int from, int to);

    int count();
}
