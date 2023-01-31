package com.zigai.submariner.components;

import com.badlogic.ashley.core.Entity;
import com.zigai.submariner.Mappers;

import java.util.Comparator;

public class LayerComparator implements Comparator<Entity> {
    public static final LayerComparator INSTANCE = new LayerComparator();

    private LayerComparator() {
    }

    @Override
    public int compare(Entity a, Entity b) {
        return Float.compare(Mappers.LAYER.get(a).value, Mappers.LAYER.get(b).value);
    }
}
