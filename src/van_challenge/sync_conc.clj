(ns van-challenge.sync-conc
  (:require [clojure.string]
            [clojure.java.io :refer [reader]]
            [clojure.core.async :refer [chan >!! <!! >! <! go]]))

(defn count-file-lines
  [file]
  (-> file
      reader
      line-seq
      count))

(defn sync-conc
  [file]
  (let [line-chan (chan)]
    (do
      (go (doseq [line (line-seq (reader file))]
            >! line-chan line))
      (go (while true (println <! line-chan))))))

(defn main
  [args]
  (assert (= (count args) 1) "Must provide one argument <file to read>")
  (let [in-file (first args)]
    (sync-conc in-file)))
        ;; line-count (count-file-lines in-file)]
    ;; (printf "Line count is: %s%n" line-count)))
;; (sync-conc "README.md")

;; read lines
;; send each line to 