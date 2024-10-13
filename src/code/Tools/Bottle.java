package code.Tools;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Objects;

public class Bottle {
    public int getCapacity() {
        return capacity;
    }

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

    public ArrayDeque<Colors> getLayers() {
        return layers;
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


    public void addLayer(Colors color) {
        if (!this.isFull()) {
            layers.add(color);
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
    public static void printBottles(ArrayList<Bottle> bottles) {

        int maxHeight = bottles.stream().mapToInt(Bottle::getCurrentSize).max().orElse(0);

        String emptyLayer = " [      ] ";

        for (int layerIndex = maxHeight - 1; layerIndex >= 0; layerIndex--) {
            for (Bottle bottle : bottles) {
                ArrayDeque<Colors> layers = bottle.getLayers();
                ArrayList<Colors> layerList = new ArrayList<>(layers);

                if (layerIndex < layerList.size()) {
                    Colors color = layerList.get(layerList.size() - 1 - layerIndex);
                    System.out.print(getLayerString(color) + " ");
                } else {
                    System.out.print(emptyLayer + " ");
                }
            }
            System.out.println();
        }
        System.out.println("--------".repeat(bottles.size()));
    }

    private static String getLayerString(Colors color) {
        switch (color) {
            case RED:
                return " [  RED ] ";
            case GREEN:
                return " [GREEN ] ";
            case BLUE:
                return " [ BLUE ] ";
            case YELLOW:
                return " [YELLOW] ";
            case ORANGE:
                return " [ORANGE] ";
            default:
                return " [      ] ";
        }
    }


}
