import java.util.*;

public class Graph {
    private final int numberOfVertices;
    private final ArrayList<Vertice> vertices;
    private final ArrayList<Edge> edges;
    public  HashMap<Vertice,HashMap<Vertice,Integer>> dijkstraMap;
    public  Dijkastra dijkastra ;
    private final ArrayList<Vertice> joinedVertices;

    public Graph(int numberOfVertices, int numberOfEdges) {
        this.numberOfVertices = numberOfVertices;
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
        dijkstraMap = new HashMap<>();
        dijkastra = new Dijkastra(this);
        joinedVertices = new ArrayList<>();
    }
    public void addVertices(String verticeList){
        String[] vertexArray = verticeList.split(" ");
        for (String s:vertexArray) {
            vertices.add(new Vertice(Integer.parseInt(s)));
        }
    }
    public void addEdges(String[] edgeList){
        for (String s:edgeList) {
            String[] dataLine = s.split(" ");
            edges.add(new Edge(findVertex(Integer.parseInt(dataLine[0])),
                    findVertex(Integer.parseInt(dataLine[1])),
                    Integer.parseInt(dataLine[2])
            ));

            Objects.requireNonNull(findVertex(Integer.parseInt(dataLine[0]))).getAdjacentVertices().add(findVertex(Integer.parseInt(dataLine[1])));
            Objects.requireNonNull(findVertex(Integer.parseInt(dataLine[1]))).getAdjacentVertices().add(findVertex(Integer.parseInt(dataLine[0])));
        }
    }
    private Vertice findVertex(int value){
        for (Vertice v:vertices) {
            if (v.getValue() == value)
                return v;
        }
        return null;
    }
    public void DFSTraverse(){
        for (Vertice v:vertices) {
            v.setVisited(false);
        }
        int randomVertexIndex = new Random().nextInt(numberOfVertices);
        Vertice srcVertex = vertices.get(randomVertexIndex);
        DFS(srcVertex);
    }
    private void DFS(Vertice startingVertex){

        startingVertex.setVisited(true);
        System.out.print(startingVertex.getValue() + " ");

        Iterator<Vertice> ite = startingVertex.getAdjacentVertices().listIterator();
        while (ite.hasNext()) {
            Vertice adj = ite.next();
            if (!adj.isVisited())
                DFS(adj);
        }
    }
    public void addJoinedVertex(int value){
        if (findVertex(value) != null)
            joinedVertices.add(findVertex(value));
    }
    public void removeJoinedVertex(int value){
        if (findVertex(value) != null)
            joinedVertices.remove(findVertex(value));
    }


    public ArrayList<Vertice> getVertices() {
        return vertices;
    }


    public int findWeight(int srcVal, int dstVal){
        for (Edge edge:edges) {
            if (edge.src.getValue() == srcVal && edge.dst.getValue() == dstVal)
                return  edge.getWeight();
            else if (edge.src.getValue() == dstVal && edge.dst.getValue() == srcVal)
                return  edge.getWeight();
        }
        return Integer.MAX_VALUE;
    }

    public void calculateFairScore(){

        for (Vertice v:vertices) {
            HashMap<Vertice,Integer> hashMap= dijkastra.dijkstraDistances(v.getValue());
            dijkstraMap.put(v,hashMap);
        }
        HashMap<Vertice, Integer> sumMap = new HashMap<>();

        int sum  , count = 0;
        for (Vertice vertice : vertices) {

            for (Vertice i:joinedVertices) {
                sum = 0;
                for (Vertice j:joinedVertices) {
                    if (i.getValue() == j.getValue() && vertice.getValue() == j.getValue())
                        continue;
                    sum += Math.abs(dijkstraMap.get(vertice).get(i) - dijkstraMap.get(vertice).get(j));
                    count++;

                }
                if (count > 0) {
                    sumMap.put(vertice, sum / count);
                    count = 0;
                }
            }
        }
        System.out.println("distance to every cafe : ");
        System.out.print("||");
        for (Vertice v:vertices) {
            String extra = " , ";
            if (vertices.indexOf(v) == vertices.size()-1)
                extra = "";
            System.out.print(v.getValue() + " -> " + sumMap.get(v)+extra);
        }
        System.out.print("||\n");

        int minimum = Integer.MAX_VALUE;
        for (int i : sumMap.values()) {
            if (i < minimum) {
                minimum = i;
            }

        }
        System.out.println("The best place to have a meeting");
        for (Vertice verticeIndex : sumMap.keySet()) {
            if (sumMap.get(verticeIndex) == minimum) {
                System.out.print(verticeIndex.getValue() +" ");
            }
        }
        System.out.println("\n---------------");
    }

}
