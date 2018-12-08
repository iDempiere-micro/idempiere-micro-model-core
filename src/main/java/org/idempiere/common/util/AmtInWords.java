package org.idempiere.common.util;

/**
 * Amount in Words. See Msg.getAmtInWords
 *
 * @author Jorg Janke
 * @version $Id: AmtInWords.java,v 1.3 2006/07/30 00:54:36 jjanke Exp $
 */
public interface AmtInWords {
    /**
     * Get Amount in Words
     *
     * @param amount numeric amount (352.80)
     * @return amount in words (three*five*two 80/100)
     * @throws Exception
     */
    String getAmtInWords(String amount) throws Exception;
} //	AmtInWords
