package ljq.ksp;

import java.util.List;

/**
 * K���·�Ľӿ�
 *
 */
public interface KSPAlgorithm {
    public boolean isLoopless();

    public List<Path> ksp(Graph graph, String sourceLabel, String targetLabel, int K);
}
