package zenika.api;

import io.quarkus.test.junit.SubstrateTest;
import zenika.api.success.ObsResourceTest;

@SubstrateTest
public class NativeObsResourceIT extends ObsResourceTest {

    // Execute the same tests but in native mode.
}
