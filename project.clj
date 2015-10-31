(defproject parachuting-robots "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "0.0-3269"]
                 [reagent "0.5.0"]
                 [re-frame "0.4.1"]
                 [com.lucasbradstreet/instaparse-cljs  "1.4.1.0"]]

  :source-paths ["src/clj"]

  :plugins [[lein-cljsbuild "1.0.6"]
            [lein-figwheel "0.3.3" :exclusions [cider/cider-nrepl]]  ]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"  ]

  :figwheel { :css-dirs ["resources/public/css"]}

  :cljsbuild {:builds [{:id "dev"
                        :source-paths ["src/cljs"]

                        :figwheel {:on-jsload "parachuting-robots.core/mount-root"}

                        :compiler {:main parachuting-robots.core
                                   :output-to "resources/public/js/compiled/app.js"
                                   :output-dir "resources/public/js/compiled/out"
                                   :asset-path "js/compiled/out"
                                   :source-map-timestamp true}}

                       {:id "min"
                        :source-paths ["src/cljs"]
                        :compiler {:main parachuting-robots.core
                                   :output-to "resources/public/js/compiled/app.js"
                                   :optimizations :advanced
                                   :pretty-print false}}]})
