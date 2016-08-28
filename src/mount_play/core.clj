(ns mount-play.core
  (:require [mount.core :as mount]))

(defmacro make-thing [v]
  `(do (println "===== Starting" ~v)
       ~v))

(mount/defstate thing-a
  :start (make-thing :a)
  :stop (println "===== Stopping thing-a, which is" thing-a))

(mount/defstate thing-b
  :start (make-thing :b)
  :stop (println "===== Stopping thing-b, which is" thing-b))

(mount/defstate thing-c
  :start (make-thing {:a thing-a
                      :b thing-b})
  :stop (println "===== Stopping thing-c, which is" thing-c))
