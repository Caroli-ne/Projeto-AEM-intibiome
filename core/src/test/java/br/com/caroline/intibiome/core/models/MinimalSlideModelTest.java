package br.com.caroline.intibiome.core.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import br.com.caroline.intibiome.core.testcontext.AppAemContext;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

@ExtendWith(AemContextExtension.class)
class MinimalSlideModelTest {
    private final AemContext context = AppAemContext.newAemContext();

    @Test void exposesSlideContentAndPosition() {
        context.addModelsForClasses(MinimalSlideModel.class);
        Resource carousel = context.create().resource("/content/carousel", "interval", 6000);
        Resource slide = context.create().resource(carousel, "slide1",
            "sling:resourceType", "intibiome/components/minimalslide",
            "title", "KITS", "image", "/content/dam/kits.webp");
        MinimalSlideModel model = slide.adaptTo(MinimalSlideModel.class);
        assertEquals("KITS", model.getTitle());
        assertEquals(6000, model.getInterval());
        assertTrue(model.isFirst());
    }
}
