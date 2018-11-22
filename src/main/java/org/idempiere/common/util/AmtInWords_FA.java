package org.idempiere.common.util;

/**
 * Amount in Words for Persian
 *
 * @author Bahman Movaqar
 * @version $Id: AmtInWords_FA.java,v 1.3 2006/07/30 00:54:35 bmovaqar Exp $
 */
public class AmtInWords_FA implements AmtInWords {
  /** AmtInWords_FA */
  public AmtInWords_FA() {
    super();
  } //	AmtInWords_FA

  /** Thousands plus */
  private static final String[] majorNames = {
    "", "هزار", "ميليون", "ميليارد", "تريليون", "کوآدريليون", "هزار کوآدريليون"
  };

  /** One hundred to nine hundreds */
  private static final String[] hundredsNames = {
    "", "يکصد", "دويست", "سيصد", "چهارصد", "پانصد", "ششصد", "هفتصد", "هشتصد", "نهصد"
  };
  /** Ten to Ninety */
  private static final String[] tensNames = {
    "", "ده", "بيست", "سي", "چهل", "پنجاه", "شصت", "هفتاد", "هشتاد", "نود"
  };

  /** numbers to 19 */
  private static final String[] numNames = {
    "", "يک", "دو", "سه", "چهار", "پنج", "شش", "هفت", "هشت", "نه", "ده", "يازده", "دوازده", "سيزده",
    "چهارده", "پانزده", "شانزده", "هفده", "هجده", "نوزده"
  };

  /**
   * Convert Less Than One Thousand
   *
   * @param number
   * @return amt
   */
  private String convertLessThanOneThousand(int number) {
    String soFar;
    //	Below 20
    if (number % 100 < 20) {
      soFar = numNames[number % 100];
      number /= 100;
      soFar =
          hundredsNames[number]
              + ("".equals(soFar) || hundredsNames[number].equals("") ? soFar : " و " + soFar);
    } else {
      soFar = numNames[number % 10];
      number /= 10;
      soFar = tensNames[number % 10] + (tensNames[number % 10].equals("") ? soFar : " و " + soFar);
      number /= 10;
      soFar =
          hundredsNames[number % 10]
              + (hundredsNames[number % 10].equals("") ? soFar : " و " + soFar);
    }
    return soFar;
  } //	convertLessThanOneThousand

  /**
   * Convert
   *
   * @param number
   * @return amt
   */
  private String convert(long number) {
    /* special case */
    if (number == 0) {
      return "صفر";
    }
    String prefix = "";
    if (number < 0) {
      number = -number;
      prefix = "منفي ";
    }
    String soFar = "";
    int place = 0;
    do {
      long n = number % 1000;
      if (n != 0) {
        String s = convertLessThanOneThousand((int) n);
        if (!soFar.equals(""))
          soFar =
              s
                  + " "
                  + majorNames[place]
                  + (s.equals("") || majorNames[place].equals("") ? soFar : " و " + soFar);
        else soFar = s + " " + majorNames[place];
      }
      place++;
      number /= 1000;
    } while (number > 0);
    return (prefix + soFar).trim();
  } //	convert

  /**
   * ************************************************************************ Get Amount in Words
   *
   * @param amount numeric amount (352.80)
   * @return amount in words (three*five*two 80/100)
   * @throws Exception
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
      if (pos == i) // 	we are done
      {
        String cents = oldamt.substring(i + 1);
        sb.append(' ').append(cents).append("/100");
        break;
      }
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
} //	AmtInWords_FA
