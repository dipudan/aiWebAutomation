package util;

public class SafeRequestBuilder {
    public static String buildRequest(String prompt) {
        // Manually escape JSON special characters
        String escapedPrompt = prompt.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");

        String messagePrompt = "Get me selenium id,name,xpath,class name locators only as json for the page source:" + escapedPrompt;

        return String.format("""
            {
              "contents": [
                {
                  "parts": [
                    {
                      "text": "%s"
                    }
                  ]
                }
              ]
            }
            """, messagePrompt);
    }
}
