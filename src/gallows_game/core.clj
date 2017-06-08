(ns gallows-game.core
  (:gen-class))

(def total-lifes 6)

(defn lose [] (println) (println "Game over! :("))
(defn win [] (println) (println "Wow! Congratulations! You win! :)"))

(defn missing-letters [word hits]
  (remove (fn [letter] (contains? hits (str letter))) word))

(defn hit-whole-word? [word hits]
  (empty? (missing-letters word hits)))

(defn input-letter! []
  (do
    (println) (println "Enter some letter: ")
    (read-line)))

(defn has-shot-in-word? [shot word] (.contains word shot))

(defn print-game [lifes word hits]
  (println) (println "Lifes: " lifes)
  (doseq [letter (seq word)]
    (if (contains? hits (str letter))
      (print letter " ") (print "_ ")))
  (println))

(defn game [lifes word hits]
  (print-game lifes word hits)
  (cond
    (= lifes 0) (lose)
    (hit-whole-word? word hits) (win)
  :else
    (let [shot (input-letter!)]
    (if (has-shot-in-word? shot word)
      (do
        (println) (println "### Yeah! You hit! ###") (println)
        (recur lifes word (conj hits shot)))
      (do
        (println) (println "### Bad choice! You lost one life! ####") (println)
        (recur (dec lifes) word hits))))))

(game total-lifes "matrix" #{})
