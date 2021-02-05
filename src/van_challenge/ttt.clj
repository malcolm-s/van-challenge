(ns van-challenge.ttt)

(defn print-banner []
  (println "Tic Tac Toe"))

(defn make-board []
  [nil nil nil nil nil nil nil nil nil])

(defn set-square [board i value]
  (assoc board (- i 1) value))

(def sides [:top :right :bottom :left])

(defn choose-side []
  (rand-nth sides))

(defn fill-middle-square [side board player]
  (case side
    :top (set-square board 2 player)
    :left (set-square board 4 player)
    :right (set-square board 6 player)
    :bottom (set-square board 8 player)))

(defn fill-empty-side [board player]
  (fill-middle-square (choose-side) board player))

(defn main
  []
  (print-banner)
  (println (let [board (make-board)]
             (fill-empty-side board "X"))))