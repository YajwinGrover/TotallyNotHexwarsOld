//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//



public class Barracks extends Building {
    int productionTime;

    public Barracks() {
        this.productionTime = 6 - this.level;
        this.hitpoints = 100;
        this.level = 1;
    }

    public void levelUp(Player p) {
        ++this.level;
        p.remove(Territory.Resources.Gold, 10);
        p.remove(Territory.Resources.Metal, 50);
        p.remove(Territory.Resources.Food, 30);
    }
}
