(ns van-challenge.ttt)

(defn print-banner []
  (println "Tic Tac Toe"))

(defn make-board []
  [nil nil nil nil nil nil nil nil nil])

(defn set-square [board i value]
  (assoc board (- i 1) value))

(defn get-square [board i]
  (get board (- i 1)))

(def sides [:top :right :bottom :left])

(defn choose-side []
  (rand-nth sides))

(defn fill-empty-side [board]
  (set-square board (rand-nth [2 4 6 8]) "X"))

(defn fill-empty-corner [board]
  (set-square board (rand-nth [1 3 7 9]) "X"))

(defn fill-centre-square [board]
  (set-square board 5 "X"))

(defn has-filled-corner [board]
  (some #(= "O" %) (map #(get-square board %) '(1 3 7 9))))

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

(defn game-loop [board player]
  (println board)
  (if (= player "O")
    (let [user-input (read-line)
          i (parse-int user-input)]
      (game-loop (fill-user-input board i) "X"))
    (game-loop (fill-empty-side board) "O")))

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
