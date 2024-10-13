package code;

import java.util.ArrayList;

import code.Tools.Bottle;

// Abstract class for a generic search problem
public abstract class GenericSearch {

    protected ArrayList<Bottle> initialState;

    protected ArrayList<String> Operators;

    protected ArrayList<ArrayList<Bottle>> stateSpace;

    abstract boolean isGoal(ArrayList<Bottle> state);
    abstract int pathCost(String setOfActions);

    public GenericSearch(ArrayList<Bottle> initialState, ArrayList<String> Operators) {
        this.initialState = initialState;
        this.Operators = Operators;
        this.stateSpace = new ArrayList<>();
        this.stateSpace.add(initialState);
    }


}
