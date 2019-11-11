package com.dukeacademy.logic.commands.browse;

import java.util.Arrays;
import java.util.logging.Logger;

import com.dukeacademy.commons.core.LogsCenter;
import com.dukeacademy.logic.commands.Command;
import com.dukeacademy.logic.commands.CommandResult;
import com.dukeacademy.logic.commands.exceptions.CommandException;
import com.dukeacademy.logic.question.QuestionsLogic;
import com.dukeacademy.model.state.Activity;
import com.dukeacademy.model.state.ApplicationState;

/**
 * The type Browse command.
 */
public class BrowseCommand implements Command {
    private final Logger logger;
    private final QuestionsLogic questionsLogic;
    private final ApplicationState applicationState;
    private final AttributeContainsKeywordsPredicate predicate;
    private final boolean validity;

    /**
     * Instantiates a new Browse command.
     *
     * @param questionsLogic the questions logic
     * @param keywords       the keywords
     */
    public BrowseCommand(QuestionsLogic questionsLogic, ApplicationState applicationState, String keywords) {
        this.logger = LogsCenter.getLogger(BrowseCommand.class);
        this.questionsLogic = questionsLogic;
        this.applicationState = applicationState;
        String[] attributeKeywords = keywords.trim().split("\\s+");
        this.predicate =
            new AttributeContainsKeywordsPredicate(Arrays.asList(attributeKeywords));
        if (keywords.isEmpty()) {
            validity = false;
        } else {
            validity = true;
        }
    }

    @Override
    public CommandResult execute() throws CommandException {
        if (validity == false) {
            throw new CommandException("Browse should be followed by keywords"
                + ".");
        }
        // Update status of question
        this.questionsLogic.filterQuestionsList(predicate);
        logger.info("Listing questions that contains keywords specified.");
        String feedback = "List all questions that contains the corresponding"
            + " keywords as long as they appear in title, topics, description,"
            + " status or difficulty.";
        this.applicationState.setCurrentActivity(Activity.QUESTION);
        return new CommandResult(feedback, false);
    }
}
