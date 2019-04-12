package zenika.obs.api.success;

import zenika.obs.api.ObsRemote;

import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.util.HashSet;
import java.util.Set;

@Alternative
@Priority(1)
@ApplicationScoped
public class MockObsRemote extends ObsRemote {

    @Override
    public Set<String> getScenes() {
        HashSet<String> result = new HashSet<>();
        result.add("Main");
        result.add("OBS");

        return result;
    }
}
