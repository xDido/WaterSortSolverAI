package code;

import java.util.ArrayDeque;
import java.util.ArrayList;

import code.Tools.Bottle;

// Abstract class for a generic search problem
public abstract class GenericSearch {

    protected ArrayList<Bottle> initialState;

    protected ArrayList<String> setOfActions;

    protected ArrayList<ArrayList<Bottle>> stateSpace;

    protected ArrayList<ArrayDeque<Object>> goalTest;

 //   protected abstract int pathCost(ArrayDeque<Object> state, String action);

    public GenericSearch(ArrayList<Bottle> initialState, ArrayList<String> setOfActions,
            ArrayList<ArrayDeque<Object>> goalTest) {
        this.initialState = initialState;
        this.setOfActions = setOfActions;
        this.goalTest = goalTest;
        this.stateSpace = new ArrayList<>();
        this.stateSpace.add(initialState);
    }
}
