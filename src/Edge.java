public class Edge {
    Vertice src;
    Vertice dst;
    int weight;

    public Edge(Vertice src, Vertice dst, int weight) {
        this.src = src;
        this.dst = dst;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }
}
