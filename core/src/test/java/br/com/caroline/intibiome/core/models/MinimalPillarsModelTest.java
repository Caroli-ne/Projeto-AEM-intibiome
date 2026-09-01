package br.com.caroline.intibiome.core.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import br.com.caroline.intibiome.core.testcontext.AppAemContext;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

@ExtendWith(AemContextExtension.class)
class MinimalPillarsModelTest {
    private final AemContext context = AppAemContext.newAemContext();

    @Test
    void exposesTitleAndAuthoredPillars() {
        context.addModelsForClasses(MinimalPillarsModel.class);
        Resource resource = context.create().resource("/content/pillars", "title", "Nossos pilares");
        context.create().resource(resource, "items");
        context.create().resource("/content/pillars/items/item0",
            "image", "/content/dam/intibiome/about/minimalismo.webp",
            "imageAlt", "Produtos Minimal Beauty",
            "title", "Minimalismo",
            "link", "/content/intibiome/us/en/About");

        MinimalPillarsModel model = resource.adaptTo(MinimalPillarsModel.class);
        assertEquals("Nossos pilares", model.getTitle());
        assertEquals(1, model.getPillars().size());
        assertEquals("Minimalismo", model.getPillars().get(0).getTitle());
        assertEquals("/content/dam/intibiome/about/minimalismo.webp", model.getPillars().get(0).getImage());
    }
}
