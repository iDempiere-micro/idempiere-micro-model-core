package org.idempiere.common.util;

/**
 * Italian Amount in Words
 *
 * @author Jorg Janke - http://www.rgagnon.com/javadetails/java-0426.html
 * @translator Angelo Dabala' (genied) - nectosoft - Italian localization - www.nectosoft.it
 * @version $Id: AmtInWords_IT.java,v 1.3 2006/07/30 00:54:36 jjanke Exp $
 */
public class AmtInWords_IT implements AmtInWords {
  /** AmtInWords_IT */
  public AmtInWords_IT() {
    super();
  } //	AmtInWords_IT

  private static final String[] majorNames = {
    "",
    "MILLE", // 10^3
    "UNMILIONE", // 10^6
    "UNMILIARDO", // 10^9
    "UNBILIONE", // 10^12
    "UNBILIARDO", // 10^15
    "UNTRILIONE" // 10^18
  };

  private static final String[] majorNamesPlural = {
    "",
    "MILA", // 10^3
    "MILIONI", // 10^6
    "MILIARDI", // 10^9
    "BILIONI", // 10^12
    "BILIARDI", // 10^15
    "TRILIONI" // 10^18
  };

  private static final String[] tensNames = {
    "",
    "DIECI",
    "VENTI",
    "TRENTA",
    "QUARANTA",
    "CINQUANTA",
    "SESSANTA",
    "SETTANTA",
    "OTTANTA",
    "NOVANTA"
  };

  private static final String[] numNames = {
    "",
    "UNO",
    "DUE",
    "TRE",
    "QUATTRO",
    "CINQUE",
    "SEI",
    "SETTE",
    "OTTO",
    "NOVE",
    "DIECI",
    "UNDICI",
    "DODICI",
    "TREDICI",
    "QUATTORDICI",
    "QUINDICI",
    "SEDICI",
    "DICIASSETTE",
    "DICIOTTO",
    "DICIANNOVE"
  };

  /**
   * Convert Less Than One Thousand
   *
   * @param number
   * @return amt
   */
  private String convertLessThanOneThousand(int number) {
    int unit = 0;
    int tens = 0;
    String soFar;
    //  Sotto 20
    if (number % 100 < 20) {
      soFar = numNames[number % 100];
      number /= 100;
    } else {
      unit = number % 10;
      soFar = numNames[unit];
      number /= 10;
      //
      tens = number % 10;
      // Uno e Otto iniziano con una vocale, quindi elido la finale dalle decine es.
      // TRENTAUNO->TRENTUNO
      if (unit == 1 || unit == 8)
        soFar = tensNames[tens].substring(0, tensNames[tens].length() - 1) + soFar;
      else soFar = tensNames[tens] + soFar;
      number /= 10;
    }
    if (number == 0) return soFar;
    // Sopra 200
    if (number > 1) return numNames[number] + "CENTO" + soFar;
    // Tra 100 e 199
    else return "CENTO" + soFar;
  } //	convertLessThanOneThousand

  /**
   * Convert
   *
   * @param number
   * @return amt
   */
  private String convert(long number) {
    /* special case */
    if (number == 0) return "ZERO";
    String prefix = "";
    if (number < 0) {
      number = -number;
      prefix = "MENO ";
    }
    String soFar = "";
    int place = 0;
    do {
      long n = number % 1000;
      if (n != 0) {
        String s = convertLessThanOneThousand((int) n);
        if (n == 1 && place > 0) soFar = majorNames[place] + soFar;
        else soFar = s + majorNamesPlural[place] + soFar;
      }
      place++;
      number /= 1000;
    } while (number > 0);
    return (prefix + soFar).trim();
  } //	convert

  /**
   * ************************************************************************ Get Amount in Words
   *
   * @param amount numeric amount (352.80 or 352,80)
   * @return amount in words (TRECENTOCINQUANTADUE/80)
   * @throws Exception
   */
  public String getAmtInWords(String amount) throws Exception {
    if (amount == null) return amount;

    // assume rightmost point or comma as decimal separator
    StringBuilder sb = new StringBuilder();
    int pos = amount.lastIndexOf(',');
    int pos2 = amount.lastIndexOf('.');
    if (pos2 > pos) pos = pos2;
    String oldamt = amount;

    // strips decimals from the amount
    if (pos >= 0) amount = amount.substring(0, pos);

    // remove points and commas
    amount = amount.replaceAll("\\.", "");
    amount = amount.replaceAll(",", "");

    long amt = amount.length() > 0 ? Long.parseLong(amount) : 0;

    sb.append(convert(amt));
    if (pos >= 0) {
      String cents = oldamt.substring(pos + 1);
      sb.append("/").append(cents);
    }
    return sb.toString();
  } //	getAmtInWords

  /**
   * Test Print
   *
   * @param amt amount
   */
  private void print(String amt) {
    try {
      System.out.println(amt + " = " + getAmtInWords(amt));
    } catch (Exception e) {
      e.printStackTrace();
    }
  } //	print
} //	AmtInWords_IT
