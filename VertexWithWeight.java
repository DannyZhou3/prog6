class VertexWithWeight implements VertexWithWeightFunctions{
    private final Integer vertex;
    private Double weight;

    public int getVertex(){
        return vertex.intValue();
    }

    public double getWeight(){
        return weight.doubleValue();
    }

    public void setWeight(double w){
        weight = Double.valueOf(w);
    }

    public String toString(){
        return "(" + vertex + "," + weight + ")";
    }

    public boolean equals(Object o){
        if (o == null)
        {
            return false;
        }
        if (this == o)
        {
            return true;
        }
        if (o instanceof VertexWithWeight)
        {
            if (this.getVertex() == ((VertexWithWeight) o).getVertex())
            {
                return true;
            }
        }
        return false;
    }

    public VertexWithWeight(int v, double w){
        vertex = Integer.valueOf(v);
        weight = Double.valueOf(w);
    }
    
}