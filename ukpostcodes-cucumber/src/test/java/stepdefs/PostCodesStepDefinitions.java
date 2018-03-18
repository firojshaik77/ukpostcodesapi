package stepdefs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

/**
 * The PostCodesStepDefinitions program implements the
 * step definitions to support the feature file "TestPostCodesAPI".
 * @author FirojShaik
 * 
 *
 */
public class PostCodesStepDefinitions {

	private Response response;
	private ValidatableResponse json;
	private RequestSpecification request;
	private String ENDPOINT_POST_CODES = "http://api.postcodes.io/postcodes";

	/**
	 * Method to implement "Given a set of GeoLocation data"
	 * @param requestFeilds
	 */
	@Given("^a set of GeoLocation data$")
	public void a_set_of_JSON_data(String requestFeilds){
		request=given().contentType("application/json\r\n").body(requestFeilds);
	}

	/**
	 * 
	 * Method to implement "Given a user provides post code <value>"
	 * @param postcode
	 */
	@Given("^a user provides postcode (.*)$")
	public void a_request_with_postcode(String postcode){
		request = given().param("q", postcode);
	}

	/**
	 * Method to implement "Given a user wants to verify the response status"
	 */
	@Given("^a user wants to verify the response status$")
	public void an_invalid_postcode()
	{
		request = given();
	}


	/**
	 * Method to implement "Given a user longitude value in parameter "lon" and 
	 * latitude value in parameter "lan"
	 * @param lonValue
	 * @param lon
	 * @param latValue
	 * @param lat
	 */
	@Given("^a user provides \"(.*?)\" in param \"(.*?)\" and \"(.*?)\" in param \"(.*?)\"$" )
	public void a_user_provides_params_for_lon_and_lat(String lonValue, String lon,String latValue, String lat){
		request = given().params(lon,lonValue,lat,latValue);
	}

	/**
	 * Method to implement "When user calls the API with <value>"
	 * @param invalidcode
	 */
	@When("^user calls the API with (.*)$")
	public void a_user_calls_the_API(String invalidcode){
		response = request.when().get(ENDPOINT_POST_CODES.concat("/"+invalidcode));
		//System.out.println("response: " + response.prettyPrint());
	}

	/**
	 * Method to implement "When user calls the post code API"
	 */
	@When("^user calls the postcode API$")
	public void user_calls_the_postcode_API(){
		response = request.when().get(ENDPOINT_POST_CODES);
		//System.out.println("response: " + response.prettyPrint());
	}

	/**
	 * Method to "When user makes a POST request"
	 */
	@When("^user makes a POST request$")
	public void user_makes_a_postrequest_API(){
		response = request.when().post(ENDPOINT_POST_CODES);
		//System.out.println("response: " + response.prettyPrint());
	}

	/**
	 * Method to implement "Then the status code is <value>"
	 * @param statusCode
	 */
	@Then("^the status code is (\\d+)$")
	public void the_status_code_is(int statusCode){
		json = response.then().statusCode(statusCode);
	}

	/**
	 * Method to assert "And the error is <value>"
	 * @param errorMsg
	 */
	@And("^the error is \"(.*)\"$")
	public void verify_error_msg(String errorMsg){
		json = response.then().statusLine(errorMsg);
	}


	/**
	 * Method to assert "And response includes the following values"
	 * @param responseFields
	 * @throws Exception
	 */
	@And("^response includes the following$")
	public void response_equals(Map<String,String> responseFields)throws Exception{

		for (Map.Entry<String, String> field : responseFields.entrySet()) {

			if(StringUtils.isNumeric(field.getValue())){
				json.body(field.getKey(), hasItems(Integer.parseInt(field.getValue())));

			}
			else{
				json.body(field.getKey(), hasItems(field.getValue().toString()));

			}
		}
	}

}


