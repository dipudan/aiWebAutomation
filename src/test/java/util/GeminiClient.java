package util;

import java.io.*;
import java.net.*;

import okhttp3.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class GeminiClient {
    private static final String API_KEY = "AIzaSyD5V8BFWq_XjXGKJ6ikiN574s90Liykrco";
    private static final String ENDPOINT = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash-001:generateContent?key=" + API_KEY;
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public static void main(String[] args) throws IOException {

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com");
        String page_source = SeleniumHtmlSanitizer.sanitizeForSelenium(driver.getPageSource());
        driver.quit();
        String requestBody = SafeRequestBuilder.buildRequest(page_source);

        RequestBody body = RequestBody.create(requestBody, JSON);
        Request request = new Request.Builder()
                .url(ENDPOINT)
                .post(body)
                .build();

        OkHttpClient client = new OkHttpClient();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            System.out.println(response.body().string());
        }
    }
}
