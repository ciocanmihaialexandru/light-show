import entities.Grid;
import entities.LightShow;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        LightShow show = new LightShow(new Grid(1000), 0, 0);
        show.setInstructions(new File("test.txt"));
        show.run();

        System.out.println("Number of lights: " + show.getTotalLights());
        System.out.println("Brightness: " + show.getTotalBrightness());
    }
}
