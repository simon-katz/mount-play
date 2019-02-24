(ns
    ^{:doc "Namespace to support hacking at the REPL."
      ;; This is in lieu of using `clojure.tools.namespace.repl/disable-reload!`,
      ;; which doesn't work well when this ns form is re-evaluated (whether
      ;; the re-evaluation is done manually or as part of Cider helpfully doing
      ;; so every time you do e.g. `cider-eval-defun-at-point`).
      :clojure.tools.namespace.repl/load false
      }
    user
  (:require [clojure.java.javadoc :refer [javadoc]]
            [clojure.pprint :refer [pp pprint]]
            [clojure.repl :refer :all ; [apropos dir doc find-doc pst source]
             ]
            [clojure.tools.namespace.move :refer :all]
            [clojure.tools.namespace.repl :refer :all]
            [mount.core :as mount]
            [mount.tools.graph :as mount-graph]))

;;;; ___________________________________________________________________________
;;;; Clojure workflow.
;;;; See:
;;;; - http://blogish.nomistech.com/clojure/clojure-workflow-demo/
;;;; - http://thinkrelevance.com/blog/2013/06/04/clojure-workflow-reloaded
;;;; - https://github.com/stuartsierra/component#reloading

(defonce the-system
  ;; "A container for the current instance of the application.
  ;; Only used for interactive development."
  ;; 
  ;; Don't want to lose this value if this file is recompiled (when
  ;; changes are made to the useful additional utilities for the REPL
  ;; at the end of the file), so use `defonce`.
  ;; But note that this /is/ blatted when a `reset` is done.
  nil)

(defn start
  "Starts the current development system."
  []
  (mount/start))

(defn stop
  "Shuts down and destroys the current development system."
  []
  (mount/stop))

(defn reset
  "Stop, refresh and start."
  []
  (stop)
  (refresh :after 'user/start))

(defn reset-all
  "Stop, refresh-all and go."
  []
  (stop)
  (refresh-all :after 'user/start))

;;;; ___________________________________________________________________________

(comment
  (mount-graph/states-with-deps) ; deps missing -- see notes.org
  )
