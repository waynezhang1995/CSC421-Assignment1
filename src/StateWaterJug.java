/**
 * Created by haodong on 2017-09-17.
 */
public class StateWaterJug {
    int[] jugs; //WaterJug array, index 0 is 12 gallons, index 1 is 8 gallons, index 2 is 3 gallons
    int path_cost;

    public StateWaterJug (int[] arr) {
        jugs = arr;
        path_cost = 0;
    }

    public StateWaterJug (StateWaterJug state) {
        jugs = new int[3];
        for (int i=0; i< 3; i++) {
            this.jugs[i] = state.jugs[i];
        }
        this.path_cost = state.path_cost;
    }

    public boolean equals(Object o)
    {
        StateWaterJug state = (StateWaterJug) o;
        if(this.path_cost != state.path_cost)
            return false;
        for (int i=0; i<3; i++)
            if (this.jugs[i] != state.jugs[i])
                return false;

        return true;
    }

    public int hashCode() {
        return jugs[0]*1000 + jugs[1]*100 + jugs[2]*10 + path_cost;
    }

    public String toString()
    {
        String ret = "[";
        for (int i=0; i<3; i++) {
            ret += this.jugs[i];
            if(i!=2) ret+=", ";
        }
        ret+="] cost=" + path_cost;
        return ret;
    }

}
