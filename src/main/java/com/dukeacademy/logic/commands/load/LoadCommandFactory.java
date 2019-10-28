package com.dukeacademy.logic.commands.load;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

import com.dukeacademy.MainApp;
import com.dukeacademy.commons.core.LogsCenter;
import com.dukeacademy.logic.commands.Command;
import com.dukeacademy.logic.commands.CommandFactory;
import com.dukeacademy.logic.commands.exceptions.InvalidCommandArgumentsException;
import com.dukeacademy.logic.question.QuestionsLogic;

/**
 * Class to represent the necessary components for the creation of a Load command.
 */
public class LoadCommandFactory implements CommandFactory {
    private final QuestionsLogic questionsLogic;
    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    /**
     * Instantiates a new Load command factory.
     *
     * @param questionsLogic         the questions logic
     */
    public LoadCommandFactory(QuestionsLogic questionsLogic) {
        this.questionsLogic = questionsLogic;
    }


    @Override
    public String getCommandWord() {
        return "loadquestions";
    }

    @Override
    public Command getCommand(String commandArguments)
        throws InvalidCommandArgumentsException{
        Path sampleQuestionsFilePath =
            Paths.get(System.getProperty("user.home")).resolve("Desktop").resolve(commandArguments);
        if (!sampleQuestionsFilePath.toFile().exists()) {
            throw new InvalidCommandArgumentsException("We cannot find the "
                + "specified text file on your desktop.\n"
                + "Is it you type the file name wrongly?");
        }
        return new LoadCommand(this.questionsLogic, sampleQuestionsFilePath);
    }
}
