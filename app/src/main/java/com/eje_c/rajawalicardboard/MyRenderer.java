package com.eje_c.rajawalicardboard;

import android.content.Context;

import org.rajawali3d.cardboard.RajawaliCardboardRenderer;
import org.rajawali3d.materials.Material;
import org.rajawali3d.materials.textures.ATexture;
import org.rajawali3d.materials.textures.Texture;
import org.rajawali3d.math.vector.Vector3;
import org.rajawali3d.primitives.Sphere;

public class MyRenderer extends RajawaliCardboardRenderer {

    public MyRenderer(Context context) {
        super(context);
    }

    @Override
    protected void initScene() {

        Sphere sphere = createPhotoSphereWithTexture(new Texture("photo", R.drawable.panorama));

        getCurrentScene().addChild(sphere);

        getCurrentCamera().setPosition(Vector3.ZERO);
        getCurrentCamera().setFieldOfView(100);

        getCurrentCamera().setFarPlane(2);
        getCurrentCamera().setNearPlane(0.000000000000001);
    }

    private static Sphere createPhotoSphereWithTexture(ATexture texture) {

        Material material = new Material();
        material.setColor(0);

        try {
            material.addTexture(texture);
        } catch (ATexture.TextureException e) {
            throw new RuntimeException(e);
        }

        //cardboard sdk matrices are in meter unit, sphere should addapt to that scale
        Sphere sphere = new Sphere(1, 128, 64);
        sphere.setScaleX(-1);
        sphere.setMaterial(material);

        return sphere;
    }
}
