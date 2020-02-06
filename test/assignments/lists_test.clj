(ns assignments.lists-test
  (:require [clojure.test :refer :all]
[assignments.lists :refer :all]))

(deftest lists
  (testing "map"
    (testing "identity with single coll"
      (is (= [1 2 3] (map' identity [1 2 3])))
      )
    (testing "fn with single coll"
      (is (= [2 4 6] (map' (partial * 2) [1 2 3])))
      )
    (testing "fn with multiple coll"
      (is (= [3 5 7] (map' + [1 2 3] [2 3 4])))
      )
    )

  (testing "filter"
    (testing "truthy pred returns whole coll"
      (is (= [1 2 3] (filter' (fn [x] true) [1 2 3])))
      )

    (testing "returns only values for which pred returns true"
      (is (= [1 2 3] (filter' #(< % 5) [1 2 3 5 6 10])))
      )
    )

  (testing "reduce"
    (testing "returns first element if collection has only one ele"
      (is (= 1 (reduce' + [1])))
      )

    (testing "returns reduced result"
      (is (= 6 (reduce' + [1 2 3])))
      )

    (testing "with initial value"
      (is (= 12 (reduce' + 6 [1 2 3])))
      )
    )


  (testing "count"
    (testing "returns of given vector"
      (is (= 1 (count' [1])))
      )

    (testing "returns 0 if empty sequence is given"
      (is (= 0 (count' [])))
      )

    (testing "with count of set values"
      (is (= 4 (count' #{ 1 2 3 4 })))
      )
    )


  (testing "reverse"
    (testing "reverses vector"
      (is (= [5 4 3 2 1] (reverse' [1 2 3 4 5])))
      )


    (testing "reverses list"
      (is (= '(1 2 3 4) (reverse' '(4 3 2 1))))
      )

    (testing "returns empty seq if vector is empty"
      (is (= [] (reverse' [])))
      )

    (testing "returns nil if sequence is not given"
      (is (nil? (reverse' 1)))
      )
    )


  (testing "every?'"
    (testing "responds with if all elements pass pred"
      (is (true? (every?' #(> 5 %) [1 2 3 4])))
      )


    (testing "response with  false if any ele fails pred"
      (is (false? (every?' #(> 5 %) '(3 4 6 7 8))))
      )

    (testing "returns true for empty"
      (is (true? (every?' #(> 5 %) [])))
      )
    )


  (testing "some?'"
    (testing "responds with true if all elements pass pred"
      (is (true? (some?' #(> 5 %) [7 8 3 9 10])))
      )


    (testing "response with  false if any ele fails pred"
      (is (false? (some?' #(> 5 %) '(7 8 9))))
      )

    (testing "returns false for empty"
      (is (false? (some?' #(> 5 %) [])))
      )
    )

  (testing "ascending?'"
    (testing "responds with true if given seq is in ascending order"
      (is (true? (ascending? [7 8 9 10])))
      )


    (testing "responds with false if given seq is not in ascending order"
      (is (false? (ascending? '(7 10 8 9))))
      )
    )

  (testing "distinct'"
    (testing "responds unique values"
      (is (= (frequencies [1 2 3 4]) (frequencies (distinct' [1 1 2 3 3 4]))))
      )
    )

  (testing "dedupe'"
    (testing "responds consequent duplicate value removed"
      (is (= [1 2 3 4 3] (dedupe' [1 1 2 2 2 3 4 3 3 3])))
      )
    )

  (testing "sum-of-adjacent-numbers'"
    (testing "should add adjacent number"
      (is (= [3 5 8] (sum-of-adjacent-digits [1 2 3 5])
             )
          )
      )
    )

  (testing "max-three-digit-sequence"
    (testing "should find maximum sum combination of 3 adjacent values"
      (is (= [3 5 8] (max-three-digit-sequence [1 2 3 5 8 1])))))

  (testing "transpose"
    (testing "transpose the matrix"
      (is (= [[1 4 7] [2 5 8] [3 6 9]] (transpose  [[1 2 3] [4 5 6] [7 8 9]])))))

  (testing "difference"
    (testing "return difference of sets"
      (is (= [6 7] (difference [1 2 3 4 5] [3 4 5 6 7])))))

  (testing "union"
    (testing "return union of sets"
      (is (= '(5 6 7 1 2 3 4 5) (union [1 2 3 4 5] [3 4 5 6 7])))))

  (testing "points-around-origin"
    (testing "return points around origin"
      (is (= [[-1 -1] [-1 0] [-1 1] [0 -1] [0 1] [1 -1] [1 0] [1 1]] (points-around-origin)))))

  (testing "cross-product"
    (testing "return cross product until both elements are same"
      (is (= [[1 4] [1 3] [1 5] [2 4] [2 3] [2 5] [3 4]] (cross-product [1 2 3 6 ] [4 3 5])))))

  (testing "double-up"
    (testing "return collection with elements repeated twice"
      (is (= [1 1 2 2 3 3] (double-up [1 2 3])))))

  (testing "third-or-fifth"
    (testing "return values with index divisible by 3 or 5"
      (is (= [0 3 5] (third-or-fifth [0 1 2 3 4 5])))))
  )

