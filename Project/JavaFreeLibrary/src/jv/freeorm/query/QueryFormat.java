package jv.freeorm.query;

/**
 *
 * @author Mustafa SACLI
 */
public class QueryFormat {

    private String format_ = "";

    public QueryFormat(QueryTypes query_type) {
        format_ = get_q_format(query_type);
    }

    public String getFormat() {
        return format_;
    }

    private String get_q_format(QueryTypes query_type) {
        String result = "";

        if (query_type == QueryTypes.Insert || query_type == QueryTypes.InsertAndGetId
                || query_type == QueryTypes.InsertAnyChange) {
            result = "INSERT INTO #table#(#columns#) VALUES(#vals#)";
            return result;
        }

        if (query_type == QueryTypes.Update) {
            result = "UPDATE #table# SET #columns# WHERE #vals#";
            return result;
        }

        if (query_type == QueryTypes.Delete) {
            result = "DELETE FROM #table# WHERE #vals#";
            return result;
        }

        if (query_type == QueryTypes.Select) {
            result = "SELECT * FROM #table#;";
            return result;
        }

        if (query_type == QueryTypes.SelectWhere) {
            result = "SELECT * FROM #table# WHERE #vals#;";
            return result;
        }

        if (query_type == QueryTypes.SelectChanges) {
            result = "SELECT #columns# FROM #table#;";
        }

        return result;
    }
}
