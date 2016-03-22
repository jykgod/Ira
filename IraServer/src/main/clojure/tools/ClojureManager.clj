(ns tools.ClojureManager
  (:import (service.clojure ScriptManager)))

(defn registerFunction
  [functionName function]
  (let [scriptManager (. ScriptManager getScriptManager)]
    (.registerMethod scriptManager functionName function)))
