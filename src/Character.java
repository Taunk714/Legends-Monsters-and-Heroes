// Abstract game.Character class.
public abstract class Character implements Tablefiable {
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
        this.HP = Math.max(HP, 0);
    }

    public abstract void revive(boolean b);

    public abstract void gainAndLevelUp(int exp, int money);
}
