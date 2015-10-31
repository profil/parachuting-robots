(ns parachuting-robots.handlers
    (:require [re-frame.core :as re-frame :refer [register-handler]]
              [parachuting-robots.db :as db]
              [parachuting-robots.interpreter :as i]
              [parachuting-robots.parser :as p]))

(register-handler
 :initialize-db
 (fn  [_ _]
   (db/init-db)))

(register-handler
  :edit
  (fn [db [_ data]]
    (assoc db :input data)))

(register-handler
  :parse
  (fn [db]
    (let [data (p/parse (:input db))]
      (assoc db :program data))))

(register-handler
  :step
  (fn [db]
    (i/step db)))

(register-handler
  :run
  (fn [db]
    (assoc db :timer (js/setInterval
                       #(re-frame/dispatch [:step])
                       100))))

(register-handler
  :stop
  (fn [db]
    (js/clearInterval (:timer db))
    (dissoc db :timer)))
