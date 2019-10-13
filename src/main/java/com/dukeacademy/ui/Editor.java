package com.dukeacademy.ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import com.dukeacademy.commons.core.LogsCenter;

import java.awt.datatransfer.Clipboard;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;

/**
 * The type Editor.
 */
public class Editor extends UiPart<Region>{

        private final Logger logger = LogsCenter.getLogger(getClass());

        private static final String FXML = "Editor.fxml";

        @FXML
        private Button btn_Copy;

        @FXML
        private Button btn_Save;

        @FXML
        private Button btn_Submit;

        @FXML
        private Button btn_Exit;

        @FXML
        private TextArea textOutput;


    /**
     * On click btn copy.
     *
     * @param e the e
     */
    @FXML
        public void onClick_btn_Copy(ActionEvent e) {
            String myText = textOutput.getText().toString();
            StringSelection stringSelection = new StringSelection(myText);
            final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection,null);
        }

    /**
     * On click btn save.
     *
     * @param e the e
     * @throws IOException the io exception
     */
    @FXML
        public void onClick_btn_Save(ActionEvent e) throws IOException {
            Stage stage = new Stage();
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Save File");
            File selectedFile = chooser.showOpenDialog(stage);
            FileWriter FW = new FileWriter(selectedFile.getAbsolutePath());
            FW.write(textOutput.getText());
            FW.close();
        }

    /**
     * On click btn submit.
     *
     * @param e the e
     */
    @FXML
        public void onClick_btn_Submit(ActionEvent e) {

        }

    /**
     * On click btn exit.
     *
     * @param e the e
     */
    @FXML
        public void onClick_btn_Exit(ActionEvent e) {
            //Platfrom.exit()?
        }

    /**
     * Sets text output.
     *
     * @param textOutput the text output
     */
    public void setTextOutput(String textOutput) {
                this.textOutput.setText(textOutput);
        }

    /**
     * Instantiates a new Editor.
     */
    public Editor() {
                super(FXML);
        }

}
