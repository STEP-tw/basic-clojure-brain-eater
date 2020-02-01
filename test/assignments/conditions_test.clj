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
  (testing "[3 2 1 5 3]"
    (is (= :wonder-woman (conditions-apply [3 2 1 5 3]))))
  (testing "[3 2 1 5 3]"
    (is (= :wonder-woman (conditions-apply [3 2 1 5 3]))))
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
  (testing "[3 1]"
    (is (= :tuntun (conditions-apply [3 1]))))
  )