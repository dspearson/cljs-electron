(defproject hello-electron "0.1.0-SNAPSHOT"
  :license {:name "The MIT License"
            :url "https://opensource.org/licenses/MIT"}
  :source-paths ["src"]
  :description "A hello world application for electron"
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/clojurescript "1.10.520"]
                 [figwheel "0.5.19"]
                 [reagent "0.8.1"]
                 [ring/ring-core "1.7.1"]]
  :plugins [[lein-cljsbuild "1.1.7"]
            [lein-figwheel "0.5.19"]
            [lein-cooper "1.2.2"]]

  :clean-targets ^{:protect false} ["resources/main.js"
                                    "resources/public/js/ui-core.js"
                                    "resources/public/js/ui-core.js.map"
                                    "resources/public/js/ui-out"]
  :cljsbuild
  {:builds
   [{:source-paths ["electron_src"]
     :id "electron-dev"
     :compiler {:output-to "resources/main.js"
                :output-dir "resources/public/js/electron-dev"
                :optimizations :simple
                :pretty-print true
                :cache-analysis true}}
    {:source-paths ["ui_src" "dev_src"]
     :id "frontend-dev"
     :compiler {:output-to "resources/public/js/ui-core.js"
                :output-dir "resources/public/js/ui-out"
                :source-map true
                :asset-path "js/ui-out"
                :optimizations :none
                :cache-analysis true
                :main "dev.core"}}
    {:source-paths ["electron_src"]
     :id "electron-release"
     :compiler {:output-to "resources/main.js"
                :output-dir "resources/public/js/electron-release"
                :externs ["cljs-externs/common.js"]
                :optimizations :advanced
                :cache-analysis true
                :infer-externs true}}
    {:source-paths ["ui_src"]
     :id "frontend-release"
     :compiler {:output-to "resources/public/js/ui-core.js"
                :output-dir "resources/public/js/ui-release-out"
                :source-map "resources/public/js/ui-core.js.map"
                :externs ["cljs-externs/common.js"]
                :optimizations :advanced
                :cache-analysis true
                :infer-externs true
                :process-shim false
                :main "ui.core"}}]}
  :figwheel {:http-server-root "public"
             :css-dirs ["resources/public/css"]
             :ring-handler tools.figwheel-middleware/app
             :server-port 3449})
