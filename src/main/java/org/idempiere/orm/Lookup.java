package org.idempiere.orm;

import org.idempiere.common.util.CLogger;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;

/**
 * Base Class for MLookup, MLocator. as well as for MLocation, MAccount (only single value)
 * Maintains selectable data as NamePairs in ArrayList The objects itself may be shared by the
 * lookup implementation (ususally HashMap)
 *
 * @author Jorg Janke
 * @version $Id: Lookup.java,v 1.3 2006/07/30 00:58:18 jjanke Exp $
 */
public abstract class Lookup extends AbstractListModel<Object>
        implements MutableComboBoxModel<Object>, Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -28200392264647953L;
    /**
     * The Data List
     */
    protected volatile ArrayList<Object> p_data = new ArrayList<Object>();
    /**
     * Logger
     */
    protected CLogger log = CLogger.getCLogger(getClass());
    /**
     * The Selected Item
     */
    private volatile Object m_selectedObject;

    /**
     * Temporary Data
     */
    private Object[] m_tempData = null;

    /**
     * Lookup
     *
     * @param displayType display type
     * @param windowNo    window no
     */
    public Lookup(int displayType, int windowNo) {
    } //  Lookup

    /**
     * Return previously selected Item
     *
     * @return value
     */
    public Object getSelectedItem() {
        return m_selectedObject;
    } //  getSelectedItem

    /**
     * ************************************************************************ Set the value of the
     * selected item. The selected item may be null.
     *
     * <p>
     *
     * @param anObject The combo box value or null for no selection.
     */
    public void setSelectedItem(Object anObject) {
        if ((m_selectedObject != null && !m_selectedObject.equals(anObject))
                || m_selectedObject == null && anObject != null) {
            if (p_data.contains(anObject) || anObject == null) {
                m_selectedObject = anObject;
                //	Log.trace(s_ll, "Lookup.setSelectedItem", anObject);
            } else {
                m_selectedObject = null;
                if (log.isLoggable(Level.FINE))
                    log.fine(getColumnName() + ": setSelectedItem - Set to NULL");
            }
            //	if (m_worker == null || !m_worker.isAlive())
            fireContentsChanged(this, -1, -1);
        }
    } //  setSelectedItem

    /**
     * Get Size of Model
     *
     * @return size
     */
    public int getSize() {
        return p_data.size();
    } //  getSize

    /**
     * Get Element at Index
     *
     * @param index index
     * @return value
     */
    public Object getElementAt(int index) {
        return p_data.get(index);
    } //  getElementAt

    /**
     * Add Element at the end
     *
     * @param anObject object
     */
    public void addElement(Object anObject) {
        p_data.add(anObject);
        fireIntervalAdded(this, p_data.size() - 1, p_data.size() - 1);
        if (p_data.size() == 1 && m_selectedObject == null && anObject != null)
            setSelectedItem(anObject);
    } //  addElement

    /**
     * Insert Element At
     *
     * @param anObject object
     * @param index    index
     */
    public void insertElementAt(Object anObject, int index) {
        p_data.add(index, anObject);
        fireIntervalAdded(this, index, index);
    } //  insertElementAt

    /**
     * Remove Item at index
     *
     * @param index index
     */
    public void removeElementAt(int index) {
        if (getElementAt(index) == m_selectedObject) {
            if (index == 0) setSelectedItem(getSize() == 1 ? null : getElementAt(index + 1));
            else setSelectedItem(getElementAt(index - 1));
        }
        p_data.remove(index);
        fireIntervalRemoved(this, index, index);
    } //  removeElementAt

    /**
     * Remove Item
     *
     * @param anObject object
     */
    public void removeElement(Object anObject) {
        int index = p_data.indexOf(anObject);
        if (index != -1) removeElementAt(index);
    } //  removeItem

    /**
     * Fill ComboBox with Data (Value/KeyNamePair)
     *
     * @param mandatory     has mandatory data only (i.e. no "null" selection)
     * @param onlyValidated only validated
     * @param onlyActive    only active
     * @param temporary     force load for temporary display
     * @return ArrayList
     */
    public abstract ArrayList<Object> getData(
            boolean mandatory,
            boolean onlyValidated,
            boolean onlyActive,
            boolean temporary,
            boolean shortlist); // IDEMPIERE 90

    /**
     * Get underlying fully qualified Table.Column Name. Used for VLookup.actionButton (Zoom)
     *
     * @return column name
     */
    public abstract String getColumnName();

    /**
     * Get Zoom - default implementation
     *
     * @param query query
     * @return Zoom Window - here 0
     *     <p>public int getZoom(MQuery query) { return 0; } // getZoom
     */

    /**
     * Get Zoom Query String - default implementation
     *
     * @return Zoom Query
     *     <p>public MQuery getZoomQuery() { return null; } // getZoomQuery
     */

    // IDEMPIERE 90
} //	Lookup
