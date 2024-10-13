package code.Tools;

import code.Tools.Colors;

import java.util.ArrayDeque;
import java.util.Objects;

public class Bottle {
    private final int capacity;
    private ArrayDeque<Colors> layers;

    public Bottle(int capacity, ArrayDeque<Colors> initialLayers) {
        this.capacity = capacity;
        this.layers = new ArrayDeque<>(initialLayers);
    }

    public Bottle(int capacity) {
        this.capacity = capacity;
        this.layers = new ArrayDeque<>();
    }

    public int getCurrentSize() {
        return layers.size();
    }

    public Colors getTopLayer() {
        return layers.peek();
    }

    public boolean isEmpty() {
        return layers.isEmpty();
    }

    public boolean isFull() {
        return layers.size() == capacity;
    }

    public int getRemainingSpace() {
        return capacity - layers.size();
    }

    public void pourInto(Bottle target) {

    }

    public void addLayer(Colors color) {
        if (!this.isFull()) {
            layers.push(color);
        }
    }

    public Colors removeLayer() {
        return layers.pop();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Bottle))
            return false;
        Bottle bottle = (Bottle) o;
        return capacity == bottle.capacity && Objects.equals(layers, bottle.layers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(capacity, layers);
    }

    @Override
    public String toString() {
        return "Bottle{" +
                "capacity=" + capacity +
                ", layers=" + layers +
                '}';
    }

    public static void main(String[] args) {

        ArrayDeque<Colors> bottle1Layers = new ArrayDeque<>();
        bottle1Layers.push(Colors.RED);
        bottle1Layers.push(Colors.BLUE);

        ArrayDeque<Colors> bottle2Layers = new ArrayDeque<>();
        bottle2Layers.push(Colors.BLUE);

        Bottle bottle1 = new Bottle(4, bottle1Layers);
        Bottle bottle2 = new Bottle(4, bottle2Layers);
        Bottle bottle3 = new Bottle(4);

        System.out.println("Bottle 1: " + bottle1);
        System.out.println("Bottle 2: " + bottle2);
        System.out.println("Bottle 3: " + bottle3);

    }
}
