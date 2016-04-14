package controller.story;

import controller.LogicTesting;
import controller.NoSendEvent;
import model.entity.Priority;
import model.entity.StoryType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by xlo on 16/4/14.
 * it's the testing code for adding story
 */
public class AddStoryTesting extends LogicTesting {

    @Test
    public void check_url() throws Exception {
        NoSendEvent noSendEvent = addStory("1", "1", "1", StoryType.STORY, "", "", Priority.MINOR, 1);

        assertEquals("addStory", noSendEvent.getMessage().get(0).getKey());
    }
}
