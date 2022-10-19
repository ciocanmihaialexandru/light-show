package entities;

public class LightBulb {

    private Coordinate coordinate;
    private int state; // 1 - on, 0 - off
    private int brightness;

    public LightBulb(Coordinate coordinate, int state, int brightness) {
        this.coordinate = coordinate;
        this.state = state;
        this.brightness = brightness;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getBrightness() {
        return brightness;
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    public boolean isInRange(Coordinate start, Coordinate end) {
        return (this.getCoordinate().getRow() >= start.getRow() &&
                    this.getCoordinate().getColumn() >= start.getColumn()) &&
                (this.getCoordinate().getRow() <= end.getRow() &&
                    this.getCoordinate().getColumn() <= end.getColumn());
    }

    public void applyInstruction(Instruction instruction) {
        switch (instruction.getCommand()) {
            case "turn on":
                this.setState(1);
                this.setBrightness(this.getBrightness() + 1);
                break;
            case "turn off":
                this.setState(0);
                this.setBrightness(Math.max(this.getBrightness() - 1, 0));
                break;
            case "toggle":
                this.setState(1 - this.getState());
                this.setBrightness(this.getBrightness() + 2);
                break;
            default:
                break;
        }
    }
}
