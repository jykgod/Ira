package model.db;

import model.entity.Story;
import org.bson.Document;

import java.util.List;
import java.util.Map;

/**
 * Created by xlo on 16/4/2.
 * it's the story collection
 */
public class StoryCollection extends IraCollection {

    public void addStory(Story story) {
        this.insertData(new Document(story.getObject()));
    }

    public Story getStory(String id) {
        this.lockCollection();
        List<Map<String, Object>> iterator = this.collection.find(new Document().append("_id", id));
        if (iterator.size() == 0) {
            return null;
        }
        return new Story(this.addDocumentToUsing(iterator.get(0)));
    }

    public Story getStoryData(String id) {
        List<Map<String, Object>> iterator = this.collection.find(new Document().append("_id", id));
        if (iterator.size() == 0) {
            return null;
        }
        return new Story(this.getDocumentNotUsing(iterator.get(0)));
    }

}
