(ns van-challenge.core

  (:gen-class))

(defn init-answer

  "Returns a number between given limits"

  [min-val max-val]

  (rand-nth (range min-val (+ max-val 1))))

(defn print-banner

  "Prints the game intro banner"

  []

  (println "<><><><><><><><><><><><><><><><><><><><>")

  (println "Welcome to the Van Number game! The aim of the name is to guess the number.")

  (println "...calculating incredibly sneaky number... done!")

  (println "<><><><><><><><><><><><><><><><><><><><>")

  (println))

(defn parse-int [number-string]

  (try (Integer/parseInt number-string)

       (catch Exception e nil)))

(defn game-loop

  "The main game loop"

  [answer]

  (println "Please enter a number to take a guess!")

  (def user-input (read-line))

  (def user-guess (parse-int user-input))

  (cond

    (= user-guess nil) (game-loop answer)

    (> user-guess answer) ((println "Too high!")

                           (game-loop answer))

    (< user-guess answer) ((println "Too low!")

                           (game-loop answer))

    (= user-guess answer) (printf "You did it! The correct answer is: %s%n" answer)))

(defn -main

  [& args]

  (print-banner)

  (def answer (init-answer 1 100))

  (printf "The answer is %s%n" answer)

  (game-loop answer)

  0)