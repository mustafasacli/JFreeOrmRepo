package jv.freeorm.entity;

import java.util.ArrayList;

/**
 *
 * @author Mustafa SACLI
 */
public interface IBaseBO {

    String getTable();

    String getIdColumn();

    /*
     int Insert();

     int InsertAndGetId();

     int Delete();

     int Update();
     */
    ArrayList<String> getChangeList();

    void addChangeList(String column);
}
