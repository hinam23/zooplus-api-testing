package web.rest.DelRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;


import static util.Constant.BASE_URL;

public class DelApiTest {

    RequestSpecification request;
    ObjectMapper mapper;

    DelApiTest() {
        RestAssured.baseURI = BASE_URL;
        request = RestAssured.given();
        mapper = new ObjectMapper();
    }

    @Test
    public void deletionFailure() {
        Response response = request.delete(BASE_URL.concat("/-1"));
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void invalidRequestFailure() {
        Response response = request.given()
                .contentType(ContentType.JSON)
                .delete ("/4");
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
    }
}

