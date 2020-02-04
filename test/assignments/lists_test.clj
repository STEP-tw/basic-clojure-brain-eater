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

  )
