package com.dukeacademy.logic.commands.help;

import com.dukeacademy.logic.commands.Command;
import com.dukeacademy.logic.commands.CommandFactory;
import com.dukeacademy.logic.commands.exceptions.InvalidCommandArgumentsException;
import com.dukeacademy.logic.commands.home.HomeCommand;
import com.dukeacademy.logic.program.ProgramSubmissionLogic;
import com.dukeacademy.logic.question.QuestionsLogic;
import com.dukeacademy.model.state.ApplicationState;

public class HelpCommandFactory implements CommandFactory {
    private final QuestionsLogic questionsLogic;
    private final ProgramSubmissionLogic programSubmissionLogic;
    private final ApplicationState applicationState;

    /**
     * Instantiates a new Help command factory.
     *
     * @param questionsLogic         the questions logic
     * @param programSubmissionLogic the program submission logic
     * @param applicationState       the application state
     */
    public HelpCommandFactory(QuestionsLogic questionsLogic, ProgramSubmissionLogic programSubmissionLogic,
                              ApplicationState applicationState) {
        this.questionsLogic = questionsLogic;
        this.programSubmissionLogic = programSubmissionLogic;
        this.applicationState = applicationState;
    }

    @Override
    public String getCommandWord() {
        return "help";
    }

    @Override
    public Command getCommand(String commandArguments) throws InvalidCommandArgumentsException {
        if (!"".equals(commandArguments)) {
            throw new InvalidCommandArgumentsException("Help command does not "
                    + "take any arguments");
        }

        return new HelpCommand(this.questionsLogic, this.programSubmissionLogic, applicationState);
    }
}
