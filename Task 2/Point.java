/**
 *  Point Represents 2 dimensional points.
 * @author Evyatar Ben David
 * @version 2.11.2020
 */
public class Point {

    //Declarations and constants
    private double _radius;
    private double _alpha;
    private final double DEFAULT_VAL = 0;
    private final double RIGHT_ANGLE = 90;
    private final double FLAT_ANGLE = 180;

    /**
     * Constructor for objects of class Point. Construct a new point with the specified x y coordinates.
     * If the x coordinate is negative it is set to zero. If the y coordinate is negative it is set to zero.
     * Parameters:
     * @param x The x coordinate
     * @param y The y coordinate
     */
    public Point(double x, double y){
        if(x < DEFAULT_VAL){
            x = DEFAULT_VAL;
        }
        if(y < DEFAULT_VAL){
            y = DEFAULT_VAL;
        }
        _radius = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        if (x == DEFAULT_VAL)
            _alpha = RIGHT_ANGLE;
        else
            _alpha = radianToDegree(Math.atan(y/x));
    }

    /**
     * Constructor for objects of class Point. Copy constructor, construct a point using another point.
     * @param other  The point from which to construct the new object.
     */
    public Point(Point other){
        if (other != null) {
            _radius = other._radius;
            _alpha = other._alpha;
        }
    }
    //Methods
    /**
     * Check the distance between this point and a given point.
     * @param other The point to check the distance from
     * @return The distance
     */
    public double distance(Point other){
        return Math.sqrt(Math.pow(other.getX() - this.getX(),2) + (Math.pow(other.getY() - this.getY(), 2)));
    }

    /**
     * Check if the given point is equal to this point.
     * @param other The point to check equality with.
     * @return True if the given point is equal to this point.
     */
    public boolean equals(Point other){
        return (_radius == other._radius && _alpha == other._alpha);
    }

    /**
     * This method returns the x coordinate of the point.
     * @return The x coordinate of the point
     */
    public double getX(){
        if (_alpha != RIGHT_ANGLE)
            return Math.round(Math.cos(degreeToRadian(_alpha)) * _radius * 1000)/(double)1000; //round the number, 4 digits after the point
        else
            return DEFAULT_VAL;
    }

    /**
     * This method returns the y coordinate of the point.
     * @return The y coordinate of the point
     */
    public double getY(){
        return Math.round(Math.sin(degreeToRadian(_alpha)) * _radius * 1000)/(double)1000; //round the number, 4 digits after the point
    }

    /**
     * Check if this point is above a received point.
     * @param other The point to check if this point is above
     * @return True if this point is above the other point
     */
    public boolean isAbove(Point other){
        return other.getY() < this.getY();
    }

    /**
     * Check if this point is left of a received point.
     * @param other The point to check if this point is left of
     * @return True if this point is left of the other point
     */
    public boolean isLeft(Point other){
        return this.getX() < other.getX();
    }

    /**
     * Check if this point is right of a received point.
     * @param other The point to check if this point is right of
     * @return True if this point is right of the other point
     */
    public boolean isRight(Point other){
        return other.isLeft(this);
    }

    /**
     * Check if this point is below a received point.
     * @param other The point to check if this point is below
     * @return True if this point is below the other point
     */
    public boolean isUnder(Point other){
        return other.isAbove(this);
    }

    /**
     * Moves a point. If either coordinate becomes negative the point remains unchanged.
     * @param dx The difference to add to x
     * @param dy The difference to add to y
     */
    public void move(double dx, double dy) {
        if ((dx + this.getX()) >= DEFAULT_VAL && (dy + this.getY()) >= DEFAULT_VAL) {
            this.setX(this.getX() + dx);
            this.setY(this.getY() + dy);
        }
    }

    /**
     * This method sets the x coordinate of the point. If the new x coordinate is negative the old x coordinate will remain unchanged.
     * @param x The new x coordinate
     */
    public void setX(double x){
        double y = this.getY(); // The calculation of Y was done with the help of the variable X during the method, so I set it in advance according to the original X.
        if (x >= DEFAULT_VAL) {
            _radius = Math.sqrt(Math.pow(y, 2) + Math.pow(x, 2));
            if (x == DEFAULT_VAL)
                _alpha = RIGHT_ANGLE;
            else
                _alpha = radianToDegree(Math.atan(y/x));
        }
    }

    /**
     * This method sets the y coordinate of the point. If the new y coordinate is negative the old y coordinate will remain unchanged.
     * @param y The new y coordinate
     */
    public void setY(double y){
        double x = this.getX();
        if (y >= DEFAULT_VAL){
            _radius = Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
            _alpha = radianToDegree(Math.atan(y/x));
        }
    }

    /**
     * Returns a string representation of Point in the format (x,y).
     * @overrides toString in class java.lang.Object
     * @return A String representation of the Point
     */
    public java.lang.String toString(){
        return ("(" + this.getX() + "," + this.getY() + ")");
    }
    
    //radianToDegree Converts number from radian scale to degrees scale.
    private double radianToDegree(double radian){
        return radian * FLAT_ANGLE / Math.PI;
    }
    
    //degreeToRadian Converts number from degrees scale to radian sacle.
    private double degreeToRadian(double degree){
        return degree * Math.PI / FLAT_ANGLE;
    }
}
