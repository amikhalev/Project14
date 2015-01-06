import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
public class World {
    private List<Object> objects;
    public World(Object[] objects) {
        this.objects = new ArrayList<Object>(Arrays.asList(objects));
    }

    public List<Object> getObjects() {
        return objects;
    }
}