package jv.freeorm.query;

import jv.freeorm.base.DbParameter;

/**
 *
 * @author Mustafa SACLI
 */
public class Query implements IQuery {

    private String text_ = "";
    private DbParameter[] prms_ = null;

    public Query() {
        text_ = "";
        prms_ = new DbParameter[]{};
    }

    @Override
    public String getText() {
        return text_;
    }

    @Override
    public void setText(String text) {
        text_ = text;
    }

    @Override
    public DbParameter[] getParameters() {
        return prms_;
    }

    @Override
    public void setParameters(DbParameter[] parameters) {
        prms_ = parameters;
    }

}
