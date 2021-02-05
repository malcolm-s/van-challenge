(ns van-challenge.ttt)

(defn print-banner []
  (println "Tic Tac Toe"))

(defn make-board []
  [nil nil nil nil nil nil nil nil nil])

(defn get-square [board i]
  (get board (- i 1)))

(defn set-square [board i value]
  (if (some? (get-square board i))
    (do
      (println (format "trying to set %s but it has value %s" i (get-square board i)))
      board)
    (assoc board (- i 1) value)))

(defn get-display-val [board i]
  (let [value (get-square board i)]
    (if (nil? value) "." value)))

(defn print-board [board]
  (printf "%s|%s|%s%n" (get-display-val board 1) (get-display-val board 2) (get-display-val board 3))
  (println "-----")
  (printf "%s|%s|%s%n" (get-display-val board 4) (get-display-val board 5) (get-display-val board 6))
  (println "-----")
  (printf "%s|%s|%s" (get-display-val board 7) (get-display-val board 8) (get-display-val board 9))
  (println))

(def corners [1 3 7 9])
(def sides [2 4 6 8])

(defn opposite-corner [corner]
  (cond
    (= 1 corner) 9
    (= 3 corner) 7
    (= 7 corner) 3
    (= 9 corner) 1))

(defn fill-empty-side [board]
  (let [empty-sides (filter #(nil? (get-square board %)) sides)]
    (set-square board (rand-nth empty-sides) "X")))

(defn fill-empty-corner [board]
  (let [empty-corners (filter #(nil? (get-square board %)) corners)]
    (set-square board (rand-nth empty-corners) "X")))

(defn fill-centre-square [board]
  (set-square board 5 "X"))

(defn has-empty-corner [board]
  (some #(nil? (get-square board %)) corners))

(defn available-opposite-corner [board]
  (let [filled-corners (filter #(= "O" (get-square board %)) corners)
        opposite-filled-corners (map #(opposite-corner %) filled-corners)
        empty-opposite-filled-corners (filter #(nil? (get-square board %)) opposite-filled-corners)]
    (first empty-opposite-filled-corners)))

(defn has-filled-corner [board]
  (not= nil (available-opposite-corner board)))

(defn fill-opposite-corner [board]
  (let [to-fill (available-opposite-corner board)]
    (if (nil? to-fill) board (set-square board to-fill "X"))))

(defn fill-user-input [board i]
  (set-square board i "O"))

(defn parse-int [number-string]
  (try (Integer/parseInt number-string)
       (catch Exception e nil)))

(def winning-positions [[1 2 3] [4 5 6] [7 8 9] [1 5 9] [3 5 7] [1 4 7] [2 5 8] [3 6 9]])

(defn is-winner [board player]
  (letfn [(is-won [pos] (every? #(= player (get-square board %)) pos))]
    (some #(is-won %) winning-positions)))

(defn centre-empty? [board]
  (nil? (get-square board 5)))

(defn fill-centre [board]
  (set-square board 5 "X"))

(defn game-loop [board player]
  (println (format "> %s's turn" player))
  (print-board board)
  (println ">>>>>>")
  (cond
    (is-winner board "O") (println "O is the winner!")
    (is-winner board "X") (println "X is the winner!")
    (= player "O") (let [user-input (read-line)
                         i (parse-int user-input)]
                     (game-loop (fill-user-input board i) "X"))
    :else (game-loop  (cond
                        (centre-empty? board) (fill-centre board)
                        (has-filled-corner board) (fill-opposite-corner board)
                        (has-empty-corner board) (fill-empty-corner board)
                        :else (fill-empty-side board))
                      "O")))

(defn main
  []
  (print-banner)
  (def board (make-board))
  (game-loop board "O"))
