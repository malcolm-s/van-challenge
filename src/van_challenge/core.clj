(ns van-challenge.core
  (:require [van-challenge.number-game :as number-game]
            [van-challenge.draw-line :as draw-line]
            [van-challenge.ttt :as ttt]
            [van-challenge.sync-conc :as sc]
            [van-challenge.csv-data :as cd])
  (:gen-class))

(defn -main
  [& args]
  (cd/main args))
  ; (ttt/main))
  ; (draw-line/main))
  ; (number-game/main))