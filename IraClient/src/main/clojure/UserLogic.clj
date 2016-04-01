(ns UserLogic
  (:import (net.sf.json JSONObject)
           (model.data ConnectionData)
           (service ProtocolBuilder)
           (view PageManager)))
(refer 'tools.ClojureManager)

(let [connectionData (. ConnectionData getConnectionData)]
  (defn getSessionId
    [message _ _]
    (let [json (. JSONObject fromObject (new String message))
          pageManager (. PageManager getPageManager)]
      (.setSessionID connectionData (.getString json "result"))
      (.unlockNowPage pageManager)
      (.showPage pageManager "login")))

  (defn login
    [message _ _]
    (let [json (. JSONObject fromObject (new String message))]
      (if (.equals "ok" (.getString json "result"))
        (.addMessage (.getPackageSolver connectionData) (. ProtocolBuilder useApp "ira"))))
    true)

  (defn useApp
    [message _ _]
    (let [json (. JSONObject fromObject (new String message))]
      (if (.equals "ok" (.getString json "result"))
        (.addMessage (.getPackageSolver connectionData) (. ProtocolBuilder testCommand))))
    true)

  (defn testCommand
    [_ _ _]

    (let [pageManager (. PageManager getPageManager)]
      (.unlockNowPage pageManager)
      (.showPage pageManager "test"))
    true)
  )

(registerFunction "/getSessionID" getSessionId)
(registerFunction "/login" login)
(registerFunction "/useApp" useApp)
(registerFunction "/testCommand" testCommand)
