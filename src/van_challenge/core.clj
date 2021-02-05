(ns van-challenge.core
  (:require [van-challenge.number-game :as number-game]
            [van-challenge.draw-line :as draw-line]
            [van-challenge.ttt :as ttt])
  (:gen-class))

(defn -main
  [& args]
  (ttt/main))
  ; (draw-line/main))
  ; (number-game/main))