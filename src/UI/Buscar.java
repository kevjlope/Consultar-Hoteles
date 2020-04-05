package UI;

import java.io.IOException;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import learninggitjava.Ciudad;
import learninggitjava.Hotel;
import learninggitjava.Provincia;
//import learninggitjava.Provincia;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author kevjlope
 */
public class Buscar {

    private VBox rootPrincipal;
    private BorderPane rootGP;
    private ScrollPane rootCentro;
    private ComboBox comboProvincia;
    private ComboBox comboCiudad;
    private Button buscar;
    private Provincia provinEscogida;
    private Ciudad ciudadEscogida;
    private ArrayList<Ciudad> ciudades;
    private ArrayList<Hotel> hoteles;
    
    public VBox getRootPrincipal() {
        return rootPrincipal;
    }

    public Buscar() {
        rootPrincipal = new VBox();
        rootPrincipal.getChildren().add(menuBusqueda());
        hoteles = new ArrayList<>();
    }

    public BorderPane menuBusqueda() {
        rootGP = new BorderPane();
        rootGP.setTop(topBusqueda());
        rootGP.setBottom(opcionesBusqueda());
        // rootGP.setTop(new Label("Escoja alguna provincia"));
        return rootGP;
    }
    
    public HBox opcionesBusqueda(){
        HBox seccionButton = new HBox();
        seccionButton.setAlignment(Pos.CENTER);
        buscar = new Button("Buscar");
        seccionButton.getChildren().add(buscar);
        buscar.setOnAction(e -> iniciarBUsqueda());
        return seccionButton;
    }
    
    public void iniciarBUsqueda(){
        VBox mostrarHoteles = new VBox();
        mostrarHoteles.getChildren().clear();
        
        if(provinEscogida != null && ciudadEscogida !=null){
            //hoteles.clear();
            hoteles = ciudadEscogida.getHotelesCiudad();
            for (Hotel hotele : hoteles) {
                Label nameH = new Label(hotele.toString());
                mostrarHoteles.getChildren().add(nameH);
            }
        }else{
            Label mensaje = new Label("Verifique si ah seleccionado alguna provincia o ciudad");
            mostrarHoteles.getChildren().add(mensaje);
        }
        rootCentro = new ScrollPane();
        rootCentro.setContent(mostrarHoteles);
        rootGP.setCenter(rootCentro);
        
    }
    
    public AnchorPane topBusqueda() {
        AnchorPane anchor = new AnchorPane();
        Label mensaje = new Label("Bienvenido escoja alguna provincia");
        HBox menuTop = new HBox();
        Label provincia = new Label("Provincia");
        comboProvincia = new ComboBox();
        cargandoProvincia();
        Label ciudad = new Label("Ciudad");
        comboCiudad = new ComboBox();
        cargarCiudad();
        anchor.getChildren().addAll(mensaje, menuTop);
        menuTop.getChildren().addAll(provincia, comboProvincia,ciudad, comboCiudad);
        menuTop.setSpacing(10);
        AnchorPane.setTopAnchor(mensaje, 10.0);
        AnchorPane.setTopAnchor(menuTop, 30.0);
        return anchor;
    }

    public void cargandoProvincia() {
        try {
            comboProvincia.getItems().addAll(Provincia.leerProvincia());
            comboProvincia.setOnAction(e -> mostrarCiudades());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    
    public void cargarCiudad(){
        comboCiudad.setOnAction(e -> mostrarHoteles());
    }
    
    public void mostrarHoteles(){
        ciudadEscogida = (Ciudad)comboCiudad.getValue();
        //hoteles = ciudadEscogida.getHotelesCiudad();
    }
    
    public void mostrarCiudades() {
         provinEscogida = (Provincia) comboProvincia.getValue();
         //System.out.println(provinEscogida.toString());
          ciudades = provinEscogida.getCiudades();
        comboCiudad.getItems().clear();
        comboCiudad.getItems().addAll(ciudades);
    }
}
