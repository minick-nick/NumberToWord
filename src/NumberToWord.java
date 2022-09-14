import java.text.DecimalFormat;

public class NumberToWord {

  private static final String[] tensNames = {
    "", " ten", " twenty", " thirty", " forty", " fifty", " sixty",
    " seventy", " eighty", " ninety" 
  };

  private static final String[] numNames = {
    "", " one", " two", " three", " four", " five", " six", " seven",
    " eight", " nine", " ten", " eleven", " twelve", " thirteen",
    " fourteen", " fifteen", " sixteen", " seventeen", " eighteen", " nineteen"
  };

  private static String convertLessThanOneThousand(int number) {
    String soFar;

    if (number % 100 < 20){
      soFar = numNames[number % 100];
      number /= 100;
    } else {
      soFar = numNames[number % 10];
      number /= 10;

      soFar = tensNames[number % 10] + soFar;
      number /= 10;
    }

    if (number == 0) return soFar;

    return numNames[number] + " hundred" + soFar;
  }


  public static String convert(double number) {
    // 0 to 999 999 999 999
    if (number == 0) { return "zero"; }

    boolean isNegative = number < 0 ? true : false;
    number = Math.abs(number);
    int decimalPart = (int) Math.round((number-Math.floor(number))*100);
    
    long longNumber = (long) number;

    String snumber = Long.toString(longNumber);

    String mask = "000000000000";
    DecimalFormat df = new DecimalFormat(mask);
    snumber = df.format(longNumber);

    int billions = Integer.parseInt(snumber.substring(0,3));
    int millions  = Integer.parseInt(snumber.substring(3,6));
    int hundredThousands = Integer.parseInt(snumber.substring(6,9));
    int thousands = Integer.parseInt(snumber.substring(9,12));

    String tradBillions;

    switch (billions) {
      case 0:
        tradBillions = "";
        break;
      case 1:
        tradBillions = convertLessThanOneThousand(billions) + " billion ";
        break;
      default :
        tradBillions = convertLessThanOneThousand(billions) + " billion ";
    }

    String result =  tradBillions;

    String tradMillions;

    switch (millions) {
      case 0:
        tradMillions = "";
        break;
      case 1:
        tradMillions = convertLessThanOneThousand(millions) + " million ";
        break;
      default :
        tradMillions = convertLessThanOneThousand(millions) + " million ";
    }

    result =  result + tradMillions;

    String tradHundredThousands;

    switch (hundredThousands) {
      case 0:
        tradHundredThousands = "";
        break;
      case 1 :
        tradHundredThousands = "one thousand ";
        break;
      default :
        tradHundredThousands = convertLessThanOneThousand(hundredThousands) + " thousand ";
    }

    result =  result + tradHundredThousands;

    String tradThousand;

    tradThousand = convertLessThanOneThousand(thousands);
    result =  result + tradThousand;

    if(isNegative) {
        result = "negative " + result;
    }
    
    if(decimalPart > 0) {
        result = result + " and " + decimalPart + "/100";
    }

    return result.trim().replaceAll(" +", " ");
  }

  public static void main(String[] args) {
      System.out.println(convert(9109001000.50));
  }
}
