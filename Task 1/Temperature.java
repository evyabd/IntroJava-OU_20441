/* Temperature class converts temperature from one to another temperature scale.
Written by Evyatar Ben David on 18.10.2020.
 */
import java.util.Scanner;
public class Temperature {
    /*
    This method gets temperature scale and degrees from the user, then prints the temperature at Celsius and Fahrenheit and Kelvin units.
     */
    public static void main(String[] args) {
        //Declaration of variables and constants.
        final double ABSOLUTE_ZERO = 273.15, WATER_STAGNATION = 32;
        final double CELSIUS_TO_FAHRENHEIT_RATIO = 1.8, FAHRENHEIT_TO_CELSIUS_RATIO = 0.555555556; // One Cels unit is 1.8 Farh unit.
        double cDegree, fDegree, kDegree; // Variable to save the calculated temperature.
        char tempType;
        double tempDegrees;

        //Getting data from the user.
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter a letter to indicate the type of temperature, and its degree:");
        tempType = scan.next().charAt(0);
        tempDegrees = scan.nextDouble();

        // calculate the temperature and print.
        switch (tempType) {
            case 'C':
            System.out.println(tempDegrees + " C" );
            fDegree = (tempDegrees * CELSIUS_TO_FAHRENHEIT_RATIO) + WATER_STAGNATION; //Celsius zero is 32 Fahrenheit zero.
            System.out.println(fDegree + " F");
            kDegree = tempDegrees + ABSOLUTE_ZERO; // Celsius zero is the frozen water, Kelvin zero is the absolute zero.
            System.out.println(kDegree + " K");
            break;
            case 'F':
            cDegree = (tempDegrees - WATER_STAGNATION) * FAHRENHEIT_TO_CELSIUS_RATIO;
            System.out.println(cDegree + " C");
            System.out.println(tempDegrees + " F");
            kDegree = cDegree + ABSOLUTE_ZERO;
            System.out.println(kDegree + " K");
            break;
            case 'K':
            cDegree = tempDegrees - ABSOLUTE_ZERO;
            System.out.println(cDegree + " C");
            fDegree = (cDegree * CELSIUS_TO_FAHRENHEIT_RATIO) + WATER_STAGNATION;
            System.out.println(fDegree + " F");
            System.out.println(tempDegrees + " K");
            break;
        }
    }
}