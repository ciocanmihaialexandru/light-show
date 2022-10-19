package entities;

public class Grid {

    private int size;
    private LightBulb[][] panel;

    public Grid(int size) {
        this.size = size;
        this.panel = new LightBulb[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.panel[i][j] = new LightBulb(new Coordinate(i, j), 0, 0);
            }
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public LightBulb[][] getPanel() {
        return panel;
    }

    public void setPanel(LightBulb[][] panel) {
        this.panel = panel;
    }
}
