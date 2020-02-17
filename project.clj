(defproject assignments "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/clojure "1.10.0-RC2"]
                 [org.clojure/core.async "0.4.490"]]
  :resource-paths ["resources/REBL-0.9.108.jar"]
  :profiles {:dev
             {:dependencies [[com.bhauman/rebel-readline "0.1.4"]]}
             :kaocha {:dependencies [[lambdaisland/kaocha "0.0-573"]]}}
  :aliases {"kaocha" ["with-profile" "+kaocha" "run" "-m" "kaocha.runner"]}
  :repl-options {:init-ns assignments.core}
  :main assignments.core)
