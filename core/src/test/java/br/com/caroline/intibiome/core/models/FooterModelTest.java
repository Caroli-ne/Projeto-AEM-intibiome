package br.com.caroline.intibiome.core.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import br.com.caroline.intibiome.core.testcontext.AppAemContext;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

@ExtendWith(AemContextExtension.class)
class FooterModelTest {
    private final AemContext context = AppAemContext.newAemContext();
    @Test void exposesColumns() {
        context.addModelsForClasses(FooterModel.class);
        Resource footer = context.create().resource("/content/footer", "aboutTitle", "A MINIMAL");
        Resource categories = context.create().resource(footer, "categoryItems");
        context.create().resource(categories, "item0", "label", "KITS", "link", "/kits");
        context.currentResource(footer);
        FooterModel model = footer.adaptTo(FooterModel.class);
        assertEquals("A MINIMAL", model.getAboutTitle());
        assertEquals("KITS", model.getCategories().get(0).getLabel());
    }
}
