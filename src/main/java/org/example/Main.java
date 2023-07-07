package org.example;


import org.example.CriaCsv.ElementosCapturados;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.printf("Chamando ElementosCapturados");

        ElementosCapturados appimScreenshot = new ElementosCapturados();
        appimScreenshot.captureScreenElements();

        System.out.println("Chamou ElementosCapturados");
    }
}