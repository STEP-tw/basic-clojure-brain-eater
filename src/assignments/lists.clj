(ns assignments.lists)

(defn first' [& cols]
  (loop [cols cols res []]
    (if (empty? cols)
      res
      (let [res (conj res (ffirst cols))]
        (recur (rest cols)
               res)
        )
      )
    )
  )


(defn drop' [num & cols]
  (loop [cols cols res []]
    (if (empty? cols)
      res
      (let [res (conj res (drop num (first cols)))]
        (recur (rest cols)
               res)
        )
      )
    )
  )

(defn map'
  "Implement a non-lazy version of map that accepts a
  mapper function and several collections. The output
  should be consistent with clojure.core/map"
  {:level        :medium
   :use          '[loop recur]
   :dont-use     '[map]
   :implemented? true}
  [f & colls] (loop
                [f f
                 colls colls
                 result []]
                (if (some empty? colls)
                  result
                  (let
                    [result (conj result (apply f (apply first' colls)))]
                    (recur f (apply (partial drop' 1) colls) result)
                    )
                  )
                )
  )

(defn filter'
  "Implement a non-lazy version of filter that accepts a
  predicate function and a collection. The output
  should be consistent with clojure.core/filter"
  {:level        :easy
   :use          '[loop recur]
   :dont-use     '[filter]
   :implemented? true}
  [pred coll] (loop [pred pred
                     coll coll
                     res []]
                (if (empty? coll)
                  res
                  (let [res (if (pred (first coll))
                              (conj res (first coll))
                              res)]
                    (recur pred (rest coll) res)
                    )

                  )))

(defn reduce'
  "Implement your own multi-arity version of reduce
  that accepts a predicate function and a collection.
  The output should be consistent with clojure.core/reduce"
  {:level        :medium
   :use          '[loop recur]
   :dont-use     '[reduce]
   :implemented? true}
  ([f coll] (loop [f f
                   res (first coll)
                   coll (rest coll)
                   ]
              (if (empty? coll) res
                                (let [res (f res (first coll))
                                      coll (rest coll)]
                                  (recur f res coll)))
              ))
  ([f init coll] (reduce' f (cons init coll))))

(defn count'
  "Implement your own version of count that counts the
  number of elements in a given sequence"
  {:level        :easy
   :use          '[loop recur]
   :dont-use     '[count]
   :implemented? true}
  ([coll] (loop [coll coll
                 count 0]
            (if (empty? coll)
              count
              (recur (rest coll) (inc count)))
            ))
  )

(defn reverse'
  "Implement your own version of reverse that reverses a coll.
  Returns nil if coll provided is not a sequence"
  {:level        :easy
   :use          '[reduce conj seqable? when]
   :dont-use     '[reverse]
   :implemented? true}
  ([coll]
   (when (seqable? coll) (reduce conj '() coll)))
  )

(defn every?'
  "Implement your own version of every? that checks if every
  element of a coll satisfies the given predicate"
  {:level        :easy
   :use          '[loop recur and]
   :dont-use     '[every?]
   :implemented? true}
  ([pred coll] (loop [pred pred
                      coll coll
                      res true]
                 (if (empty? coll)
                   res
                   (recur
                     pred
                     (rest coll)
                     (and res (pred (first coll)))
                     )
                   )
                 )
   )
  )

(defn some?'
  "Implement your own version of some that checks if at least one
  element of a coll satisfies the given predicate. Always return
  a boolean. The original clojure.core/some returns a nil when
  no element satisfies the given predicate"
  {:level        :easy
   :use          '[loop recur or]
   :dont-use     '[some]
   :implemented? true}
  ([pred coll] (loop [pred pred
                      coll coll]
                 (if (empty? coll)
                   false
                   (if (pred (first coll))
                     true
                     (recur pred (rest coll))
                     )
                   )
                 )
   )
  )

(defn ascending?
  "Verify if every element is greater than or equal to its predecessor"
  {:level        :easy
   :use          '[partition every? partial apply <=]
   :dont-use     '[loop recur]
   :implemented? true}
  [coll] (every? #(apply <= %) (partition 2 1 coll)))

(defn distinct'
  "Implement your own lazy sequence version of distinct which returns
  a collection with duplicates eliminated. Might have to implement another
  function, or use a letfn"
  {:level        :medium
   :use          '[lazy-seq set conj let :optionally letfn]
   :dont-use     '[loop recur distinct]
   :implemented? true}
  [coll] (lazy-seq (set coll)))

(defn dedupe'
  "Implement your own lazy sequence version of dedupe which returns
  a collection with consecutive duplicates eliminated (like the uniq command).
  Might have to implement another function, or use a letfn"
  {:level        :medium
   :use          '[lazy-seq conj let :optionally letfn]
   :dont-use     '[loop recur dedupe]
   :implemented? true}
  [coll]
  (if (empty? (rest coll))
    [(first coll)]
    (lazy-seq
      (if (= (first coll) (second coll))
        (dedupe' (rest coll))
        (conj (dedupe' (rest coll)) (first coll))
        ))
    )
  )

(defn sum-of-adjacent-digits
  "Given a collection, returns a map of the sum of adjacent digits.
  [a b c] => [a+b b+c]"
  {:level        :medium
   :use          '[map + rest]
   :dont-use     '[loop recur partition]
   :implemented? true}
  [coll] (map + coll (rest coll)))

(defn max-three-digit-sequence
  "Given a collection of numbers, find a three digit sequence that
  yields the max sum. If the collection has fewer than 3 elements,
  returns the collection itself.
  [1 2 -1 2 0] => [2 -1 2]"
  {:level        :medium
   :use          '[map next nnext max-key partial apply + if ->>]
   :dont-use     '[loop recur partition]
   :implemented? true}
  [coll] (->> (nnext coll)
              (map
                (partial conj [])
                coll (next coll))
              (apply max-key #(apply + %))
       )
  )

;; transpose is a def. Not a defn.
(def
  ^{:level        :easy
    :dont-use     '[loop recur for nth get]
    :implemented? true}
  transpose
  "Transposes a given matrix.
  [[a b] [c d]] => [[a c] [b d]].
  Note this is a def. Not a defn.
  Return a vector of vectors, not list of vectors or vectors of lists"
  (partial apply map vector))

(defn difference
  "Given two collections, returns only the elements that are present
  in the second coll but not the first"
  {:level        :easy
   :use          '[remove set]
   :dont-use     '[loop recur if]
   :implemented? true}
  [coll1 coll2] (remove #(contains? (set coll1) %) coll2))

(defn union
  "Given two collections, returns a new collection with elements from the second
  collection added to the first collection if they are missing in the first
  collection to begin with. Return a list, not a set. It also doesn't matter
  if elements repeat."
  {:level        :easy
   :use          '[remove into set ->>]
   :implemented? false}
  [coll1 coll2] (->> (set coll2)
                     (remove (partial contains? coll1))
                     (into (apply list coll1))
                     ))


(defn union
  "Given two collections, returns a new collection with elements from the second
  collection added to the first collection if they are missing in the first
  collection to begin with. Return a list, not a set. It also doesn't matter
  if elements repeat."
  {:level        :easy
   :use          '[remove into set ->>]
   :implemented? true}
  [coll1 coll2] (->> (set coll2)
                     (remove (partial contains? coll1))
                     (into (apply list coll1))
                     ))

;; points-around-origin is a def not a defn
(def
  ^{:level        :easy
    :use          '[for]
    :dont-use     '[hardcoded-values map filter]
    :implemented? true}
  points-around-origin
  "Calculate all the points around the origin
  [-1 -1] [0 -1] [1 -1] etc. There should be 8 points
  Note this is a def, not a defn"
  #(for [x (range -1 2)
         y (range -1 2)
         :when (not= [0 0] [x y])]
    [x y]))

(defn cross-product
  "Given two sequences, generate every combination in the sequence
  until two elements are equal
  [1 2 3] [4 3 5] =>
  [[1 4] [1 3] [1 5] [2 4] [2 3] [2 5] [3 4]]"
  {:level        :easy
   :use          '[for]
   :implemented? true}
  [seq1 seq2]
  (take-while (partial apply not=)
              (for [x seq1 y seq2]
                [x y]))
  )

(defn double-up
  "Given a collection, return a new collection that contains
  each element repeated twice"
  {:level        :easy
   :use          '[mapcat partial repeat :optionally vector]
   :implemented? true}
  [coll] (mapcat (partial repeat 2) coll))

(defn divisible? [a b] (= 0 (mod a b)))

(defn third-or-fifth
  "Given a collection return a new collection that contains
  elements whose index is either divisible by three or five"
  {:level        :easy
   :use          '[keep-indexed when :optionally map-indexed filter]
   :implemented? true}
  [coll]
  (keep-indexed
    #(when
       (or (divisible? %1 3) (divisible? %1 5))
       %2)
    coll)
  )

(defn sqr-of-the-first
  "Given a collection, return a new collection that contains the
  same number of elements as the given collection all of which
  are the square of the first element
  [4 5 6] => [16 16 16]"
  {:level        :easy
   :use          '[map constantly let]
   :implemented? true}
  [coll]
  (map
    (constantly (* (first coll) (first coll)))
    coll
    )
  )

(defn russian-dolls
  "Given a collection and a number, wrap each element in a nested vector
  with a nesting factor of the number provided.
  [1 2 3] 3 => [[[1]] [[2]] [[3]]]"
  {:level        :medium
   :use          '[iterate mapv partial vector drop first ->>]
   :dont-use     '[for loop recur reduce]
   :implemented? true    }
  [coll nesting-factor]
  (mapv #(->> %
              (iterate vector)
              (drop (- nesting-factor 1))
              (first)) coll))

(defn split-comb
  "Given a collection, return a new sequence where the first
  half of the sequence is interleaved with the second half.
  If the given collection has an odd number of elements, then
  preserve the last element of the original collection
  [1 2 3 4 5] => [1 3 2 4 5]"
  {:level        :easy
   :use          '[interleave split-at if rem concat take-last]
   :dont-use     '[loop recur map-indexed take drop]
   :implemented? true}
  [coll]
  (let [interleaved-coll
          (apply
           interleave
           (split-at (quot (count coll) 2) coll))]
    (if (zero? (rem (count coll) 2))
      interleaved-coll
      (concat interleaved-coll (take-last 1 coll)))))

(defn always-zero [x] 0)

(defn muted-thirds
  "Given a sequence of numbers, make every third element
  0 while preserving the other elements
  [1 2 8 4 15 2 7] => [1 2 0 4 15 0 7]"
  {:level        :easy
   :use          '[map cycle]
   :dont-use     '[loop recur map-indexed take take-nth]
   :implemented? true}
  [coll]
  (map
   #(%2 %1)
   coll
   (cycle [identity identity always-zero])))

(defn palindrome?
  "Implement a recursive palindrome check of any given sequence"
  {:level        :easy
   :use          '[empty? loop recur butlast rest]
   :dont-use     '[reverse]
   :implemented? true}
  [coll]
  (loop [coll coll]
    (if (empty? coll)
      true
      (if (= (first coll) (last coll))
        (recur (-> coll
                   rest
                   butlast))
        false))))

(defn index-of
  "index-of takes a sequence and an element and finds the index
  of the element in the given sequence. Returns -1 if element
  is not found"
  {:level        :easy
   :use          '[loop recur rest]
   :dont-use     '[.indexOf memfn]
   :implemented? true}
  [coll n]
  (loop [coll coll
         n n
         index 0]
    (if (empty? coll)
      -1
      (if (= (first coll) n)
        index
        (recur (rest coll) n (inc index))))))

(defn valid-sudoko-row
  [row]
  (= #{1 2 3 4 5 6 7 8 9} (into #{} row)))

(defn get-3*3 [grid]
  (->> grid
   (map (partial partition 3))
   (transpose)
   (flatten)
   (partition 9)))

(defn validate-sudoku-grid
  "Given a 9 by 9 sudoku grid, validate it."
  {:level        :hard
   :implemented? false}
  [grid]
  (every?
   valid-sudoko-row
   (concat grid (transpose grid) (get-3*3 grid))))