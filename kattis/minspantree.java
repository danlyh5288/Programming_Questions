import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class Minspantree {

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String line;
        while((line = br.readLine()) != null) {
            String[] ch = line.split(" ");
            int numNodes = Integer.parseInt(ch[0]);
            int numEdges = Integer.parseInt(ch[1]);
            // EOF
            if(numNodes == 0 && numEdges == 0) {
                break;
            }
            if(numEdges == 0) {
                System.out.println("Impossible");
                continue;
            }
            int[] p = new int[numNodes + 1];
            List<Edge> edges = new ArrayList<>();
            List<Output> outputs = new ArrayList<>();
            // store all the edges of current problem into list
            for(int i = 0; i < numEdges; i++) {
                line = br.readLine();
                String[] edge = line.split(" ");
                int first = Integer.parseInt(edge[0]);
                int second = Integer.parseInt(edge[1]);
                int third = Integer.parseInt(edge[2]);
                edges.add(new Edge(first, second, third));
            }
            // execute kruskal for each question
            Kruskal kruskal = new Kruskal(numNodes, p, edges, outputs);
            kruskal.run(numNodes);
        }
    }
}

class Kruskal{
    private int[] p;
    private int numNodes;
    private List<Edge> edges;
    private List<Output> outputs;

    public Kruskal(int numNodes, int[] p, List<Edge> edges, List<Output> outputs) {
        this.numNodes = numNodes;
        this.p = p;
        this.edges = edges;
        this.outputs = outputs;
    }

    private void make_set(int node){
        p[node] = node;
    }

    private int find(int node) {
        if (p[node] == node) {
            return node;
        }
        // path compression
        return p[node] = find(p[node]);
    }

    private boolean union(int a, int b) {
        int root_a = find(a);
        int root_b = find(b);
        if(root_a != root_b) {
            p[root_a] = root_b;
            return true;
        }
        return false;
    }

    public void run(int numNodes) {
        // result weight
        int weight = 0;
        // initialize set for each nodes
        for(int i = 0; i < numNodes; i++){
            make_set(i);
        }
        // order by edge weight by ascending order
        edges.sort(Comparator.comparingInt(e -> e.z));
        // apply kruskal's algorithm
        for(Edge e : edges) {
            if(union(e.x, e.y)) {
                // add output to list by left < right order
                if(e.x < e.y) {
                    Output toAdd = new Output(e.x, e.y, e.z);
                    outputs.add(toAdd);
                }
                else{
                    Output toAdd = new Output(e.y, e.x, e.z);
                    outputs.add(toAdd);
                }
            }
        }
        if(outputs.size() == numNodes - 1) {
            for(Output o : outputs) {
                weight += o.weight;
            }
            System.out.println(weight);
            // print mst edges by lexical order
            outputs.sort((o1, o2) -> {
                if(o1.source == o2.source) {
                    return o1.target - o2.target;
                }
                else{
                    return o1.source - o2.source;
                }
            });
            for (Output o : outputs) {
                System.out.println(o.toString());
            }
        }
        else{
            System.out.println("Impossible");
        }
    }
}

class Edge {
    int x;
    int y;
    int z;

    public Edge(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}

class Output{
    int source;
    int target;
    int weight;

    public Output(int source, int target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return source + " " + target;
    }
}
