package africa.absa.ebi;

import africa.absa.ebi.TestRail.APIClient;
import africa.absa.ebi.TestRail.TestRails;
import africa.absa.ebi.Utilities.Constants;
import africa.absa.ebi.Utilities.PropertyManager;
import org.json.simple.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class TestsBase {
    String PROJECT_ID = "2";
    APIClient client = null;
    PropertyManager pm;

    @BeforeSuite
    public void createSuite(ITestContext ctx) throws Throwable {
        pm = new PropertyManager();
        Map properties = pm.loadTestRailProperties();
        client = new APIClient(properties.get("testRailURL").toString());
        client.setUser(properties.get("testRailUsername").toString());
        client.setPassword(properties.get("testRailPassword").toString());

        Map data = new HashMap();
        data.put("suite_id", "1");
        data.put("include_all", true);
        data.put("name", "Test Run " + System.currentTimeMillis());
        JSONObject c = (JSONObject) client.sendPost("add_run/" + PROJECT_ID, data);

        Long suite_id = (Long) c.get("id");
        ctx.setAttribute("suiteId", suite_id);
    }

    @BeforeMethod
    public void beforeTest(ITestContext ctx, Method method) throws NoSuchMethodException {
        Method m = TestsBase.class.getMethod(method.getName());
        if (m.isAnnotationPresent(TestRails.class)) {
            TestRails ta = m.getAnnotation(TestRails.class);
            System.out.println(ta.id());
            ctx.setAttribute("caseId", ta.id());
        }
    }

    @Test
    private void testOne(){

    }
}
