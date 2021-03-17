import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
public class DummyAPITests {


    @Before
    public void setup() {
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1/";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Test
    public void getResponseBodyTest() {
        RestAssured.basePath = "/employees";
        given().when().get().then().log().all();
    }

    @Test
    public void verifyTheStatusCodeTest() {
        RestAssured.basePath = "/employees";
        given().when().get().then().statusCode(200);
    }

    @Test
    public void verifyTheEmployee() {
        RestAssured.basePath = "/employee/1";
        given().when().get().then().body("data.employee_name", equalTo("Tiger Nixon"))
                .body("data.employee_salary", equalTo(320800)).body("data.employee_age", equalTo(61)).body("data.profile_image", equalTo(""))
                .body("message", equalTo("Successfully! Record has been fetched.")).statusCode(200);

    }
    @Test
    public void createANewEmployee() {
        NewEmployee employee = new NewEmployee();
        employee.setName("Test Employee");
        employee.setSalary("2000");
        employee.setAge("25");

        given()
                .contentType("application/json")
                .body(employee)
                .when()
                .post("/create").then().body("data.name", equalTo("Test Employee"));
    }

    }
