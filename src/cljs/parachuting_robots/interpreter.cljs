(ns parachuting-robots.interpreter)

(defn find-label
  [label program]
  (some (fn [[i expr]]
          (when (= label
                   (second (get expr 2)))
            i))
        (map vector (range) program)))

(defn robot-step
  [{:keys [inst pos p] :as r} {p2 :p} program]
  (let [[_ instruction _] (nth program inst)]
    (merge r
           (condp = (second instruction)
             "left" {:pos (dec pos) :inst (inc inst)}
             "right" {:pos (inc pos) :inst (inc inst)}
             "skipNext" {:pos pos :inst (if (or (= pos p) (= pos p2))
                                          (+ 2 inst)
                                          (+ 1 inst))}
             "goto" {:pos pos :inst (find-label (nth instruction 2)
                                                program)}))))

(defn step
  [db]
  (let [program (:program db)
        r1 (:r1 db)
        r2 (:r2 db)]
    (if (= (:pos r1) (:pos r2))
      (assoc db :collision true)
      (-> db
          (assoc :r1 (robot-step r1 r2 program)
                 :r2 (robot-step r2 r1 program))))))
