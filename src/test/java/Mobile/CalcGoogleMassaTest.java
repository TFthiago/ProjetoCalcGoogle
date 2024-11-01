package Mobile;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.options.BaseOptions;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CalcGoogleMassaTest {

    private AndroidDriver driver;
    public WebDriverWait wait;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        var options = new BaseOptions()
                .amend("platformName", "Android")
                .amend("appium:deviceName", "emulator-5554")
                .amend("appium:automationName", "uiautomator2")
                .amend("appium:appPackage", "com.google.android.calculator")
                .amend("appium:appActivity", "com.android.calculator2.Calculator")
                .amend("appium:ensureWebviewsHavePages", true)
                .amend("appium:nativeWebScreenshot", true)
                .amend("appium:newCommandTimeout", 3600)
                .amend("appium:connectHardwareKeyboard", true);

        URL privateUrl = new URL("http://127.0.0.1:4723");

        driver = new AndroidDriver(privateUrl, options);
        wait = new WebDriverWait(driver, Duration.ofMillis(20000));

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/dados_teste_calculadora.csv", numLinesToSkip = 1)
        void testeMultiplicacao(String numero1, String numero2, String resultadoEsperado) throws Exception {
            // ... código para configurar o driver do Appium

            // Encontrar os elementos usando os IDs diretamente

            WebElement botaoMultiplicar = driver.findElement(AppiumBy.accessibilityId("multiply"));
            WebElement botaoResultado = driver.findElement(AppiumBy.accessibilityId("equals"));
            WebElement resultadoPreview = driver.findElement(AppiumBy.id("com.google.android.calculator:id/result_preview"));
           // WebElement elementoResultado = driver.findElement(AppiumBy.id("com.google.android.calculator:id/result_final"));
//            var infoResultado = driver.findElement(AppiumBy.id("com.google.android.calculator:id/result_final"));
            WebElement botaoClear = driver.findElement(AppiumBy.accessibilityId("clear"));


        for (char digit : numero1.toCharArray()) {
            WebElement elementoNumero = driver.findElement(AppiumBy.accessibilityId(String.valueOf(digit)));
            elementoNumero.click();
        }

        botaoMultiplicar.click();

        for (char digit : numero2.toCharArray()) {
            WebElement elementoNumero = driver.findElement(AppiumBy.accessibilityId(String.valueOf(digit)));
            elementoNumero.click();
        }

        wait.until(ExpectedConditions.elementToBeClickable(resultadoPreview));

        botaoResultado.click();

        //wait.until(ExpectedConditions.visibilityOf(elementoResultado));

        // Validate result using assertEquals
//        assertEquals(resultadoEsperado, elementoResultado.getText());

        WebElement elementoResultado = driver.findElement(AppiumBy.id("com.google.android.calculator:id/result_final"));
        assertEquals(resultadoEsperado, elementoResultado.getText());

        botaoClear.click();

            //Adicionar os cliques:
//            elementoNumero1.click();
//            botaoMultiplicar.click();
//            elementoNumero2.click();
//            botaoResultado.click();
//            assertEquals(resultadoEsperado, elementoResultado.getText());
//            botaoClear.click();


        }
}

