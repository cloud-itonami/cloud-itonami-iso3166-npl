(ns statute.facts-test
  (:require [clojure.string :as str]
            [clojure.test :refer [deftest is]]
            [statute.facts :as facts]))

(deftest npl-has-spec-basis
  (let [sb (facts/spec-basis "NPL")]
    (is (= 5 (count sb)))
    (is (every? #(str/starts-with? (:statute/url %) "https://") sb))
    (is (every? :statute/law-number sb))))

(deftest unknown-jurisdiction-has-no-spec-basis
  (is (nil? (facts/spec-basis "ATL")))
  (is (nil? (facts/spec-basis "ZZZ"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["NPL" "JPN" "ATL"])]
    (is (= 3 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["ATL" "JPN"] (:missing-jurisdictions c)))))

(deftest by-topic-filters
  (is (= #{"npl.fitta-2075" "npl.ppp-and-investment-act-2075"}
         (set (mapv :statute/id (facts/by-topic "NPL" :foreign-investment)))))
  (is (= #{"npl.companies-act-2063"}
         (set (mapv :statute/id (facts/by-topic "NPL" :corporate-governance)))))
  (is (= #{"npl.public-procurement-act-2063"}
         (set (mapv :statute/id (facts/by-topic "NPL" :public-procurement)))))
  (is (= #{"npl.labour-act-2074"}
         (set (mapv :statute/id (facts/by-topic "NPL" :labor)))))
  (is (empty? (facts/by-topic "ATL" :foreign-investment))))

(deftest npl-income-tax-act-is-honestly-absent
  (is (empty? (facts/by-topic "NPL" :tax))
      "this iteration could not independently confirm the Income Tax Act's own law-number/date -- deliberately not claimed, see namespace docstring"))
