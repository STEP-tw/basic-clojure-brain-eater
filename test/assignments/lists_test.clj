(ns assignments.lists-test
  (:require [clojure.test :refer :all]
            [assignments.lists :refer :all]))

(deftest map-test
  (testing "identity with single coll"
           (is (= [1 2 3] (map' identity [1 2 3]))))
  (testing "fn with single coll"
           (is (= [2 4 6] (map' (partial * 2) [1 2 3]))))
  (testing "fn with multiple coll"
           (is (= [3 5 7] (map' + [1 2 3] [2 3 4])))))

(deftest filter-test
  (testing "truthy pred returns whole coll"
           (is (= [1 2 3] (filter' (fn [x] true) [1 2 3]))))

  (testing "returns only values for which pred returns true"
           (is (= [1 2 3] (filter' #(< % 5) [1 2 3 5 6 10])))))

(deftest reduce-test
  (testing "returns first element if collection has only one ele"
           (is (= 1 (reduce' + [1]))))

  (testing "returns reduced result"
           (is (= 6 (reduce' + [1 2 3]))))

  (testing "with initial value"
           (is (= 12 (reduce' + 6 [1 2 3])))))


(deftest count-test
  (testing "returns of given vector"
           (is (= 1 (count' [1]))))

  (testing "returns 0 if empty sequence is given"
           (is (= 0 (count' []))))

  (testing "with count of set values"
           (is (= 4 (count' #{1 2 3 4})))))


(deftest reverse-test
  (testing "reverses vector"
           (is (= [5 4 3 2 1] (reverse' [1 2 3 4 5]))))


  (testing "reverses list"
           (is (= '(1 2 3 4) (reverse' '(4 3 2 1)))))

  (testing "returns empty seq if vector is empty"
           (is (= [] (reverse' []))))

  (testing "returns nil if sequence is not given"
           (is (nil? (reverse' 1)))))


(deftest every?'-test
  (testing "responds with if all elements pass pred"
           (is (true? (every?' #(> 5 %) [1 2 3 4]))))


  (testing "response with  false if any ele fails pred"
           (is (false? (every?' #(> 5 %) '(3 4 6 7 8)))))

  (testing "returns true for empty"
           (is (true? (every?' #(> 5 %) [])))))


(deftest some?'-test
  (testing "responds with true if all elements pass pred"
           (is (true? (some?' #(> 5 %) [7 8 3 9 10]))))


  (testing "response with  false if any ele fails pred"
           (is (false? (some?' #(> 5 %) '(7 8 9)))))

  (testing "returns false for empty"
           (is (false? (some?' #(> 5 %) [])))))

(deftest ascending?'-test
  (testing "responds with true if given seq is in ascending order"
           (is (true? (ascending? [7 8 9 10]))))


  (testing "responds with false if given seq is not in ascending order"
           (is (false? (ascending? '(7 10 8 9))))))

(deftest distinct'-test
  (testing "responds unique values"
           (is (= (frequencies [1 2 3 4]) (frequencies (distinct' [1 1 2 3 3 4]))))))

(deftest dedupe'-test
  (testing "responds consequent duplicate value removed"
           (is (= [1 2 3 4 3] (dedupe' [1 1 2 2 2 3 4 3 3 3])))))

(deftest sum-of-adjacent-numbers'-test
  (testing "should add adjacent number"
           (is
             (= [3 5 8] (sum-of-adjacent-digits [1 2 3 5])))))

(deftest max-three-digit-sequence-test
  (testing "should find maximum sum combination of 3 adjacent values"
           (is (= [3 5 8] (max-three-digit-sequence [1 2 3 5 8 1])))))

(deftest transpose-test
  (testing "transpose the matrix"
           (is (= [[1 4 7] [2 5 8] [3 6 9]] (transpose [[1 2 3] [4 5 6] [7 8 9]])))))

(deftest difference-test
  (testing "return difference of sets"
           (is (= [6 7] (difference [1 2 3 4 5] [3 4 5 6 7])))))

(deftest union-test
  (testing "return union of sets"
           (is (= '(5 6 7 1 2 3 4 5) (union [1 2 3 4 5] [3 4 5 6 7])))))

(deftest points-around-origin-test
  (testing "return points around origin"
           (is
             (= [[-1 -1] [-1 0] [-1 1] [0 -1] [0 1] [1 -1] [1 0] [1 1]] (points-around-origin)))))

(deftest cross-product-test
  (testing "return cross product until both elements are same"
           (is
             (= [[1 4] [1 3] [1 5] [2 4] [2 3] [2 5] [3 4]] (cross-product [1 2 3 6] [4 3 5])))))

(deftest double-up-test
  (testing "return collection with elements repeated twice"
           (is (= [1 1 2 2 3 3] (double-up [1 2 3])))))

(deftest third-or-fifth-test
  (testing "return values with index divisible by 3 or 5"
           (is (= [0 3 5] (third-or-fifth [0 1 2 3 4 5])))))

(deftest sqr-of-the-first-test
  (testing "returns the same size collection with sqr of first ele as all elements"
           (is (= [16 16 16] (sqr-of-the-first [4 5 6])))))

(deftest russian-dolls-test
  (testing "returns nested vectors for each element"
           (is (= [[1] [2] [3]] (russian-dolls [1 2 3] 2)))))

(deftest split-comb-test
  (testing "with even sequence"
           (is (= [1 3 2 4] (split-comb [1 2 3 4]))))
  (testing "with odd sequence"
           (is (= [1 3 2 4 5] (split-comb [1 2 3 4 5])))))

(deftest index-of-test
  (testing "with empty collection"
           (is (= -1 (index-of [] "something"))))
  (testing "with element present in the collection"
           (is (= 4 (index-of [0 1 2 5 3] 3))))
  (testing "with element not present in the collection"
           (is (= -1 (index-of [1 2 3] 0)))))

(deftest palindrome?-test
  (testing "with empty collection"
           (is (true? (palindrome? []))))
  (testing "with palindrome list"
           (is (true? (palindrome? [1 2 1]))))
  (testing "with string"
           (is (true? (palindrome? "NaN"))))
  (testing "not a palindrome"
           (is (false? (palindrome? [1 2 3])))))

(deftest validate-sudoku-grid-test
  (testing "correct grid"
           (is
             (true?
               (validate-sudoku-grid
                [[4 3 5 2 6 9 7 8 1]
                 [6 8 2 5 7 1 4 9 3]
                 [1 9 7 8 3 4 5 6 2]
                 [8 2 6 1 9 5 3 4 7]
                 [3 7 4 6 8 2 9 1 5]
                 [9 5 1 7 4 3 6 2 8]
                 [5 1 9 3 2 6 8 7 4]
                 [2 4 8 9 5 7 1 3 6]
                 [7 6 3 4 1 8 2 5 9]]))))
  (testing "incorrect grid"
           (is
             (false?
               (validate-sudoku-grid
                [[4 4 5 2 6 9 7 8 1]
                 [6 8 2 5 7 1 4 9 3]
                 [1 9 7 8 3 4 5 6 2]
                 [8 2 6 1 9 5 3 4 7]
                 [3 7 4 6 8 2 9 1 5]
                 [9 5 1 7 4 3 6 2 8]
                 [5 1 9 3 2 6 8 7 4]
                 [2 4 8 9 5 7 1 3 6]
                 [7 6 3 4 1 8 2 5 9]])))))
  

