import java.util.Arrays;
import java.util.Iterator;

// The unit during the game.
public class Team implements Iterable<Hero>{
    private Hero[] heroes;
    private int teamSize;
    private int currSize = 0;
    private boolean dead = true;
    private int[] pos = new int[2];

    public Team(Hero[] heroes, int posRow, int posCol) {
        this.heroes = heroes;
        teamSize = heroes.length;
        currSize = teamSize;
        this.pos[0] = posRow;
        this.pos[1] = posCol;
    }

    public Team(int size, int posRow, int posCol) {
        this.heroes = new Hero[size];
        teamSize = size;
        this.pos[0] = posRow;
        this.pos[1] = posCol;
    }

    public Team(Hero[] heroes) {
        this(heroes, 0, 0);
    }

    public Team(int size) {
        this(size, 0, 0);
    }

    public Hero getHero(int i) {
        return heroes[i];
    }

    public int[] getPos() {
        return new int[]{pos[0],pos[1]};
    }

    public void setPos(int[] pos) {
        this.pos = pos;
    }

    public void setHeroes(int index, Hero hero) {
        this.heroes[index] = hero;
    }

    public int getTeamSize() {
        return teamSize;
    }

    // return whether all the heroes died.
    public boolean isDead(){
        for (int i = 0; i < heroes.length; i++) {
            if (heroes[i].getHP() > 0){
                return false;
            }
        }
        return true;
    }

    // revive if the heroes lose but still want to take the adventure.
    public void revive(){
        for (int i = 0; i < teamSize; i++) {
            assert heroes[i].getHP() <= 0;
        }

        for (int i = 0; i < teamSize; i++) {
            heroes[i].revive(true);
        }

        dead = false;
    }

    // Settlement after winning the battle. If hero is faint, he will get half the hp and mana
    public void gainAfterBattle(int exp, int money){
        for (Hero hero : heroes) {
            System.out.println(hero.toString());
            if (hero.getHP() > 0) {
                System.out.printf("%s gains %d exp and $%d\n", hero.toString(), exp, money);
                hero.gainAndLevelUp(exp, money);
            } else {
                System.out.printf("%s is faint. Revive!\n", hero.toString());
                hero.revive(false);
            }
            System.out.print("\n");
        }
    }

    @Override
    public String toString() {
        return MyFont.ANSI_CYAN + "◢◣" + MyFont.ANSI_RESET;
    }

    @Override
    public Iterator<Hero> iterator() {
        return Arrays.stream(heroes).iterator();
    }
}
