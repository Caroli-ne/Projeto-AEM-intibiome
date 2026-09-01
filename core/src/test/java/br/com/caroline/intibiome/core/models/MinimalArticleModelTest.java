package br.com.caroline.intibiome.core.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import br.com.caroline.intibiome.core.testcontext.AppAemContext;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

@ExtendWith(AemContextExtension.class)
class MinimalArticleModelTest {
    private final AemContext context = AppAemContext.newAemContext();

    @Test
    void exposesArticleVariation() {
        context.addModelsForClasses(MinimalArticleModel.class);
        Resource resource = context.create().resource("/content/article",
            "title", "O que fazemos?",
            "imagePosition", "left",
            "theme", "beige",
            "columnRatio", "third",
            "height", "tall",
            "spacing", "roomy",
            "titleStyle", "original",
            "focalPoint", "center-top");

        MinimalArticleModel model = resource.adaptTo(MinimalArticleModel.class);
        assertEquals("O que fazemos?", model.getTitle());
        assertEquals("beige", model.getTheme());
        assertEquals("third", model.getColumnRatio());
        assertEquals("original", model.getTitleStyle());
        assertEquals("center-top", model.getFocalPoint());
    }
}
