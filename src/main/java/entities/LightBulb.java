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
}
