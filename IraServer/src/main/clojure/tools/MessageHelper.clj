(ns tools.MessageHelper)

(defn setDefaultMessage
  [eventMessage url]
  (. eventMessage setDefaultMessage url))

(defn setSuccessMessage
  [eventMessage url message]
  (. eventMessage setSuccessMessage url message))

(defn setFailedMessage
  [eventMessage url]
  (. eventMessage setFailedMessage url))
