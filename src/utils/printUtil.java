package utils;

/**
 * a util to print with colors in the console
 */
public class printUtil {
    
    public static void printRed(String s){
        System.out.println("\u001b[31m"+s+"\u001b[0m");
    }

    public static void printYellow(String s){
        System.out.println("\u001b[33m"+s+"\u001b[0m");
    }

    public static void printYellowSign(String s){
        System.out.print("\u001b[33m"+s+"\u001b[0m");
    }


    public static void printGreen(String s){
        System.out.println("\u001b[32m"+s+"\u001b[0m");
    }

    public static void printBlue(String s){
        System.out.println("\u001b[34m"+s+"\u001b[0m");
    }

    public static void printWhiteBackground(String s){
        System.out.print("|  "+s+"  |");
    }

    public static void printGreenBackground(String s){
        System.out.print("|\u001b[42m  "+s+"  \u001b[0m|");
    }

    public static void printBlueBackground(String s){
        System.out.print("|\u001b[44m  "+s+"  \u001b[0m|");
    }

    // public static void printBackground(String s){
    //     System.out.print("|\u001b[44m  "+s+"  \u001b[0m|");
    // }

    public static void printYellowBackground(String s){
        System.out.print("|\u001b[43m  "+s+"  \u001b[0m|");
    }

    public static void printBlackBackground(String s){
        System.out.print("|\u001b[40m  "+s+"  \u001b[0m|");
    }

    public static void printRedBackground(String s){
        System.out.println("\u001b[41m"+s+"\u001b[0m");
    }

    public static void print(String s){
        System.out.println(s);
    }

    public static void printBoundary(){
        System.out.println("--------------------------------------------------------");
    }

    public static void printDotBoundary(String s){
        System.out.println("+ + + + + + + + + + + + " + s+" + + + + + + + + + + + +");
    }

    public static void printWithBoundary(String s){
        System.out.println();
        System.out.println("------------------------"+s+"---------------------");
    }

    public static void print(){
        System.out.println();

    }

    public static void println(String s){
        System.out.println(s);

    }

    public static void printSingle(String s){
        System.out.print(s);

    }

    

    public static void printOpt(String s){
        System.out.print("("+s+") ");
    }

    public static void printError(String s){
        printRed("ERROR INPUT: "+s);

    }

    public static void printHeroDamage(String heroName, String monsterName,int damage){
        printGreenBackground("Hero");
        printSingle(String.format(" %s  -----%d damage---->  ",heroName, damage));
        printBlueBackground("Monster ");
        printSingle(monsterName);
        print();
        print();

    }

    public static void printMonsterDamage(String monsterName, String heroName,int damage){
        printBlueBackground("Monster");
        printSingle(String.format(" %s  -----%d damage---->  ",monsterName, damage));
        printBlueBackground("Hero ");
        printSingle(heroName);
        print();
        print();

    }


}
