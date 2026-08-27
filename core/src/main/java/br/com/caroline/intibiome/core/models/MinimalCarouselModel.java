package br.com.caroline.intibiome.core.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MinimalCarouselModel {
    private static final String SLIDE_RESOURCE_TYPE = "intibiome/components/minimalslide";

    @Self private Resource resource;
    @ValueMapValue @Default(booleanValues = true) private boolean autoplay;
    @ValueMapValue @Default(intValues = 5000) private int interval;
    private List<String> slideNames;

    @PostConstruct
    protected void init() {
        List<String> result = new ArrayList<>();
        if (resource != null) {
            for (Resource child : resource.getChildren()) {
                if (SLIDE_RESOURCE_TYPE.equals(child.getResourceType())) result.add(child.getName());
            }
        }
        slideNames = Collections.unmodifiableList(result);
        if (interval < 2000) interval = 2000;
    }

    public boolean isAutoplay() { return autoplay; }
    public int getInterval() { return interval; }
    public List<String> getSlideNames() { return slideNames; }
    public String getId() { return "minimalBeautyCarousel-" + (resource == null ? "carousel" : resource.getName()); }
}
