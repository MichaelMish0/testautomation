import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class Task3Tests {
    String booksURI="https://bookstore.toolsqa.com/BookStore/v1/Books";

    @Test
    public void checkStatusCode(){
        int statusCode= given()
                        .when()
                        .get(booksURI)
                        .getStatusCode();
        System.out.println("The status code is"+statusCode);
        Assert.assertEquals(statusCode,200);

    }

    @Test
    public void checkIsbn(){
       given()
               .when()
               .get(booksURI)
               .then()
               .assertThat()
               .body("books[-1].isbn",equalTo("9781593277574"));

    }

    @Test
    public void checkPages(){
        given()
                .when()
                .get(booksURI)
                .then()
                .log().all()
                .assertThat()
                .body("books[0,1].pages",hasItems(234,254));

    }

}

