package com.dukeacademy.logic.commands;

import static com.dukeacademy.logic.commands.CommandTestUtil.DESC_AMY;
import static com.dukeacademy.logic.commands.CommandTestUtil.DESC_BOB;
import static com.dukeacademy.logic.commands.CommandTestUtil.VALID_DIFFICULTY_BOB;
import static com.dukeacademy.logic.commands.CommandTestUtil.VALID_STATUS_BOB;
import static com.dukeacademy.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static com.dukeacademy.logic.commands.CommandTestUtil.VALID_TITLE_BOB;
import static com.dukeacademy.logic.commands.CommandTestUtil.VALID_TOPIC_BOB;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.dukeacademy.testutil.EditQuestionDescriptorBuilder;

public class EditQuestionDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditCommand.EditQuestionDescriptor descriptorWithSameValues = new EditCommand.EditQuestionDescriptor(DESC_AMY);
        assertTrue(DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY.equals(DESC_AMY));

        // null -> returns false
        assertFalse(DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY.equals(DESC_BOB));

        // different name -> returns false
        EditCommand.EditQuestionDescriptor editedAmy = new EditQuestionDescriptorBuilder(DESC_AMY)
            .withTitle(VALID_TITLE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different topic -> returns false
        editedAmy = new EditQuestionDescriptorBuilder(DESC_AMY).withTopic(VALID_TOPIC_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different status -> returns false
        editedAmy = new EditQuestionDescriptorBuilder(DESC_AMY).withStatus(VALID_STATUS_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different difficulty -> returns false
        editedAmy = new EditQuestionDescriptorBuilder(DESC_AMY).withDifficulty(VALID_DIFFICULTY_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditQuestionDescriptorBuilder(DESC_AMY).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }
}