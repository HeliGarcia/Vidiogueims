package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.effect.ParticleEmitter;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.FogFilter;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Sphere;
import com.jme3.system.AppSettings;

/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 * @author normenhansen
 */
public class Main extends SimpleApplication {
    
    public Spatial spatial_var = null;
    public Spatial spatial_var2 = null;
    public Spatial spatial_var3 = null;
    
    public static void main(String[] args) {
        AppSettings settings = new AppSettings(true);
        
        settings.setTitle("Chinchilla's Rampart");
        Main app = new Main();
        settings.getCenterWindow();
        app.setSettings(settings);
        app.start();
    }

    @Override
    public void simpleInitApp() {
        Box b = new Box(1, 1, 1);
        Geometry espacio = new Geometry("Box", b);
        Geometry geomTierra = new Geometry("Box", b);
        Geometry geomLuna = new Geometry("Box", b);
        geomLuna.scale(1,1,1);
        Material mat1 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat1.setColor("Color", ColorRGBA.Brown);
        geomTierra.setMaterial(mat1);
        Material mat2 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat2.setColor("Color", ColorRGBA.White);
        geomLuna.setMaterial(mat2);
        Material mat3 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        espacio.setMaterial(mat3);
        
        Node espace = new Node("espeis");
        espace.attachChild(espacio);
        espace.move(2, 2, 2);
        rootNode.attachChild(espace);
        
        Node padre_cubo = new Node("padre_cubo");
        padre_cubo.attachChild(geomTierra);
        padre_cubo.move(1, -2, 0);
        rootNode.attachChild(padre_cubo);
        geomLuna.move(2, 2, 2);
        rootNode.attachChild(padre_cubo);
        rootNode.attachChild(geomLuna);
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
        spatial_var2 = rootNode.getChild("Box");
        spatial_var2.rotate(0, tpf, 0);
        spatial_var = rootNode.getChild("padre_cubo");
        spatial_var.rotate(0, tpf, 0);
        spatial_var3 = rootNode.getChild("espeis");
        spatial_var3.rotate(0, tpf, 0);
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
