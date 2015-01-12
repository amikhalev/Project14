import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class World {
    private List<Object> objects;

    public World(Object[] objects) {
        this.objects = new ArrayList<Object>(Arrays.asList(objects));
    }

    /**
     * Get all the objects in the World
     * @return the objects in the World
     */
    public List<Object> getObjects() {
        return objects;
    }
}