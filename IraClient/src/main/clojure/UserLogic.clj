(ns UserLogic
  (:import (net.sf.json JSONObject)
           (model.data ConnectionData)
           (view PageManager)))
(refer 'tools.ClojureManager)

(defn getSessionId
  [message _ _ ]
  (let [json (. JSONObject fromObject (new String message))
        pageManager (. PageManager getPageManager)]
    (.setSessionID (. ConnectionData getConnectionData) (.getString json "result"))
    (.unlockNowPage pageManager)
    (.showPage pageManager "register")))

(defn testCommand
  [message _ _]
  (println (new String message))
  true)

(registerFunction "/getSessionID" getSessionId)
(registerFunction "testCommand" testCommand)
