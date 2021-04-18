/**
 * Polygon represents a convex polygon using an array that maintains the list of vertices.
 * @author Evyatar Ben David.
 * @version 8.12.2020
 */
public class Polygon {
    //Declarations and constants
    private Point[] _vertices;
    private int _noOfVertices;
    private final int MAX_NUM_OF_VERTICES = 10;

    /**
     * Default constructor for array of Points.
     * construct an array of ten Points, and saving the current number of Points.
     */
    public Polygon(){
        _vertices = new Point[MAX_NUM_OF_VERTICES];
        _noOfVertices = 0;
    }

    /**
     * Adds a vertex to the polygon, if there is space, and return ture.
     * If there is no space, will return false.
     * @param x the 'x' value of the new point.
     * @param y the 'y' value of the new point.
     * @return True if added a point, false if not
     */
    public boolean addVertex(double x, double y){
        if (_noOfVertices == MAX_NUM_OF_VERTICES) {
            return false;
        }
        _vertices[_noOfVertices++] = new Point(x, y);
        return true;
    }

    /**
     * highestVertex returns a copy of the the vertex that is highest in the polygon
     * @return the highest vertex in the polygon
     */
    public Point highestVertex(){
        if(_noOfVertices == 0)
            return null;
        Point highest = new Point(_vertices[0]);
        for(int i = 1; i < _noOfVertices; i++){
            if (_vertices[i].isAbove(highest))
                highest = _vertices[i];
        }
        return highest;
    }

    /**
     * Returns a string of characters representing the polygon.
     * @overrides toString in class java.lang.Object
     * @return A string representing the polygon
     */
    public String toString(){
        if(_noOfVertices == 0){
            return "The polygon has 0 vertices.";
        }
        String s = "The polygon has " + _noOfVertices + " vertices:\n" +
            "("; // I chained in order to print correctly regardless of the number of members in the array
        for (int i = 0; i < _noOfVertices - 1; i++){
            s += _vertices[i] + ",";
        }
        s += _vertices[_noOfVertices - 1] + ")"; // The fence post printed alon because there is no comma at the end.
        return s;
    }

    public double calcPerimeter(){
        if(_noOfVertices <= 1)
            return 0;
        double sum = 0;
        if(_noOfVertices == 2)
            return _vertices[0].distance(_vertices[1]);
        for (int i = 0; i < _noOfVertices - 1; i++){
            sum += _vertices[i].distance(_vertices[i+1]);
        }
        sum += _vertices[_noOfVertices - 1].distance(_vertices[0]);
        return sum;
    }

    /**
     * calcArea returns the polygon area, by using Heron's formula and calculates the area of every triangle in the polygon.
     * @return polygon area
     */
    public double calcArea(){
        if(_noOfVertices < 3)
            return 0;
        double area = 0;
        for(int i = 1; i < _noOfVertices - 1; i++){
            area += triangleArea(_vertices[0], _vertices[i], _vertices[i+1]); //I used Heron's formula for every triangle in the polygon.
        }
        return area;
    }

    /**
     * isBigger checks whether the polygon on which the method is applied is larger than the other.
     * If he is bigger will return truth, otherwise will return a false.
     * @param other The polygon to compare to.
     * @return true ig bigger, false if smaller or equals.
     */
    public boolean isBigger(Polygon other){
        return this.calcArea() > other.calcArea();
    }

    /**
     * findVertex checks whether the point is in the polygon. If so - it returns its index. If not returns (-1).
     * @param p the point to find in the polygon.
     * @return the point index.
     */
    public int findVertex(Point p){
        for(int i = 0; i < _noOfVertices; i++){
            if (_vertices[i].equals(p))
                return i;
        }
        return -1;
    }

    /**
     * getNextVertex checks whether the point is in the polygon. If so - it returns the next point in the polygon.
     * If p is the last point it will return the first point. If p isn't in the polygon it will return null.
     * @param p the point to find in the polygon.
     * @return the next point in the polygon.
     */
    public Point getNextVertex(Point p){
        for(int i = 0; i < _noOfVertices; i++){
            if(_vertices[i].equals(p)) {
                if(i == (_noOfVertices - 1)){
                    return new Point(_vertices[0]);
                }
                else{
                    return new Point(_vertices[i+1]);
                }
            }
        }
        return null;
    }

    /**
     * getBoundingBox returns the rectangle that blocks the polygon. If the polygon has less then 3 sides, will return null.
     * @return rectangle that blocks the polygon, as a new polygon.
     */
    public Polygon getBoundingBox(){
        if (_noOfVertices < 3)
            return null;
        // I checked each point - whether their x or y values are the largest or smallest in the array.
        Point leftX = new Point(this._vertices[0]);
        Point rightX = new Point(this._vertices[0]);
        Point upY = new Point(this._vertices[0]);
        Point downY = new Point(this._vertices[0]);
        //double leftX = this._vertices[0].getX();
        //double rightX = this._vertices[0].getX();
        //double upY = this._vertices[0].getY();
        //double downY = this._vertices[0].getY();
        for (int i = 1; i < _noOfVertices; i++){
            if (_vertices[i].isLeft(leftX))
                leftX = new Point(_vertices[i]);
            if (_vertices[i].isRight(rightX))
                rightX = new Point (_vertices[i]);
            if(_vertices[i].isAbove(upY))
                upY =new Point(_vertices[i]);
            if(_vertices[i].isUnder(downY))
                downY = new Point(_vertices[i]);
        }
        // The bounding box consist of the point whose values are most extrem.
        Polygon boundingBox = new Polygon();
        boundingBox.addVertex(leftX.getX(), downY.getY());
        boundingBox.addVertex(rightX.getX(), downY.getY());
        boundingBox.addVertex(rightX.getX(), upY.getY());
        boundingBox.addVertex(leftX.getX(), upY.getY());
        return boundingBox;
    }

    // triangleArea calculates a triangle area by Heron's formula.
    private double triangleArea(Point a, Point b, Point c){
        double s = (a.distance(b) + b.distance(c) + c.distance(a)) / 2; //i need to find another name to s.
        return Math.sqrt(s*(s-a.distance(b))*(s-b.distance(c))*(s-c.distance(a)));
    }
}