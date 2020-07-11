/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jv.freeorm.query;

/**
 *
 * @author Mustaf SACLI
 */
public enum QueryTypes {

    Select(IQueryTypes.QUERY_TYPE_SELECT),
    Insert(IQueryTypes.QUERY_TYPE_INSERT),
    Update(IQueryTypes.QUERY_TYPE_UPDATE),
    Delete(IQueryTypes.QUERY_TYPE_DELETE),
    InsertAndGetId(IQueryTypes.QUERY_TYPE_INSERT_AND_GETID),
    SelectWhere(IQueryTypes.QUERY_TYPE_SELECT_WHERE),
    InsertAnyChange(IQueryTypes.QUERY_TYPE_INSERT_ANY_CHANGES),
    SelectChanges(IQueryTypes.QUERY_TYPE_SELECT_CHANGES);

    private int _query_type = 0;

    private QueryTypes(int query_type) {
        _query_type = query_type;
    }

    public int getType() {
        return _query_type;
    }

}
