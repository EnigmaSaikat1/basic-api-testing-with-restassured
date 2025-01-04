package cloud.irm.apiautomation;

import cloud.irm.apiautomaion.payloads.payload;
import io.restassured.path.json.JsonPath;

public class complexJsonParse {
    public static void main(String[] args) {
        JsonPath js = new JsonPath(payload.CoursePrice()); //mocked

        //Testcase-001 : Print no. of Test cases returned by API
        System.out.println("Testcase-001 : Print no. of Test cases returned by API");
        int countOfCourses = js.get("courses.size()");
        System.out.println(countOfCourses);

        //Testcase-002 : Print no. of Test cases returned by API
        System.out.println("Testcase-002 : Print no. of Test cases returned by API");
        int totalAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println(totalAmount);

        //Testcase-003 : Print title of the first course
        System.out.println("Testcase-003 : Print title of the first course");
        String titleOfFirstCourse = js.get("courses[0].title");
        System.out.println(titleOfFirstCourse);

        //Testcase-004 : Print all course title and their respective prices
        System.out.println("Testcase-004 : Print all course title and their respective prices");
        for (int i = 0;i< countOfCourses; i++){
            System.out.println(js.get("courses["+i+"].title").toString());
            System.out.println(js.get("courses["+i+"].price").toString());
        }

        //Testcase-005 : Print number of copies sold by RPA course
        System.out.println("Testcase-005 : Print number of copies sold by RPA course");
        for (int i = 0;i< countOfCourses; i++){
            String courseTitles = js.get("courses["+i+"].title");
            if(courseTitles.equalsIgnoreCase("RPA")){
                System.out.println(js.get("courses["+i+"].copies").toString());
                break;
            }
        }


    }
}
