(ns UserLogic
  (:import (net.sf.json JSONObject)))
(refer 'tools.ClojureManager)
(refer 'tools.MessageHelper)
(refer 'tools.SessionHelper)

(defn loginApp
  "it's the login function"
  [message socket eventMessage]
  (setDefaultMessage eventMessage "/loginApp")
  (let [json (. JSONObject fromObject (new String message))
        username (.getString json "username")]
    (loginSession socket username))
  true)

(defn testCommand
  [_ _ eventMessage]
  (setDefaultMessage eventMessage "/testCommand")
  true)

(registerFunction "/loginApp" loginApp)
(registerFunction "/testCommand" testCommand)