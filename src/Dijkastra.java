import java.util.HashMap;

public class Dijkastra {
    private final Graph graph;


    public Dijkastra(Graph graph) {
        this.graph = graph;
    }

    public  HashMap<Vertice, Integer> dijkstraDistances(int nodeValue) {
        HashMap<Vertice, Integer> distanceMap = new HashMap<>();
        // hashmap initialization
        for (Vertice v : graph.getVertices()) {
            v.setVisited(false);
            if (v.getValue() == nodeValue){
                distanceMap.put(v, 0);
            }
            else
                distanceMap.put(v, Integer.MAX_VALUE);
        }
        for (Vertice ignored :graph.getVertices()) {
                Vertice vertice = findMinDistance(distanceMap);
                    vertice.setVisited(true);

                for (Vertice ve: graph.getVertices()) {
                    if (!ve.isVisited() && vertice.getAdjacentVertices().contains(ve) && distanceMap.get(vertice) +
                            graph.findWeight(vertice.getValue(), ve.getValue()) < distanceMap.get(ve)) {
                        distanceMap.put(ve, distanceMap.get(vertice) + graph.findWeight(vertice.getValue(), ve.getValue()));
                    }
            }
        }
        return distanceMap;
    }
    private  Vertice findMinDistance(HashMap<Vertice,Integer> distanceMap){
        int minDistance = Integer.MAX_VALUE;
        Vertice minDistanceVertex  = null;
        for (Vertice v: graph.getVertices()) {
            if (!v.isVisited() && distanceMap.get(v) < minDistance) {
                minDistance = distanceMap.get(v);
                minDistanceVertex = v;
            }
        }
        return minDistanceVertex;
    }
}
