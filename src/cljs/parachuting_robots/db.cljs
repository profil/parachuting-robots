(ns parachuting-robots.db)

(defn init-db
  []
  (let [p1 (+ 32 (rand-int 16))
        p2 (+ (- p1 8) (rand-int 16))]
    {:r1 {:pos p1 :p p1 :inst 0}
     :r2 {:pos p2 :p p2 :inst 0}
     :input (str "# Useless example program\n"
                 "start: left\n"
                 "       goto start")}))
