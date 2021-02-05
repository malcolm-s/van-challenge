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

(def corners [1 3 7 9])
(def sides [2 4 6 8])

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

(defn has-filled-corner [board]
  (some #(= "O" (get-square board %)) corners))

(defn fill-opposite-corner [board]
  (set-square board (cond
                      (= "O" (get-square board 1)) 9
                      (= "O" (get-square board 3)) 7
                      (= "O" (get-square board 7)) 3
                      (= "O" (get-square board 9)) 1) "X"))

(defn fill-user-input [board i]
  (set-square board i "O"))

(defn parse-int [number-string]
  (try (Integer/parseInt number-string)
       (catch Exception e nil)))

(def winning-positions [[1 2 3] [4 5 6] [7 8 9] [1 5 9] [3 5 7] [1 4 7] [2 5 8] [3 6 9]])

(defn is-winner [board player]
  (letfn [(is-won [pos] (every? #(= player (get-square board %)) pos))]
    (some #(is-won %) winning-positions)))

(defn game-loop [board player]
  (println board)
  (cond
    (is-winner board "O") (println "O is the winner!")
    (is-winner board "X") (println "X is the winner!")
    (= player "O") (let [user-input (read-line)
                         i (parse-int user-input)]
                     (game-loop (fill-user-input board i) "X"))
    :else (game-loop  (cond (has-empty-corner board) (fill-empty-corner board)
                            :else (fill-empty-side board)
                            "O"))))

  ;; (def user-input (read-line))
  ;; (def user-guess (parse-int user-input))
  ;; (if
  ;;  (= user-guess answer) (printf "You did it! The correct answer is: %s%n" answer)
  ;;  (do (cond
  ;;        (= user-guess nil) (println "Please enter a number to take a guess!")
  ;;        (> user-guess answer) (println "Too high!")
  ;;        (< user-guess answer) (println "Too low!"))
  ;;      (game-loop answer))))


(defn main
  []
  (print-banner)
  (def board (make-board))
  (game-loop board "O"))
