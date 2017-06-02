(ns gallows-game.core
  (:gen-class))

(def total-lifes 6)

(defn lose [] (print "Game over! :("))
(defn win [] (print "Wow! Congratulations! You win! :)"))

(defn missing-letters [word hits]
  (remove (fn [letter] (contains? hits (str letter))) word))

(defn hit-whole-word? [word hits]
  (empty? (missing-letters word hits)))

(defn input-letter! []
  (do
    (println "Enter some letter: ")
    (read-line)))

(defn has-shot-in-word? [shot word] (.contains word shot))

(defn game [lifes word hits]
  (cond
    (= lifes 0) (lose)
    (hit-whole-word? word hits) (win)
  :else
    (let [shot (input-letter!)]
    (if (has-shot-in-word? shot word)
      (do
        (println "Yeah! You hit!")
        (recur lifes word (conj hits shot)))
      (do
        (println "Bad choice! You lost one life!")
        (recur (dec lifes) word hits))))))


(game total-lifes "matrix" #{})
