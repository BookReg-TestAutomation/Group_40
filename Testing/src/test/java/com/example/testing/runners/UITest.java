//package com.example.testing.runners;
//
//import io.cucumber.junit.CucumberOptions;
//import net.serenitybdd.junit5.SerenityJUnit5Extension;
//import org.junit.jupiter.api.extension.ExtendWith;
//
//@ExtendWith(SerenityJUnit5Extension.class)
//@CucumberOptions(
//        features = "src/test/resources/features/ui",
//        glue = "com.example.testing.steps.ui",
//        plugin = {
//                "pretty",
//                "html:target/cucumber-reports"
//        }
//)
//public class UITest {
//}

package com.example.testing.runners;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features/ui")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.example.testing.steps.ui")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, html:target/cucumber-reports, json:target/cucumber.json, io.cucumber.core.plugin.SerenityReporter")
public class UITest {
}
