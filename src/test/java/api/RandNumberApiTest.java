package api;

import io.restassured.RestAssured;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static api.RandomOrgConstants.BASE_URI;
import static api.RandomOrgConstants.GET_INTEGERS;
import static common.Log.log;
import static io.restassured.RestAssured.given;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.Matchers.lessThan;

public class RandNumberApiTest {

    @Before
    public void setup() {
        RestAssured.baseURI = BASE_URI + GET_INTEGERS;
    }

    @Test
    public void GetIntegersAssertOkStatus(){
        int statusCode = given()
                .when()
                .get("?num=10&min=1&max=6&col=1&base=10&format=plain&rnd=new")
                .getStatusCode();

        Assert.assertEquals("Status code wasn't OK", 200, statusCode);
    }

    @Test
    public void GetIntegersAssertMinMaxValue(){
        List<Integer> integerList= Arrays.stream(given()
                .when()
                .get("?num=250&min=-100&max=100&col=1&base=10&format=plain&rnd=new")
                .then().log().headers()
                .extract().body().asString()
                .split("\n"))
                .map(Integer::valueOf)
                .sorted()
                .collect(Collectors.toList());

        Assert.assertTrue("Incorrect min value", integerList.get(0) >= -100);
        Assert.assertTrue("Incorrect max value", integerList.get(integerList.size() - 1) <= 100);
    }

    @Test
    public void GetIntegersAssertCount(){
        String[] integerList= (given()
                .when()
                .get("?num=250&min=-100&max=100&col=1&base=10&format=plain&rnd=new")
                .then()
                .extract().body().asString()
                .split("\n"));

        Assert.assertEquals("Wrong generated integers count", 250, integerList.length);
    }

    @Test
    public void GetIntegersAssertResponseTime(){
        given()
                .when()
                .get("?num=100&min=1&max=6&col=1&base=10&format=plain&rnd=new")
                .then()
                .time(lessThan(1L), SECONDS);
    }

    @Test
    public void GetIntegersPrintResponseTime(){
        long responseTime = given()
                .when()
                .get("?num=1000&min=1&max=100&col=10&base=10&format=plain&rnd=new")
                .time();
        log("Time: " + responseTime + "ms");
    }
}
