package web.rest.PutRequest;

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

import static util.Constant.BASE_URL;
import static util.TestUtil.intializePetObj;

public class PutApiTest {

    RequestSpecification request;
    ObjectMapper mapper;

    PutApiTest() {
        RestAssured.baseURI = BASE_URL;
        request = RestAssured.given();
        mapper = new ObjectMapper();
    }

    @Test
    public void updateRecordSuccess() throws JsonProcessingException {
        Pet petRequest = intializePetObj();
        request.body(petRequest);
        request.header(HTTP.CONTENT_TYPE, "application/json");
        Response response = request.put(BASE_URL);
        Pet petResponse = mapper.readValue(response.asString(), Pet.class);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        Assert.assertEquals(petResponse.getName(), petRequest.getName());
        Assert.assertEquals(petResponse.getCategory().getName(), petRequest.getCategory().getName());
        Assert.assertEquals(petResponse.getCategory().getId(), petRequest.getCategory().getId());
    }

    @Test(enabled = false)
    public void invalidIdSupplied() {
        request.header(HTTP.CONTENT_TYPE, "application/json");
        Response response = request.put(BASE_URL.concat("/%"));
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST);

    }

    @Test
    public void validationException() {
        request.header(HTTP.CONTENT_TYPE, "application/json");
        Response response = request.put(BASE_URL.concat("/yhgh"));
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_METHOD_NOT_ALLOWED);
    }

    @Test
    public void petNotFound() {
        request.header(HTTP.CONTENT_TYPE, "application/json");
        Response response = request.put(BASE_URL.concat("i"));
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_NOT_FOUND);

    }
}
