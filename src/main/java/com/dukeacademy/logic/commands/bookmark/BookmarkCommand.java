package com.dukeacademy.logic.commands.bookmark;

import java.util.logging.Logger;

import com.dukeacademy.commons.core.LogsCenter;
import com.dukeacademy.logic.commands.Command;
import com.dukeacademy.logic.commands.CommandResult;
import com.dukeacademy.logic.commands.attempt.AttemptCommand;
import com.dukeacademy.logic.commands.exceptions.CommandException;
import com.dukeacademy.logic.program.ProgramSubmissionLogic;
import com.dukeacademy.logic.question.QuestionsLogic;
import com.dukeacademy.model.question.Question;
import com.dukeacademy.model.question.entities.Status;

/**
 * Command for attempting a question. This command loads the selected question into the registered
 * ProgramSubmissionLogic instance.
 */
public class BookmarkCommand implements Command {
    private final Logger logger;
    private final QuestionsLogic questionsLogic;
    private final int index;

    /**
     * Instantiates a new Attempt command.
     *
     * @param index                  the index
     * @param questionsLogic         the questions logic
     */
    public BookmarkCommand(int index, QuestionsLogic questionsLogic) {
        this.logger = LogsCenter.getLogger(BookmarkCommand.class);
        this.index = index - 1;
        this.questionsLogic = questionsLogic;
    }

    @Override
    public CommandResult execute() throws CommandException {
        try {
            // Update isBookmarked of question
            Question bookmarkedQuestion = this.questionsLogic.getQuestion(index).withNewIsBookmarked(true);
            this.questionsLogic.setQuestion(index, bookmarkedQuestion);
            logger.info("Bookmarked question at index " + index + " : " + bookmarkedQuestion);

            String feedback = "Bookmarked question " + (index + 1) + " : " + bookmarkedQuestion.getTitle();
            return new CommandResult(feedback, false, false, false, false);
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException("Index " + (index + 1) + " entered out of range for current list of questions.");
        }
    }

}
