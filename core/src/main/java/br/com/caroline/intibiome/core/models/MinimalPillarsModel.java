package br.com.caroline.intibiome.core.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MinimalPillarsModel {

    @ValueMapValue
    private String title;

    @ChildResource
    private Resource items;

    private List<Pillar> pillars;

    @PostConstruct
    protected void init() {
        List<Pillar> result = new ArrayList<>();
        if (items != null) {
            for (Resource child : items.getChildren()) {
                result.add(new Pillar(child));
            }
        }
        pillars = Collections.unmodifiableList(result);
    }

    public String getTitle() {
        return title == null || title.trim().isEmpty() ? "OS TRÊS PILARES MINIMAL" : title;
    }

    public List<Pillar> getPillars() {
        return pillars;
    }

    public static final class Pillar {
        private final String image;
        private final String imageAlt;
        private final String title;
        private final String link;

        private Pillar(Resource resource) {
            ValueMap values = resource.getValueMap();
            image = values.get("image", "");
            imageAlt = values.get("imageAlt", "");
            title = values.get("title", "");
            link = values.get("link", "");
        }

        public String getImage() { return image; }
        public String getImageAlt() { return imageAlt; }
        public String getTitle() { return title; }
        public String getLink() { return link; }
    }
}
