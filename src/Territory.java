//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import com.redlit.engine.Renderer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Territory {
    int x;
    int y;
    String name;
    HashMap<Resources, Integer> resources = new HashMap();
    Type type;
    List<Integer> troopList = new ArrayList();
    List<Building> buildingList = new ArrayList();
    private boolean selected = false;

    public Territory(String name, int x, int y, Type type, Resources resources1, Resources resources2, int res1Prd, int res2Prd) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.type = type;
        this.resources.put(resources1, res1Prd);
        this.resources.put(resources2, res2Prd);
    }

    public void render(Renderer renderer, int offX, int offY) {
        renderer.drawCircle(12, -8912896, this.x + offX, this.y + offY);
        renderer.drawCircle(9, -1703422, this.x + 3 + offX, this.y + 3 + offY);
    }

    public void setSelected(boolean value) {
        this.selected = value;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public String getName() {
        return this.name;
    }

    public Type getType() {
        return this.type;
    }

    public Resources getMainResource() {
        Resources[] keys = (Resources[])this.resources.keySet().toArray(new Resources[0]);
        return keys[0];
    }

    public Resources getSecondaryResource() {
        Resources[] keys = (Resources[])this.resources.keySet().toArray(new Resources[0]);
        return keys[1];
    }

    public int getMainProduction() {
        return (Integer)this.resources.get(this.getMainResource());
    }

    public int getSecondaryProduction() {
        return (Integer)this.resources.get(this.getSecondaryResource());
    }

    public HashMap<Resources, Integer> getResources() {
        return this.resources;
    }

    static enum Type {
        Plain,
        Forest,
        Coastal;

        private Type() {
        }
    }

    static enum Resources {
        Metal,
        Gold,
        Oil,
        Food;

        private Resources() {
        }
    }
}
