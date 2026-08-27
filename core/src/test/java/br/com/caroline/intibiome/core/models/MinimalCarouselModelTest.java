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
class MinimalCarouselModelTest {
    private final AemContext context = AppAemContext.newAemContext();
    @Test void exposesSlidesAndSettings() {
        context.addModelsForClasses(MinimalCarouselModel.class);
        Resource carousel = context.create().resource("/content/carousel", "autoplay", true, "interval", 6000);
        context.create().resource(carousel, "slide1", "sling:resourceType", "intibiome/components/minimalslide");
        MinimalCarouselModel model = carousel.adaptTo(MinimalCarouselModel.class);
        assertTrue(model.isAutoplay()); assertEquals(6000, model.getInterval());
        assertEquals("slide1", model.getSlideNames().get(0));
    }
}
