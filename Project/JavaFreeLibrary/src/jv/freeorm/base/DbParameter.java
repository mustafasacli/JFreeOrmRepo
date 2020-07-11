package jv.freeorm.base;

/**
 *
 * @author Mustafa SACLI
 */
public class DbParameter implements IDbParameter {

    private final static int def_sql_type = -10;

    private Integer _Index = 1;
    private Object value_ = null;
    private boolean _is_input = true;
    private int _sqlType = -10;

    public DbParameter(Object value) {
        this(value, true);
    }

    /**
     * @param value Value Of Parameter
     * @param is_input determines parameter type is input or output.
     */
    public DbParameter(Object value, boolean is_input) {
        value_ = value;
        _is_input = is_input;
    }

    public DbParameter(int index, Object value) {
        _Index = index;
        value_ = value;
    }

    public DbParameter(int index, Object value, boolean is_input) {
        _Index = index;
        value_ = value;
        _is_input = is_input;
    }

    /**
     * @return Returns Index Of DbParameter.
     *
     *
     */
    @Override
    public Integer getIndex() {
        return _Index;
    }

    /**
     * @param index Index Of DbParameter.
     *
     *
     */
    @Override
    public void setIndex(Integer index) {
        _Index = index > 0 ? index : 1;
    }

    /**
     * @return Returns Value Of DbParameter.
     *
     *
     */
    @Override
    public Object getValue() {
        return value_;
    }

    /**
     * @param value Value Of DbParameter.
     *
     *
     */
    @Override
    public void setValue(Object value) {
        value_ = value;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        Runtime.getRuntime().gc();
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;

        if (obj instanceof DbParameter) {
            DbParameter p = (DbParameter) obj;
            result = this._is_input == p.isInput();
            result &= (this.value_ == p.getValue());
        }

        return result;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this._is_input ? 1 : 0);
        return hash;
    }

    /**
     * @return the _is_input
     */
    @Override
    public boolean isInput() {
        return _is_input;
    }

    /**
     * @param _is_input the _is_input to set
     */
    @Override
    public void setInput(boolean _is_input) {
        this._is_input = _is_input;
    }

    /**
     * @return the _sqlType
     */
    public int getSqlType() {
        return _sqlType;
    }

    /**
     * @param _sqlType the _sqlType to set
     */
    public void setSqlType(int _sqlType) {
        this._sqlType = _sqlType;
    }

    public static int getDefaultType() {
        return def_sql_type;
    }
}
