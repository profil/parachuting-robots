(ns parachuting-robots.views
  (:require [re-frame.core :as re-frame :refer [subscribe
                                                dispatch]]))
(defn parachute
  [color pos]
  [:path {:d (str "m 0 13 m " (* 10 pos) " 8 l 0 -3 a 5 5, 0, 1, 1, 10 0 l 0 3 Z")
           :fill color}])
(defn robot
  [color pos]
  [:rect {:x (* 10 pos) :y 24 :width "10" :height "10" :fill color}])

(defn visualization
  []
  (let [r1 (subscribe [:r1])
        r2 (subscribe [:r2])]
    (fn []
      [:svg {:width "100"
             :height "50"}
       [parachute "#00aad4" (:p @r1)]
       [robot "#00aad4" (:pos @r1)]
       [parachute "#b20045" (:p @r2)]
       [robot "#b20045" (:pos @r2)]])))

(defn input
  []
  (let [input (subscribe [:input])
        program (subscribe [:program])
        r1 (subscribe [:r1])
        r2 (subscribe [:r2])
        timer (subscribe [:timer])]
    (fn []
      [:div#input
       [:div#buttons
        [:button {:on-click #(dispatch [:parse])} "Parse"]
        [:button {:disabled (nil? @program) :on-click #(dispatch [:step])} "Step"]
        [:button {:disabled (nil? @program)
                  :on-click #(dispatch [(if @timer :stop :run)])}
         (if @timer "Stop" "Run")]
        [:button {:on-click #(dispatch [:initialize-db])} "Reset"]]
       [:textarea {:rows "10" :placeholder "Code goes here"
                   :on-change #(dispatch [:edit (-> % .-target .-value)])}
        @input]
       [:table
        [:tr
         [:th "R" [:sub "1"]]
         [:th "R" [:sub "2"]]
         [:th "Label"]
         [:th "Instruction"]]
        (doall
          (map-indexed
          (fn [i [_ instruction label]]
            ^{:key i}
            [:tr
             [:td [:div {:class (when (= i (:inst @r1)) "r1")}]]
             [:td [:div {:class (when (= i (:inst @r2)) "r2")}]]
             [:td (second label)]
             [:td (->> instruction
                       (rest)
                       (interpose " ")
                       (apply str))]])
          @program))]])))

(defn app []
  (let []
    (fn []
      [:div#app
       [visualization]
       [input]])))
