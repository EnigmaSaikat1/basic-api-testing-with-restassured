package cloud.irm.apiautomaion.payloads;

public class addBookPayloads {
    public static String addbook(String isbn, String aisle){
        return "{\n" +
                "  \"name\": \"Learn Appium Automation with Java\",\n" +
                "  \"isbn\": \""+isbn+"\",\n" +
                "  \"aisle\": \""+aisle+"\",\n" +
                "  \"author\": \"John foe\"\n" +
                "}";
    }
}
