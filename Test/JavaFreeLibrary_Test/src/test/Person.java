package test;

/**
 *
 * @author Krkt
 */
import jv.freeorm.entity.BaseBO;

public class Person extends BaseBO {

    private int _person_id;
    private String _first_name = "";
    private String _last_name = "";
    private double _salary = 0.0d;

    @Override
    public String getTable() {
        return "person";
    }

    /**
     *
     * @return Id Column Name Of Business Object
     */
    @Override
    public String getIdColumn() {
        return "person_id";
    }

    /**
     * @return the _person_id
     */
    public int getPersonId() {
        return _person_id;
    }

    /**
     * @param personId the _person_id to set
     */
    public void setPerson_id(int personId) {
        this._person_id = personId;
        addChangeList("person_id");
    }

    /**
     * @return the _FirstName
     */
    public String getFirstName() {
        return _first_name;
    }

    /**
     * @param firstName the _FirstName to set
     */
    public void setFirstName(String firstName) {
        this._first_name = firstName;
        addChangeList("first_name");
    }

    /**
     * @return the _last_name
     */
    public String getLastName() {
        return _last_name;
    }

    /**
     * @param lastName the _last_name to set
     */
    public void setLastName(String lastName) {
        this._last_name = lastName;
        addChangeList("last_name");
    }

    /**
     * @return the _salary
     */
    public double getSalary() {
        return _salary;
    }

    /**
     * @param salary the _salary to set
     */
    public void setSalary(double salary) {
        this._salary = salary;
        addChangeList("salary");
    }
}
