package controller.story;

import controller.LogicTesting;
import controller.NoSendEvent;
import model.db.StoryCollection;
import model.entity.Priority;
import model.entity.Story;
import model.entity.StoryType;
import net.sf.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by xlo on 16/4/14.
 * it's the testing code for adding watcher to story
 */
public class AddWatcherToStoryTesting extends LogicTesting {

    @Test
    public void check_url() throws Exception {
        NoSendEvent noSendEvent = addWatcherToStory("1", "1");

        assertEquals("addWatcherToStory", noSendEvent.getMessage().get(0).getKey());
    }

    @Test
    public void should_fail_when_add_story_with_out_login() throws Exception {
        addStory("1", "1", "1", StoryType.STORY, "", "", Priority.MINOR, 1);

        logout();

        JSONObject jsonObject = JSONObject.fromObject(new String(
                addWatcherToStory("1", "aim")
                        .getMessage().get(0).getValue()));

        assertEquals("fail", jsonObject.getString("result"));
    }

    @Test
    public void should_have_new_watcher_when_add_ok() throws Exception {
        loginApp("test user");
        addStory("1", "1", "1", StoryType.STORY, "", "", Priority.MINOR, 1);
        JSONObject jsonObject = JSONObject.fromObject(new String(
                addWatcherToStory("1", "aim")
                        .getMessage().get(0).getValue()));
        Story story = new StoryCollection().getStoryData("1");

        assertEquals("ok", jsonObject.getString("result"));
        assertEquals(2, story.getWatcher().size());
        assertTrue(story.getWatcher().contains("aim"));
    }

}
