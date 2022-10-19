package entities;

public class Instruction {

    private String command;
    private Coordinate start;
    private Coordinate end;

    public Instruction(String command, Coordinate start, Coordinate end) {
        this.command = command;
        this.start = start;
        this.end = end;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Coordinate getStart() {
        return start;
    }

    public void setStart(Coordinate start) {
        this.start = start;
    }

    public Coordinate getEnd() {
        return end;
    }

    public void setEnd(Coordinate end) {
        this.end = end;
    }
}
