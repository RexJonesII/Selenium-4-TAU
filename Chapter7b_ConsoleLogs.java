import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.log.Log;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Chapter7b_ConsoleLogs {
  EdgeDriver driver;

  @BeforeClass
  public void setUp(){
    WebDriverManager.edgedriver().setup();
    driver = new EdgeDriver();
    driver.manage().window().maximize();
  }

  @Test
  public void viewBrowserConsoleLogs(){
    // Get The DevTools & Create A Session
    DevTools devTools = driver.getDevTools();
    devTools.createSession();

    // Enable The Console Logs
    devTools.send(Log.enable());

    // Add A Listener For The Logs
    devTools.addListener(Log.entryAdded(), logEntry -> {
      System.out.println("----------");
      System.out.println("Level: " + logEntry.getLevel());
      System.out.println("Text: " + logEntry.getText());
      System.out.println("Broken URL: " + logEntry.getUrl());
    });

    // Load The AUT
    driver.get("http://the-internet.herokuapp.com/broken_images");
  }
}