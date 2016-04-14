(ns StoryLogic
  (:import (model.entity Story)
           (model.db StoryCollection)))
(refer 'tools.ClojureManager)
(refer 'tools.MessageHelper)
(refer 'tools.SessionHelper)

(defn addStory
  [message socket eventMessage]
  (setDefaultMessage eventMessage "addStory")
  (if (haveAccess socket "addStory")
    (let [username (getUsername socket)
          story (new Story)]
      (.updateValueFromJson story (new String message))
      (.addWatcher story username)
      (.addStory (new StoryCollection) story)
      true)
    false))

(registerFunction "addStory" addStory)
