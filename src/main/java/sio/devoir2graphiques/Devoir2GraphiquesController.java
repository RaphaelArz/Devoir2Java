package sio.devoir2graphiques;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import sio.devoir2graphiques.Tools.ConnexionBDD;
import sio.devoir2graphiques.Tools.GraphiqueController;

import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Devoir2GraphiquesController implements Initializable
{
    @FXML
    private Button btnGraph1;
    @FXML
    private Button btnGraph2;
    @FXML
    private Button btnGraph3;
    @FXML
    private AnchorPane apGraph1;
    @FXML
    private LineChart graph1;
    @FXML
    private Label lblTitre;
    @FXML
    private AnchorPane apGraph2;
    @FXML
    private AnchorPane apGraph3;
    @FXML
    private PieChart graph3;
    @FXML
    private BarChart graph2;
    ConnexionBDD maCnx;
    XYChart.Series<Number, Number> serieGraph1;
    XYChart.Series<String, Number> serieGraph3;
    GraphiqueController graphiqueController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            maCnx = new ConnexionBDD();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        lblTitre.setText("Devoir : Graphique n°1");
        apGraph1.toFront();
        try {
            remplirGraph1();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // A vous de jouer

    }

    @FXML
    public void menuClicked(Event event) throws SQLException {
        if(event.getSource() == btnGraph1)
        {
            lblTitre.setText("Devoir : Graphique n°1");
            apGraph1.toFront();
            remplirGraph1();

            // A vous de jouer

        }
        else if(event.getSource() == btnGraph2)
        {
            lblTitre.setText("Devoir : Graphique n°2");
            apGraph2.toFront();

            // A vous de jouer

        }
        else
        {
            lblTitre.setText("Devoir : Graphique n°3");
            apGraph3.toFront(); lblTitre.setText("TP4 : Graphique n°2");
            graphiqueController = new GraphiqueController();
            HashMap<String, Integer> dataGraph3 = graphiqueController.getDataGraph3();
            ObservableList lst = FXCollections.observableArrayList();

            for (String unSexe : dataGraph3.keySet()) {
                lst.add(new PieChart.Data(unSexe, dataGraph3.get(unSexe)));
            }
            graph3.setData(lst);

            
            for (PieChart.Data entry : graph3.getData()) {
                Tooltip tooltip = new Tooltip(entry.getPieValue() + " " + entry.getName());
                Tooltip.install(entry.getNode(), tooltip);
            }


        }

    }
    private void remplirGraph1() throws SQLException {
        graph1.getData().clear();
        graphiqueController = new GraphiqueController();
        HashMap<Integer, Double> dataGraph = graphiqueController.getDataGraph1();
        serieGraph1 = new XYChart.Series<>();

        for (int unAge : dataGraph.keySet()) {
            serieGraph1.getData().add(new XYChart.Data<>(unAge, dataGraph.get(unAge)));
        }
        graph1.getData().add(serieGraph1);
        serieGraph1.setName("Salaire");
    }
}