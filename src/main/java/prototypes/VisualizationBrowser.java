package prototypes;

/**
 * @author Jean-Paul Labadie
 * @date 8/11/2015
 */

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;


public class VisualizationBrowser extends Region {

    final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();

    public VisualizationBrowser() {
        //apply the styles
        getStyleClass().add("browser");
        // load the web page
        String local_path = this.getClass().getResource("/flare_d3/tree.html").toExternalForm();
        System.out.println(local_path);
        webEngine.load(local_path);
        //add the web view to the scene
        getChildren().add(browser);

    }
    private Node createSpacer() {
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        return spacer;
    }

    @Override protected void layoutChildren() {
        double w = getWidth();
        double h = getHeight();
        layoutInArea(browser,0,0,w,h,0, HPos.CENTER, VPos.CENTER);
    }

    @Override protected double computePrefWidth(double height) {
        return 800;
    }

    @Override protected double computePrefHeight(double width) {
        return 600;
    }
}