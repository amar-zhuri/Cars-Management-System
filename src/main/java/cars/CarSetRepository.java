package cars;
import base.Queries;
import base.Repository;
import lombok.NonNull;

import java.io.IOException;
import java.util.*;

/**
 * The CarSetRepository class serves as a repository for managing sets of CarsSet objects.
 * It extends the Repository class and implements various querying functionalities for CarsSet instances
 * based on specifications and car types.
 *
 */
public class CarSetRepository extends Repository<CarsSet>
        implements Queries<CarsSet, CarsSet.Specifications, CarsSet.CarType> {
    /**
     * A constructor for initializing the repository.
     *
     * @throws IOException if an I/O error happens
     */
    public CarSetRepository() throws IOException {
        super(CarsSet.class);
    }

    @Override
    public long getHighestPrice() {
        // Retrieve all CarsSet objects from the repository
        List<CarsSet> cars = getAll();

        // Initialize the maximum price with the first CarsSet object's price
        long max = cars.get(0).getPrice();

        // Iterate through the list to find the highest price
        for (int i = 1; i < cars.size(); i++) {
            // Compare the price of each CarsSet object with the current maximum price
            if(cars.get(i).getPrice() > max ){
                // If a higher price is found, update the maximum price
                max = cars.get(i).getPrice();
            }
        }
        // Return the highest price found among CarsSet objects in the repository
        return max;
    }

    @Override
    public Set<String> getCarsName() {
        // Initialize a Set to store unique names of CarsSet objects
        Set<String> names = new HashSet<>();

        // Iterate through all CarsSet objects in the repository
        for (CarsSet carsSet : getAll()){
            // Add the name of each CarsSet object to the Set
            names.add(carsSet.getName());
        }
        // Return the Set containing unique names of CarsSet instances
        return names;
    }

    @Override
    public List<CarsSet> getCarsOrderByPriceDesc() {
        // Retrieve all CarsSet objects from the repository and store them in a new ArrayList
        List<CarsSet> cars = new ArrayList<>(getAll());

        // Sort the list of CarsSet objects based on price in descending order using a custom Comparator
        cars.sort(new Comparator<CarsSet>() {
            @Override
            public int compare(CarsSet o1, CarsSet o2) {
                // Compare prices in descending order using Integer.compare
                return -Integer.compare(o1.getPrice(), o2.getPrice());

            }
        });

        // Return the sorted List of CarsSet instances by price in descending order
        return cars;
    }

    @Override
    public Map<CarsSet.CarType, Long> getCountOfCarsByCarType() {
        // Initialize a Map to store the count of CarsSet objects categorized by CarType
        Map<CarsSet.CarType, Long> result = new EnumMap<>(CarsSet.CarType.class);

        // Iterate through all CarsSet objects in the repository
        for (CarsSet carsSet : getAll()){
            // Get the CarType of each CarsSet object and update the count in the Map
            result.put(
                    carsSet.getCartype(),
                    result.getOrDefault(carsSet.getCartype(),0L) + 1
            );
        }

        // Return the Map containing the count of CarsSet instances for each CarType
        return  result;
    }

    @Override
    public Map<CarsSet.CarType, Set<CarsSet>> getCarsByCarTypes() {
        // Initialize a Map to group CarsSet objects by their CarType
        Map<CarsSet.CarType, Set<CarsSet>> result = new EnumMap<>(CarsSet.CarType.class);

        // Create an entry for each CarType in the Map with an empty Set
        for(CarsSet.CarType carType : CarsSet.CarType.values()){
            result.put(carType, new HashSet<>());
        }

        // Iterate through all CarsSet objects in the repository
        for (CarsSet carsSet : getAll()){
            // Add each CarsSet object to the corresponding Set based on its CarType
            result.get(carsSet.getCartype()).add(carsSet);
        }

        // Return the Map grouping CarsSet instances by CarType
        return result;
    }

    @Override
    public Map<CarsSet.CarType, Map<String, CarsSet>> getCarsByCarTypeAndCodes() {
        // Initialize a Map to store CarsSet objects categorized by CarType and unique codes
        Map<CarsSet.CarType, Map<String, CarsSet>> result = new EnumMap<>(CarsSet.CarType.class);

        // Iterate through all CarsSet objects in the repository
        for (CarsSet carsSet : getAll()){
            // Check if the outer Map does not contain the CarType as a key
            if (!result.containsKey(carsSet.getCartype())){
                // If the CarType is not present, add a new entry with an inner TreeMap
                result.put(carsSet.getCartype(), new TreeMap<>());
            }

            // Add the CarsSet object to the inner Map with its unique code as the key
            result.get(carsSet.getCartype())
                    .put(carsSet.getCode(),carsSet);
        }

        // Return the hierarchical Map containing CarsSet instances categorized by CarType and unique codes
        return result;
    }
}
