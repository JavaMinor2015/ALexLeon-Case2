package javaminor.al.repository.abs;

import java.util.List;

/**
 * Created by alex on 11/22/15.
 */
public class MockRepository extends Repository<String> {

    @Override
    public List<String> getAll() {
        return super.getAll(String.class);
    }
}
