package jv.freeorm.conf;

/**
 *
 * @author Krkt
 */
public class DbConfiguration {

    private String _conn_type_name = "";
    private String _driver = "";
    private String _url = "";
    private String _user;
    private String _password;

    private boolean has_error = false;
    private String msg_error = "";


    /**
     * @return the _conn_type_name
     */
    public String getConnTypeName() {
        return _conn_type_name;
    }

    /**
     * @param conn_type_name the _conn_type_name to set
     */
    public void setConnTypeName(String conn_type_name) {
        this._conn_type_name = conn_type_name;
    }

    /**
     * @return the _driver
     */
    public String getDriver() {
        return _driver;
    }

    /**
     * @param driver the driver to set
     */
    public void setDriver(String driver) {
        this._driver = driver;
    }

    /**
     * @return the _url
     */
    public String getUrl() {
        return _url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this._url = url;
    }

    /**
     * @return the _user
     */
    public String getUser() {
        return _user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this._user = user;
    }

    /**
     * @return the _password
     */
    public String getPassword() {
        return _password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this._password = password;
    }
    
    /**
     *
     * @param error_msg
     */
    public void setError(String error_msg) {
        has_error = true;
        msg_error = error_msg;
    }

    /**
     *
     * @return Returns configuration settings has error.
     */
    public boolean hasError() {
        return has_error;
    }

    /**
     *
     * @return Returns Configuration Error Message.
     */
    public String getErrorMessage() {
        return msg_error;
    }
}
