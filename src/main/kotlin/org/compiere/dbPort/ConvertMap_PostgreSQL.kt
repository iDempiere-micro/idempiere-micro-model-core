package org.compiere.dbPort

import java.util.TreeMap

object ConvertMap_PostgreSQL {
    private val s_pg = TreeMap<String, String>()

    /**
     * Return Map for PostgreSQL
     *
     * @return TreeMap with pattern as key and the replacement as value
     */
    val convertMap: TreeMap<String, String>
        get() {
            if (s_pg.size == 0) initConvertMap()
            return s_pg
        } //  getConvertMap

    /**
     * PostgreSQL Init
     */
    private fun initConvertMap() {
        //      Oracle Pattern                  Replacement

        //  Data Types
        s_pg["\\bNUMBER\\b"] = "NUMERIC"
        s_pg["\\bDATE\\b"] = "TIMESTAMP"
        s_pg["\\bVARCHAR2\\b"] = "VARCHAR"
        s_pg["\\bNVARCHAR2\\b"] = "VARCHAR"
        s_pg["\\bNCHAR\\b"] = "CHAR"
        s_pg["\\bBLOB\\b"] = "BYTEA" //  BLOB not directly supported
        s_pg["\\bCLOB\\b"] = "TEXT" //  CLOB not directly supported
        s_pg["\\bLIMIT\\b"] = "\"limit\""
        s_pg["\\bACTION\\b"] = "\"action\""
        s_pg["\\bold\\b"] = "\"old\""
        s_pg["\\bnew\\b"] = "\"new\""

        //  Storage
        s_pg["\\bCACHE\\b"] = ""
        s_pg["\\bUSING INDEX\\b"] = ""
        s_pg["\\bTABLESPACE\\s\\w+\\b"] = ""
        s_pg["\\bSTORAGE\\([\\w\\s]+\\)"] = ""
        //
        s_pg["\\bBITMAP INDEX\\b"] = "INDEX"

        //  Functions
        s_pg["\\bSYSDATE\\b"] = "statement_timestamp()"
        s_pg["\\bCURRENT_TIMESTAMP\\b"] = "statement_timestamp()"
        s_pg["\\bDUMP\\b"] = "MD5"
        s_pg["END CASE"] = "END"
        s_pg["\\bgetDate\\b\\(\\)"] = "statement_timestamp()"
        s_pg["\\bNVL\\b"] = "COALESCE"
        s_pg["\\bTO_DATE\\b"] = "TO_TIMESTAMP"
        //
        s_pg["\\bDBMS_OUTPUT.PUT_LINE\\b"] = "RAISE NOTICE"
        s_pg["\\bTO_NCHAR\\b"] = ""

        //  Temporary
        s_pg["\\bGLOBAL TEMPORARY\\b"] = "TEMPORARY"
        s_pg["\\bON COMMIT DELETE ROWS\\b"] = ""
        s_pg["\\bON COMMIT PRESERVE ROWS\\b"] = ""

        // DDL

        //  DROP TABLE x CASCADE CONSTRAINTS
        s_pg["\\bCASCADE CONSTRAINTS\\b"] = ""

        //  Select
        s_pg["\\sFROM\\s+DUAL\\b"] = ""

        //  Statements
        s_pg["\\bELSIF\\b"] = "ELSE IF"
        s_pg["\\bREC \\b"] = "AS REC "

        //  Sequences
        s_pg["\\bSTART WITH\\b"] = "START"
        s_pg["\\bINCREMENT BY\\b"] = "INCREMENT"
    } //  initPostgreSQL
}
