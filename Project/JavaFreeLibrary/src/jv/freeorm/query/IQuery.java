/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jv.freeorm.query;

import jv.freeorm.base.DbParameter;

/**
 *
 * @author Krkt
 */
public interface IQuery {

    String getText();

    void setText(String text);

    DbParameter[] getParameters();

    void setParameters(DbParameter[] parameters);

}
