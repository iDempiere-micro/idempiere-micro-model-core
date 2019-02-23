package org.compiere.dbPort;

import mu.KLogger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.compiere.dbPort.BaseConvertKt.getLogger;

/**
 * Convert SQL to Target DB
 *
 * @author Jorg Janke, Victor Perez
 * @author Teo Sarca, www.arhipac.ro
 * <li>BF [ 2782095 ] Do not log *Access records
 * https://sourceforge.net/tracker/?func=detail&aid=2782095&group_id=176962&atid=879332
 * <li>TODO: BF [ 2782611 ] Migration scripts are not UTF8
 * https://sourceforge.net/tracker/?func=detail&aid=2782611&group_id=176962&atid=879332
 * @author Teo Sarca
 * <li>BF [ 3137355 ] PG query not valid when contains quotes and backslashes
 * https://sourceforge.net/tracker/?func=detail&aid=3137355&group_id=176962&atid=879332
 * @version $Id: Convert.java,v 1.3 2006/07/30 00:55:04 jjanke Exp $
 */
public abstract class Convert {

    /**
     * RegEx: insensitive and dot to include line end characters
     */
    public static final int REGEX_FLAGS = Pattern.CASE_INSENSITIVE | Pattern.DOTALL;
    /**
     * Logger
     */
    protected static KLogger log = getLogger();

    private static String[] dontLogTables =
            new String[]{
                    "AD_ACCESSLOG",
                    "AD_ALERTPROCESSORLOG",
                    "AD_CHANGELOG",
                    "AD_DOCUMENT_ACTION_ACCESS",
                    "AD_FORM_ACCESS",
                    "AD_INFOWINDOW_ACCESS",
                    "AD_ISSUE",
                    "AD_LDAPPROCESSORLOG",
                    "AD_PACKAGE_IMP",
                    "AD_PACKAGE_IMP_BACKUP",
                    "AD_PACKAGE_IMP_DETAIL",
                    "AD_PACKAGE_IMP_INST",
                    "AD_PACKAGE_IMP_PROC",
                    "AD_PINSTANCE",
                    "AD_PINSTANCE_LOG",
                    "AD_PINSTANCE_PARA",
                    "AD_PREFERENCE",
                    "AD_PROCESS_ACCESS",
                    "AD_RECENTITEM",
                    "AD_REPLICATION_LOG",
                    "AD_SCHEDULERLOG",
                    "AD_SESSION",
                    "AD_WINDOW_ACCESS",
                    "AD_WORKFLOW_ACCESS",
                    "AD_WORKFLOWPROCESSORLOG",
                    "CM_WEBACCESSLOG",
                    "C_ACCTPROCESSORLOG",
                    "K_INDEXLOG",
                    "R_REQUESTPROCESSORLOG",
                    "T_AGING",
                    "T_ALTER_COLUMN",
                    "T_DISTRIBUTIONRUNDETAIL",
                    "T_INVENTORYVALUE",
                    "T_INVOICEGL",
                    "T_REPLENISH",
                    "T_REPORT",
                    "T_REPORTSTATEMENT",
                    "T_SELECTION",
                    "T_SELECTION2",
                    "T_SPOOL",
                    "T_TRANSACTION",
                    "T_TRIALBALANCE"
            };
    private static String m_oldprm_COMMENT = "";
    /**
     * Last Conversion Error
     */
    protected String m_conversionError = null;
    /**
     * Verbose Messages
     */
    protected boolean m_verbose = true;

    /**
     * Convert SQL Statement (stops at first error). Statements are delimited by / If an error occured
     * hadError() returns true. You can get details via getConversionError()
     *
     * @param sqlStatements
     * @return converted statement as a string
     */
    public String convertAll(String sqlStatements) {
        String[] sql = convert(sqlStatements);
        StringBuilder sb = new StringBuilder(sqlStatements.length() + 10);
        for (int i = 0; i < sql.length; i++) {
            //  line.separator
            sb.append(sql[i]).append("\n;\n");
            if (m_verbose) log.info("Statement " + i + ": " + sql[i]);
        }
        return sb.toString();
    } //  convertAll

    /**
     * Convert SQL Statement (stops at first error). If an error occured hadError() returns true. You
     * can get details via getConversionError()
     *
     * @param sqlStatements
     * @return Array of converted Statements
     */
    public String[] convert(String sqlStatements) {
        m_conversionError = null;
        if (sqlStatements == null || sqlStatements.length() == 0) {
            m_conversionError = "SQL_Statement is null or has zero length";
            log.info(m_conversionError);
            return null;
        }
        //
        return convertIt(sqlStatements);
    } //  convert

    /**
     * ************************************************************************ Conversion routine
     * (stops at first error).
     *
     * <pre>
     *  - convertStatement
     *      - convertWithConvertMap
     *      - convertComplexStatement
     *      - decode, sequence, exception
     *  </pre>
     *
     * @param sqlStatements
     * @return array of converted statements
     */
    protected String[] convertIt(String sqlStatements) {
        ArrayList<String> result = new ArrayList<String>();
        result.addAll(convertStatement(sqlStatements)); //  may return more than one target statement

        //  convert to array
        String[] sql = new String[result.size()];
        result.toArray(sql);
        return sql;
    } //  convertIt

    /**
     * Clean up Statement. Remove trailing spaces, carrige return and tab
     *
     * @param statement
     * @return sql statement
     */
    protected String cleanUpStatement(String statement) {
        String clean = statement.trim();

        // Convert cr/lf/tab to single space
        Matcher m = Pattern.compile("\\s+").matcher(clean);
        clean = m.replaceAll(" ");

        clean = clean.trim();
        return clean;
    } // removeComments

    /**
     * Utility method to replace quoted string with a predefined marker
     *
     * @param retValue
     * @param retVars
     * @return string
     */
    protected String replaceQuotedStrings(String inputValue, Vector<String> retVars) {
        // save every value
        // Carlos Ruiz - globalqss - better matching regexp
        retVars.clear();

        // First we need to replace double quotes to not be matched by regexp - Teo Sarca BF [3137355 ]
        final String quoteMarker = "<--QUOTE" + System.currentTimeMillis() + "-->";
        inputValue = inputValue.replace("''", quoteMarker);

        Pattern p = Pattern.compile("'[[^']*]*'");
        Matcher m = p.matcher(inputValue);
        int i = 0;
        StringBuffer retValue = new StringBuffer(inputValue.length());
        while (m.find()) {
            String var =
                    inputValue
                            .substring(m.start(), m.end())
                            .replace(quoteMarker, "''"); // Put back quotes, if any
            retVars.addElement(var);
            m.appendReplacement(retValue, "<--" + i + "-->");
            i++;
        }
        m.appendTail(retValue);
        return retValue.toString().replace(quoteMarker, "''") // Put back quotes, if any
                ;
    }

    /**
     * Utility method to recover quoted string store in retVars
     *
     * @param retValue
     * @param retVars
     * @return string
     */
    protected String recoverQuotedStrings(String retValue, Vector<String> retVars) {
        for (int i = 0; i < retVars.size(); i++) {
            // hengsin, special character in replacement can cause exception
            String replacement = retVars.get(i);
            replacement = escapeQuotedString(replacement);
            retValue = retValue.replace("<--" + i + "-->", replacement);
        }
        return retValue;
    }

    /**
     * hook for database specific escape of quoted string ( if needed )
     *
     * @param in
     * @return string
     */
    protected String escapeQuotedString(String in) {
        return in;
    }

    /**
     * Convert simple SQL Statement. Based on ConvertMap
     *
     * @param sqlStatement
     * @return converted Statement
     */
    private String applyConvertMap(String sqlStatement) {
        // Error Checks
        if (sqlStatement.toUpperCase().indexOf("EXCEPTION WHEN") != -1) {
            String error = "Exception clause needs to be converted: " + sqlStatement;
            log.info(error);
            m_conversionError = error;
            return sqlStatement;
        }

        // Carlos Ruiz - globalqss
        // Standard Statement -- change the keys in ConvertMap

        String retValue = sqlStatement;

        Pattern p;
        Matcher m;

        // for each iteration in the conversion map
        Map<String, String> convertMap = getConvertMap();
        if (convertMap != null) {
            Iterator<?> iter = convertMap.keySet().iterator();
            while (iter.hasNext()) {

                // replace the key on convertmap (i.e.: number by numeric)
                String regex = (String) iter.next();
                String replacement = convertMap.get(regex);
                try {
                    p = Pattern.compile(regex, REGEX_FLAGS);
                    m = p.matcher(retValue);
                    retValue = m.replaceAll(replacement);

                } catch (Exception e) {
                    String error = "Error expression: " + regex + " - " + e;
                    log.info(error);
                    m_conversionError = error;
                }
            }
        }
        return retValue;
    } // convertSimpleStatement

    /**
     * do convert map base conversion
     *
     * @param sqlStatement
     * @return string
     */
    protected String convertWithConvertMap(String sqlStatement) {
        try {
            sqlStatement = applyConvertMap(cleanUpStatement(sqlStatement));
        } catch (RuntimeException e) {
            log.warn(e.getLocalizedMessage());
        }

        return sqlStatement;
    }

    /**
     * Get convert map for use in sql convertion
     *
     * @return map
     */
    protected Map<String, String> getConvertMap() {
        return null;
    }

    /**
     * Convert single Statements. - remove comments - process FUNCTION/TRIGGER/PROCEDURE - process
     * Statement
     *
     * @param sqlStatement
     * @return converted statement
     */
    protected abstract ArrayList<String> convertStatement(String sqlStatement);

} //  Convert
