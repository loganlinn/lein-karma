(ns leiningen.karma
  "Execute Karma test runner.

Assumes the Karma has been installed via NPM (see lein-npm) and bin
can be located in NPM's node_modules directory.

Everything after `lein karama` will be passed through to Karma bin.

See `lein karma -- --help` for available commands"
  (:require
   [clojure.java.io :as io]
   [leiningen.core.eval :refer [sh]]
   [leiningen.core.main :as main]
   [leiningen.core.project :as project]))

(defn npm-root
  "Returns path of npm's node_modules directory. Follows convention of lein-npm"
  [project]
  (if-let [root (:npm-root project)]
    (if (keyword? root)
      (project root) ;; e.g. support using :target-path
      root)
    (:root project)))

(defn ^java.io.File karma-bin
  "Returns File reference to karma bin from NPM node_modules"
  [project]
  (io/file (npm-root project) "node_modules" "karma" "bin" "karma"))

(defn karma
  {:help-arglists '([karma-cmd? karma-args*])}
  [project & args]
  (let [karma (karma-bin project)]
    (when-not (.exists karma)
      (main/abort "Could not locate karma executable. Ensure NPM dependencies have been installed."))
    (when-not (.canExecute karma)
      (main/abort (str (.getPath karma) " is not executable")))
    (let [exit-code (apply sh (.getPath karma) args)]
      (when (pos? exit-code)
        (main/abort exit-code "Karma exited with status " exit-code)))))
