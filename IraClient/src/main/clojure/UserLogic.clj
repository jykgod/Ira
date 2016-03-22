(ns UserLogic)
(refer 'tools.ClojureManager)

(defn testCommand
  [message _ _]
  (println (new String message))
  true)

(registerFunction "testCommand" testCommand)
