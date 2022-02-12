import java.util.ArrayList;

public class Vertice {
    private final int value;
    private boolean visited;
    private final ArrayList<Vertice> adjacentVertices;

    public Vertice(int value) {
        this.value = value;
        this.visited = false;
        adjacentVertices = new ArrayList<>();
    }

    public int getValue() {
        return value;
    }

    public ArrayList<Vertice> getAdjacentVertices() {
        return adjacentVertices;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
