import java.util.ArrayList;
import java.util.PriorityQueue;
class zhou_WeightedGraph implements WeightedGraphFunctions {

    private final ArrayList<Integer> vertices;
    private final ArrayList<EdgeWithWeight> edges;  
    private boolean debugOutput = false;  

    public zhou_WeightedGraph(){
        vertices = new ArrayList<Integer>();
        edges = new ArrayList<EdgeWithWeight>();  
    }

    public boolean hasPath(int fromVertex, int toVertex)
    {
        return (boolean) getPath(fromVertex, toVertex, WeightedGraphReturnType.HAS_PATH);
    }

	public double getMinimumWeight(int fromVertex, int toVertex)
    {
        return (double) getPath(fromVertex, toVertex, WeightedGraphReturnType.GET_MINIMUM_WEIGHT);
    }

	public EdgeWithWeight[] getPath(int fromVertex, int toVertex)
    {
        return (EdgeWithWeight[]) getPath(fromVertex, toVertex, WeightedGraphReturnType.GET_PATH);
    }

    public int getIndex(int v)
    {
        return vertices.indexOf(v);
    }

    private Object getPath(int fromVertex, int toVertex, WeightedGraphReturnType typeOfInfo)
    {
        PriorityQueue<VertexWithWeight> minPriorityQueueByWeight = new PriorityQueue<>(vertices.size(), new VertexWithWeightComparator());
        VertexWithWeight[] verticeCost = new VertexWithWeight[vertices.size()];
        int[] parent = new int[vertices.size()];

        for (int i = 0; i < vertices.size(); i++)
        {
            parent[i] = -1;
            verticeCost[i] = new VertexWithWeight(vertices.get(i), Double.POSITIVE_INFINITY);
        }
        parent[getIndex(fromVertex)] = fromVertex;
        verticeCost[getIndex(fromVertex)] = new VertexWithWeight(vertices.get(fromVertex), 0.0);

        for (int i = 0; i < vertices.size(); i++)
        {
            minPriorityQueueByWeight.add(verticeCost[i]);
        }

        while (minPriorityQueueByWeight.size() > 0)
        {
            VertexWithWeight beforeV = minPriorityQueueByWeight.poll();
            int v = beforeV.getVertex();
            if (parent[getIndex(v)] == -1 || beforeV.getWeight() == Double.POSITIVE_INFINITY || v == toVertex)
            {
                break;
            }
            for (EdgeWithWeight e : edges)
            {
                if (e.fromVertex() == v)
                {
                    int u = e.toVertex();
                    if ((beforeV.getWeight() + e.weight()) < verticeCost[getIndex(u)].getWeight())
                    {
                        minPriorityQueueByWeight.remove(verticeCost[getIndex(u)]);
                        verticeCost[getIndex(u)].setWeight(beforeV.getWeight() + e.weight());
                        minPriorityQueueByWeight.add(verticeCost[getIndex(u)]);
                        parent[getIndex(u)] = v;
                    }
                }
            }
        }

        if(parent[getIndex(toVertex)] == -1 || verticeCost[getIndex(toVertex)].getWeight() == Double.POSITIVE_INFINITY)
        {
            if(typeOfInfo == WeightedGraphReturnType.GET_MINIMUM_WEIGHT)
            {
                return Double.NaN;
            }
            if(typeOfInfo == WeightedGraphReturnType.HAS_PATH)
            {
                return false;
            }
            if(typeOfInfo == WeightedGraphReturnType.GET_PATH)
            {
                return new EdgeWithWeight[0];
            }
        }

        if (typeOfInfo == WeightedGraphReturnType.HAS_PATH)
        {
            boolean path = false;
            if (parent[getIndex(toVertex)] != -1)
            {
                path = true;
            }
            return path;
        }
        
        if (typeOfInfo == WeightedGraphReturnType.GET_MINIMUM_WEIGHT)
        {
            double minWeight = verticeCost[getIndex(toVertex)].getWeight();
            return minWeight;
        }

        if (typeOfInfo == WeightedGraphReturnType.GET_PATH)
        {
            ArrayList<Integer> reversePath = new ArrayList<Integer>();
            int p = toVertex;
            reversePath.add(p);
            while(p != fromVertex)
            {
                p = parent[getIndex(p)];
                reversePath.add(p);
            }

            ArrayList<Integer> forwardPath = new ArrayList<Integer>();
            for (int i = reversePath.size() - 1; i >= 0 ; i--)
            {
                forwardPath.add(reversePath.get(i));
            }
            
            EdgeWithWeight[] workingEdges = new EdgeWithWeight[forwardPath.size() - 1];
            for (int i = 0; i < forwardPath.size() - 1; i++)
            {
                int from = forwardPath.get(i);
                int to = forwardPath.get(i+1);
                for (EdgeWithWeight e: edges)
                {
                    if (e.fromVertex() == from && e.toVertex() == to)
                    {
                        workingEdges[i] = e;
                    }
                }
                
            }
            return workingEdges;
        }
        return null;
    }

	public boolean addVertex(int v)
    {
        if(vertices.contains(v))
        {
            return false;
        }
        vertices.add(Integer.valueOf(v));
        return true;
    }

	public boolean addWeightedEdge(int from, int to, double weight)
    {
        if(!(vertices.contains(from) && vertices.contains(to)))
        {
            return false;
        }
        EdgeWithWeight edge = new EdgeWithWeight(Integer.valueOf(from), Integer.valueOf(to), Double.valueOf(weight));
        if(edges.contains(edge))
        {
            return false;
        }
        edges.add(edge);
        return true;
    }

	public String toString()
    {
        StringBuilder str = new StringBuilder();
        str.append("G = (V, E)\nV = {");
        for(int vertex : vertices)
        {
            str.append(vertex + ",");
        }
        str.setCharAt(str.length() -1, '}');
        str.append("\nE = {");
        for(EdgeWithWeight e : edges) 
        {
            str.append(e);
            str.append(",");
        }
        str.setCharAt(str.length() - 1, '}');
        return str.toString();
    }
}
