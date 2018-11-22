package org.idempiere.common.util;

/**
 * Amount in Words for Polish
 *
 * <p>
 *
 * <ul>
 *   <li>2006-02-13 - teo_sarca - integrated Gavin Dunse patch [ 1658661 ] translate polish
 *       AmtInWords_PL.java Windows-1250 to UTF-8
 * </ul>
 *
 * @author Marek Mosiewicz - http://www.rgagnon.com/javadetails/java-0426.html
 * @version $Id: AmtInWords_PL.java,v 1.4 2005/12/31 06:33:21 jjanke Exp $
 */
public class AmtInWords_PL implements AmtInWords {
  /** AmtInWords_EN */
  public AmtInWords_PL() {
    super();
  } // AmtInWords_PL

  /** Thousands plus */
  private static final String[][] majorNames = {
    {"", "", ""},
    {"tysiąc", "tysiące", "tysięcy"},
    {"milion", "miliony", "milionów"},
    {"miliard", "miliardy", "miliardów"},
    {"bilion", "biliony", "bilionów"},
    {"trylion", "tryliony", "trylionów"},
    {"quadrylion", "quadryliony", "quadrylionów"}
  };

  /** Ten to Ninety */
  private static final String[] tensNames = {
    "",
    "dziesięć",
    "dwadzieścia",
    "trzydzieści",
    "czterdzieści",
    "pięćdziesiąt",
    "sześćdziesiąt",
    "siedemdziesiąt",
    "osiemdziesiąt",
    "dziewięćdziesiąt"
  };

  private static final String[] hundredNames = {
    "",
    "sto",
    "dwieście",
    "trzysta",
    "czterysta",
    "pięćset",
    "sześćset",
    "siedemset",
    "osiemset",
    "dziewięćset"
  };

  /** numbers to 19 */
  private static final String[] numNames = {
    "",
    "jeden",
    "dwa",
    "trzy",
    "cztery",
    "pięć",
    "sześć",
    "siedem",
    "osiem",
    "dzieiwięć",
    "dziesięć",
    "jedenaście",
    "dwanaście",
    "trzynaście",
    "czternaście",
    "pietnaście",
    "szescnaście",
    "siedemnaście",
    "osiemnaście",
    "dziewiętnaście"
  };

  /**
   * Convert Less Than One Thousand
   *
   * @param number
   * @return amt
   */
  private String convertLessThanOneThousand(int number) {
    String soFar;
    // Below 20
    if (number % 100 < 20) {
      soFar = numNames[number % 100];
      number /= 100;
    } else {
      soFar = numNames[number % 10];
      number /= 10;
      soFar = tensNames[number % 10] + " " + soFar;
      number /= 10;
    }
    soFar = hundredNames[number % 10] + " " + soFar;
    number /= 10;
    return soFar;
  } // convertLessThanOneThousand

  /**
   * Convert
   *
   * @param number
   * @return amt
   */
  private String convert(long number) {
    /* special case */
    if (number == 0) {
      return "zero";
    }
    String prefix = "";
    if (number < 0) {
      number = -number;
      prefix = "minus ";
    }
    String soFar = "";
    int place = 0;
    do {
      long n = number % 1000;
      if (n != 0) {
        String s = convertLessThanOneThousand((int) n);
        int pos;
        if (number % 10 == 1) {
          pos = 0;
        } else if (number % 10 < 5) {
          pos = 1;
        } else {
          pos = 2;
        }
        if (number > 9 && number < 20) {
          pos = 2;
        }
        soFar = s + " " + majorNames[place][pos] + " " + soFar;
      }
      place++;
      number /= 1000;
    } while (number > 0);
    return (prefix + soFar).trim();
  } // convert

  /**
   * ************************************************************************* Get Amount in Words
   *
   * @param amount numeric amount (352.80)
   * @return amount in words (three*five*two 80/100)
   */
  public String getAmtInWords(String amount) throws Exception {
    if (amount == null) return amount;
    //
    StringBuilder sb = new StringBuilder();
    int pos = amount.lastIndexOf('.');
    int pos2 = amount.lastIndexOf(',');
    if (pos2 > pos) pos = pos2;
    String oldamt = amount;
    amount = amount.replaceAll(",", "");
    int newpos = amount.lastIndexOf('.');
    long dollars = Long.parseLong(amount.substring(0, newpos));
    sb.append(convert(dollars));
    for (int i = 0; i < oldamt.length(); i++) {
      if (pos == i) // we are done
      {
        String cents = oldamt.substring(i + 1);
        sb.append(' ').append(cents).append("/100");
        break;
      }
    }
    return sb.toString();
  } // getAmtInWords

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
  } // print
} // AmtInWords_EN
