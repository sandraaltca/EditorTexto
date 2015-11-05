package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.*;

public class Controller {

    private Stage stage;
    public TextArea textArea;
    public CheckMenuItem Free;
    public CheckMenuItem Sans ;
    public CheckMenuItem Arial;
    public CheckMenuItem CoSans ;
    public CheckMenuItem Verdana;


    private static final String defaultFileName = "sense_nom.txt";

    /**
     * Metode per modificar la font
     * @param actionEvent
     */

    public void font(ActionEvent actionEvent) {
        CheckMenuItem font = (CheckMenuItem) actionEvent.getSource();
        /*
        Posem al false tots els checkmenu
         */
        Free.setSelected(false);
        Sans.setSelected(false);
        Arial.setSelected(false);
        CoSans.setSelected(false);
        Verdana.setSelected(false);
        /*
        Posem el check a la font sel?lecionada.
         */
        font.setSelected(true);

        textArea.setFont(new Font(font.getText(), textArea.getFont().getSize()));
    }


    /**
     * Metode tancar la app
     * @param actionEvent
     */
    public void close(ActionEvent actionEvent) {
        Platform.exit();
    }

    /**
     * M?tode copiar
     * @param actionEvent
     */
    public void copy(ActionEvent actionEvent) {
        textArea.copy();
    }

    /**
     * Funcio per enganchar text
     * @param actionEvent
     * @throws IOException
     * @throws UnsupportedFlavorException
     */
    public void paste (ActionEvent actionEvent) throws IOException, UnsupportedFlavorException {

        textArea.paste();
    }

    /**
     * Funci? per retallar text.
     * @param actionEvent
     */
    public void cut(ActionEvent actionEvent) {
        textArea.cut();
    }

    /**
     * Abaut del editor.
     * @param actionEvent
     */
    public void about(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText("Editor de Text");
        alert.setContentText("Editor de text sencill");

        alert.showAndWait();
    }

    /**
     * Metode per canviar tamany de la font.
     * @param actionEvent
     */
    public void size(ActionEvent actionEvent)
    {
        double fontSize = Double.parseDouble(((MenuItem) actionEvent.getSource()).getText());
        textArea.setFont(new Font(fontSize));
    }

    /**
     * Metode per desfer el canvis.
     * @param actionEvent
     */
    public void undo(ActionEvent actionEvent) {
        textArea.undo();
    }

    public void obrir(ActionEvent actionEvent) {

        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
        lleguirFitxer(file);

    }

    /**
     * Metode per llegeix file
     * @param file
     * @throws IOException
     */
    private void lleguirFitxer(File file){

        FileReader fr = null;
        BufferedReader br = null;
        String linea="";
        String espacio=" ";

        try {
            fr = new FileReader (file);
            br = new BufferedReader(fr);
            while((linea=br.readLine())!=null){
                textArea.setText(textArea.getText()+espacio+linea);
            }
            br.close();
        }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }


    }

    /**
     * Metode Buscador per saber on guardar el arxiu
     */

    public void save(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar arxiu");
        fileChooser.setInitialFileName(defaultFileName);
        File file = fileChooser.showSaveDialog(stage);
        crearFitxer(file);
    }

    /**
     * Crear un arxiu.
     * @param file
     * @throws IOException
     */
    private void crearFitxer(File file) {

        String txt = textArea.getText();
        try {
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write(txt);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}




