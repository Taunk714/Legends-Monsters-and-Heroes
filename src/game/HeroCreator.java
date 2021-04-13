package game;

import game.utils.MyFont;

import java.util.HashMap;
import java.util.HashSet;

// game.HeroCreator class. It's a singleton. One game only have one game.HeroCreator. If the hero was chosen, he can't be chosen anymore.
public class HeroCreator {

    private static final String warriorString = """
            Gaerdal_Ironhand    100     700     500     600     1354    7  true  true  false
            Sehanine_Monnbow    600     700     800     500     2500    8  true  true  false
            Muamman_Duathall    300     900     500     750     2546    6  true  true  false
            Flandal_Steelskin   200     750     650     700     2500    7  true  true  false
            Undefeated_Yoj      400     800     400     700     2500    7  true  true  false
            Eunoia_Cyn          400     700     800     600     2500    6  true  true  false""".replaceAll("\t"," ");

    private static final String sorcererString = """
            Rillifane_Rallathil     1300    750     450     500     2500    9  false  true  true
            Segojan_Earthcaller     900     800     500     650     2500    5  false  true  true
            Reign_Havoc             800     800     800     800     2500    8  false  true  true
            Reverie_Ashels          900     800     700     400     2500    7  false  true  true
            Kalabar                 800     850     400     600     2500    6  false  true  true
            Skye_Soar               1000    700     400     500     2500    5  false  true  true""".replaceAll("\t"," ");

    private static final String paladinString = """
            Parzival             300     750      650     700     2500    7   true  false  true
            Sehanine_Moonbow     300     750      700     700     2500    7   true  false  true
            Skoraeus_Stonebones  250     650      600     350     2500    4   true  false  true
            Garl_Glittergold     100     600      500     400     2500    5   true  false  true
            Amaryllis_Astra      500     500      500     500     2500    5   true  false  true
            Caliber_Heist        400     400      400     400     2500    8   true  false  true""".replaceAll("\t"," ");

    private static final String[][] warrior = initHeroList(warriorString);
    private static final String[][] sorcerer = initHeroList(sorcererString);
    private static final String[][] paladin = initHeroList(paladinString);

    private static final HashMap<String, Integer> occIds = new HashMap<>();

    static {
        occIds.put("Warrior", 0);
        occIds.put("Sorcerer", 1);
        occIds.put("Paladin", 2);
    }

    private static HeroCreator instance = new HeroCreator();
    private HeroCreator(){}

    public static HeroCreator getInstance(){
        return instance;
    }

    private HashSet<Integer> chosenHero = new HashSet<>();

    public Hero createHero(String occupation, int id){
        if (occupation.equalsIgnoreCase("warrior")){
            return new Warrior(warrior[id]);
        }else if (occupation.equalsIgnoreCase("sorcerer")){
            return new Sorcerer(sorcerer[id]);
        }else if (occupation.equalsIgnoreCase("paladin")){
            return new Paladin(paladin[id]);
        }
        return null;
    }

    // show all the heroes of specific occupation.
    public void showOccInfo(String occupation){
        String[][] target = getHeroInfoList(occupation);
        System.out.println("Here are all the "+ occupation.toLowerCase() +"s you can choose");
        StringBuilder s = new StringBuilder();
        StringBuilder sLine = new StringBuilder();
        int[] tableSize = new int[]{3, Legends.getHeroNameLen(), 5, 10, 10, 10, 10};
        s.append("|%") ;
        sLine.append("+");
        for (int i = 0; i < tableSize.length; i++) {
            s.append(tableSize[i]).append("s|%");
            sLine.append("-".repeat(tableSize[i])).append("+");
        }
        s.deleteCharAt(s.length()-1);
        s.append("\n");
        System.out.println(sLine.toString());
        System.out.printf(s.toString(),"ID", "Hero Name"," Mana","Strength","Agility","Dexterity", "Start EXP");
        System.out.println(sLine);
        int id = occIds.get(occupation);
        for (int i = 0; i < target.length; i++) {
            String[] targetInfo = target[i];
            if (chosenHero.contains(id*10 + i)){
                System.out.print(MyFont.ANSI_DELETE);
            }
            System.out.printf(s.toString(),i, targetInfo[0], targetInfo[1],
                    targetInfo[2], targetInfo[3],targetInfo[4], targetInfo[6]);
            System.out.print(MyFont.ANSI_RESET);
        }
        System.out.println(sLine.toString());
    }

    // return the number of heroes of a specific type.
    public int getHeroNum(String type){
        return getHeroInfoList(type).length;
    }

    // parse the occupation info and return the corresponding InfoList.
    private String[][] getHeroInfoList(String occupation){
        if (occupation.equalsIgnoreCase("warrior")){
            return warrior;
        }else if (occupation.equalsIgnoreCase("sorcerer")){
            return sorcerer;
        }else if (occupation.equalsIgnoreCase("paladin")){
            return paladin;
        }
        return null;
    }

    // Solve the text, and split into string[]. Objects are created using String[] data.
    private static String[][] initHeroList(String infoString){
        String[][] info;
        String[] singleInfo = infoString.split("\n");
        info = new String[singleInfo.length][];
        for (int i = 0; i < info.length; i++) {
            info[i] = singleInfo[i].split(" +");
        }
        return info;
    }

    public void markUsed(int id){
        chosenHero.add(id);
    }
}
