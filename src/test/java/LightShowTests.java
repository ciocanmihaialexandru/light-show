import entities.Coordinate;
import entities.Grid;
import entities.Instruction;
import entities.LightShow;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LightShowTests {

    LightShow show;

    @BeforeEach
    void setUp() {
        show = new LightShow(new Grid(1000), 0, 0);
    }

    @Test
    void testFromInput() {
        List<Instruction> instructions = new ArrayList<>();
        instructions.add(new Instruction("turn on", new Coordinate(0, 0), new Coordinate(999, 999)));
        instructions.add(new Instruction("turn off", new Coordinate(499, 499), new Coordinate(500, 500)));
        instructions.add(new Instruction("toggle", new Coordinate(0, 499), new Coordinate(999, 500)));

        show.setInstructions(instructions);
        show.run();

        Assertions.assertEquals(998004, show.getTotalLights());
        Assertions.assertEquals(1003996, show.getTotalBrightness());
    }

    @Test
    void testFromFile1() {
        Assertions.assertDoesNotThrow(() -> show.setInstructions(new File("testData/test1.txt")));
        show.run();

        Assertions.assertEquals(998004, show.getTotalLights());
        Assertions.assertEquals(1003996, show.getTotalBrightness());
    }

    @Test
    void testFromFile2() {
        Assertions.assertDoesNotThrow(() -> show.setInstructions(new File("testData/test2.txt")));
        show.run();

        Assertions.assertEquals(385705, show.getTotalLights());
        Assertions.assertEquals(1716513, show.getTotalBrightness());
    }
}
