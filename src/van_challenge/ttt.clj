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

(defn fill-empty-side [board player]
  (set-square board (rand-nth [2 4 6 8]) player))

(defn fill-empty-corner [board player]
  (set-square board (rand-nth [1 3 7 9]) player))

(defn fill-centre-square [board player]
  (set-square board 5 player))

(defn main
  []
  (print-banner)
  (println (let [board (make-board)]
             (fill-centre-square board "X"))))