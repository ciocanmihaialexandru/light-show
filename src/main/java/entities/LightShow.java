package entities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LightShow {

    private Grid grid;
    private List<Instruction> instructions;
    private int totalLights;
    private int totalBrightness;

    public LightShow(Grid grid, int lights, int brightness) {
        this.totalLights = lights;
        this.totalBrightness = brightness;
        this.grid = grid;
    }

    public int getTotalLights() {
        return totalLights;
    }

    public void setTotalLights(int totalLights) {
        this.totalLights = totalLights;
    }

    public int getTotalBrightness() {
        return totalBrightness;
    }

    public void setTotalBrightness(int totalBrightness) {
        this.totalBrightness = totalBrightness;
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<Instruction> instructions) {
        this.instructions = instructions;
    }

    public void setInstructions(File file) throws IOException {
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

        this.setInstructions(instructions);
    }

    public void run() {
        for (int i = 0; i < this.grid.getSize(); i++) {
            for (int j = 0; j < this.grid.getSize(); j++) {
                LightBulb lightBulb = this.grid.getPanel()[i][j];
                for (Instruction instruction : this.instructions) {
                    if (lightBulb.isInRange(instruction.getStart(), instruction.getEnd())) {
                        lightBulb.applyInstruction(instruction);
                    }
                }
                this.setTotalLights(this.getTotalLights() + lightBulb.getState());
                this.setTotalBrightness(this.getTotalBrightness() + lightBulb.getBrightness());
            }
        }
    }
}
