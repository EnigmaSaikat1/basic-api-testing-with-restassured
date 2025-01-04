package cloud.irm.apiautomaion.payloads;

import io.restassured.path.json.JsonPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TestComplexJsonParseExample  {
    private static final Logger logger = LoggerFactory.getLogger(TestComplexJsonParseExample.class);
    @Test
    public void sumOfCourses(){
        JsonPath js = new JsonPath(payload.CoursePrice());
        int totalAmount = js.getInt("dashboard.purchaseAmount");
        int sum = 0;
        int countOfCourses = js.get("courses.size()");
        for (int i = 0; i < countOfCourses; i++){
            int price = js.getInt("courses["+i+"].price");
            int copies = js.getInt("courses["+i+"].copies");
            int amount = price * copies;
            System.out.println(amount);
            sum += amount;
        }
        System.out.println(sum);
        try {
            Assert.assertEquals(sum, totalAmount);
            System.out.println("Test Passed: The sum matches the total amount.");
            logger.info("Test Passed: The sum matched the total amount.");

        } catch (AssertionError e) {
            System.out.println("Test Failed: The sum does not match the total amount.");
            logger.error("Test Failed: The sum does not match the total amount.", e);
        }

    }
}
