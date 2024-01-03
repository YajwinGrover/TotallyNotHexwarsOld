//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import com.redlit.engine.AbstractGame;
import com.redlit.engine.GameContainer;
import com.redlit.engine.Renderer;
import com.redlit.engine.gfx.Image;
import java.io.FileNotFoundException;

public class GameManager extends AbstractGame {
    Map map;
    Territory[] territories;
    Territory territorySelected = null;
    boolean producing = false;
    Image barracks = new Image("/res/images/barracks.png");
    Image lockedIcon = new Image("/res/images/lockedIcon.png");
    Image arrow = new Image("/res/images/arrow.png");
    int globalOffX;
    int globalOffY;
    int screenWidth = 977;
    int screenHeight = 550;
    Player red = new Player("Red", -32897);
    Player blue = new Player("Blue", -5383962);
    Player currPlayer;

    public GameManager() {
        this.currPlayer = this.red;
        this.map = new Map(new Image("/res/images/map.png"));
        ReadTerritoryFile rtf = new ReadTerritoryFile(this.getClass().getResourceAsStream("TerritoryCoords.txt"));

        try {
            this.territories = rtf.getTerritoriesFromFile();
        } catch (FileNotFoundException var3) {
            var3.printStackTrace();
        }

        this.red.setTurn(true);
    }

    public void setup(GameContainer gameContainer) {
    }

    public void update(GameContainer gameContainer, float deltaTime) {
        this.map.update(gameContainer, deltaTime);
        this.globalOffX = this.map.getPosX();
        this.globalOffY = this.map.getPosY();
        int mouseX;
        int mouseY;
        if (gameContainer.getInput().isButtonDown(1)) {
            mouseX = gameContainer.getInput().getMouseX() - this.globalOffX;
            mouseY = gameContainer.getInput().getMouseY() - this.globalOffY;
            if (this.territorySelected == null) {
                Territory[] var5 = this.territories;
                int var6 = var5.length;

                for(int var7 = 0; var7 < var6; ++var7) {
                    Territory t = var5[var7];
                    int x = t.getX();
                    int y = t.getY();
                    double dist = Math.sqrt((double)((mouseX - x) * (mouseX - x) + (mouseY - y) * (mouseY - y)));
                    if (dist < 50.0) {
                        this.territorySelected = t;
                        t.setSelected(true);
                    }
                }
            } else if (this.territorySelected != null) {
                mouseX = gameContainer.getInput().getMouseX();
                mouseY = gameContainer.getInput().getMouseY();
                if (mouseX > 605 && mouseX < 615 && mouseY > 35 && mouseY < 45) {
                    this.territorySelected.setSelected(false);
                    this.territorySelected = null;
                    this.producing = false;
                }
            }
        }

        if (gameContainer.getInput().isButtonDown(1)) {
            mouseX = gameContainer.getInput().getMouseX();
            mouseY = gameContainer.getInput().getMouseY();
            if (mouseX > 10 && mouseX < 160 && mouseY > 500 && mouseY < 540) {
                this.red.setTurn(!this.red.getTurn());
                this.blue.setTurn(!this.blue.getTurn());
                if (!this.blue.getTurn()) {
                    this.currPlayer = this.red;
                } else {
                    this.currPlayer = this.blue;
                }
            }
        }

        if (gameContainer.getInput().isButtonDown(1) && this.territorySelected != null) {
            mouseX = gameContainer.getInput().getMouseX();
            mouseY = gameContainer.getInput().getMouseY();
            if (mouseX > 720 && mouseX < 795 && mouseY > 180 && mouseY < 230) {
                this.producing = true;
            }
        }

        if (gameContainer.getInput().isButtonDown(1) && this.producing) {
            mouseX = gameContainer.getInput().getMouseX();
            mouseY = gameContainer.getInput().getMouseY();
            if (mouseX > 55 && mouseX < 65 && mouseY > 405 && mouseY < 415) {
                this.producing = false;
            }

            if (mouseX > 60 && mouseX < 110 && this.currPlayer.getTroopStatus()[0] && mouseY > 425 && mouseY < 475) {
                this.territorySelected.troopList.add(Troop.SOLDIER);
            }
        }

    }

    public void render(GameContainer gameContainer, Renderer renderer) {
        this.map.render(gameContainer, renderer);
        Territory[] var3 = this.territories;
        int var4 = var3.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            Territory t = var3[var5];
            if (t != null && t.getY() + this.globalOffY < this.screenHeight && t.getX() + this.globalOffX < this.screenWidth) {
                t.render(renderer, this.globalOffX, this.globalOffY);
            }
        }

        if (this.territorySelected != null) {
            this.infoPanel(renderer);
        }

        if (this.producing) {
            this.soldierBuildPanel(renderer);
        }

        renderer.fillRect(10, 500, 150, 40, -1509949440);
        renderer.drawText("Next Player Turn", 15, 510, -1);
        this.red.drawResourceBar(renderer);
        this.blue.drawResourceBar(renderer);
    }

    public void reset() {
    }

    private void infoPanel(Renderer renderer) {
        String stateName = this.territorySelected.getName();
        Territory.Type type = this.territorySelected.getType();
        Territory.Resources mainRes = this.territorySelected.getMainResource();
        Territory.Resources secondRes = this.territorySelected.getSecondaryResource();
        int mainProduction = this.territorySelected.getMainProduction();
        int secondaryProduction = this.territorySelected.getSecondaryProduction();
        int centerPos = stateName.length() / 2 * 15;
        renderer.drawRect(599, 29, 301, 501, -16777216);
        renderer.fillRect(600, 30, 300, 500, -1);
        renderer.drawText(stateName, 750 - centerPos, 50, -65536);
        renderer.drawText("Terrain: " + type, 620, 70, -16777216);
        renderer.drawText("" + mainRes + ": " + mainProduction, 620, 90, -16777216);
        renderer.drawText("" + secondRes + ": " + secondaryProduction, 620, 110, -16777216);
        renderer.drawText("Total troops: " + this.territorySelected.troopList.size(), 620, 130, -16777216);
        renderer.drawText("Total buildings: " + this.territorySelected.buildingList.size(), 620, 150, -16777216);
        renderer.drawRect(619, 179, 76, 51, -8912896);
        renderer.fillRect(620, 180, 75, 50, -1493237760);
        renderer.drawImage(this.barracks, 620, 180);
        renderer.drawRect(719, 179, 76, 51, -8912896);
        renderer.fillRect(720, 180, 75, 50, -1493237760);
        renderer.drawImage(Troop.SOLDIER_IMAGE, 720, 180);
        renderer.fillRect(605, 35, 10, 10, -65536);
    }

    private void soldierBuildPanel(Renderer renderer) {
        int[] offsets = new int[]{60, 185, 305};
        renderer.fillRect(50, 400, 500, 100, -1);
        renderer.drawRect(49, 399, 500, 100, -16777216);
        renderer.drawImage(Troop.SOLDIER_IMAGE, 60, 425);
        renderer.drawImage(new Image("/res/images/comingSoon.png"), 425, 425);

        for(int i = 0; i < this.currPlayer.getTroopStatus().length; ++i) {
            if (!this.currPlayer.getTroopStatus()[i]) {
                renderer.drawImage(this.lockedIcon, offsets[i], 425);
            }

            renderer.drawImage(this.arrow, offsets[i] + 80, 425);
        }

        renderer.fillRect(55, 405, 10, 10, -65536);
    }

    public static void main(String[] args) {
        GameContainer gameContainer = new GameContainer(new GameManager());
        gameContainer.setScale(1.0F);
        gameContainer.setHeight(550);
        gameContainer.setWidth(gameContainer.getHeight() * 16 / 9);
        gameContainer.setTitle("Totally Not HexWars");
        gameContainer.start();
    }
}
