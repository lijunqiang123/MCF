package ljq.ksp;


import java.util.*;

/**
 * 
 *	使用Yen算法来实现K最短路，其中路径是无环的
 * 
 */
public final class ksp implements KSPAlgorithm {

    public ksp() {};

    public boolean isLoopless() {
        return true;
    }


    public List<Path> ksp(Graph graph, String sourceLabel, String targetLabel, int K) {
 
        ArrayList<Path> ksp = new ArrayList<Path>();
        PriorityQueue<Path> candidates = new PriorityQueue<Path>();

        try {
            Path kthPath = Dijkstra.shortestPath(graph, sourceLabel, targetLabel);
            ksp.add(kthPath);

            for (int k = 1; k < K; k++) {
                Path previousPath = ksp.get(k-1);

                for (int i = 0; i < previousPath.size(); i++) {
                    LinkedList<Edge> removedEdges = new LinkedList<Edge>();

                    String spurNode = previousPath.getEdges().get(i).getFromNode();

                    Path rootPath = previousPath.cloneTo(i);

                    for(Path p:ksp) {
                        Path stub = p.cloneTo(i);
                        if (rootPath.equals(stub)) {
                            Edge re = p.getEdges().get(i);
                            graph.removeEdge(re.getFromNode(),re.getToNode());
                            removedEdges.add(re);
                        }
                    }

                    for(Edge rootPathEdge : rootPath.getEdges()) {
                        String rn = rootPathEdge.getFromNode();
                        if (!rn.equals(spurNode)) {
                            removedEdges.addAll(graph.removeNode(rn));
                        }
                    }

                    Path spurPath = Dijkstra.shortestPath(graph, spurNode, targetLabel);

                    if (spurPath != null) {
                        Path totalPath = rootPath.clone();
                        totalPath.addPath(spurPath);

                        if (!candidates.contains(totalPath))
                            candidates.add(totalPath);
                    }

                    graph.addEdges(removedEdges);
                }

                boolean isNewPath;
                do {
                    kthPath = candidates.poll();
                    isNewPath = true;
                    if (kthPath != null) {
                        for (Path p : ksp) {
                            if (p.equals(kthPath)) {
                                isNewPath = false;
                                break;
                            }
                        }
                    }
                } while(!isNewPath);

                if (kthPath == null)
                    break;

                ksp.add(kthPath);
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }

        return ksp;
    }


    public static  List<Path> ksp_v2(Graph graph, String sourceLabel, String targetLabel, int K) {
        ArrayList<Path> ksp = new ArrayList<Path>();
        PriorityQueue<Path> candidates = new PriorityQueue<Path>();

        try {
            Path kthPath = Dijkstra.shortestPath(graph, sourceLabel, targetLabel);
            ksp.add(kthPath);

            for (int k = 1; k < K; k++) {
                Path previousPath = ksp.get(k-1);

                LinkedList<Edge> rootPathEdges = new LinkedList<Edge>();
                Iterator<Edge> it = previousPath.getEdges().iterator();
                for (int i = 0; i < previousPath.size(); i++) {
                    if (i > 0) {
                        rootPathEdges.add(it.next());
                    }

                    LinkedList<Edge> removedEdges = new LinkedList<Edge>();

                    String spurNode = previousPath.getEdges().get(i).getFromNode();

                    Path rootPath = previousPath.cloneTo(i);

                    for(Path p:ksp) {
                        int pSize = p.size();
                        if (pSize < i)
                            continue;
                        boolean rootMatch = true;
                        for (int rootPos = 0; rootPos < i; rootPos++) {
                            if (!p.getEdges().get(rootPos).equals(rootPathEdges.get(rootPos))) {
                                rootMatch = false;
                                break;
                            }
                        }

                        if (rootMatch) {

                            Edge re = p.getEdges().get(i);
                            graph.removeEdge(re.getFromNode(),re.getToNode());
                            removedEdges.add(re);
                        }
                    }

                    for(Edge rootPathEdge : rootPathEdges) {
                        String rn = rootPathEdge.getFromNode();
                        if (!rn.equals(spurNode)) {
                            removedEdges.addAll(graph.removeNode(rn));
                        }
                    }

                    Path spurPath = Dijkstra.shortestPath(graph, spurNode, targetLabel);

                    if (spurPath != null) {
                        Path totalPath = rootPath.clone();
                        totalPath.addPath(spurPath);

                        candidates.add(totalPath);
                    }

                    graph.addEdges(removedEdges);
                }

                boolean isNewPath;
                do {
                    kthPath = candidates.poll();
                    isNewPath = true;
                    if (kthPath != null) {
                        for (Path p : ksp) {
                            if (p.equals(kthPath)) {
                                isNewPath = false;
                                break;
                            }
                        }
                    }
                } while(!isNewPath);

                if (kthPath == null)
                    break;

                ksp.add(kthPath);
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }

        return ksp;
    }
}
