(ns van-challenge.core
  (:require [van-challenge.number-game :as number-game]
            [van-challenge.draw-line :as draw-line])
  (:gen-class))

(defn -main
  [& args]
  (draw-line/main))
  ; (number-game/main))