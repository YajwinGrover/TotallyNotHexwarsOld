//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;

public class ReadTerritoryFile {
    InputStream file;

    public ReadTerritoryFile(InputStream file) {
        this.file = file;
    }

    public Territory[] getTerritoriesFromFile() throws FileNotFoundException {
        Territory[] territories = new Territory[30];
        Scanner scanner = new Scanner(this.file);
        Random random = new Random();
        scanner.useDelimiter(",");
        int fieldCounter = 0;
        int idx = 0;
        String state = "";
        int posX = 0;
        int posY = 0;
        Territory.Type type = null;
        Territory.Resources resources1 = null;
        Territory.Resources resources2 = null;
        int res1Prod = 0;

        while(scanner.hasNext()) {
            switch (fieldCounter) {
                case 0:
                    state = scanner.next();
                    ++fieldCounter;
                    break;
                case 1:
                    posX = Integer.parseInt(scanner.next());
                    ++fieldCounter;
                    break;
                case 2:
                    posY = Integer.parseInt(scanner.next());
                    ++fieldCounter;
                    break;
                case 3:
                    type = Territory.Type.valueOf(scanner.next());
                    ++fieldCounter;
                    break;
                case 4:
                    resources1 = Territory.Resources.valueOf(scanner.next());
                    ++fieldCounter;
                    break;
                case 5:
                    resources2 = Territory.Resources.valueOf(scanner.next());
                    ++fieldCounter;
                    break;
                case 6:
                    scanner.next();
                    res1Prod = (random.nextInt(10) + 1) * 10;
                    ++fieldCounter;
                    break;
                case 7:
                    scanner.next();
                    int res2Prd = (random.nextInt(10) + 1) * 10;
                    territories[idx] = new Territory(state, posX, posY, type, resources1, resources2, res1Prod, res2Prd);
                    fieldCounter = 0;
                    ++idx;
            }
        }

        return territories;
    }
}
