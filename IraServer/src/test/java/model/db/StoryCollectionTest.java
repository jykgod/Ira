package model.db;

import model.entity.Priority;
import model.entity.Story;
import model.entity.StoryType;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by xlo on 16/4/6.
 * it's the testing code for story collection
 */
public class StoryCollectionTest extends DBTesting {

    @Test
    public void should_have_story_after_add_story() throws Exception {
        Story insertStory = new Story("1", "1", "1", StoryType.STORY, "", "", Priority.MINOR, 1);
        StoryCollection storyAddCollection = new StoryCollection();
        storyAddCollection.addStory(insertStory);
        storyAddCollection.submit();

        StoryCollection storyGetCollection = new StoryCollection();
        Story resultStory = storyGetCollection.getStoryData("1");
        assertEquals("1", resultStory.getEpicId());
    }
}