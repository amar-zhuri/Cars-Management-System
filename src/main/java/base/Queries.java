package base;

import cars.CarsSet;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Defines queries for your {@link Repository} implementation.
 *
 * @param <CarsSet> the type of your entity (first class)
 * @param <Specifications> the type of your nested class (second class)
 * @param <CarType>> the type of your enum class
 */
public interface Queries<CarsSet, Specifications, CarType extends Enum<cars.CarsSet.CarType>> {

    /**
     * Retrieves the highest price among all CarsSet objects stored in the repository.
     *
     * @return The highest price found among CarsSet instances.
     * @implNote This method iterates through all CarsSet objects in the repository to determine the highest price.
     *           It computes and returns the maximum price found.
     */
    long getHighestPrice();

    /**
     * Retrieves a set of unique names of CarsSet objects stored in the repository.
     *
     * @return A Set containing unique names of CarsSet instances.
     * @implNote This method iterates through all CarsSet objects in the repository, extracting their names
     *           and adding them to a Set to ensure uniqueness.
     *           The method's operation does not modify or affect the repository or its stored entities.
     */

    Set<String> getCarsName();

    /**
     * Retrieves a list of CarsSet objects.
     *
     * @return A List containing CarsSet instances sorted by price in descending order.
     * @implNote This method retrieves all CarsSet objects from the repository, creates a new list,
     *           and sorts the list based on the prices of the CarsSet objects in descending order.
     *           The method's operation does not modify or affect the repository or its stored entities.
     */
    List<cars.CarsSet> getCarsOrderByPriceDesc();


    /**
     * Retrieves a Map of CarsSet objects.
     *
     * @return A Map<CarsSet.CarType, Long> where keys are CarType enums and values represent the count of CarsSet instances for each CarType.
     * @implNote This method iterates through all CarsSet objects in the repository, categorizes them by CarType,
     *           and counts the occurrences of each CarType using a Map.
     *           The method's operation does not modify or affect the repository or its stored entities.
     */
    Map<cars.CarsSet.CarType,Long> getCountOfCarsByCarType();

    /**
     * Retrieves a Map grouping CarsSet objects.
     *
     * @return A Map<CarsSet.CarType, Set<CarsSet>> where keys are CarType enums, and values are Sets containing CarsSet instances for each CarType.
     * @implNote This method categorizes CarsSet objects by their CarType and organizes them into Sets within a Map.
     */
    Map<cars.CarsSet.CarType, Set<CarsSet>> getCarsByCarTypes();

    /**
     * Retrieves a hierarchical mapping of CarsSet objects.
     *
     * @return A Map<CarsSet.CarType, Map<String, CarsSet>> where keys are CarType enums, and values are inner Maps.
     *         Inner Maps contain unique codes as keys associated with respective CarsSet instances for each CarType.
     * @implNote This method categorizes CarsSet objects by their CarType and organizes them into a nested Map structure
     *           where each CarType maps to an inner Map containing unique codes mapped to corresponding CarsSet instances.
     */
    Map<cars.CarsSet.CarType, Map<String, CarsSet>> getCarsByCarTypeAndCodes();
}
