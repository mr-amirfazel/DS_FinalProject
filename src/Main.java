/**
DS final project fall 00-01
@author Amirfazel koozegar
@student-ID 9931099
 */
import java.util.Scanner;

public class Main {
    static  Scanner scanner = new Scanner(System.in);
    static Graph graph;

    public  static  Graph createGraph(int vertex , int edge){
        return new Graph(vertex,edge);
    }
    public static void getUserGraph(){
        int countOfVertex, countOfEdges;
        String line1 = scanner.nextLine();
        countOfVertex = Integer.parseInt(line1.split(" ")[0]);
        countOfEdges = Integer.parseInt(line1.split(" ")[1]);
        graph = createGraph(countOfVertex, countOfEdges);
        String line2 = scanner.nextLine();
        graph.addVertices(line2);
        String[] edges = new String[countOfEdges];
        for (int i = 0; i < countOfEdges ; i++) {
            edges[i] = scanner.nextLine();
        }
        graph.addEdges(edges);
    }

    public static void showMenu(){
        System.out.println("**********\t Menu \t***********");
        System.out.println("test ------------>  prints a DFS traverse on your given graph");
        System.out.println("join <node Number> ------------>  person enters the meeting");
        System.out.println("left <node Number> ------------>  person leaves the meeting");
        System.out.println("exit ------------>  close the program");
    }

    public static String getUserInput(){
        System.out.println("please enter your choice : ");
        return scanner.nextLine();
    }

    public static void main(String[] args) {
        System.out.println("**********\t MAHALE QARAR \t**********");
        System.out.println("**********\t First make a graph \t**********");

        getUserGraph();


        boolean appOn = true;

        while (appOn){
            showMenu();
            String userChoice = getUserInput();

            if (userChoice.equals("test")){
                graph.DFSTraverse();
                System.out.println(" ");
            }

            else  if (userChoice.startsWith("join")){
                String[] data = userChoice.split(" ");
                for (int i = 1; i < data.length ; i++) {
                    graph.addJoinedVertex(Integer.parseInt(data[i]));
                }
                graph.calculateFairScore();
            }
            else if (userChoice.startsWith("left")){
                String[] data = userChoice.split(" ");
                for (int i = 1; i < data.length ; i++) {
                    graph.removeJoinedVertex(Integer.parseInt(data[i]));
                }
                graph.calculateFairScore();
            }
            else if (userChoice.equals("exit")){
                appOn = false;
            }
            else
                System.out.println("wrong entry or input detected Please try Again");
        }

    }
}
