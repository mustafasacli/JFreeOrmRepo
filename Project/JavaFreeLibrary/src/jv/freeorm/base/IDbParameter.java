/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jv.freeorm.base;

/**
 *
 * @author Mustafa SACLI
 */
public interface IDbParameter {

    Integer getIndex();

    void setIndex(Integer Index);

    Object getValue();

    void setValue(Object value);

    boolean isInput();

    /**
     * @param is_input the _is_input to set
     */
    void setInput(boolean is_input);
}
