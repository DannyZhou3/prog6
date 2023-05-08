interface WeightedGraphFunctions
{
	public boolean hasPath(int fromVertex, int toVertex);
	public double getMinimumWeight(int fromVertex, int toVertex);
	public EdgeWithWeight[] getPath(int fromVertex, int toVertex);
	private Object getPath(int fromVertex, int toVertex, WeightedGraphReturnType typeOfInfo)
	{
		return null;
	}
	public boolean addVertex(int v);
	public boolean addWeightedEdge(int from, int to, double weight);
	public String toString();
}
