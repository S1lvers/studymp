import org.junit.Test;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class EchoIT {

    @Test
    public void firstEchoTest() {
        get("/test/echo/hello").then().assertThat().body("message", equalTo("hello"));
    }
}