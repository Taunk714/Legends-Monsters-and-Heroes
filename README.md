Legends: Monsters and Heroes
# List of Class

* **StartGame**: main class.<br><br>


* **Game**: Abstract Game interface.
* **Legends implements Game**: Legends. It's a singleton. I make it a singleton because it's really helpful to implement 
  show map and inrfomation at any moment. And since it's not a cyber game, it will only have one Legend class.<br><br>


* **Grid**: Grid class. The playing grid. Consist of different cells.<br><br>
  

* **Cell**: Cell interface. form the grid.
* **InaccessibleCell implements Cell**: Inaccessible cell. The team can't go here, will reject and go back to the previous cell.
* **Common implements Cell**: Common cell. Can change weapon or armor freely if the team doesn't meet monsters.
* **MarketCell implements Cell**: Market cell. Can buy or sell items.<br><br>


* **Battle**: Battle class. Heroes and monsters attack one by one until all members of one group are dead.<br><br>


* **Item**: Abstract Item class.
* **Armor extends Item**: Armor class. Can be worn.
* **Weapon extends Item**: Weapon class. Can be hold in the hand.
* **Spell extends Item**: Spell class. Can only use once, but the effect will last forever.
* **Potion extends Item**: Weapon class. Can only use once, but the skill bonus will last forever.<br><br>


* **Character**: Abstract character class.
* **Hero extends Character**: Hero class. All the heroes are created by HeroCreator
* **Monster extends Character**: Monster class. All the monsters are created by MonsterCreator.
* **HeroCreator extends Character**: HeroCreator class. It's a singleton. Every hero is unique, can only be instantiated once.
  One game only have one HeroCreator, once the hero was chosen, he can't be chosen anymore.
* **MonsterCreator extends Character**: MonsterCreator class. Create the monster by level.
* **Team**: Team class.<br><br>


* **Skill**: Skill class. Store the favor information.
* **Bag**: Bag class. Hero stores items in the Bag.<br><br>


* **MyFont**: static class. Store some style information like bold, color or background color.<br><br>

# how to run
play StartGame.main()

# Via command line
- javac StartGame.java
- java StartGame

## PS.

[comment]: <> (I use strikeout at many places, but it's not supported on some terminal. It's supported on ItelliJ.)

It is easy to add new types of hero or monster, we only need to add info in the two Creator class. 
It's also easy to add new types of items. Legends is singleton, so it's easy to show info at any moment without store Legends instance in every object.
HeroCreator is also singleton so that even in the future we want to add function like create a new team or a new team member, every hero is unit.
