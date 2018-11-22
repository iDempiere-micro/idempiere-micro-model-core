package org.idempiere.common.util;

/**
 * Amount in Words for Serbia.
 *
 * @author Nikola Petkov -The class is based on the AmtInWords_EN.java written by jjanke
 */
public class AmtInWords_SR implements AmtInWords {

  /** AmtInWords_RS */
  public AmtInWords_SR() {
    super();
  }

  private static final String zero = "Nula";

  private static final String negative = "minus";

  private static final String concat = "i";

  private static final String din[] = {"dinar", "dinara"};

  /** Thousands plus */
  private static final String[][] majorNames = {
    {"", "", ""},
    {"Hiljadu", "Hiljade", "Hiljada"},
    {"Milion", "Miliona", "Miliona"},
    {"Milijarda", "Milijarde", "Milijardi"},
    {"Bilion", "Biliona", "Biliona"},
    {"Bilijarda", "Bilijarde", "Bilijardi"},
    {"Trilion", "Triliona", "Triliona"}
  };

  /** Ten to Ninety */
  private static final String[] tensNames = {
    "",
    "Deset",
    "Dvadeset",
    "Trideset",
    "Četrdeset",
    "Pedeset",
    "Šestdeset",
    "Sedamdeset",
    "Osamdeset",
    "Devedeset"
  };

  /* 100-1000 */
  private static final String[] hundredNames = {
    "",
    "JednaStotina",
    "DveStotine",
    "TriStotine",
    "ČetiriStotine",
    "PetStotina",
    "ŠestStotina",
    "SedamStotina",
    "OsamStotina",
    "DevetStotina"
  };

  /** numbers to 19 */
  private static final String[][] numNames = {
    {"", ""},
    {"Jedan", "Jedna"},
    {"Dva", "Dve"},
    {"Tri", "Tri"},
    {"Četiri", "Četiri"},
    {"Pet", "Pet"},
    {"Šest", "Šest"},
    {"Sedam", "Sedam"},
    {"Osam", "Osam"},
    {"Devet", "Devet"},
    {"Deset", "Deset"},
    {"Jedanaest", "Jedanast"},
    {"Dvanaeset", "Dvanaest"},
    {"Trinaeset", "Trinaest"},
    {"Četrnaest", "Četrnaest"},
    {"Petnaest", "Petnaest"},
    {"Šestnaest", "Šestnaest"},
    {"Sedamnaest", "Sedamnaest"},
    {"Osamnaest", "Osamnaest"},
    {"Devetnaest", "Devetnaest"}
  };

  /**
   * Convert Less Than One Thousand
   *
   * @param number
   * @return amt
   */
  private String convertLessThanOneThousand(int number, int w) {
    String soFar;
    // Below 20
    if (number % 100 < 20) {
      soFar = numNames[number % 100][w];
      number /= 100;
    } else {
      soFar = numNames[number % 10][w];
      number /= 10;
      if (soFar.compareTo("") == 0) {
        soFar = tensNames[number % 10];
      } else {
        soFar = tensNames[number % 10] + concat + soFar;
      }
      number /= 10;
    }
    if (number == 0) return soFar;
    return hundredNames[number] + soFar;
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
      return zero;
    }
    String prefix = "";
    if (number < 0) {
      number = -number;
      prefix = negative + " ";
    }
    String soFar = "";
    int place = 0;
    do {
      long n = number % 1000;
      if (n != 0) {
        String s = convertLessThanOneThousand((int) n, place == 1 ? 1 : 0);
        if ((place == 1) && (s.compareTo(numNames[1][1]) == 0)) {
          soFar = majorNames[place][0] + soFar;
        } else {
          long lastDigit = n % 10;
          long lastTwoDigits = n % 100;
          boolean teen = (lastTwoDigits > 10 && lastTwoDigits < 20);
          String major = null;
          if (lastDigit == 1 && !teen) {
            major = majorNames[place][0];
          }
          // 2,3,4,22,23,24,32,33,34 ...
          else if ((lastDigit > 1 && lastDigit < 5) && !teen) {
            major = majorNames[place][1];
          } else {
            // 0,5,6,7,8,9,11,12,13,...,19
            major = majorNames[place][2];
          }
          soFar = s + major + soFar;
        }
      }
      place++;
      number /= 1000;
    } while (number > 0);
    return (prefix + soFar).trim();
  } // convert

  /**
   * ************************************************************************ Get Amount in Words
   *
   * @param amount numeric amount (352.80)
   * @return amount in words (three*five*two 80/100)
   * @throws Exception
   */
  public String getAmtInWords(String amount) throws Exception {
    if (amount == null) return amount;
    StringBuilder sb = new StringBuilder();
    amount = amount.replaceAll(" ", "").replaceAll("\u00A0", "");
    char sep = amount.contains(",") ? ',' : '.'; // Try to determine the
    // separator either
    // comma or a full stop
    int pos = amount.lastIndexOf(sep);
    long dins = Long.parseLong((pos >= 0) ? amount.substring(0, pos) : amount);
    sb.append(convert(dins) + " " + din[dins == 1 ? 0 : 1]);
    if (pos > 0) {
      String para = amount.substring(pos + 1);
      if (para.length() > 2) {
        para = para.substring(0, 2);
      }
      sb.append(" " + concat + " ").append(para).append("/100"); // para"
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
} // AmtInWords_RS
