(ns parachuting-robots.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as re-frame :refer [register-sub]]))

(register-sub
 :r1
 (fn [db]
   (reaction (:r1 @db))))

(register-sub
 :r2
 (fn [db]
   (reaction (:r2 @db))))

(register-sub
 :input
 (fn [db]
   (reaction (:input @db))))

(register-sub
  :program
  (fn [db]
    (reaction (:program @db))))

(register-sub
  :timer
  (fn [db]
    (reaction (:timer @db))))
