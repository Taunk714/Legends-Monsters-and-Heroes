// Abstract Character class.
public abstract class Character {
    private int level = 1;
    private String name;
    private int HP;

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public int getHP() {
        return HP;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

}
