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
    )
  )
  