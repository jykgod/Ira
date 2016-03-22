(ns UserLogic)
(refer 'tools.ClojureManager)
(refer 'tools.MessageHelper)

(defn testCommand
  [_ _ eventMessage]
  (setDefaultMessage eventMessage "testCommand")
  true)

(registerFunction "testCommand" testCommand)