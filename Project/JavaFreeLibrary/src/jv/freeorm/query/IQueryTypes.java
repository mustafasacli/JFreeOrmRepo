package jv.freeorm.query;

/**
 *
 * @author Mustafa SACLI
 */
public interface IQueryTypes {

    public static final Integer QUERY_TYPE_SELECT = 1;
    public static final Integer QUERY_TYPE_INSERT = 2;
    public static final Integer QUERY_TYPE_UPDATE = 3;
    public static final Integer QUERY_TYPE_DELETE = 4;
    public static final Integer QUERY_TYPE_INSERT_AND_GETID = 5;
    public static final Integer QUERY_TYPE_SELECT_WHERE = 6;
    public static final Integer QUERY_TYPE_INSERT_ANY_CHANGES = 7;
    public static final Integer QUERY_TYPE_SELECT_CHANGES = 8;
}
