import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import net.thucydides.core.annotations.WithTag;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/features/Image.feature", tags={"@See"})
public class TestRunner {
}
