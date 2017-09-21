import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by haodong on 2017-09-17.
 */
public class ProblemWaterJug extends Problem {

    boolean goal_test(Object state){
        StateWaterJug jugs_state = (StateWaterJug) state;
        if(jugs_state.jugs[0] == 1 || jugs_state.jugs[1] == 1 || jugs_state.jugs[2] == 1)
            return true;
        return false;
    }
    Set<Object> getSuccessors(Object state){
        Set<Object> set = new HashSet<>();
        StateWaterJug jugs_state = (StateWaterJug) state;

        StateWaterJug successor_state;
        int cost;

        //fill 12 gallon jug
        successor_state = new StateWaterJug(jugs_state);
        cost = 12 - successor_state.jugs[0];
        successor_state.jugs[0] = 12;
        successor_state.path_cost += cost;
        if(cost != 0 && isValid(successor_state)) set.add(successor_state);

        //fill 8 gallon jug
        successor_state = new StateWaterJug(jugs_state);
        cost = 8 - successor_state.jugs[1];
        successor_state.jugs[1] = 8;
        successor_state.path_cost += cost;
        if(cost != 0 && isValid(successor_state)) set.add(successor_state);

        //fill 3 gallon jug
        successor_state = new StateWaterJug(jugs_state);
        cost = 3 - successor_state.jugs[2];
        successor_state.jugs[2] = 3;
        successor_state.path_cost += cost;
        if(cost != 0 && isValid(successor_state)) set.add(successor_state);

        //pour out 12 gallon
        successor_state = new StateWaterJug(jugs_state);
        cost = successor_state.jugs[0];
        successor_state.jugs[0] = 0;
        successor_state.path_cost += cost;
        if(cost != 0 && isValid(successor_state)) set.add(successor_state);

        //pour out 8 gallon
        successor_state = new StateWaterJug(jugs_state);
        cost = successor_state.jugs[1];
        successor_state.jugs[1] = 0;
        successor_state.path_cost += cost;
        if(cost != 0 && isValid(successor_state)) set.add(successor_state);

        //pour out 3 gallon
        successor_state = new StateWaterJug(jugs_state);
        cost = successor_state.jugs[2];
        successor_state.jugs[2] = 0;
        successor_state.path_cost += cost;
        if(cost != 0 && isValid(successor_state)) set.add(successor_state);

        //pour from 12 gallon to 8 gallon
        successor_state = new StateWaterJug(jugs_state);
        int water = successor_state.jugs[0];
        int room = 8 - successor_state.jugs[1];
        cost = room >= water ? water : room;
        successor_state.jugs[0] = successor_state.jugs[0] - cost;
        successor_state.jugs[1] = successor_state.jugs[1] + cost;
        successor_state.path_cost += cost;
        if(cost != 0 && isValid(successor_state)) set.add(successor_state);

        //pour from 12 gallon to 3 gallon
        successor_state = new StateWaterJug(jugs_state);
        water = successor_state.jugs[0];
        room = 3 - successor_state.jugs[2];
        cost = room >= water ? water : room;
        successor_state.jugs[0] = successor_state.jugs[0] - cost;
        successor_state.jugs[2] = successor_state.jugs[2] + cost;
        successor_state.path_cost += cost;
        if(cost != 0 && isValid(successor_state)) set.add(successor_state);

        //pour from 8 gallon to 3 gallon
        successor_state = new StateWaterJug(jugs_state);
        water = successor_state.jugs[1];
        room = 3 - successor_state.jugs[2];
        cost = room >= water ? water : room;
        successor_state.jugs[1] = successor_state.jugs[1] - cost;
        successor_state.jugs[2] = successor_state.jugs[2] + cost;
        successor_state.path_cost += cost;
        if(cost != 0 && isValid(successor_state)) set.add(successor_state);

        //pour from 8 gallon to 12 gallon
        successor_state = new StateWaterJug(jugs_state);
        water = successor_state.jugs[1];
        room = 12 - successor_state.jugs[0];
        cost = room >= water ? water : room;
        successor_state.jugs[1] = successor_state.jugs[1] - cost;
        successor_state.jugs[0] = successor_state.jugs[0] + cost;
        successor_state.path_cost += cost;
        if(cost != 0 && isValid(successor_state)) set.add(successor_state);

        //pour from 3 gallon to 12 gallon
        successor_state = new StateWaterJug(jugs_state);
        water = successor_state.jugs[2];
        room = 12 - successor_state.jugs[0];
        cost = room >= water ? water : room;
        successor_state.jugs[2] = successor_state.jugs[2] - cost;
        successor_state.jugs[0] = successor_state.jugs[0] + cost;
        successor_state.path_cost += cost;
        if(cost != 0 && isValid(successor_state)) set.add(successor_state);

        //pour from 3 gallon to 8 gallon
        successor_state = new StateWaterJug(jugs_state);
        water = successor_state.jugs[2];
        room = 8 - successor_state.jugs[1];
        cost = room >= water ? water : room;
        successor_state.jugs[2] = successor_state.jugs[2] - cost;
        successor_state.jugs[1] = successor_state.jugs[1] + cost;
        successor_state.path_cost += cost;
        if(cost != 0 && isValid(successor_state)) set.add(successor_state);

        return set;
    }
    double step_cost(Object fromState, Object toState){
        StateWaterJug from = (StateWaterJug) fromState;
        StateWaterJug to = (StateWaterJug) toState;
        return to.path_cost - from.path_cost;
    }
    public double h(Object state){
        return 0;
    }

    private boolean isValid(StateWaterJug state) {
        if(state.jugs[0] <= 12 && state.jugs[0] >= 0 &&
           state.jugs[1] <= 8 && state.jugs[1] >= 0 &&
           state.jugs[2] <= 3 && state.jugs[2] >= 0)
            return true;
        return false;
    }

    public static void main(String[] args) {
        ProblemWaterJug problem = new ProblemWaterJug();
        int[] jugs = {0, 0, 0};
        problem.initialState = new StateWaterJug(jugs);

        Search search  = new Search(problem);

        System.out.println("BreadthFirstTreeSearch:\t\t" + search.BreadthFirstTreeSearch());

        System.out.println("BreadthFirstGraphSearch:\t" + search.BreadthFirstGraphSearch());

        System.out.println("DepthFirstTreeSearch:\t\t" + search.DepthFirstTreeSearch());

        System.out.println("DepthFirstGrapthSearch:\t\t" + search.DepthFirstGraphSearch());

        System.out.println("UniformCostGraphSearch:\t\t" + search.UniformCostGraphSearch());

        System.out.println("UniformCostTreeSearch:\t\t" + search.UniformCostTreeSearch());

        System.out.println("IterativeDeepeningTreeSearch:\t" + search.IterativeDeepeningTreeSearch());

        System.out.println("IterativeDeepeningGraphSearch:\t" + search.IterativeDeepeningGraphSearch());
        
    }
}
