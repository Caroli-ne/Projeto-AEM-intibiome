package br.com.caroline.intibiome.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MinimalSlideModel {
    private static final String SLIDE_RESOURCE_TYPE = "intibiome/components/minimalslide";

    @Self private Resource resource;
    @ValueMapValue private String image;
    @ValueMapValue private String imageAlt;
    @ValueMapValue private String title;
    @ValueMapValue private String subtitle;
    @ValueMapValue private String buttonText;
    @ValueMapValue @Default(values = "#") private String buttonLink;
    @ValueMapValue @Default(values = "center") private String alignment;
    @ValueMapValue @Default(values = "50% center") private String mobilePosition;

    public String getImage() { return image; }
    public String getImageAlt() { return imageAlt; }
    public String getTitle() { return title; }
    public String getSubtitle() { return subtitle; }
    public String getButtonText() { return buttonText; }
    public String getButtonLink() { return buttonLink; }
    public String getAlignment() { return alignment; }
    public String getMobilePosition() { return mobilePosition; }

    public boolean isFirst() {
        if (resource == null || resource.getParent() == null) return false;
        for (Resource sibling : resource.getParent().getChildren()) {
            if (SLIDE_RESOURCE_TYPE.equals(sibling.getResourceType())) return sibling.getPath().equals(resource.getPath());
        }
        return false;
    }

    public int getInterval() {
        Resource parent = resource == null ? null : resource.getParent();
        ValueMap values = parent == null ? ValueMap.EMPTY : parent.getValueMap();
        return Math.max(values.get("interval", 5000), 2000);
    }
}
