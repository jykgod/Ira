(ns StoryLogic
  (:import (model.entity Story)
           (model.db StoryCollection)
           (net.sf.json JSONObject)))
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

(defn addWatcherToStory
  [message socket eventMessage]
  (setDefaultMessage eventMessage "addWatcherToStory")
  (if (haveAccess socket "addWatcherToStory")
    (let [json (. JSONObject fromObject (new String message))
          username (getUsername socket)
          story (.getStory (new StoryCollection) (.getString json "id"))
          aimUser (.getString json "username")
          watcher (.getWatcher story)]
      (if (and (.contains watcher username) (not (.contains watcher aimUser)))
        (do (.addWatcher story aimUser) true) false))
    false))

(registerFunction "addStory" addStory)
(registerFunction "addWatcherToStory" addWatcherToStory)
