package br.com.caroline.intibiome.core.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import br.com.caroline.intibiome.core.testcontext.AppAemContext;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

@ExtendWith(AemContextExtension.class)
class HeaderModelTest {
    private final AemContext context = AppAemContext.newAemContext();

    @BeforeEach
    void setUp() {
        context.addModelsForClasses(HeaderModel.class);
        Resource header = context.create().resource("/content/header",
                "promotionText", "Free shipping", "homeLink", "/content/home.html");
        Resource menu = context.create().resource(header, "menuItems");
        Resource skin = context.create().resource(menu, "item0", "label", "Skin types", "link", "/content/skin", "active", true);
        Resource submenu = context.create().resource(skin, "submenu");
        context.create().resource(submenu, "item0", "label", "Dry skin", "link", "/content/skin/dry");
        context.currentResource(header);
    }

    @Test
    void exposesAuthoredNavigation() {
        HeaderModel model = context.currentResource().adaptTo(HeaderModel.class);
        assertEquals("Free shipping", model.getPromotionText());
        assertEquals(1, model.getMenuItems().size());
        assertTrue(model.getMenuItems().get(0).isActive());
        assertTrue(model.getMenuItems().get(0).isHasChildren());
        assertEquals("Dry skin", model.getMenuItems().get(0).getChildren().get(0).getLabel());
    }
}
