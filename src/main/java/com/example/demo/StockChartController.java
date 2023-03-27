package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StockChartController {


    @FXML
    private Button Back;
    @FXML private Button ordersList;
    @FXML private Button customerList;
    @FXML private NumberAxis caOrders;
    @FXML
    private CategoryAxis caProducts;
    @FXML
    private BarChart<String, Number> barChart;
    private Connection connection;
    private ResultSet resultSet;





    public void initialize() {
        try {
            // Connect to the database
            this.connection = DatabaseConnection.getInstance().getConnection();

            // Execute a query to get the data
            resultSet = connection.createStatement().executeQuery("SELECT product_id, SUM(quantity) as totalQuantity FROM product GROUP BY product_id");

            // Create an observable list to hold the data for the bar chart
            ObservableList<XYChart.Data<String, Number>> barChartData = FXCollections.observableArrayList();
            // Create a bar chart using the observable list of data
            barChart.setTitle("Products Quantities");
            caProducts.setLabel("Product ID");
            caOrders.setLabel("Quantity");
            barChart.setData(FXCollections.observableArrayList(new XYChart.Series<>("Quantities", barChartData)));

            // Iterate through the result set and add the data to the observable list
            while (resultSet.next()) {
                String productId = resultSet.getString("product_id");
                int quantity = resultSet.getInt("totalQuantity");
                XYChart.Data<String, Number> data = new XYChart.Data<>(productId, quantity);
                data.setNode(new StockChartController.HoveredThresholdNode(productId, quantity));
                data.getNode().setStyle("-fx-bar-fill: #7FFF00;");
                barChartData.add(data);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handelBack(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Unable to load main.fxml");
            alert.setContentText("Please try again later.");
            alert.showAndWait();
        }
    }

    @FXML
    private  void handelOrders(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MenuList.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }
    @FXML
    private  void handelCustomers(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CustomerChart.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }
    class HoveredThresholdNode extends StackPane {
        HoveredThresholdNode(String productId, int quantity) {
            setPrefSize(15, 15);

            final Label label = createDataThresholdLabel(quantity);

            setOnMouseEntered(mouseEvent -> {
                getChildren().setAll(label);
                setCursor(Cursor.NONE);
                toFront();
            });
            setOnMouseExited(mouseEvent -> {
                getChildren().clear();
                setCursor(Cursor.CROSSHAIR);
            });
        }

        private Label createDataThresholdLabel(int quantity) {
            final Label label = new Label(Integer.toString(quantity));
            label.getStyleClass().addAll("default-color0", "chart-line-symbol", "chart-series-line");
            label.setStyle("-fx-font-size: 12; -fx-font-weight: bold;");
            label.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);

            // get the layout bounds of the bar
            Bounds boundsInParent = barChart.localToParent(barChart.getBoundsInLocal());
            // set the y-coordinate of the label to be slightly above the top of the bar
            label.setLayoutX(boundsInParent.getMinX() + boundsInParent.getWidth() / 2 - label.getWidth() / 2);
            label.setLayoutY(boundsInParent.getMinY() - label.getHeight() - 10);
            return label;
        }
    }
}
