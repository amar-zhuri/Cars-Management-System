package cars;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Comparator;
import java.util.Objects;
import java.util.Set;

/**
 * The CarsSet class represents a car entity with essential attributes: code, name, year, cartype, price, and specifications.
 * It enables instance comparison via a designated Comparator and manages car specification details.
 *
 * <p><strong>Attributes:</strong>
 * <ul>
 *     <li><code>code</code>: The unique identifier for the car.</li>
 *     <li><code>name</code>: The model or name of the car.</li>
 *     <li><code>year</code>: The manufacturing year of the car.</li>
 *     <li><code>cartype</code>: The type of the car, categorized via the CarType enum.</li>
 *     <li><code>price</code>: The price of the car.</li>
 *     <li><code>specifications</code>: A Set containing car specifications (e.g., features, configurations).
 *         Excluded from toString to prevent irrelevant details in the output.</li>
 * </ul>
 *
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString

public class CarsSet implements Comparable<CarsSet>{
    /**
     * A Comparator to compare instances of CarsSet based on specific criteria.
     * Used for sorting and comparison operations on CarsSet instances.
     */
    public static final Comparator<CarsSet> COMPARATOR = new CarsSetComparator();
    /**
     * The unique identifier code for the car.
     */
    @EqualsAndHashCode.Include
    private String code;

    /**
     * The name or model of the car.
     */
    private String name;

    /**
     * The manufacturing year of the car.
     */
    private int year;

    /**
     * The type of the car (enum: CarType).
     */
    private CarType cartype;

    /**
     * The price of the car.
     */
    private int price;

    /**
     * A Set containing the specifications related to the car (e.g., features, configurations).
     * Excluded from the toString method to prevent unnecessary details in the output.
     */
    @ToString.Exclude
    private Set<Specifications> specifications;

    /**
     * Compares this CarsSet instance with another CarsSet based on the criteria defined in the Comparator.
     *
     * @param o The CarsSet object to compare with this instance.
     * @return A value indicating the comparison result based on the defined Comparator.
     *         Returns a negative integer if this instance is less than the given CarsSet (o),
     *         zero if they are equal, or a positive integer if this instance is greater than the given CarsSet.
     * @implNote This method uses the specified Comparator (COMPARATOR) to compare two CarsSet instances based on their attributes.
     *           The comparison logic defined in the Comparator is applied to determine the relative ordering between instances.
     *           It delegates the comparison to the Comparator's compare method, allowing customized comparison behavior.
     */
    @Override
    public int compareTo(CarsSet o) {
        return Objects.compare(this,o,COMPARATOR);
    }

    /**
     * The Specifications class represents detailed specifications of a car, such as ID, name, power (in horsepower), and weight (in kilograms).
     * It encapsulates information about specific attributes related to a car's specifications.
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
    @AllArgsConstructor
    @Getter
    @Setter
    @ToString
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)

    public static class Specifications{
        /**
         * The unique identifier for the specifications.
         */
        @EqualsAndHashCode.Include
        private final String id;

        /**
         * The name or description of the specifications.
         */
        private final String name;

        /**
         * The power of the car in horsepower.
         */
        private final int power_hp;

        /**
         * The weight of the car in kilograms.
         */
        private final int weight_in_kg;

    }

    /**
     * The CarType enum represents different types or categories of cars.
     * Each enum constant signifies a distinct car type, providing a classification for cars.
     */
    public enum CarType{

        /**
         * Represents the Limousine car type.
         */
        @JsonProperty("Limousine") LIMOUSINE,

        /**
         * Represents the Sport car type.
         */
        @JsonProperty("Sport") SPORT,

        /**
         * Represents the SUV (Sports Utility Vehicle) car type.
         */
        @JsonProperty("SUV") SUV,

        /**
         * Represents the Sedan car type.
         */
        @JsonProperty("Sedan") SEDAN,

        /**
         * Represents the Super car type.
         */
        @JsonProperty("Super") SUPER,

        /**
         * Represents the Pickup truck car type.
         */
        @JsonProperty("Pickup") PICKUP,

        /**
         * Represents the Compact car type.
         */
        @JsonProperty("Compact") COMPACT

    }

    /**
     * Comparator for sorting CarsSet instances based on multiple criteria.
     * Compares CarsSet objects first by name, then by year, and finally by code.
     */
    public static class CarsSetComparator implements Comparator<CarsSet>{
        /**
         * Compares two CarsSet objects based on their name, year, and code.
         *
         * @param o1 the first CarsSet object to compare
         * @param o2 the second CarsSet object to compare
         * @return a negative integer if the first CarsSet object is "less than" the second one,
         *         zero if they are "equal," and a positive integer if the first CarsSet object
         *         is "greater than" the second one, based on the defined criteria.
         *         Comparison Priority:
         *         1. Name: Objects are compared by their names using natural ordering.
         *         2. Year: If names are equal, comparison is based on the year in descending order.
         *         3. Code: If both name and year are the same, comparison is done based on the code
         *            in reverse order.
         */
        @Override
        public int compare(CarsSet o1, CarsSet o2) {
            // Compare by name
            if (!Objects.equals(o1.getName(), o2.getName())){
                return Objects.compare(o1.getName(),o2.getName(),Comparator.naturalOrder());
            }
            // If names are equal, compare by year
            if(o1.getYear() != o2.getYear()){
                return -Integer.compare(o1.getYear(),o2.year);
            }
            // If both name and year are equal, compare by code in reverse order
            return Objects.compare(o1.getCode(),o2.getCode(), Comparator.reverseOrder());
        }
    }

}
