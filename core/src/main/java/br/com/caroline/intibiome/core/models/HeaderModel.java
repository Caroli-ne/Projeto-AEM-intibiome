package br.com.caroline.intibiome.core.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HeaderModel {
    @ValueMapValue @Default(values = "FRETE GRÁTIS a partir de 199") private String promotionText;
    @ValueMapValue private String promotionLink;
    @ValueMapValue private String logoReference;
    @ValueMapValue @Default(values = "Minimal Beauty") private String logoAlt;
    @ValueMapValue @Default(values = "/content/intibiome/us/en.html") private String homeLink;
    @ValueMapValue @Default(values = "#") private String searchLink;
    @ValueMapValue @Default(values = "#") private String accountLink;
    @ValueMapValue @Default(values = "#") private String bagLink;
    @ChildResource private Resource menuItems;
    private List<MenuItem> items;

    @PostConstruct protected void init() {
        List<MenuItem> result = new ArrayList<>();
        if (menuItems != null) for (Resource item : menuItems.getChildren()) result.add(new MenuItem(item));
        items = Collections.unmodifiableList(result);
    }
    public String getPromotionText() { return promotionText; }
    public String getPromotionLink() { return promotionLink; }
    public String getLogoReference() { return logoReference; }
    public String getLogoAlt() { return logoAlt; }
    public String getHomeLink() { return homeLink; }
    public String getSearchLink() { return searchLink; }
    public String getAccountLink() { return accountLink; }
    public String getBagLink() { return bagLink; }
    public List<MenuItem> getMenuItems() { return items; }
    public boolean isConfigured() { return StringUtils.isNotBlank(promotionText) || !items.isEmpty(); }

    public static final class MenuItem {
        private final String label, link; private final boolean active; private final List<SubmenuItem> children;
        private MenuItem(Resource resource) {
            ValueMap values = resource.getValueMap(); label = values.get("label", String.class); link = values.get("link", "#"); active = values.get("active", false);
            List<SubmenuItem> result = new ArrayList<>(); Resource submenu = resource.getChild("submenu");
            if (submenu != null) for (Resource child : submenu.getChildren()) result.add(new SubmenuItem(child));
            children = Collections.unmodifiableList(result);
        }
        public String getLabel() { return label; } public String getLink() { return link; }
        public boolean isActive() { return active; } public List<SubmenuItem> getChildren() { return children; }
        public boolean isHasChildren() { return !children.isEmpty(); }
    }
    public static final class SubmenuItem {
        private final String label, link;
        private SubmenuItem(Resource resource) { ValueMap values = resource.getValueMap(); label = values.get("label", String.class); link = values.get("link", "#"); }
        public String getLabel() { return label; } public String getLink() { return link; }
    }
}
