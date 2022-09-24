import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.println("Type expression: ");
        String expression = input.nextLine();
        System.out.println(calc(expression));
    }

    public static String calc(String input) throws Exception {
        Map<String, String> rim_arab = new HashMap<String, String>() {{
            put("I", "1");put("II", "2");put("III", "3");put("IV", "4");put("V", "5");put("VI", "6");put("VII", "7");put("VIII", "8");put("IX", "9");put("X", "10");
        }};
        String [] rim_numbers = new String[] {"I","II","III","IV","V","VI","VII","VIII","IX","X"};
        char action = 0;
        input = input.replaceAll("\\s+", "");
        char [] actions = {'+', '-', '/', '*'};
        for (int i = 1; i<input.length()-1; i++){
            for (char element: actions){
                if (element == input.charAt(i)){
                    action = input.charAt(i);
                    break;
                }
            }
        }
        String [] query = input.split("[/*+-]");
        if (query.length != 2){
            throw new Exception("Amount of operands is not equal to 1 / String is not a mathematical operation!");
        }
        int first = 0;
        int second = 0;
        boolean flag = false;
        if ( rim_arab.get(query[0])!=null && rim_arab.get(query[1])!=null){
            flag = true;
            first = Integer.parseInt(String.valueOf( rim_arab.get(query[0])));
            second = Integer.parseInt(String.valueOf( rim_arab.get(query[1])));
        } else if ( rim_arab.get(query[0])==null && rim_arab.get(query[1])==null) {
            first = Integer.parseInt(String.valueOf(query[0]));
            second = Integer.parseInt(String.valueOf(query[1]));
        }
        else {
            throw new Exception("Different number systems are used at the same time / Roman numbers must be in range from I to X!");
        }
        if (first > 10 || second > 10) {
            throw new Exception("Numbers must be in range from 0 to 10!");
        }
        int result = 0;
        switch (action) {
            case '+' -> {
                if (flag) {
                    return "Result: " + getRim(first + second);
                }
                return "Result: " + (first + second);
            }
            case '/' -> {
                if (flag) {
                    if (first % second != 0) {
                        throw new Exception("Error");
                    }
                    return "Result: " + getRim(first / second).toString();
                }
                return "Result: " + (double) first / second;
            }
            case '-' -> {
                if (flag) {
                    if ((first - second) < 0) {
                        throw new Exception("There are no negative Roman numbers");
                    }
                    return "Result: " + getRim(first - second).toString();
                }
                return "Result: " + (first - second);
            }
            case '*' -> {
                if (flag) {
                    return "Result: " + getRim(first * second).toString();
                }
                return "Result: " + first * second;
            }
            default -> throw new Exception("Unknown operand!");
        }
    }
    public static String getRim(int result) {
        Map<String, String> rim_arab = new HashMap<String, String>() {{
            put("1", "I");
            put("2", "II");
            put("3", "III");
            put("4", "IV");
            put("5", "V");
            put("6", "VI");
            put("7", "VII");
            put("8", "VIII");
            put("9", "IX");
            put("10", "X");
        }};
        if (result / 10 == 10) {
            return "C";
        }
        String a_str = null;
        int a_int = 0;
        String b = null;
        if (result / 10 >= 1) {
            if (result / 10 >= 5) {
                if (result / 10 > 8) {
                    a_str = "XC";
                    a_int = 90;
                } else {
                    a_str = "L";
                    a_int = 50;
                    for (int i = 5; i < 9; i++) {
                        if (result / 10 == i) {
                            break;
                        } else {
                            a_str += "X";
                            a_int += 10;
                        }
                    }
                }
            } else {
                if (result / 10 > 3) {
                    a_str = "XL";
                    a_int = 40;
                } else {
                    a_str = "X";
                    a_int = 10;
                    for (int i = 1; i < 4; i++) {
                        if (result / 10 == i) {
                            break;
                        } else {
                            a_str += "X";
                            a_int += 10;
                        }
                    }
                }
            }
        }
        else a_str ="";
        if (result%10==0){
            b="";
        }
        else {
            b = rim_arab.get(String.valueOf(result - a_int));
        }
        return a_str + b;
    }
}


