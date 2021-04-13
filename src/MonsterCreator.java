import java.util.*;
// game.Monster creator. Store all the monster info and create monster based on level.
public class MonsterCreator{
    private static final String monsterString = """
            Desghidorrah	 Dragon   3       300       400     35
            Chrysophylax     Dragon   2       200       500     20
            BunsenBurner	 Dragon   4       400       500     45
            Natsunomeryu 	 Dragon   1       100       200     10
            TheScaleless	 Dragon   7       700       600     75
            Kas-Ethelinh     Dragon   5       600       500     60
            Alexstraszan	 Dragon   10      1000      9000    55
            Phaarthurnax	 Dragon   6       600       700     60
            D-Maleficent	 Dragon   9       900       950     85
            TheWeatherbe 	 Dragon   8       800       900     80
            Igneel           Dragon   6       600       400     60
            BlueEyesWhite    Dragon   9       900       600     75
            Cyrrollalee     Exoskeleton  7       700        800     75
            Brandobaris     Exoskeleton  3       350        450     30
            BigBad-Wolf     Exoskeleton  1       150        250     15
            WickedWitch     Exoskeleton  2       250        350     25
            Aasterinian     Exoskeleton  4       400        500     45
            Chronepsish     Exoskeleton  6       650        750     60
            Kiaransalee     Exoskeleton  8       850        950     85
            St-Shargaas     Exoskeleton  5       550        650     55
            Merrshaullk     Exoskeleton  10      1000       900     55
            St-Yeenoghu     Exoskeleton  9       950        850     90
            DocOck          Exoskeleton  6       600        600     55
            Exodia          Exoskeleton  10      1000       1000    50
            Andrealphus     Spirit  2       600       500     40
            Blinky          Spirit  1       450       350     35
            Andromalius     Spirit  3       550       450     25
            Chiang-shih     Spirit  4       700       600     40
            FallenAngel     Spirit  5       800       700     50
            Ereshkigall     Spirit  6       950       450     35
            Melchiresas     Spirit  7       350       150     75
            Jormunngand     Spirit  8       600       900     20
            Rakkshasass     Spirit  9       550       600     35
            Taltecuhtli     Spirit  10      300       200     50
            Casper          Spirit  1       100       100     50""".replaceAll("\t"," ");


//    private static final String[][] monsterList = initMonsterList();
    private static final HashMap<Integer, ArrayList<String[]>> monsterListByLevel = new HashMap<>();

    static {
        initMonsterList();
    }

    public static Monster createHeroByLevel(int level){
        Random rnd = new Random();
        ArrayList<String[]> monsters = monsterListByLevel.get(level);
        return new Monster(monsters.get(rnd.nextInt(monsters.size())));
    }


    private static void initMonsterList(){
        String[] singleInfo = MonsterCreator.monsterString.split("\n");
        for (String s : singleInfo) {
            String[] info = s.split(" +");
            int level = Integer.parseInt(info[2]);
            if (monsterListByLevel.containsKey(level)) {
                monsterListByLevel.get(level).add(info);
            } else {
                monsterListByLevel.put(level, new ArrayList<>());
                monsterListByLevel.get(level).add(info);
            }
        }
    }
}
