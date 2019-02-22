package org.idempiere.common.util;

/**
 * (String) Value Name Pair
 *
 * @author Jorg Janke
 * @version $Id: ValueNamePair.java,v 1.2 2006/07/30 00:52:23 jjanke Exp $
 */
public final class ValueNamePair extends NamePair {
    /**
     * The Value
     */
    private String m_value = null;

    /**
     * Construct KeyValue Pair
     *
     * @param value value
     * @param name  string representation
     */
    public ValueNamePair(String value, String name) {
        super(name);
        m_value = value;
        if (m_value == null) m_value = "";
    } //  ValueNamePair

    /**
     * Get Value
     *
     * @return Value
     */
    public String getValue() {
        return m_value;
    } //	getValue

    /**
     * Equals
     *
     * @param obj Object
     * @return true, if equal
     */
    public boolean equals(Object obj) {
        if (obj instanceof ValueNamePair) {
            ValueNamePair pp = (ValueNamePair) obj;
            return pp.getName() != null
                    && pp.getValue() != null
                    && pp.getName().equals(getName())
                    && pp.getValue().equals(m_value);
        }
        return false;
    } //	equals

    /**
     * Return Hashcode of value
     *
     * @return hascode
     */
    public int hashCode() {
        return m_value.hashCode();
    } //  hashCode
} //	KeyValuePair
