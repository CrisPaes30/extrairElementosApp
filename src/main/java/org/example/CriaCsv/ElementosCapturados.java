package org.example.CriaCsv;

import io.appium.java_client.android.AndroidDriver;
import org.example.config.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class ElementosCapturados {

    public void captureScreenElements() throws IOException, InterruptedException {

        Config config = new Config();
        String url = "http://localhost:4723"; //url server appium

        AndroidDriver driver = new AndroidDriver(new URL(url), config.configAppium());
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        int waitTime = 5; // Tempo em segundos para aguardar
        String previousActivity = null;
        long startTime = System.currentTimeMillis() / 1000;

        while ((System.currentTimeMillis() / 1000) - startTime < waitTime) {
            String currentActivity = driver.getDeviceTime();

            if (!currentActivity.equals(previousActivity)) {
                previousActivity = currentActivity;
                startTime = System.currentTimeMillis() / 1000;
            }

            // Obtém todos os elementos da tela
            java.util.List<WebElement> elements = driver.findElements(By.xpath("//android.widget.TextView"));

            // Cria o arquivo CSV
            //Caminho para salvar o csv
            String csvFilePath = "caminho\\salvar\\csv" + driver.currentActivity() + ".csv";
            FileWriter csvWriter = new FileWriter(csvFilePath);
            csvWriter.write("current_activity,resourceid,clickable,enabled,scrollable,element_class,content_desc,text\n");

            // Escreve os elementos no arquivo CSV
            for (WebElement element : elements) {
                String activity = driver.currentActivity();
                String resourceId = element.getAttribute("resource-id");
                String clickable = element.getAttribute("clickable");
                String enabled = String.valueOf(element.isEnabled());
                String scrollable = element.getAttribute("scrollable");
                String elementClass = element.getAttribute("class");
                String contentDesc = element.getAttribute("content-desc");
                String text = element.getAttribute("text");

                Rectangle elementRect = element.getRect();
                int left = elementRect.getX();
                int top = elementRect.getY();
                int right = left + elementRect.getWidth();
                int bottom = top + elementRect.getHeight();

                csvWriter.append(activity).append(",").append(resourceId).append(",").append(clickable).append(",")
                        .append(enabled).append(",").append(scrollable).append(",").append(elementClass).append(",")
                        .append(contentDesc).append(",").append(text).append("\n");
            }

            csvWriter.flush();
            csvWriter.close();

            Thread.sleep(5000); // Aguardar 5 segundos
        }

        driver.quit();
    }
}
