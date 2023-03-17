package top.pi1grim.util;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.util.Map;

public class THUtil {

    private static final TemplateEngine ENGINE = new TemplateEngine();
    public static String process(String template, Map<String, Object> map) {
        Context context = new Context();

        return ENGINE.process(template, context);
    }
}
