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

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MinimalBenefitsModel {

    @ChildResource private Resource items;
    private List<Benefit> benefits;

    @PostConstruct
    protected void init() {
        List<Benefit> result = new ArrayList<>();
        if (items != null) {
            for (Resource child : items.getChildren()) result.add(new Benefit(child));
        }
        benefits = Collections.unmodifiableList(result);
    }

    public List<Benefit> getBenefits() { return benefits; }

    public static final class Benefit {
        private final String title;
        private final String description;

        private Benefit(Resource resource) {
            ValueMap values = resource.getValueMap();
            title = values.get("title", "");
            description = values.get("description", "");
        }

        public String getTitle() { return title; }
        public String getDescription() { return description; }
    }
}
