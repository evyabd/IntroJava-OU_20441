/**
 * Segment1 represents a line (parallel to the x-axis) using two Points.
 * @author Evyatar Ben David
 * @version 4.11.2020
 */
public class Segment1 {

    //Declarations and constants
    private Point _poLeft;
    private Point _poRight;
    private final double DEFAULT_VAL = 0;

    /**
     * Constructs a new segment using two Points. If the y coordinates are different, change the y of the right point to be equal to the y of the left point.
     * @param left the left point of the segment
     * @param right the right point of the segment
     */
    public Segment1(Point left, Point right){
        _poLeft = new Point(left);
        _poRight = new Point(right);
        _poRight.setY(_poLeft.getY());
    }

    /**
     * Constructs a new segment using 4 specified x y coordinates: Two coordinates for the left point and two coordinates for the right point. If the y coordinates are different, change the y of the right point to be equal to the y of the left point.
     * @param leftX X value of left point
     * @param leftY  Y value of left point
     * @param rightX X value of right point
     * @param rightY Y value of right point
     */
    public Segment1(double leftX, double leftY, double rightX, double rightY){
        _poLeft = new Point(leftX, leftY);
        _poRight = new Point(rightX, leftY);
    }

    /**
     * Copy Constructor. Construct a segment using a reference segment.
     * @param other the reference segment
     */
    public Segment1(Segment1 other){
        this(other._poLeft, other._poRight);
    }

    //Methods
    /**
     * Change the segment size by moving the right point by delta.
     * Will be implemented only for a valid delta: only if the new right point remains the right point.
     * @param delta  the length change
     */
    public void changeSize(double delta){
        if(_poRight.getX() + delta >= _poLeft.getX()){
            _poRight.setX(_poRight.getX() + delta);
        }
    }

    /**
     * Check if the reference segment is equal to this segment.
     * @param other the reference segment
     * @return True if the reference segment is equal to this segment
     */
    public boolean equals(Segment1 other){
        return (_poLeft.equals(other._poLeft) && _poRight.equals(other._poRight));
    }

    /**
     * Returns the segment length.
     * @return The segment length
     */
    public double getLength(){
        return _poLeft.distance(_poRight);
    }

    /**
     * Returns the left point of the segment.
     * @return The left point of the segment
     */
    public Point getPoLeft(){
        return new Point(_poLeft);
    }

    /**
     * Returns the right point of the segment.
     * @return The right point of the segment
     */
    public Point getPoRight(){
        return new Point(_poRight);
    }

    /**
     * Check if this segment is above a reference segment.
     * @param other the reference segment
     * @return True if this segment is above the reference segment
     */
    public boolean isAbove(Segment1 other){
        return _poLeft.getY() > other._poLeft.getY();
    }

    /**
     * Check if this segment is bigger than a reference segment.
     * @param other the reference segment
     * @return True if this segment is bigger than the reference segment
     */
    public boolean isBigger(Segment1 other){
        return this.getLength() > other.getLength();
    }

    /**
     * Check if this segment is left of a received segment.
     * @param other the reference segment
     * @return True if this segment is left to the reference segment
     */
    public boolean isLeft(Segment1 other){
        return (this._poRight.getX() < other._poLeft.getX());
    }

    /**
     * Check if this segment is right of a received segment.
     * @param other the reference segment
     * @return True if this segment is right to the reference segment
     */
    public boolean isRight(Segment1 other){
        return other.isLeft(this); //Applying the opposite method to the object of comparison saves writing and ensures accuracy
    }

    /**
     * Check if this segment is under a reference segment.
     * @param other the reference segment
     * @return True if this segment is under the reference segment
     */
    public boolean isUnder(Segment1 other){
        return other.isAbove(this); //Applying the opposite method to the object of comparison saves writing and ensures accuracy
    }

    /**
     * Move the segment horizontally by delta.
     * @param delta the displacement size
     */
    public void moveHorizontal(double delta){
        if((_poLeft.getX() + delta) >= DEFAULT_VAL && (_poRight.getX() + delta) >= DEFAULT_VAL) {
            _poLeft.move(delta, 0); // I used zero and not regular, because the intention is not to move at all (if the intention was to move by default I would use regular)
            _poRight.move(delta, 0);
        }
    }

    /**
     * Move the segment vertically by delta.
     * @param delta the displacement size
     */
    public void moveVertical(double delta){
        if((_poLeft.getY() + delta) >= DEFAULT_VAL){
            _poLeft.setY(_poLeft.getY() + delta);
            _poRight.setY(_poRight.getY() + delta);
        }
    }

    /**
     * Check if a point is located on the segment.
     * @param p  a point to be checked
     * @return True if p is on this segment
     */
    public boolean pointOnSegment(Point p){
        if(p.getY() != _poLeft.getY()) //A point can be on the segment only if it is at the height of the line
            return false;
        else
            return  (_poLeft.getX() <= p.getX() && _poRight.getX() >= p.getX());

    }

    /**
     * Returns the overlap size of this segment and a reference segment.
     * @param other the reference segment
     * @return The overlap size
     */
    public double overlap(Segment1 other){
        if (this._poLeft.getX() <= other._poLeft.getX() && other._poLeft.getX() <= this._poRight.getX()) { //Check if the other line starts within the original line and continues to the right
            if (other._poRight.getX() <= this._poRight.getX())
                return other.getLength();
            else
                return this._poRight.getX() - other._poLeft.getX();
        }
        else if (this._poLeft.getX() <= other._poRight.getX() && other._poRight.getX() <= this._poRight.getX()){ //Check if the other line starts within the original line and continues to the left
            if (this._poLeft.getX() <= other._poLeft.getX()) //Check whether the other line is contained within the original line
                return other.getLength();
            else
                return other._poRight.getX() - this._poLeft.getX();
        }
        else
            return 0;
    }

    /**
     * Return a string representation of this segment in the format (3.0,4.0)---(3.0,6.0).
     * @overrides toString in class java.lang.Object
     * @return String representation of this segment
     */
    public String toString(){
        return (_poLeft + "---" + _poRight);
    }

    /**
     * Compute the trapeze perimeter, which is constructed by this segment and a reference segment.
     * @param other the reference segment
     * @return The trapeze perimeter
     */
    public double trapezePerimeter(Segment1 other){
        return this.getLength() + other.getLength() + this._poLeft.distance(other._poLeft) + this._poRight.distance(other._poRight);
    }
}