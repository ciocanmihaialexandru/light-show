package entities;

public class LightShowResult {
    private int lights;
    private int brightness;

    public LightShowResult(int lights, int brightness) {
        this.lights = lights;
        this.brightness = brightness;
    }

    public int getLights() {
        return lights;
    }

    public void setLights(int lights) {
        this.lights = lights;
    }

    public int getBrightness() {
        return brightness;
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }
}
