package dollarsbank.utility;

public class TextHelper {
    
    public TextHelper() {
        
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_RED = "\u001B[31m";
    
    public static String colorTextYellow(String input){
        return ANSI_YELLOW + input + ANSI_RESET;
    }
    
    public static String colorTextPurple(String input){
        return ANSI_PURPLE + input + ANSI_RESET;
    }

    public static String colorTextGreen(String input){
        return ANSI_GREEN + input + ANSI_RESET;
    }

    public static String colorTextBlue(String input){
        return ANSI_BLUE + input + ANSI_RESET;
    }

    public static String colorTextCyan(String input){
        return ANSI_CYAN + input + ANSI_RESET;
    }

    public static String colorTextRed(String input){
        return ANSI_RED + input + ANSI_RESET;
    }

    public static void lineBarrier(){
        System.out.println(colorTextPurple("#####################################"));
    }

    public static void blankLine(){
        System.out.println("");
    }

    public static void topperLine(){
        lineBarrier();
        blankLine();
    }

    public static void bottomLine(){
        blankLine();
        lineBarrier();
    }
}
