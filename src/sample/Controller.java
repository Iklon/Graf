package sample;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.FileChooser;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private FileReader reader;
    private BufferedReader bufferedreader;
    private List<Point> list = new ArrayList<>();
    private XYChart.Series series = new XYChart.Series();

    @FXML
    private LineChart<Number, Number> chart = new LineChart<>(new NumberAxis(), new NumberAxis());

    @FXML
    private void load() {
        FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(new File("D:\\Users\\Iklon\\Desktop\\Graf"));
        File file = chooser.showOpenDialog(null);
        if (file != null) {
            try {
                reader = new FileReader(file);
                bufferedreader = new BufferedReader(reader);
                try{
                    String line;
                    String[] souradnice;
                    while ((line = bufferedreader.readLine()) != null) {
                        line = line.replace("context.lineTo(", "");
                        line = line.replace(");", "");
                        line = line.trim();
                        souradnice = line.split(",");
                        list.add(new Point(Double.parseDouble(souradnice[0]), Double.parseDouble(souradnice[1])));
                    }
                }
                catch (IOException ex) {ex.printStackTrace();}
            } catch (FileNotFoundException exc) {exc.printStackTrace();}
        }
    }

    @FXML
    private void draw() {
        if (series != null) series.getData().clear();
        for(int k=0; k<list.size(); k++) {
            series.getData().add(new XYChart.Data(list.get(k).getX(), list.get(k).getY()));
            System.out.println("Cyklus:" + k + " X:" + list.get(k).getX() + " Y:" + list.get(k).getY());
        }
        chart.getData().add(series);
    }
}
