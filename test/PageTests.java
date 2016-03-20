import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.*;

import models.forms.*;
import play.data.Form;

import play.mvc.*;
import play.test.*;
import play.data.DynamicForm;
import play.data.validation.ValidationError;
import play.data.validation.Constraints.RequiredValidator;
import play.i18n.Lang;
import play.libs.F;
import play.libs.F.*;
import play.twirl.api.Content;

import static play.test.Helpers.*;
import static org.junit.Assert.*;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class PageTests {

    @Test
    public void simpleCheck() {
        int a = 1 + 1;
        assertEquals(2, a);
    }

    @Test
    public void errorTest() {
        Content html = views.html.error.render("This is an error");
        assertEquals("text/html", contentType(html));
        assertTrue(contentAsString(html).contains("This is an error"));
    }

    @Test
    public void activationTest() {
        Content html = views.html.activation.render("Generic Message Test");
        assertEquals("text/html", contentType(html));
        assertTrue(contentAsString(html).contains("Generic Message Test"));
    }

    @Test
    public void genericLanderTest() {
        Content html = views.html.genericLander.render("This is the title", "Generic Message Test");
        assertEquals("text/html", contentType(html));
        assertTrue(contentAsString(html).contains("Generic Message Test"));
    }

    @Test
    public void homePageTest() {
        Content html = views.html.home.render("First Name");
        assertEquals("text/html", contentType(html));
        assertTrue(contentAsString(html).contains("First Name"));
    }

}
