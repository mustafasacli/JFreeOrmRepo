/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jv.freeorm.query;

import jv.freeorm.base.DriverType;
import jv.freeorm.base.DbParameter;

/**
 *
 * @author  Mustafa SACLI
 */
public interface IQueryBuilder {
    
    QueryTypes getQueryType();
    
     void setQueryType(QueryTypes queryType);
     
     DriverType getConnectionType();
     
     void setConnectionType(DriverType connectionType);
     
     DbParameter[] getParameters();
     
     String getQuery();
}
