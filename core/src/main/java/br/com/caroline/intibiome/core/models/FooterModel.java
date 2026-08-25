package br.com.caroline.intibiome.core.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FooterModel {
    @ValueMapValue private String logoReference;
    @ValueMapValue @Default(values = "Minimal Beauty") private String logoAlt;
    @ValueMapValue @Default(values = "A MINIMAL") private String aboutTitle;
    @ValueMapValue private String aboutText;
    @ValueMapValue @Default(values = "CATEGORIAS") private String categoriesTitle;
    @ValueMapValue @Default(values = "INFORMAÇÕES") private String informationTitle;
    @ValueMapValue private String facebookLink;
    @ValueMapValue private String instagramLink;
    @ValueMapValue private String youtubeLink;
    @ValueMapValue @Default(values = "© 2026, Minimal Beauty") private String copyright;
    @ChildResource private Resource categoryItems;
    @ChildResource private Resource informationItems;
    private List<LinkItem> categories;
    private List<LinkItem> information;

    @PostConstruct protected void init() {
        categories = readLinks(categoryItems);
        information = readLinks(informationItems);
    }
    private List<LinkItem> readLinks(Resource parent) {
        List<LinkItem> result = new ArrayList<>();
        if (parent != null) for (Resource child : parent.getChildren()) result.add(new LinkItem(child));
        return Collections.unmodifiableList(result);
    }
    public String getLogoReference() { return logoReference; }
    public String getLogoAlt() { return logoAlt; }
    public String getAboutTitle() { return aboutTitle; }
    public String getAboutText() { return aboutText; }
    public String getCategoriesTitle() { return categoriesTitle; }
    public String getInformationTitle() { return informationTitle; }
    public String getFacebookLink() { return facebookLink; }
    public String getInstagramLink() { return instagramLink; }
    public String getYoutubeLink() { return youtubeLink; }
    public String getCopyright() { return copyright; }
    public List<LinkItem> getCategories() { return categories; }
    public List<LinkItem> getInformation() { return information; }

    public static final class LinkItem {
        private final String label, link;
        private LinkItem(Resource resource) { ValueMap values = resource.getValueMap(); label = values.get("label", String.class); link = values.get("link", "#"); }
        public String getLabel() { return label; }
        public String getLink() { return link; }
    }
}
