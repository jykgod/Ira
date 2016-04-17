(ns tools.SessionHelper
  (:import (service.net SessionManager)
           (model.config UserAccessConfig)))

(let [sessionManager (. SessionManager getSessionManager)]
  (defn getSessionMessage
    [socket]
    (.getSessionMessage sessionManager socket))

  (defn loginSession
    [socket username]
    (.loginSession sessionManager socket username))

  (defn isLogin
    [socket]
    (.isLogin sessionManager socket))

  (defn getUsername
    [socket]
    (.getUsername sessionManager socket))

  (defn getPackageServer
    [socket]
    (.getPackageServer sessionManager socket))

  (defn haveAccess
    [socket command]
    (and (isLogin socket) (.haveLicense (. UserAccessConfig getConfig) (getUsername socket) command))))

