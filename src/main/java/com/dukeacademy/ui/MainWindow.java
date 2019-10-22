package com.dukeacademy.ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

import com.dukeacademy.commons.core.GuiSettings;
import com.dukeacademy.commons.core.LogsCenter;
import com.dukeacademy.logic.commands.CommandResult;
import com.dukeacademy.logic.commands.exceptions.CommandException;
import com.dukeacademy.logic.parser.exceptions.ParseException;
import com.dukeacademy.logic.question.QuestionsLogic;
import com.dukeacademy.model.program.TestCaseResult;
import com.dukeacademy.model.question.TestCase;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";



    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private QuestionsLogic questionsLogic;

    // Independent Ui parts residing in this Ui container
    private QuestionListPanel questionListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;

    private Editor editorPanel;
    private RunCodeResult runCodeResultPanel;
    private ProblemStatementPanel problemStatementPanel;


    @FXML
    private StackPane problemStatementPlaceholder;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane questionListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    @FXML

    private AnchorPane editorPlaceholder;

    @FXML
    private AnchorPane runCodeResultPlaceholder;

    @FXML
    private HBox activityWindowPlaceholder;

    public MainWindow(Stage primaryStage, QuestionsLogic questionsLogic) {

        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.questionsLogic = questionsLogic;

        // Configure the UI
        setWindowDefaultSize(questionsLogic.getGuiSettings());

        setAccelerators();

        helpWindow = new HelpWindow();

        //set controller
        //fxmlLoader.setController(new MainWindowController)
    }

    /**
     * Gets primary stage.
     *
     * @return the primary stage
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);

        /*
         * TODO: the code below can be removed once the bug reported here
         * https://bugs.openjdk.java.net/browse/JDK-8131666
         * is fixed in later version of SDK.
         *
         * According to the bug report, TextInputControl (TextField, TextArea) will
         * consume function-key events. Because CommandBox contains a TextField, and
         * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will
         * not work when the focus is in them because the key event is consumed by
         * the TextInputControl(s).
         *
         * For now, we add following event filter to capture such key events and open
         * help window purposely so to support accelerators even when focus is
         * in CommandBox or ResultDisplay.
         */
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                menuItem.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        questionListPanel = new QuestionListPanel(questionsLogic.getFilteredPersonList());
        questionListPanelPlaceholder.getChildren().add(questionListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter =
            new StatusBarFooter(questionsLogic.getQuestionBankFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());

        editorPanel = new Editor();
        editorPlaceholder.getChildren().add(editorPanel.getRoot());

        // Passing in sample test case and sample test case result into the constructor of RunCodeResult.
        // The sample problem in this context is an adder function.
        // Test case given is 1, 1. Expected result is 2, from 1 + 1.
        runCodeResultPanel = new RunCodeResult(new TestCase("1 1", "2"),
                TestCaseResult.getFailedTestCaseResult("1 1", "2", "3"));
        runCodeResultPlaceholder.getChildren().add(runCodeResultPanel.getRoot());

        //problemStatementPanel =
        //    new ProblemStatementPanel(questionsLogic.getProblemStatement());
        problemStatementPanel = new ProblemStatementPanel("test problem "
            + "statement field");
        problemStatementPlaceholder.getChildren().add(problemStatementPanel.getRoot());


    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }

    /**
     * Opens a new text file from editor.
     */
    @FXML
    public void handleNew() {

    }

    /**
     * Opens an existing text file from editor.
     *
     * @throws IOException the io exception
     */
    @FXML
    public void handleOpen() throws IOException {
        Stage stage = new Stage();
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        File selectedFile = chooser.showOpenDialog(stage);

        FileReader FR = new FileReader(selectedFile.getAbsolutePath().toString());
        BufferedReader BF = new BufferedReader(FR);

        StringBuilder sb = new StringBuilder();

        String myText = "";

        while ((myText = BF.readLine()) != null) {
            sb.append(myText + "\n");
        }
    }

    /**
     * Saves the current text file in editor.
     */
    @FXML
    public void handleSave() {

    }

    /**
     * Saves the current text file in editor, with custom filepath.
     */
    @FXML
    public void handleSaveAs() {

    }


    /**
     * Show.
     */
    void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        questionsLogic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
    }

    /**
     * Gets person list panel.
     *
     * @return the person list panel
     */
    public QuestionListPanel getPersonListPanel() {
        return questionListPanel;
    }

    public Editor getEditorPanel() {
        return editorPanel;
    }

    public RunCodeResult getRunCodeResultPanel() {
        return runCodeResultPanel;
    }

    /**
     * Executes the command and returns the result.
     *
     * @see QuestionsLogic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws Exception {
        try {
            CommandResult commandResult = questionsLogic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            if (commandResult.isView()) {
                problemStatementPanel.setProblemStatement(questionsLogic.getProblemStatement());
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }

    @FXML
    private void displayProblemStatement() throws Exception{
        try {
            problemStatementPanel = new ProblemStatementPanel();
            activityWindowPlaceholder.getChildren().add(problemStatementPanel.getRoot());
        } catch (Exception e) {
            throw e;
        }
    }
}
