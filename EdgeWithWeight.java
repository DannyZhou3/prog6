record EdgeWithWeight(Integer fromVertex, Integer toVertex, Double weight) implements EdgeWithWeightFunctions{
    public String toString()
    {
        return "(" + fromVertex + "," + toVertex + "," + weight + ")";
    }
    
}
