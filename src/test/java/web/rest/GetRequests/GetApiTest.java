package web.rest.GetRequests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.Pet;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static util.Constant.BASE_URL;


public class GetApiTest {
    RequestSpecification request;
    ObjectMapper mapper;

    GetApiTest() {
        RestAssured.baseURI = BASE_URL;
        request = RestAssured.given();
        mapper = new ObjectMapper();
    }

    @Test
    public void testPetFound() {
        Response response = RestAssured.get("/10");
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
    }

    @Test
    public void testPetNotFound() {
        Response response = RestAssured.get("/-1");
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_NOT_FOUND);
    }

    @Test(enabled = false)
    public void testBadRequest() {
        Response response = RestAssured.get("/%");
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void testStatusPending() throws JsonProcessingException {
        Response response = RestAssured.get("/findByStatus?status=pending");
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        List<Pet> petlist = mapper.readValue(response.asString(), new TypeReference<List<Pet>>() {
        });
        petlist.forEach(p -> Assert.assertEquals(p.getStatus(), "pending"));
    }

    @Test
    public void testStatusAvailable() throws JsonProcessingException {
        Response response = RestAssured.get("/findByStatus?status=available");
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        List<Pet> petlist = mapper.readValue(response.asString(), new TypeReference<List<Pet>>() {
        });
        petlist.forEach(p -> Assert.assertEquals(p.getStatus(), "available"));
    }

    @Test
    public void testStatusSold() throws JsonProcessingException {
        Response response = RestAssured.get("/findByStatus?status=sold");
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        List<Pet> petlist = mapper.readValue(response.asString(), new TypeReference<List<Pet>>() {
        });
        petlist.forEach(p -> Assert.assertEquals(p.getStatus(), "sold"));
    }


}