/* Circle class calculates the areas and the perimeters of the excircle and the incircle of a the rectangle.
Written by Evyatar Ben David, on 25.10.2020. */
import java.util.Scanner;
public class Circle {
    /*
    This method gets two points of rectangle from the user, then calculates the areas and the perimeters
    of the excircle and the incircle of a the rectangle.
     */
    public static void main(String[] args) {
        // Declaration of variables and constants
        final double EX_RADIUS, EX_DIAMETER, EX_PERIMETER, EX_AREA; // The blocking circle constants.
        final double IN_DIAMETER, IN_RADIUS, IN_PERIMETER, IN_AREA; // The blocked circle constants.
        int leftUpX, leftUpY;
        int rightDownX, rightDownY;
        
        // Getting data from the user
        Scanner scan = new Scanner(System.in);
        System.out.println("This program calculates the areas " +
            "and the perimeters of the excircle and the incircle " +
            " of a given rectangle ");
        System.out.println("Please enter the two coordinates of the " + "left-upper point of the rectangle: ");
        leftUpX = scan.nextInt();
        leftUpY = scan.nextInt();
        System.out.println("Please enter the two coordinates of the " + "bottom right point of the rectangle: ");
        rightDownX = scan.nextInt();
        rightDownY = scan.nextInt();

        /* Calculation of the blocking circle
        Radius is half diameter. The perimeter formula is radius * 2 * PI. The area is PI * radius to the power of 2*/
        EX_DIAMETER = Math.sqrt((Math.pow((leftUpX - rightDownX), 2)) + (Math.pow((leftUpY - rightDownY), 2)) );
        EX_RADIUS = EX_DIAMETER / 2;
        EX_PERIMETER = 2 * Math.PI * EX_RADIUS;
        EX_AREA = Math.PI * Math.pow(EX_RADIUS, 2);
        // Calculation of the blocked circle
        IN_DIAMETER = leftUpY - rightDownY;
        IN_RADIUS = IN_DIAMETER / 2;
        IN_PERIMETER = 2 * Math.PI * IN_RADIUS;
        IN_AREA = Math.PI * Math.pow(IN_RADIUS, 2);
        System.out.println("Incircle: radius = " + Math.abs(IN_RADIUS) + ", area = " + Math.abs(IN_AREA) + ", perimeter = " + Math.abs(IN_PERIMETER));
        System.out.println("Excircle: radius = " + Math.abs(EX_RADIUS) + ", area = " + Math.abs(EX_AREA) + ", perimeter = " + Math.abs(EX_PERIMETER));
    }
}