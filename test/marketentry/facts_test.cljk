(ns marketentry.facts-test
  (:require [clojure.test :refer [deftest is testing]]
            [marketentry.facts :as facts]))

(deftest npl-has-spec-basis
  (let [sb (facts/spec-basis "NPL")]
    (is (some? sb))
    (is (string? (:provenance sb)))
    (is (seq (:required-evidence sb)))
    (is (some? (facts/corporate-number-spec-basis "NPL")))
    (is (some? (facts/tax-id-spec-basis "NPL")))
    (is (some? (facts/investment-approval-authority-spec-basis "NPL")))))

(deftest npl-rep-spec-basis-is-honestly-absent
  (testing "no verifiable Nepal-specific representative provision (distinct from OCR company registration) was located -- deliberately not claimed"
    (is (nil? (facts/rep-spec-basis "NPL")))))

(deftest unknown-jurisdiction-has-no-spec-basis
  (is (nil? (facts/spec-basis "ATL")))
  (is (nil? (facts/spec-basis "ZZZ"))))

(deftest required-evidence-satisfied
  (let [sb (facts/spec-basis "NPL")
        all (:required-evidence sb)]
    (is (true? (facts/required-evidence-satisfied? "NPL" all)))
    (is (not (facts/required-evidence-satisfied? "NPL" (take 1 all))))
    (is (nil? (facts/required-evidence-satisfied? "ATL" all)))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["NPL" "USA" "ATL"])]
    (is (= 3 (:requested c)))
    (is (= 2 (:covered c)))
    (is (= ["ATL"] (:missing-jurisdictions c)))))

(deftest investment-approval-authority-spec-basis-criteria
  (let [aa (facts/investment-approval-authority-spec-basis "NPL")]
    (is (= 6000000000 (get-in aa [:investment-approval-authority-criteria :investment-board-threshold-npr])))))

(deftest tax-id-spec-basis-is-distinct-from-corporate-number
  (testing "Nepal's company registration (OCR) and tax-ID (PAN/IRD) regimes are two different agencies, modeled as two separate accessors"
    (let [corp (facts/corporate-number-spec-basis "NPL")
          tax (facts/tax-id-spec-basis "NPL")]
      (is (not= (:corporate-number-owner-authority corp) (:tax-id-owner-authority tax))))))
