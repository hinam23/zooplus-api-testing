package web.rest.PostRequests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.Pet;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.apache.http.protocol.HTTP;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static util.Constant.BASE_URL;
import static util.TestUtil.intializePetObj;


public class PostApiTest {
    RequestSpecification request;
    ObjectMapper mapper;

    PostApiTest() {
        RestAssured.baseURI = BASE_URL;
        request = given();
        mapper = new ObjectMapper();
    }

    @Test
    public void creationSuccess() throws JsonProcessingException {
        Pet petRequest = intializePetObj();
        request.body(petRequest);
        request.header(HTTP.CONTENT_TYPE, "application/json");
        Response response = request.post();
        Pet petResponse = mapper.readValue(response.asString(), Pet.class);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        Assert.assertEquals(petResponse.getName(), petRequest.getName());
        Assert.assertEquals(petResponse.getCategory().getName(), petRequest.getCategory().getName());
        Assert.assertEquals(petResponse.getCategory().getId(), petRequest.getCategory().getId());
    }

    @Test
    public void creationFailure() {
        request.header(HTTP.CONTENT_TYPE, "application/json");
        Response response = request.post(BASE_URL);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_METHOD_NOT_ALLOWED);

    }

    @Test
    public void petUpdateSuccess() {
        Response response = given().urlEncodingEnabled(true)
                .param("name", "Black Cat")
                .param("status", "available")
                .post("/1");
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
    }


}

