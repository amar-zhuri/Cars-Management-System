package cars;

import java.io.IOException;

/**
 * The TestQueries class serves as a testing ground for the functionalities implemented within the CarSetRepository.
 * It demonstrates the usage of various querying methods available in the repository.
 *
 * <p>This class executes a series of queries on the CarSetRepository:
 * <ul>
 *     <li>Instantiates a CarSetRepository to perform querying operations.</li>
 *     <li>Displays the repository object.</li>
 *     <li>Retrieves and displays the highest price among the stored car sets.</li>
 *     <li>Displays the unique names of cars stored in the repository.</li>
 *     <li>Displays a list of cars sorted in descending order based on their prices.</li>
 *     <li>Displays a grouping of cars based on their car types.</li>
 *     <li>Displays a hierarchical mapping of cars categorized by car types and unique codes.</li>
 *     <li>Displays a count of cars categorized by their car types.</li>
 * </ul>
 */
public class TestQueries {
    /**
     * Executes a series of queries on the CarSetRepository and displays the results.
     *
     * @param args The command-line arguments (not utilized in this context).
     * @throws IOException if an I/O error occurs during the execution of queries in the repository.
     */
    public static void main(String[] args) throws IOException {
        final var repo = new CarSetRepository();
        // Displaying the repository object
        System.out.println(repo);
        System.out.println("---------------");

        // Retrieving and displaying the highest price among the stored car sets
        System.out.println(repo.getHighestPrice());
        System.out.println("---------------");

        // Displaying the unique names of cars stored in the repository
        System.out.println(repo.getCarsName());
        System.out.println("---------------");

        // Displaying a list of cars sorted in descending order based on their prices
        System.out.println(repo.getCarsOrderByPriceDesc());
        System.out.println("---------------");

        // Displaying a grouping of cars based on their car types
        System.out.println(repo.getCarsByCarTypes());
        System.out.println("---------------");

        // Displaying a hierarchical mapping of cars categorized by car types and unique codes
        System.out.println(repo.getCarsByCarTypeAndCodes());
        System.out.println("---------------");

        // Displaying a count of cars categorized by their car types
        System.out.println(repo.getCountOfCarsByCarType());

    }
}
