package code;

import java.util.Stack;
import java.util.EmptyStackException;

public class Bottle {

    private final int capacity;
    private Stack<Color> layers;

    public Bottle(int capacity) {
        this.capacity = capacity;
        this.layers = new Stack<>();
    }


    public int getCapacity() {
        return capacity;
    }

    public Stack<Color> getLayers() {
        return layers;
    }

    public Color getTopLayer() {
        if (!layers.isEmpty()) {
            return layers.peek();
        }
        return null;
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


    public void addLayer(Color color) {
        if (!isFull()) {
            layers.push(color);
        }
    }

    public Color removeLayer()  {
        if (!layers.isEmpty()) {
            return layers.pop();
        }
        return null;
    }

    public Bottle deepCopy() {
        Bottle copy = new Bottle(this.capacity);
        copy.layers = (Stack<Color>) this.layers.clone();
        return copy;
    }
}
