(ns van-challenge.draw-line)

(defn fill-line
  "Fills a line into a buffer"
  [buffer point-a point-b])

(def point-a {:x 0 :y 0})
(def point-b {:x 3 :y 3})

(def buf [[0 0 1]
          [0 1 0]
          [1 0 0]])

(defn main
  []
  (doseq [x buf]
    (printf "%s%n" (clojure.string/join "" (map #(if (= 0 %) " " "X") x)))))