package base;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A repository for storing entities.
 *
 * @param <CarsSet> the type of the entities
 */
public abstract class Repository<CarsSet> {
    public static final ObjectMapper MAPPER = new ObjectMapper();
    private final List<CarsSet> entities;

    /**
     * A constructor for initializing the repository.
     *
     * @param clazz the type of your entity (first class)
     * @throws IOException if an I/O error happens
     */
    protected Repository(
            @NonNull final Class<CarsSet> clazz) throws IOException {

        this.entities = MAPPER.readValue(
                Repository.class.getResourceAsStream("../data.json"),
                MAPPER.getTypeFactory().constructCollectionType(ArrayList.class, clazz)
        );
    }

    /**
     * Returns the entities.
     *
     * @return the list of entities
     */
    public final List<CarsSet> getAll() {
        return Collections.unmodifiableList(this.entities);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String toString() {
        return entities.stream()
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }
}
