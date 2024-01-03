//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import com.redlit.engine.GameContainer;
import com.redlit.engine.GameObject;
import com.redlit.engine.Renderer;
import com.redlit.engine.gfx.Image;

public class Map extends GameObject {
    Image mapImage;
    int dx = 0;
    int dy = 0;

    public Map(Image image) {
        this.mapImage = image;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    public void update(GameContainer gameContainer, float deltaTime) {
        int mouseX = 0;
        int mouseY = 0;
        if (gameContainer.getInput().isButton(1)) {
            mouseX = gameContainer.getInput().getMouseX();
            mouseY = gameContainer.getInput().getMouseY();
            if (this.dx == 0 && this.dy == 0) {
                this.dx = this.posX - mouseX;
                this.dy = this.posY - mouseY;
            }

            this.posX = mouseX + this.dx;
            this.posY = mouseY + this.dy;
            if (this.posX > 0) {
                this.posX = 0;
            }

            if (this.posY > 0) {
                this.posY = 0;
            }

            if (this.posX < -this.width + 888) {
                this.posX = -this.width + 888;
            }

            if (this.posY < -this.height + 500) {
                this.posY = -this.height + 500;
            }
        }

        if (gameContainer.getInput().isButtonUp(1)) {
            this.dx = 0;
            this.dy = 0;
        }

    }

    public void render(GameContainer gameContainer, Renderer renderer) {
        renderer.drawImage(this.mapImage, this.posX, this.posY);
    }
}
