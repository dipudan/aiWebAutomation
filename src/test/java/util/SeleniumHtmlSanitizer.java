package util;

import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;

public class SeleniumHtmlSanitizer {

    private static final PolicyFactory SELENIUM_POLICY = new HtmlPolicyBuilder()
            .allowElements(
                    "input", "button", "a", "select", "textarea",
                    "div", "span", "img", "form", "table", "tr", "td"
            )
            .allowAttributes(
                    "id", "name", "class", "href", "value", "type",
                    "src", "alt", "title", "data-*"
            ).globally()
            .allowStyling()
            .allowStandardUrlProtocols()
            .requireRelNofollowOnLinks()
            .toFactory();

    public static String sanitizeForSelenium(String html) {
        return SELENIUM_POLICY.sanitize(html);
    }
}
