package Primitives;
/*
Made by:
//Yuval Sarusi
Eden Amzaleg
 */
public class Coordinate {

    protected double pointValue;

    public Coordinate(double point){
        this.setPointValue(point);
    }

    public Coordinate(Coordinate coordinate){
        this.setCoordinate(coordinate);
    }

    public Coordinate(){
        this.setPointValue(0);
    }

    public void setPointValue(double coordinate){
        this.pointValue =coordinate;
    }

    public double getPointValue() {
        return this.pointValue;
    }

    public void setCoordinate(Coordinate c){
        this.setPointValue(c.getPointValue());
    }


    @Override
    public boolean equals(Object coordinate) {
        try{
            Coordinate c = new Coordinate((Coordinate)coordinate);
            return this.getPointValue() == c.getPointValue();
        }
        catch (ClassCastException classCastException){
            return false;
        }


    }

    @Override
    public String toString(){
        return Double.toString(this.getPointValue());
    }

    public Coordinate add(Coordinate c){
        return new Coordinate(this.getPointValue()+c.getPointValue());
    }
}
