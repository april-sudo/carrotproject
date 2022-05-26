package carrotproject.common;


import carrotproject.MyPageApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = { MyPageApplication.class })
public class CucumberSpingConfiguration {
    
}
