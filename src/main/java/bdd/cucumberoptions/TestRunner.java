package bdd.cucumberoptions;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/main/java/bdd/features", glue = "src/main/java/bdd/stepdefinitions")
public class TestRunner {

}