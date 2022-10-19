import entities.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LightShow {

    public static List<Instruction> loadInstructions(File file) throws IOException {

        List<Instruction> instructions = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;

        while ((st = br.readLine()) != null) {
            String[] parts = st.split(" ");
            if (st.substring(0, 7).equalsIgnoreCase("turn on")) {
                instructions.add(new Instruction("turn on",
                        new Coordinate(Integer.parseInt(parts[2].split(",")[0]), Integer.parseInt(parts[2].split(",")[1])),
                        new Coordinate(Integer.parseInt(parts[4].split(",")[0]), Integer.parseInt(parts[4].split(",")[1]))));
            } else if (st.substring(0, 8).equalsIgnoreCase("turn off")) {
                instructions.add(new Instruction("turn off",
                        new Coordinate(Integer.parseInt(parts[2].split(",")[0]), Integer.parseInt(parts[2].split(",")[1])),
                        new Coordinate(Integer.parseInt(parts[4].split(",")[0]), Integer.parseInt(parts[4].split(",")[1]))));
            } else {
                instructions.add(new Instruction("toggle",
                        new Coordinate(Integer.parseInt(parts[1].split(",")[0]), Integer.parseInt(parts[1].split(",")[1])),
                        new Coordinate(Integer.parseInt(parts[3].split(",")[0]), Integer.parseInt(parts[3].split(",")[1]))));
            }
        }

        return instructions;
    }

    public static LightShowResult applyInstructions(Grid grid, List<Instruction> instructions) {
        LightShowResult result = new LightShowResult(0, 0);
        for (int i = 0; i < grid.getSize(); i++) {
            for (int j = 0; j < grid.getSize(); j++) {
                LightBulb lightBulb = grid.getPanel()[i][j];
                for (Instruction instruction : instructions) {
                    if (isInRange(lightBulb, instruction.getStart(), instruction.getEnd())) {
                        lightBulb = applyIntruction(lightBulb, instruction);
                    }
                }
                result.setLights(result.getLights() + lightBulb.getState());
                result.setBrightness(result.getBrightness() + lightBulb.getBrightness());
            }
        }

        return result;
    }

    public static LightBulb applyIntruction(LightBulb lightBulb, Instruction instruction) {
        switch (instruction.getCommand()) {
            case "turn on":
                lightBulb.setState(1);
                lightBulb.setBrightness(lightBulb.getBrightness() + 1);
                break;
            case "turn off":
                lightBulb.setState(0);
                lightBulb.setBrightness(Math.max(lightBulb.getBrightness() - 1, 0));
                break;
            case "toggle":
                lightBulb.setState(1 - lightBulb.getState());
                lightBulb.setBrightness(lightBulb.getBrightness() + 2);
                break;
            default:
                break;
        }
        return lightBulb;
    }

    public static boolean isInRange(LightBulb lightBulb, Coordinate start, Coordinate end) {
        return (lightBulb.getCoordinate().getRow() >= start.getRow() &&
                        lightBulb.getCoordinate().getColumn() >= start.getColumn()) &&
                (lightBulb.getCoordinate().getRow() <= end.getRow() &&
                        lightBulb.getCoordinate().getColumn() <= end.getColumn());
    }

    public static void main(String[] args) throws IOException {

        Grid grid = new Grid(1000);
        LightShowResult result;

        List<Instruction> instructions = loadInstructions(new File("test.txt"));
        result = applyInstructions(grid, instructions);
        System.out.println("Number of lights: " + result.getLights());
        System.out.println("Brightness: " + result.getBrightness());
    }
}
