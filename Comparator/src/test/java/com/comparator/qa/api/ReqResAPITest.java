package com.comparator.qa.api;

import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comparator.qa.test.TestNGListener;
import com.comparator.qa.utils.APIDataProvider;
import com.comparator.qa.utils.JsonComparator;

import static io.restassured.RestAssured.given;

@Listeners(TestNGListener.class)
public class ReqResAPITest {

    @Test(dataProvider = "requestURL", dataProviderClass = APIDataProvider.class)
    public void testAPIs(String urlOne, String urlTwo, ITestContext testContext) {
        try {
            
        	testContext.setAttribute("baseObj", urlOne);
        	testContext.setAttribute("newObj", urlTwo);
            
            String urlOneResponse = given().when().get(urlOne).thenReturn().body().asString();
            String urlTwoResponse = given().when().get(urlTwo).thenReturn().body().asString();

            JSONObject urlOneJSON = null;
            JSONObject urlTwoJSON = null;

            try {
            	urlOneJSON = new JSONObject(urlOneResponse);
            	urlTwoJSON = new JSONObject(urlTwoResponse);
            } catch (Exception e){
            	urlOneJSON = new JSONObject().put("key", new JSONArray(urlOneResponse));
            	urlTwoJSON = new JSONObject().put("key", new JSONArray(urlTwoResponse));
            }
            Assert.assertTrue(JsonComparator.jsonCompareObject(urlOneJSON, urlTwoJSON));
        }catch (Exception e) {
            Assert.fail();
            
        }
    }
}
