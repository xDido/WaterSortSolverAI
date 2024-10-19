package code;

import java.util.ArrayList;


public class WaterSortSearch extends GenericSearch {

    public WaterSortSearch(String initialState, ArrayList<String> setOfActions) {
        super(initialState);
    }

    public static String solve(String initialState, String strategy, boolean visualize) {
        String solution = "NOSOLUTION";
        GenericSearch search = new GenericSearch(initialState);
        solution = search.generalSearch(initialState, strategy);
        return solution;
    }


}