package org.compiere.model

/** Has Name.
 * Alphanumeric identifier of the entity
 */
interface HasName2 {
    companion object {
        // Column name Name
        val COLUMNNAME_Name2 = "Name2"
    }

    /** Name.
     * Alphanumeric identifier of the entity
     */
    var Name: String
}