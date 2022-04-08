package com.switchfully.eurder.users.security;

import java.util.List;

public enum Role {
    ADMIN(List.of(Feature.ADD_ITEM, Feature.GET_ALL_CUSTOMERS));

    private final List<Feature> featureList;

    Role(List<Feature> featureList) {
        this.featureList = featureList;
    }

    public boolean containsFeature(Feature feature) {
        return featureList.contains(feature);
    }
}
