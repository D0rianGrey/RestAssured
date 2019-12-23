import config.TestConfig;
import org.testng.annotations.Test;

import static constants.Constants.Actions.*;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

public class JsonPlaceHolderTest extends TestConfig {

    @Test
    public void get() {
        given().queryParam("postId", 1).log().uri().
                when().get(JSONPLACEHOLDER_GET).
                then().log().body().statusCode(200);
    }

    @Test
    public void put() {
        String putBodyJson = "{\n" +
                "     \"id\": 1,\n" +
                "      \"title\": \"foo\",\n" +
                "      \"body\": \"bar\",\n" +
                "      \"userId\": 1\n" +
                "    }";

        given().body(putBodyJson).log().uri().
                when().put(JSONPLACEHOLDER_PUT).
                then().log().body().statusCode(200);
    }

    @Test
    public void delete() {
        given().log().uri().
                when().delete(JSONPLACEHOLDER_DELETE).
                then().log().body().statusCode(200);
    }

    @Test
    public void postWithJson() {

        String postJsonBody = "{\n" +
                "      \"title\": \"foo\",\n" +
                "      \"body\": \"bar\",\n" +
                "      \"userId\": 1\n" +
                "    }";
        given().body(postJsonBody).log().all().
                when().post(JSONPLACEHOLDER_POST).
                then().spec(responseSpecificationForPost).log().body();
    }

    @Test
    public void postWithXml() {
        String postXmlBody = "<Company>\n" +
                "  <Employee>\n" +
                "      <FirstName>Tanmay</FirstName>\n" +
                "      <LastName>Patil</LastName>\n" +
                "      <ContactNo>1234567890</ContactNo>\n" +
                "      <Email>tanmaypatil@xyz.com</Email>\n" +
                "      <Address>\n" +
                "           <City>Bangalore</City>\n" +
                "           <State>Karnataka</State>\n" +
                "           <Zip>560212</Zip>\n" +
                "      </Address>\n" +
                "  </Employee>\n" +
                "</Company>";

        given().spec(requestSpecificationXml).body(postXmlBody).log().all().
                when().post("").
                then().spec(responseSpecificationForGet).log().body();
    }


}
