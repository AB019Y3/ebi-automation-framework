package africa.absa.ebi.Utilities;

import africa.absa.ebi.Logger.Log;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class PropertyManager {
    Properties properties = new Properties();
    public Map loadTestRailProperties(){
        try {
            properties.load(new FileInputStream(Constants.testRailPropFile));
            System.out.println(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
