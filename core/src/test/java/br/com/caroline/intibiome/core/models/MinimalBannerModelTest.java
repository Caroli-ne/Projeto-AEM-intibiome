package br.com.caroline.intibiome.core.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import br.com.caroline.intibiome.core.testcontext.AppAemContext;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

@ExtendWith(AemContextExtension.class)
class MinimalBannerModelTest {
    private final AemContext context = AppAemContext.newAemContext();

    @Test
    void exposesContentAndLayout() {
        context.addModelsForClasses(MinimalBannerModel.class);
        Resource resource = context.create().resource("/content/banner",
            "image", "/content/dam/intibiome/about/manifesto.webp",
            "imageAlt", "Close-up de um olhar",
            "title", "MANIFESTO",
            "headingLevel", "h1",
            "alignment", "center",
            "textColor", "light",
            "overlay", "soft",
            "height", "standard",
            "focalPoint", "center-center");

        MinimalBannerModel model = resource.adaptTo(MinimalBannerModel.class);
        assertEquals("MANIFESTO", model.getTitle());
        assertEquals("h1", model.getHeadingLevel());
        assertEquals("/content/dam/intibiome/about/manifesto.webp", model.getImage());
        assertEquals("center", model.getAlignment());
        assertEquals("soft", model.getOverlay());
    }
}
