/**
 * Segment2 represents a line (parallel to the x-axis) using a center point and length.
 * @author Evyatar Ben David
 * @version 8.11.2020
 */
public class Segment2 {
    public static void main(String[]args) {
        Point p1=new Point(1,2);
        Point p2=new Point(5,6);
        Point p3=new Point(10, 12);
        Segment2 s1=new Segment2(p1, p2);
        Segment2 s2=new Segment2(p2,p3);
        System.out.println(s1);
        System.out.println(s2);
    }

    private Point _poCenter;
    private double _length;
    private final double DEFAULT_VAL = 0;

    /**
     * Constructs a new segment using 4 specified x y coordinates: two coordinates for the left point and two coordinates for the right point.
     * If the y coordinates are different, change the y of the right point to be equal to the y of the left point.
     * @param leftX X value of left point
     * @param leftY Y value of left point
     * @param rightX X value of right point
     * @param rightY Y value of right point
     */
    public Segment2(double leftX, double leftY, double rightX, double rightY){
        rightY = leftY;
        _length = rightX - leftX;
        _poCenter = new Point ((rightX + leftX)/2, leftY);
    }

    /**
     * Constructs a new segment using a center point and the segment length.
     * @param poCenter the Center Point
     * @param length the segment length
     */
    public Segment2(Point poCenter, double length){
        _length = length;
        _poCenter = new Point(poCenter);
    }

    /**
     * Constructs a new segment using two Points.
     * If the y coordinates are different, change the y of the right point to be equal to the y of the left point.
     * @param left the left point of the segment
     * @param right the right point of the segment
     */
    public Segment2(Point left, Point right){
        Point newRight = new Point(right); // I created a new point to avoid aliasing.
        newRight.setY(left.getY());
        _length = left.distance(newRight);
        _poCenter = new Point((left.getX() + right.getX()) / 2, left.getY());
    }

    /**
     * Copy Constructor. Construct a segment using a reference segment.
     * @param other the reference segment
     */
    public Segment2(Segment2 other){
        this(other._poCenter, other._length);
    }

    /**
     * Change the segment size by moving the right point by delta.
     * Will be implemented only for a valid delta: only if the new right point remains the right point.
     * @param delta the length change
     */
    public void changeSize(double delta){
        if (this.getPoRight().getX() + delta >= this.getPoLeft().getX()){ //I didn't use move method from Point because i need to make sure the right point does not move from right to left (greater then the left point).
            _length = this.getPoRight().getX() + delta - this.getPoLeft().getX();
            _poCenter = new Point((delta/2) + _poCenter.getX(), this._poCenter.getY());
        }
    }

    /**
     * Check if the reference segment is equal to this segment.
     * @param other the reference segment
     * @return True if the reference segment is equal to this segment
     */
    public boolean equals(Segment2 other){
        return _poCenter.equals(other._poCenter) && _length == other._length;
    }

    /**
     * Returns the segment length.
     * @return The segment length
     */
    public double getLength(){
        return _length;
    }

    /**
     * Returns the left point of the segment.
     * @return The left point of the segment
     */
    public Point getPoLeft(){
        return new Point(_poCenter.getX() - (_length/2), _poCenter.getY());
    }

    /**
     * Returns the right point of the segment.
     * @return The right point of the segment
     */
    public Point getPoRight(){
        return new Point(_poCenter.getX() + (_length/2), _poCenter.getY());
    }

    /**
     * Check if this segment is above a reference segment.
     * @param other the reference segment
     * @return True if this segment is above the reference segment
     */
    public boolean isAbove(Segment2 other){
        return _poCenter.isAbove(other._poCenter);
    }

    /**
     * Check if this segment is bigger than a reference segment.
     * @param other the reference segment
     * @return True if this segment is bigger than the reference segment
     */
    public boolean isBigger(Segment2 other){
        return _length > other._length;
    }

    /**
     * Check if this segment is left of a received segment.
     * @param other the reference segment
     * @return True if this segment is left to the reference segment
     */
    public boolean isLeft(Segment2 other){
        return this.getPoRight().isLeft(other.getPoLeft());
    }

    /**
     * Check if this segment is right of a received segment.
     * @param other the reference segment
     * @return True if this segment is right to the reference segment
     */
    public boolean isRight(Segment2 other){
        return other.isLeft(this); //Applying the opposite method to the object of comparison saves writing and ensures accuracy
    }

    /**
     * Check if this segment is under a reference segment.
     * @param other the reference segment
     * @return True if this segment is under the reference segment
     */
    public boolean isUnder(Segment2 other){
        return other.isAbove(this); //Applying the opposite method to the object of comparison saves writing and ensures accuracy
    }

    /**
     * Move the segment horizontally by delta. Will be implemented only for a valid delta
     * @param delta the displacement size
     */
    public void moveHorizontal(double delta){
        if ((this.getPoLeft().getX() + delta) >= DEFAULT_VAL)
            _poCenter.move(delta, 0); // I used zero and not regular, because the intention is not to move at all (if the intention was to move by default I would use regular)
    }

    /**
     * Move the segment vertically by delta. Will be implemented only for a valid delta
     * @param delta the displacement size
     */
    public void moveVertical(double delta){
        if(this._poCenter.getY() + delta >= DEFAULT_VAL)
            _poCenter.move(0, delta ); // I used zero and not regular, because the intention is not to move at all (if the intention was to move by default I would use regular)
    }

    /**
     * Returns the overlap size of this segment and a reference segment.
     * @param other the reference segment
     * @return The overlap size
     */
    public double overlap(Segment2 other){
        if(this.getPoLeft().getX() <= other.getPoLeft().getX() && other.getPoLeft().getX() <= this.getPoRight().getX()){ //Check if the other line starts within the original line and continues to the right
            if (other.getPoRight().getX() <= this.getPoRight().getX())
                return other.getLength();
            else
                return (this.getPoRight().getX() - other.getPoLeft().getX());
        }
        else if(this.getPoLeft().getX() <= other.getPoRight().getX() && other.getPoRight().getX() <= this.getPoRight().getX()){ //Check if the other line starts within the original line and continues to the left
            if (this.getPoLeft().getX() <= other.getPoLeft().getX()) //Check whether the other line is contained within the original line
                return other.getLength();
            else
                return (other.getPoRight().getX() - this.getPoLeft().getX());
        }
        else
            return DEFAULT_VAL;
    }

    /**
     * Check if a point is located on the segment.
     * @param p a point to be checked
     * @return True if p is on this segment
     */
    public boolean pointOnSegment(Point p){
        if(p.getY() != _poCenter.getY())
            return false;
        else
            return (this.getPoLeft().getX() <= p.getX() && p.getX() <= this.getPoRight().getX());
    }

    /**
     * Return a string representation of this segment in the format (3.0,4.0)---(3.0,6.0).
     * @overrides toString in class java.lang.Object
     * @return String representation of this segment
     */
    public java.lang.String toString(){
        return (this.getPoLeft() + "---" + this.getPoRight());
    }

    /**
     * Compute the trapeze perimeter, which constructed by this segment and a reference segment.
     * @param other the reference segment
     * @return The trapeze perimeter
     */
    public double trapezePerimeter(Segment2 other){
        return (this.getLength() + other.getLength() + this.getPoLeft().distance(other.getPoLeft()) + this.getPoRight().distance(other.getPoRight()));
    }
}
