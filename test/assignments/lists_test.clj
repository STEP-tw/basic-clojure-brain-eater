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

    )
  )
