(ns marketentry.registry-test
  (:require [clojure.test :refer [deftest is testing]]
            [marketentry.registry :as registry]))

(deftest engagement-fee-recompute
  (let [e {:base-fee 500000 :monthly-rate 30000 :monitoring-months 12 :claimed-fee 860000.0}]
    (is (== 860000.0 (registry/compute-engagement-fee e)))
    (is (true? (registry/engagement-fee-matches-claim? e))))
  (let [bad {:base-fee 500000 :monthly-rate 30000 :monitoring-months 12 :claimed-fee 999000.0}]
    (is (false? (registry/engagement-fee-matches-claim? bad)))))

(deftest register-draft-and-submit
  (let [d (registry/register-draft "eng-1" "NPL" 0)
        s (registry/register-submit "eng-1" "NPL" 0)]
    (is (= "NPL-DFT-000000" (get d "draft_number")))
    (is (= "NPL-SUB-000000" (get s "submit_number")))
    (is (nil? (get-in d ["certificate" "proof"])))
    (is (= "draft-unsigned" (get-in s ["certificate" "status"])))))

(deftest register-requires-ids
  (is (thrown? Exception (registry/register-draft "" "NPL" 0)))
  (is (thrown? Exception (registry/register-submit "eng-1" "" 0))))

(deftest investment-approval-authority-department-of-industry-tier
  (testing "foreign investment at or below NPR 6,000,000,000 routes to the Department of Industry (FITTA 2075 दफा १७(१))"
    (is (= :department-of-industry
           (registry/investment-approval-authority {:foreign-investment-npr 6000000000})))
    (is (= :department-of-industry
           (registry/investment-approval-authority {:foreign-investment-npr 1})))
    (is (= :department-of-industry
           (registry/investment-approval-authority {:foreign-investment-npr 2000000000})))))

(deftest investment-approval-authority-investment-board-tier
  (testing "foreign investment exceeding NPR 6,000,000,000 routes to Investment Board Nepal (FITTA 2075 दफा १७(२))"
    (is (= :investment-board-nepal
           (registry/investment-approval-authority {:foreign-investment-npr 6000000001})))
    (is (= :investment-board-nepal
           (registry/investment-approval-authority {:foreign-investment-npr 8000000000})))))

(deftest investment-approval-authority-no-declared-amount-is-indeterminate
  (testing "no declared foreign-investment amount -> nil, never guessed"
    (is (nil? (registry/investment-approval-authority {})))
    (is (nil? (registry/investment-approval-authority {:foreign-investment-npr nil})))))

(deftest investment-approval-authority-mismatch-is-entity-scope-gated
  (testing "an engagement with no claimed authority at all is never flagged"
    (is (false? (registry/investment-approval-authority-mismatch? {:foreign-investment-npr 8000000000}))))
  (testing "a claimed authority that does NOT match the independently recomputed tier -> mismatch"
    (is (true? (registry/investment-approval-authority-mismatch?
                {:foreign-investment-npr 8000000000
                 :claimed-investment-approval-authority :department-of-industry}))))
  (testing "a claimed authority that DOES match -> not flagged"
    (is (false? (registry/investment-approval-authority-mismatch?
                 {:foreign-investment-npr 8000000000
                  :claimed-investment-approval-authority :investment-board-nepal})))
    (is (false? (registry/investment-approval-authority-mismatch?
                 {:foreign-investment-npr 2000000000
                  :claimed-investment-approval-authority :department-of-industry}))))
  (testing "an indeterminate recompute (no declared amount) is never treated as a mismatch, even if a claim is present"
    (is (false? (registry/investment-approval-authority-mismatch?
                 {:claimed-investment-approval-authority :investment-board-nepal})))))
