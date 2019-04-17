package zenika.api.success;

import zenika.api.obs.ObsService;

import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.util.HashSet;
import java.util.Set;

@Alternative
@Priority(1)
@ApplicationScoped
public class MockObsService extends ObsService {

    @Override
    public Set<String> getScenes() {
        HashSet<String> result = new HashSet<>();
        result.add("Main");
        result.add("OBS");

        return result;
    }
}
