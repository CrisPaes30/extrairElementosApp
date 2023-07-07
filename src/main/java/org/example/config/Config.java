package org.example.config;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.MalformedURLException;

public class Config {

    public UiAutomator2Options configAppium() throws MalformedURLException {

        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android"); //Plataforma
        options.setDeviceName("device name");//Nome do Dispositivo
        options.setApp("Caminho\\da\\Apk\\do\\App");//Caminho da Apk do App
        options.setAppActivity("com.app.MainActivity");//Endereco da Activity do app

        return options;
    }
}
