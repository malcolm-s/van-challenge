(ns van-challenge.csv-data
  (:require [clojure.data.csv :refer [read-csv]]))


(defn load-csv
  "Slurps a file and returns it as csv"
  [file]
  (-> file
      slurp
      read-csv))

(defn parse-float
  [n]
  (try (Float/parseFloat n)
       (catch NumberFormatException e nil)))

(defn make-weather-record
  [row]
  {:min (parse-float (get row 9))
   :max (parse-float (get row 11))
   :date (get row 4)})

(defn is-valid-weather-record
  [{min :min max :max}]
  (and (some? min) (some? max)))

(defn temp-diff
  [{min :min max :max}]
  (Math/abs (- max min)))

(defn weather-diff
  []
  (->>  (load-csv "resources/week6/data/weather.csv")
        (drop 1) ; skip header row
        (map make-weather-record)
        (filter is-valid-weather-record)
        (reduce
         (fn [totals row]
           (if (and
                (nil? (:biggest-diff-day totals))
                (nil? (:smallest-diff-day totals)))
             {:biggest-diff-day row
              :smallest-diff-day row}
             (let [diff (temp-diff row)
                   biggest-diff (temp-diff (:biggest-diff-day totals))
                   smallest-diff (temp-diff (:smallest-diff-day totals))]
               (cond
                 (< biggest-diff diff) (assoc totals :biggest-diff-day row)
                 (> smallest-diff diff) (assoc totals :smallest-diff-day row)
                 :else totals))))
         {})))

(defn main
  [& args]
  (let [weather (weather-diff)]
    (println ">>> Weather stats >>>")
    (printf "Biggest day difference was on %s with difference of %sC%n"
            (get-in weather [:biggest-diff-day :date])
            (temp-diff (:biggest-diff-day weather)))
    (printf "Smallest day difference was on %s with difference of %sC%n"
            (get-in weather [:smallest-diff-day :date])
            (temp-diff (:smallest-diff-day weather)))))
