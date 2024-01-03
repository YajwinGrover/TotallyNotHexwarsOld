//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import com.redlit.engine.Renderer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Player {
    HashMap<Territory.Resources, Integer> resources = new HashMap();
    ArrayList<Territory> owned = new ArrayList();
    boolean[] troopsUnlocked = new boolean[]{true, false, false};
    boolean turn = false;
    int color;
    String name;

    public Player(String name, int color) {
        this.resources.put(Territory.Resources.Food, 100);
        this.resources.put(Territory.Resources.Gold, 100);
        this.resources.put(Territory.Resources.Metal, 100);
        this.resources.put(Territory.Resources.Oil, 100);
        this.color = color;
        this.name = name;
    }

    private void addResources() {
        Iterator var1 = this.owned.iterator();

        while(var1.hasNext()) {
            Territory t = (Territory)var1.next();
            Iterator var3 = t.getResources().keySet().iterator();

            while(var3.hasNext()) {
                Territory.Resources r = (Territory.Resources)var3.next();
                this.resources.put(r, (Integer)this.resources.get(r) + (Integer)t.getResources().get(r));
            }
        }

    }

    public void drawResourceBar(Renderer renderer) {
        if (this.turn) {
            int off = 105;
            renderer.fillRect(30, 30, 550, 50, this.color);
            renderer.drawText("Food: " + this.resources.get(Territory.Resources.Food), 35, 32, -16777216);
            renderer.drawText("Metal: " + this.resources.get(Territory.Resources.Metal), 35 + off, 32, -16777216);
            renderer.drawText("Oil: " + this.resources.get(Territory.Resources.Oil), 35 + off * 2, 32, -16777216);
            renderer.drawText("Gold: " + this.resources.get(Territory.Resources.Gold), 35 + off * 3, 32, -16777216);
            renderer.drawText("Territories : " + this.owned.size(), 35 + off * 4, 32, -16777216);
        }

    }

    public void setTurn(boolean turnVal) {
        this.turn = turnVal;
        this.addResources();
    }

    public boolean getTurn() {
        return this.turn;
    }

    public void remove(Territory.Resources r, int removeAmt) {
        this.resources.put(r, (Integer)this.resources.get(r) - removeAmt);
    }

    public boolean[] getTroopStatus() {
        return this.troopsUnlocked;
    }
}
