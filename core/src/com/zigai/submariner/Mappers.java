package com.zigai.submariner;

import com.badlogic.ashley.core.ComponentMapper;
import com.zigai.submariner.components.*;

public class Mappers {
    public static final ComponentMapper<Texture> TEXTURE = ComponentMapper.getFor(Texture.class);
    public static final ComponentMapper<Transform> TRANSFORM = ComponentMapper.getFor(Transform.class);
    public static final ComponentMapper<Layer> LAYER = ComponentMapper.getFor(Layer.class);
    public static final ComponentMapper<Physics> PHYSICS = ComponentMapper.getFor(Physics.class);
    public static final ComponentMapper<Size> SIZE = ComponentMapper.getFor(Size.class);
    public static final ComponentMapper<BoxCollider> BOX_COLLIDER = ComponentMapper.getFor(BoxCollider.class);
    public static final ComponentMapper<CircleCollider> CIRCLE_COLLIDER = ComponentMapper.getFor(CircleCollider.class);
    public static final ComponentMapper<Fish> FISH = ComponentMapper.getFor(Fish.class);
    public static final ComponentMapper<Mine> MINE = ComponentMapper.getFor(Mine.class);
    public static final ComponentMapper<Heart> HEART = ComponentMapper.getFor(Heart.class);
    public static final ComponentMapper<Submarine> SUBMARINE = ComponentMapper.getFor(Submarine.class);
    public static final ComponentMapper<Projectile> PROJECTILE = ComponentMapper.getFor(Projectile.class);

}
