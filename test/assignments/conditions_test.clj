(ns assignments.conditions-test
  (:require [clojure.test :refer :all]
            [assignments.conditions :refer :all]))

(deftest safe-division
  (testing "non zero denominator"
    (is (= 2 (safe-divide 4 2))))
  (testing "zero denominator"
    (is (nil? (safe-divide 3 0)))))


(deftest informative-division
  (testing "non zero denominator"
    (is (= 2 (informative-divide 4 2))))
  (testing "zero denominator"
    (is (= :infinite (informative-divide 3 0)))))


(deftest harishchandra-test
  (testing "non zero number"
    (is (= 2 (harishchandra 2))))
  (testing "zero"
    (is (= 0 (harishchandra 0))))
  (testing nil
    (is (nil? (harishchandra nil))))
  (testing false
    (is (nil? (harishchandra false))))
  )

(deftest yudishtira-test
  (testing "non zero number"
    (is (= 2 (yudishtira 2))))
  (testing "zero"
    (is (= 0 (yudishtira 0))))
  (testing nil
    (is (= :ashwatham (yudishtira nil))))
  (testing false
    (is (= :ashwatham (yudishtira false))))
  )

(deftest duplicate-first-test
  (testing "single element vector"
    (is (= [2 2] (duplicate-first [2]))))
  (testing "multiple element vector"
    (is (= [1 1 2 3] (duplicate-first [1 2 3]))))
  (testing "not vector"
    (is (nil? (duplicate-first []))))
  )


(deftest five-point-someone-test
  (testing "y 5"
    (is (= :chetan-bhagat (five-point-someone 3 5))))
  (testing "x 5"
    (is (= :satan-bhagat (five-point-someone 5 3))))
  (testing "x > y"
    (is (= :greece (five-point-someone 6 3))))
  (testing "x < y"
    (is (= :universe (five-point-someone 1 3))))
  (testing "x = y"
    (is (= :universe (five-point-someone 1 1))))
  )

(deftest conditions-apply-test
  (testing "[1 3]"
    (is (= :wonder-woman (conditions-apply [1 3]))))
  (testing "[:a :b :c]"
    (is (= :durga (conditions-apply [:a :b :c]))))
  (testing "[:a :c :b :d :c]"
    (is (= :durga (conditions-apply [:a :b :c]))))
  (testing "[:a :c :b :d :c]"
    (is (= :durga (conditions-apply [:a :b :c]))))
  (testing "[[2 3] [4 5]]"
    (is (= :cleopatra (conditions-apply [[2 3] [4 5]]))))
  (testing "[1]"
    (is (= :tuntun (conditions-apply [1]))))
  )

(deftest repeat-and-truncate-test
  (testing "(range 4) false true 6)"
    (is (= [0 1 2 3] (repeat-and-truncate (range 4) false true 6) ))
    )
  (testing "(range 4) true false nil)"
    (is (= [0 1 2 3 0 1 2 3] (repeat-and-truncate (range 4) true false nil) ))
    )
  (testing "(range 4) false false nil)"
    (is (= [0 1 2 3] (repeat-and-truncate (range 4) false false nil) ))
    )
  (testing "(range 4) true true 6)"
    (is (= [0 1 2 3 0 1 ] (repeat-and-truncate (range 4) true true 6) ))
    )
  )

(deftest order-in-words-test
  (testing "[4 3 2]"
    (is (= [:x-greater-than-y :y-greater-than-z] (order-in-words 4 3 2) )))
  (testing "[4 3 5]"
    (is (= [:x-greater-than-y :z-greater-than-x] (order-in-words 4 3 5) )))
  (testing "[2 3 4]"
    (is (= [:z-greater-than-x] (order-in-words 2 3 4) )))
  (testing "[1 1 1]"
    (is (= [] (order-in-words 1 1 1) )))
  )

(deftest zero-aliases-test
  (testing "0"
    (is (= :zero (zero-aliases 0) )))
  (testing "\"\""
    (is (= :empty-string (zero-aliases "") )))
  (testing "[]"
    (is (= :empty (zero-aliases []) )))
  (testing "()"
    (is (= :empty (zero-aliases ()) )))
  (testing "#{}"
    (is (= :empty-set (zero-aliases #{}) )))
  (testing "{}"
    (is (= :empty-map (zero-aliases {}) )))
  (testing "non zero"
    (is (= :not-zero (zero-aliases 5) )))
  )

(deftest zero-separated-palindrome-test
  (testing "[1 2 3]"
    (is (= [4 3 2 0 2 3 4] (zero-separated-palindrome [1 2 3]) )))
  )