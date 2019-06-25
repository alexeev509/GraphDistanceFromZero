import java.util.*;

public class Main {
    public static int V;
    public static int E;
    public static Scanner scanner=new Scanner(System.in);
    public static int startOfEdge;
    public static int endOfEdge;
    public static Map<Integer,List<Integer>> mapOfEdges;

    public static void main(String[] args) {
        createGraph();
        countDistanse();
    }

    public static void createGraph(){
        V=scanner.nextInt();
        E=scanner.nextInt();
//        V=6;
//        E=7;
        mapOfEdges=new HashMap<>(E);

        for (int i=0;i<V;i++)
            mapOfEdges.put(i,new ArrayList<Integer>());

        for (int i=0;i<E;i++){
            startOfEdge=scanner.nextInt();
            endOfEdge=scanner.nextInt();

            if(!mapOfEdges.get(startOfEdge).contains(endOfEdge) && startOfEdge!=endOfEdge)
                mapOfEdges.get(startOfEdge).add(endOfEdge);
            //Because we have not oriented graph
            if(!mapOfEdges.get(endOfEdge).contains(startOfEdge) && startOfEdge!=endOfEdge)
                mapOfEdges.get(endOfEdge).add(startOfEdge);
        }
//        mapOfEdges.get(0).add(1);
//        mapOfEdges.get(1).add(2);
//        mapOfEdges.get(2).add(0);
//        mapOfEdges.get(3).add(2);
//
//        mapOfEdges.get(4).add(3);
//        mapOfEdges.get(4).add(2);
//        mapOfEdges.get(5).add(4);
//
//        mapOfEdges.get(1).add(0);
//        mapOfEdges.get(2).add(1);
//        mapOfEdges.get(0).add(2);
//        mapOfEdges.get(2).add(3);
//
//        mapOfEdges.get(3).add(4);
//        mapOfEdges.get(2).add(4);
//        mapOfEdges.get(4).add(5);
    }

    public static void countDistanse(){
        Map<Integer,Integer> distancesToNodesFromeZeroNode =new HashMap<>(V);

        int counter=1;

        Queue<Integer> queueOfNodes=new PriorityQueue<>();
        Queue<Integer> queueOfNodesHelper=new PriorityQueue<>();
        //add start node
        queueOfNodes.add(0);
        distancesToNodesFromeZeroNode.put(0,0);

        for (;!queueOfNodes.isEmpty();counter++){

            while (!queueOfNodes.isEmpty()) {
                //get neighbours nodes
                List<Integer> neighbourNodes = mapOfEdges.get(queueOfNodes.poll());

                for (int j = 0; j < neighbourNodes.size(); j++) {
                    if (!distancesToNodesFromeZeroNode.containsKey(neighbourNodes.get(j))) {
                        queueOfNodesHelper.add(neighbourNodes.get(j));
                        distancesToNodesFromeZeroNode.put(neighbourNodes.get(j), counter);
                    }
                }
            }
            queueOfNodes=new PriorityQueue<>(queueOfNodesHelper);
            queueOfNodesHelper=new PriorityQueue<>();
        }

        for (Map.Entry entry: distancesToNodesFromeZeroNode.entrySet()) {
            System.out.print(entry.getValue()+" ");
        }
    }

}
