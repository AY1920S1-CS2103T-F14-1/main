package com.dukeacademy.model;

import static com.dukeacademy.commons.util.CollectionUtil.requireAllNonNull;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.logging.Logger;

import com.dukeacademy.commons.core.GuiSettings;
import com.dukeacademy.commons.core.LogsCenter;
import com.dukeacademy.model.question.Question;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

/**
 * Represents the in-memory model of the question bank data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final QuestionBank questionBank;
    private final UserPrefs userPrefs;
    private final FilteredList<Question> filteredQuestions;
    private final ArrayList<String> profile;
    private final ArrayList<Question> questionToView;

    /**
     * Initializes a ModelManager with the given questionBank and userPrefs.
     *
     * @param questionBank the question bank
     * @param userPrefs    the user prefs
     */
    public ModelManager(ReadOnlyQuestionBank questionBank, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(questionBank, userPrefs);

        logger.fine("Initializing with question bank: " + questionBank + " and user prefs " + userPrefs);

        this.questionBank = new QuestionBank(questionBank);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredQuestions = new FilteredList<>(this.questionBank.getQuestionList());
        profile = new ArrayList<>();
        profile.add("initialised profile placeholder");
        questionToView = new ArrayList<>();
    }

    /**
     * Instantiates a new Model manager.
     */
    public ModelManager() {
        this(new QuestionBank(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getQuestionBankFilePath() {
        return userPrefs.getQuestionBankFilePath();
    }

    @Override
    public void setQuestionBankFilePath(Path questionBankFilePath) {
        requireNonNull(questionBankFilePath);
        userPrefs.setQuestionBankFilePath(questionBankFilePath);
    }

    //=========== QuestionBank ================================================================================

    @Override
    public void setQuestionBank(ReadOnlyQuestionBank questionBank) {
        this.questionBank.resetData(questionBank);
    }

    @Override
    public ReadOnlyQuestionBank getQuestionBank() {
        return questionBank;
    }

    @Override
    public boolean hasQuestion(Question question) {
        requireNonNull(question);
        return questionBank.hasQuestion(question);
    }

    @Override
    public void deleteQuestion(Question target) {
        questionBank.removeQuestion(target);
    }

    @Override
    public void addQuestion(Question question) {
        questionBank.addQuestion(question);
        updateFilteredQuestionList(PREDICATE_SHOW_ALL_QUESTIONS);
    }

    @Override
    public void setQuestion(Question target, Question editedQuestion) {
        requireAllNonNull(target, editedQuestion);

        questionBank.setQuestion(target, editedQuestion);
    }

    //=========== Filtered Question List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Question} backed by the internal list of
     * {@code versionedQuestionBank}
     */
    @Override
    public ObservableList<Question> getFilteredQuestionList() {
        return filteredQuestions;
    }

    @Override
    public void updateFilteredQuestionList(Predicate<Question> predicate) {
        requireNonNull(predicate);
        filteredQuestions.setPredicate(predicate);
    }

    //============= Profile Page =============================================================================

    /**
     * Returns the user's profile page. (will be updated to adoc or markdown form).
     * @param s
     */
    @Override
    public void updateProfile(String s) {
        questionBank.updateProfile(s);
        profile.clear();
        profile.add(s);
        logger.info("updated profile in model manager: " + profile.get(0));
    }

    /**
     * Returns the user profile page.
     */
    public ArrayList<String> getProfile() {
        return profile;
    }

    //============= View a Question ===========================================
    @Override
    public void setQuestionToView(Question questionToView) {
        this.questionToView.set(0, questionToView);
    }

    @Override
    public String getProblemStatement() {
        if (this.questionToView.isEmpty()) {
            return "problem view page is empty.";
        }
        return this.questionToView.get(0).getDescription().description;
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return questionBank.equals(other.questionBank)
                && userPrefs.equals(other.userPrefs)
                && filteredQuestions.equals(other.filteredQuestions);
    }

}
