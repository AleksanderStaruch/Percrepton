import java.util.Arrays;

public class Point {
    private double[] tab;
    private String name;

    public Point(double[] tab, String name) {
        this.tab = tab;
        this.name = name;
    }

    public double[] getTab() {
        return tab;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "Point" +Arrays.toString(tab) + ", name=" + name;
    }


}
