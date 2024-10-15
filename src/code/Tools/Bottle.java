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
    public Colors getTopLayer() {
        return layers.peek();
    }
    public boolean isEmpty() {
        return layers.isEmpty();
    }
    public boolean isFull() {
        return layers.size() == capacity;
    }
    public boolean isHomogeneous() {
       return layers.stream().distinct().count() <= 1;
    }
    public void addLayer(Colors color) {
        if (!this.isFull()) {
            layers.addLast(color);
        }
    }
    public Colors removeLayer() {
        return layers.pop();
    }
    @Override
    public String toString() {
        return "Bottle{" +
                "capacity=" + capacity +
                ", layers=" + layers +
                '}';
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
    public Bottle deepCopy() {
        Bottle copy = new Bottle(this.capacity);
        copy.layers = new ArrayDeque<>(this.layers);
        return copy;
    }
    @Override
    public int hashCode() {
        return Objects.hash(capacity, layers);
    }

    public int getCurrentSize() {
        return layers.size();
    }


    public int getRemainingSpace() {
        return capacity - layers.size();
    }

//    public static void printBottles(ArrayList<Bottle> bottles) {
//        int maxCapacity = bottles.stream().mapToInt(Bottle::getCapacity).max().orElse(0);
//
//        // Print the bottles from bottom to top (reversed to mimic stack)
//        for (int layer = maxCapacity-1; layer >=0; layer--) {
//            for (Bottle bottle : bottles) {
//                ArrayDeque<Colors> bottleLayers = bottle.getLayers();
//                int bottleSize = bottleLayers.size();
//                if (layer < bottleSize) {
//                    Colors[] layersArray = bottleLayers.toArray(new Colors[0]);
//                    System.out.print(getLayerString(layersArray[layer]));
//                } else {
//                    System.out.print(" [      ] ");
//                }
//            }
//            System.out.println();
//        }
//
//        // Print the bottle numbers
//        for (int i = 0; i < bottles.size(); i++) {
//            System.out.printf("   [%d]    ", i);
//        }
//        System.out.println("\n");
//    }
//
//
//    private static String getLayerString(Colors color) {
//        switch (color) {
//            case RED:
//                return " [  RED ] ";
//            case GREEN:
//                return " [GREEN ] ";
//            case BLUE:
//                return " [ BLUE ] ";
//            case YELLOW:
//                return " [YELLOW] ";
//            case ORANGE:
//                return " [ORANGE] ";
//            default:
//                return " [      ] ";
//        }
//    }


}
