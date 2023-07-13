package Utils;

import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import static io.qameta.allure.Allure.step;

public class ScreenshotHelper {

    public static byte[] takeScreenshot(WebDriver driver) throws IOException {
        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(450)).takeScreenshot(driver);
        return toByteArray(screenshot.getImage());
    }

    private static byte[] toByteArray(BufferedImage image) throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(image, "png", baos);
            return baos.toByteArray();
        }
    }

    public static void compareScreenshots(String description, byte[] actualScreenshot, String expectedScreenshotName) throws IOException {
        try (InputStream expectedStream = ScreenshotHelper.class.getResourceAsStream("/screenshots/" + expectedScreenshotName)) {
            if (expectedStream != null) {
                byte[] expectedScreenshot = expectedStream.readAllBytes();

                Allure.addAttachment(description + " - Фактический скриншот", new ByteArrayInputStream(actualScreenshot));
                Allure.addAttachment(description + " - Ожидаемый скриншот", new ByteArrayInputStream(expectedScreenshot));

                if (Arrays.equals(actualScreenshot, expectedScreenshot)) {
                    step(description, Status.PASSED);
                } else {
                    step(description, Status.FAILED);
                    throw new AssertionError("Скриншоты не совпадают");
                }
            } else {
                throw new IOException("Не удалось загрузить ожидаемый скриншот");
            }
        }
    }

    public static byte[] takeFullPageScreenshot(WebDriver driver) throws IOException {
        Screenshot fullPageScreenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(450))
                .takeScreenshot(driver);
        BufferedImage fullPageImage = fullPageScreenshot.getImage();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(fullPageImage, "PNG", baos);
        return baos.toByteArray();
    }
}