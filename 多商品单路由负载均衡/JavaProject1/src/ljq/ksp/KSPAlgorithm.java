package ljq.ksp;

import java.util.List;

/**
 * K最短路的接口
 *
 */
public interface KSPAlgorithm {
    public boolean isLoopless();

    public List<Path> ksp(Graph graph, String sourceLabel, String targetLabel, int K);
}
