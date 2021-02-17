(ns van-challenge.sync-conc
  (:require [clojure.string]
            [clojure.java.io :refer [reader]]
            [clojure.core.async :refer [close! chan >!! <!! >! <! go put! take! go-loop]]))

(defn count-file-lines
  [file]
  (-> file
      reader
      line-seq
      count))

(defn sync-conc
  [file]
  (let [line-chan (chan)]
    ;; (doseq [line (line-seq (reader file))]
    ;;   (printf "from reader>%s%n" line)
    ;;   (put! line-chan line))
    ;; (take! line-chan println)))
    (go (doseq [line (line-seq (reader file))]
          (printf "from reader>%s%n")
          (close!)
          (>! line-chan line)))
    (go (while true (printf "from printer>%s%n" (<! line-chan))))))

;; (<!! (sync-conc "README.md"))

(defn main
  [args]
  (assert (= (count args) 1) "Must provide one argument <file to read>")

  (let [in-file (first args)
        line-chan (chan)
        done-chan (chan)]
    (go
      (doseq [line (line-seq (reader in-file))]
        (>! line-chan line))
      (close! line-chan)
      (printf "reader:%s%n" (<! done-chan)))
    (<!! (go-loop []
           (if-let [line (<! line-chan)]
             (do
               (printf "printer:%s%n" line)
               (recur))
             (>! done-chan "printing finished"))))))

    ;; line-count (count-file-lines in-file)]
    ;; (printf "Line count is: %s%n" line-count)))


;; read lines
;; send each line to 