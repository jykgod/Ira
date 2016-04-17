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

    @Test
    public void should_fail_when_add_story_with_out_login() throws Exception {
        JSONObject jsonObject = JSONObject.fromObject(new String(
                addStory("1", "1", "1", StoryType.STORY, "", "", Priority.MINOR, 1)
                        .getMessage().get(0).getValue()));

        assertEquals("fail", jsonObject.getString("result"));
    }

    @Test
    public void should_be_the_watcher_when_add_ok() throws Exception {
        loginApp("test user");
        JSONObject jsonObject = JSONObject.fromObject(new String(
                addStory("1", "1", "1", StoryType.STORY, "", "", Priority.MINOR, 1)
                        .getMessage().get(0).getValue()));
        Story story = new StoryCollection().getStoryData("1");

        assertEquals("ok", jsonObject.getString("result"));
        assertEquals(1, story.getWatcher().size());
        assertEquals("test user", story.getWatcher().get(0));
    }
}
