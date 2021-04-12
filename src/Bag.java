import java.util.ArrayList;
import java.util.HashMap;


// Bag class. Every Hero has a Bag. Bag stores the items hero have.
public class Bag {
    private final ArrayList<Item>[] items = new ArrayList[4];
    private static HashMap<String, Integer> itemId = new HashMap<>();
    private static String[] reverseId = new String[]{"Armor", "Potion", "Spell", "Weapon"};
    private static int DEFAULT_SIZE = 20;
    private static int MAX_SIZE = 40;
    private int volume = DEFAULT_SIZE;
    private int used = 0;
    private Hero hero;

    static {
        itemId.put("Armor", 0);
        itemId.put("Potion", 1);
        itemId.put("Spell", 2);
        itemId.put("Weapon", 3);
        itemId.put("IceSpell", 2);
        itemId.put("LightningSpell", 2);
        itemId.put("FireSpell", 2);
    }

    public Bag(Hero hero) {
        for (int i = 0; i < items.length; i++) {
            items[i] = new ArrayList<>();
        }
        this.hero = hero;
    }

    public int getVolume() {
        return volume;
    }

    public int getUsed(){
        return used;
    }

    public int getAvailable(){
        return volume - used;
    }

    public void useItem(String type, int index){
        Item item = getItem(type, index);
        item.use();
        removeItem(item);
        used--;
    }

    public void useItem(int index){
        Item item = getItem(index);
        item.use();
        removeItem(item);
        used--;
    }

    public void addItem(Item item){
        used++;
        items[itemId.get(item.getType())].add(item);
    }

    public void removeItem(Item item){
        items[itemId.get(item.getType())].remove(item);
    }

    public void removeItem(int itemId){
        if (itemId >= used){
            throw new IndexOutOfBoundsException();
        }else{
            for (ArrayList<Item> item : items) {
                if (itemId < item.size()) {
                    item.remove(itemId);
                    used--;
                } else {
                    itemId -= item.size();
                }
            }
        }
    }

    public Item getItem(int id){
        if (id >= used){
            throw new IndexOutOfBoundsException();
        }else{
            for (ArrayList<Item> item : items) {
                if (id < item.size()) {
                    return item.get(id);
                } else {
                    id -= item.size();
                }
            }
        }
        return null;
    }

    public Item getItem(String type, int id){
        if (id >= used){
            throw new IndexOutOfBoundsException();
        }else{
            return items[itemId.get(type)].get(id);
        }
    }


    public void printItems(){
        System.out.printf("%s%s's bag. Volume: %d/%d%s\n",
                MyFont.ANSI_BACKGROUNDWHITE, hero.toString(), used, volume, MyFont.ANSI_RESET);
        int start = 0;
        if (items[itemId.get("Armor")].size() > 0){
            showArmor(start);
        }
        start += items[itemId.get("Armor")].size();
        if (items[itemId.get("Potion")].size() > 0) {
            showPotion(start);
        }
        start += items[itemId.get("Potion")].size();
        if (items[itemId.get("Spell")].size() > 0) {
            showSpell(start);
        }

        start += items[itemId.get("Spell")].size();
        if (items[itemId.get("Weapon")].size() > 0) {
            showWeapon(start);
        }
    }

    public int getItemNum(String type){
        return items[itemId.get(type)].size();
    }

    public boolean hasItem(String type){
        return items[itemId.get(type)].size() > 0;
    }

    public void showSpell(int start){
        String it = "Spell";
        StringBuilder s = new StringBuilder();
        StringBuilder sLine = new StringBuilder();
        int[] tableSize = new int[]{3, 20,25, 5,3,6, 6};  // Name/LightningSpell/cost/required level/damage/mana cost
        s.append("|%") ;
        sLine.append("+");
        for (int j : tableSize) {
            s.append(j).append("s|%");
            sLine.append("-".repeat(j)).append("+");
        }
        s.deleteCharAt(s.length()-1);
        s.append("\n");
        System.out.println(MyFont.ANSI_BOLD+MyFont.ANSI_BACKGROUNDWHITE+" ".repeat((sLine.length()-it.length())/2)+
                it+ " ".repeat(sLine.length()-it.length() - (sLine.length()-it.length())/2) + MyFont.ANSI_RESET);
        System.out.println(sLine.toString());
//        Name/cost/required level/attribute increase/attribute affected
        System.out.printf(s.toString(),"ID","Item Name", "Spell Type","Cost","Lv","damage","mana");
        System.out.println(sLine);
        for (int i = 0; i < items[itemId.get(it)].size(); i++) {
            Spell target = (Spell) items[itemId.get(it)].get(i);
            System.out.printf(s.toString(),(start+i), target.getName(), target.getType(), target.getCost(),
                    target.getLevel(), target.getDamage(),target.getManaCost());
        }
        System.out.println(sLine.toString());
    }

    public void showPotion(int start){
        String it = "Potion";
        StringBuilder s = new StringBuilder();
        StringBuilder sLine = new StringBuilder();
        int[] tableSize = new int[]{3, 20, 5,3,6, 45}; // id/name level hp mana weapon armor
        s.append("|%") ;
        sLine.append("+");
        for (int j : tableSize) {
            s.append(j).append("s|%");
            sLine.append("-".repeat(j)).append("+");
        }
        s.deleteCharAt(s.length()-1);
        s.append("\n");
        System.out.println(MyFont.ANSI_BOLD+MyFont.ANSI_BACKGROUNDWHITE+" ".repeat((sLine.length()-it.length())/2)+
                it+ " ".repeat(sLine.length()-it.length() - (sLine.length()-it.length())/2) + MyFont.ANSI_RESET);
        System.out.println(sLine.toString());
//        Name/cost/required level/attribute increase/attribute affected
        System.out.printf(s.toString(),"ID","Item Name", "Cost","Lv","+++","Attribute");
        System.out.println(sLine);
        for (int i = 0; i < items[itemId.get(it)].size(); i++) {
            Potion target = (Potion) items[itemId.get(it)].get(i);
            System.out.printf(s.toString(),(start+i), target.getName(), target.getCost(),
                    target.getLevel(), target.getIncrease(),target.getEffect());
        }
        System.out.println(sLine.toString());
    }

    public void showArmor(int start){
        String it = "Armor";
        StringBuilder s = new StringBuilder();
        StringBuilder sLine = new StringBuilder();
        int[] tableSize = new int[]{3, 20, 5,3, 10};  // id/Name/cost/level/damage reduction
        s.append("|%") ;
        sLine.append("+");
        for (int j : tableSize) {
            s.append(j).append("s|%");
            sLine.append("-".repeat(j)).append("+");
        }
        s.deleteCharAt(s.length()-1);
        s.append("\n");
        System.out.println(MyFont.ANSI_BOLD+MyFont.ANSI_BACKGROUNDWHITE+" ".repeat((sLine.length()-it.length())/2)+
                it+ " ".repeat(sLine.length()-it.length() - (sLine.length()-it.length())/2) + MyFont.ANSI_RESET);
        System.out.println(sLine.toString());
//        Name/cost/required level/attribute increase/attribute affected
        System.out.printf(s.toString(),"ID","Item Name", "Cost","Lv","damage--");
        System.out.println(sLine);
        for (int i = 0; i < items[itemId.get(it)].size(); i++) {
            Armor target = (Armor) items[itemId.get(it)].get(i);
            System.out.printf(s.toString(), (start+i),target.getName(), target.getCost(),
                    target.getLevel(), target.getDamageReduction());
        }
        System.out.println(sLine.toString());
    }

    public void showWeapon(int start){
        String it = "Weapon";
        StringBuilder s = new StringBuilder();
        StringBuilder sLine = new StringBuilder();
        int[] tableSize = new int[]{3, 20, 5,3, 6, 6};  // Name/cost/level/damage/hands
        s.append("|%") ;
        sLine.append("+");
        for (int j : tableSize) {
            s.append(j).append("s|%");
            sLine.append("-".repeat(j)).append("+");
        }
        s.deleteCharAt(s.length()-1);
        s.append("\n");
        System.out.println(MyFont.ANSI_BOLD+MyFont.ANSI_BACKGROUNDWHITE+" ".repeat((sLine.length()-it.length())/2)+
                it+ " ".repeat(sLine.length()-it.length() - (sLine.length()-it.length())/2) + MyFont.ANSI_RESET);
        System.out.println(sLine.toString());
//        Name/cost/required level/attribute increase/attribute affected
        System.out.printf(s.toString(),"ID","Item Name", "Cost","Lv","Damage", " Hand");
        System.out.println(sLine);
        for (int i = 0; i < items[itemId.get(it)].size(); i++) {
            Weapon target = (Weapon) items[itemId.get(it)].get(i);
            System.out.printf(s.toString(), (start+i),target.getName(), target.getCost(),
                    target.getLevel(), target.getDamage(), target.getHand());
        }
        System.out.println(sLine.toString());

    }

    public boolean canUseSpell(){
        for (Item spell:items[itemId.get("Spell")]) {
            if (((Spell)spell).getManaCost() <= hero.getMana()){
                return true;
            }
        }
        return false;
    }

}
