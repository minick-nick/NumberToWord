public class NumberToWord {
    public static void main(String[] args) {
        int number = 1150000000;

        String numberInWords = "";


        numberInWords += toWord((number/1000000000), "billion");
        numberInWords += toWord(((number/1000000)%100), "million");
        numberInWords += toWord(((number/1000)%100), "thousand");
        numberInWords += toWord(((number/100)%10), "hundred");
        numberInWords += toWord((number%100), "");

        System.out.println(numberInWords.trim());
    }

    public static String toWord(int number, String suffix) {
        //2242
        //2252
        // 242
        // 214
        // Two Hundred Forty

        String units[] = {"", "one", "two", "three", "four", "five", "six", "seven",
        "eight", "nine"};
        
        String tens[] = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen",
        "seventeen", "eighteen", "nineteen"};
        
        String tenss[] = {"", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};

        String word = " ";

        if(number == 0) {
            return "";
        }

        if(number <= 9) {
            word += units[number] + " " + suffix;
        } else if(number <= 19) {
            word += tens[number%10] + " " + suffix;
        } else if(number <= 90) {
            word += tenss[number/10] + " " + units[number%10] + " " + suffix;
        }
      
        return word;
    }
}
