package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Sphere;

public class SistemaSolar extends SimpleApplication {

    public static void main(String[] args) {
        SistemaSolar app = new SistemaSolar();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        // Nodo para el espacio
        Node espacioNode = new Node("Espacio");
        
        // Crear el sol
        Node solNode = crearPlaneta("Sol", 10, "Textures/sol.jpg");
        espacioNode.attachChild(solNode);
        
        // Crear los planetas
        float distanciaEntrePlanetas = 20;
        
        Node mercurioNode = crearPlaneta("Mercurio", 1, "Textures/mercurio.jpg");
        mercurioNode.setLocalTranslation(distanciaEntrePlanetas * 1, 0, 0);
        espacioNode.attachChild(mercurioNode);
        
        Node venusNode = crearPlaneta("Venus", 2, "Textures/venus.jpeg");
        venusNode.setLocalTranslation(distanciaEntrePlanetas * 2, 0, 0);
        espacioNode.attachChild(venusNode);
        
        Node tierraNode = crearPlaneta("Tierra", 3, "Textures/earth.jpg");
        tierraNode.setLocalTranslation(distanciaEntrePlanetas * 3, 0, 0);
        espacioNode.attachChild(tierraNode);
        
        Node marteNode = crearPlaneta("Marte", 2, "Textures/marte.jpg");
        marteNode.setLocalTranslation(distanciaEntrePlanetas * 4, 0, 0);
        espacioNode.attachChild(marteNode);
        
        Node jupiterNode = crearPlaneta("Jupiter", 5, "Textures/jupiter.jpg");
        jupiterNode.setLocalTranslation(distanciaEntrePlanetas * 5, 0, 0);
        espacioNode.attachChild(jupiterNode);
        
        // Posición inicial de la cámara
        cam.setLocation(new Vector3f(0, 50, 100));
        cam.lookAt(Vector3f.ZERO, Vector3f.UNIT_Y);
        
        // Añadir el espacio al mundo
        rootNode.attachChild(espacioNode);
    }
    
    private Node crearPlaneta(String nombre, float tamano, String textura) {
        Sphere sphere = new Sphere(32, 32, tamano);
        Geometry planet = new Geometry(nombre, sphere);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setTexture("ColorMap", assetManager.loadTexture(textura));
        planet.setMaterial(mat);
        planet.rotate(-FastMath.HALF_PI,0,0);
        
        Node planetNode = new Node(nombre + "_Node");
        planetNode.attachChild(planet);
        
        return planetNode;
    }

    @Override
    public void simpleUpdate(float tpf) {
        // Obtener el nodo del espacio
        Node espacioNode = (Node) rootNode.getChild("Espacio");
        
        // Rotación del sol
        Node solNode = (Node) espacioNode.getChild("Sol_Node");
        solNode.rotate(0, tpf * 0.1f, 0);
        
        // Velocidades de traslación de los planetas
        float[] velocidades = {0.12f, 0.07f, 0.05f, 0.04f, 0.03f};
        
        // Rotación de los planetas alrededor del sol y traslación
        for (int i = 1; i < espacioNode.getChildren().size(); i++) {
            Node planetNode = (Node) espacioNode.getChild(i);
            
            // Rotación
            planetNode.rotate(0, tpf * 0.5f, 0); // Rotación más lenta para los planetas
            
            // Traslación alrededor del sol
            float velocidadTraslacion = velocidades[i - 1];
            Quaternion rotation = new Quaternion().fromAngleAxis(velocidadTraslacion * tpf, Vector3f.UNIT_Y);
            planetNode.setLocalTranslation(rotation.mult(planetNode.getLocalTranslation()));
        }
    }
}