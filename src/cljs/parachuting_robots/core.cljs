(ns parachuting-robots.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [parachuting-robots.handlers]
            [parachuting-robots.subs]
            [parachuting-robots.views :as views]))

(defn mount-root []
  (reagent/render [views/app] (.-body js/document)))

(defn init [] 
  (re-frame/dispatch-sync [:initialize-db])
  (mount-root))

(init)
