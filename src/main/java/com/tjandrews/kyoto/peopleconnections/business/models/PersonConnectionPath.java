package com.tjandrews.kyoto.peopleconnections.business.models;

import java.util.Arrays;
import java.util.List;

public class PersonConnectionPath {
    private final List<Integer> pathIds;

    public PersonConnectionPath(List<Integer> pathIds) {
        this.pathIds = pathIds;
    }

    public List<Integer> getPathIds() {
        return pathIds;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PersonConnectionPath other = (PersonConnectionPath) obj;
        if (pathIds == null) {
            if (other.pathIds != null)
                return false;
        } 
        return Arrays.deepEquals(pathIds.toArray(), other.getPathIds().toArray());
    }
    
}