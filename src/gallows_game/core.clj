(ns gallows-game.core
  (:gen-class))

(def total-lifes 6)

(defn lose [] (print "You lose!"))
(defn win [] (print "You win!"))

(defn missing-letters [word hits]
  (remove (fn [letter] (contains? hits (str letter))) word))

(defn hit-whole-word? [word hits]
  (empty? (missing-letters word hits)))

(defn read-letter! [] (read-line))

(defn has-shot-in-word? [shot word] (.contains word shot))

(defn validate-shot [shot lifes word hits]
  (if (has-shot-in-word? shot word)
    (game lifes word (conj hits shot))
    (game (dec lifes) word hits)
  )
)

(defn game [lifes word hits]
  (if (= lifes 0)
    (lose)
    (if (hit-whole-word? word hits)
      (win)
      (validate-shot (read-letter!) lifes word hits)
    )
  )
)

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
