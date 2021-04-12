// Spell class. IceSpell, FireSpell and LightningSpell are all in this class.
public class Spell extends Item {
    private int damage;
    private int manaCost;
    private Monster target;
    public Spell(String[] info) {
        super(info[0],info[1],Integer.parseInt(info[2]), Integer.parseInt(info[3]));
        damage = Integer.parseInt(info[4]);
        manaCost = Integer.parseInt(info[5]);
    }


    @Override
    public void use() {
        int finalDamage = getHero().getDexterity()/10000* damage + damage;
        target.hurt(finalDamage);
        target.effect(getType());
        target = null;
    }

    public void setTarget(Monster target) {
        this.target = target;
    }

    public int getDamage() {
        return damage;
    }

    public int getManaCost() {
        return manaCost;
    }
}
