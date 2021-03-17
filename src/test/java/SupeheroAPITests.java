import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class SupeheroAPITests {

    @Before
    public void setup() {
        RestAssured.baseURI = "https://www.superheroapi.com/api.php/3960500697307844/";
    }


    @Test
    public void getResponseBodyTest() {
        RestAssured.basePath = "/1";
        given().when().get().then().log().all();
    }

    @Test
    public void verifyTheStatusCodeTest() {
        RestAssured.basePath = "/1";
        given().when().get().then().statusCode(200);
    }

    @Test
    public void verifyNameOfSuperHeroTest() {
        RestAssured.basePath = "/1";
        given().when().get().then().body(containsString("A-Bomb"));
    }

    @Test
    public void verifyTheHeightOfSuperHeroTest() {
        RestAssured.basePath = "/1";
        given().when().get().then().body("appearance.height", containsInAnyOrder("6'8", "203 cm"));
    }

    @Test
    public void verifyTheLastSuperHeroId() {
        RestAssured.basePath = "/733";
        given().when().get().then().body("response", equalTo("error")).body("error", equalTo("invalid id"));
    }


    @Test
    public void verifyThePowerStatsMethod() {
        RestAssured.basePath = "/1/powerstats";
        given().when().get().then().body("intelligence", equalTo("38"))
                .body("strength", equalTo("100"))
                .body("speed", equalTo("17"))
                .body("durability", equalTo("80"))
                .body("power", equalTo("24"))
                .body("combat", equalTo("64"));
    }


    @Test
    public void verifyTheBiographyMethod() {
        RestAssured.basePath = "/1/biography";
        given().when().get().then()
                .body("alter-egos", equalTo("No alter egos found."))
                .body("aliases", containsInAnyOrder("Rick Jones"))
                .body("place-of-birth", equalTo("Scarsdale, Arizona"))
                .body("first-appearance", equalTo("Hulk Vol 2 #2 (April, 2008) (as A-Bomb)"))
                .body("publisher", equalTo("Marvel Comics"))
                .body("alignment", equalTo("good"));
    }

    @Test
    public void verifyTheAppearanceMethod() {
        RestAssured.basePath = "/1/appearance";
        given().when().get().then()
                .body("gender", equalTo("Male"))
                .body("race", equalTo("Human"))
                .body("height", containsInAnyOrder("6'8", "203 cm"))
                .body("weight", containsInAnyOrder("980 lb", "441 kg" ))
                .body("eye-color", equalTo("Yellow"))
                .body("hair-color", equalTo("No Hair"));
    }
    @Test
    public void verifyTheWorkMethod() {
        RestAssured.basePath = "/1/work";
        given().when().get().then()
                .body("occupation", equalTo("Musician, adventurer, author; formerly talk show host"))
                .body("base", equalTo("-"));

    }

    @Test
    public void verifyTheConnectionsMethod() {
        RestAssured.basePath = "/1/connections";
        given().when().get().then()
                .body("group-affiliation", startsWith("Hulk Family; Excelsior (sponsor), Avengers (honorary member)"))
                .body("relatives", startsWith("Marlo Chandler-Jones (wife); Polly (aunt);"));

    }

    @Test
    public void verifyTheImageMethod() {
        RestAssured.basePath = "/1/image";
        given().when().get().then()
                .body("url", endsWith(".jpg"));


    }

}
