package africa.absa.ebi.Utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Constants {
    /*
        Onboarding base urls
     */
    public static final String devBaseURL = "";
    public static final String sitBaseURL = "";
    public static final String uatBaseURL = "";
    public static final String testRailPropFile = "src/main/java/africa/absa/ebi/Properties/testRail.properties";
    public static final String EXTENTREPORTPATH = System.getProperty("user.dir")+ "/ExtentReports/" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()) + "/";
}
