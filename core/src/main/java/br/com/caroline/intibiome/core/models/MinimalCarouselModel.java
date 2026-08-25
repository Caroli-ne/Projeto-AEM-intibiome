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
public class MinimalCarouselModel {
    @ValueMapValue @Default(booleanValues = true) private boolean autoplay;
    @ValueMapValue @Default(intValues = 5000) private int interval;
    @ChildResource private Resource slides;
    private List<Slide> items;

    @PostConstruct protected void init() {
        List<Slide> result = new ArrayList<>();
        if (slides != null) for (Resource child : slides.getChildren()) result.add(new Slide(child));
        items = Collections.unmodifiableList(result);
        if (interval < 2000) interval = 2000;
    }
    public boolean isAutoplay() { return autoplay; }
    public int getInterval() { return interval; }
    public List<Slide> getItems() { return items; }

    public static final class Slide {
        private final String image, title, subtitle, buttonText, buttonLink, alignment, mobilePosition;
        private Slide(Resource resource) {
            ValueMap value = resource.getValueMap();
            image = value.get("image", ""); title = value.get("title", ""); subtitle = value.get("subtitle", "");
            buttonText = value.get("buttonText", ""); buttonLink = value.get("buttonLink", "#");
            alignment = value.get("alignment", "center"); mobilePosition = value.get("mobilePosition", "50% center");
        }
        public String getImage() { return image; } public String getTitle() { return title; }
        public String getSubtitle() { return subtitle; } public String getButtonText() { return buttonText; }
        public String getButtonLink() { return buttonLink; } public String getAlignment() { return alignment; }
        public String getMobilePosition() { return mobilePosition; }
    }
}
