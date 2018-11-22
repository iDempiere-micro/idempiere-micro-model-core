package org.compiere.model

/** Has Name.
 * Alphanumeric identifier of the entity
 */
interface HasName {
    companion object {
        // Column name Name
        val COLUMNNAME_Name = "Name"
    }

    /** Name.
     * Alphanumeric identifier of the entity
     */
    var Name: String
}