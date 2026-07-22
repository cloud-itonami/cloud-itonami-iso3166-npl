(ns marketentry.registry
  "Pure-function market-entry filing-draft + filing-submit record
  construction -- an append-only market-entry book-of-record draft.

  Like every sibling actor's registry, there is no single international
  reference-number standard for a public-sector market-entry filing --
  every jurisdiction assigns its own format. This namespace does NOT
  invent one; it builds a jurisdiction-scoped sequence number and
  validates the record's required fields, the same honest,
  non-fabricating discipline `marketentry.facts` uses.

  `engagement-fee-matches-claim?` is an HONEST reapplication of the
  SAME ground-truth-recompute DISCIPLINE sibling actors use (verify a
  claimed monetary total against the entity's own recorded quantity x
  unit fields), reapplied to a market-entry engagement fee line.

  `investment-approval-authority` / `investment-approval-authority-
  mismatch?` are the SAME discipline applied to a genuinely Nepal-
  specific mechanism: the Foreign Investment and Technology Transfer
  Act, 2075 (FITTA) दफा १७ (Section 17)'s own foreign-investment-
  approval-authority threshold -- '(१) छ अर्ब रुपैयाँसम्मको विदेशी
  लगानीको स्वीकृति विभागले गर्नेछ। (२) छ अर्ब रुपैयाँभन्दा बढीको विदेशी
  लगानीको लगानी बोर्ड ऐन, २०६८ अन्तर्गतको लगानी बोर्डले गर्नेछ।' (own
  text, read directly, PDF fetched from doind.gov.np's own upload path):
  foreign investment up to NPR 6,000,000,000 is approved by the
  Department of Industry; foreign investment exceeding that amount is
  approved by Investment Board Nepal. Unlike `cloud-itonami-iso3166-
  lao`'s Arts.49-50 mechanism (two textual criteria -- land-area OR
  household-count -- routing between two LEGISLATIVE bodies, with a
  genuinely indeterminate gap band), Nepal's FITTA दफा १७ mechanism is a
  SINGLE clean monetary threshold routing between two EXECUTIVE bodies
  (Department of Industry vs. Investment Board Nepal): every declared
  foreign-investment amount determines an unambiguous tier, with no gap
  band -- the only genuinely indeterminate case this namespace models is
  when the engagement declares NO foreign-investment amount at all (see
  `investment-approval-authority`'s own docstring).

  This is a GENUINELY DIFFERENT check SHAPE from every prior iso3166
  sibling this repo mirrors (grep-verified absent as a governor check
  function name across the iso3166 siblings present in this workspace's
  shared `orgs/cloud-itonami/` checkout as of this session -- a good-
  faith, not exhaustive, fleet-wide check): `cloud-itonami-iso3166-ind`'s
  in-entity-missing / `cloud-itonami-iso3166-pak`'s pk-entity-missing
  checks are boolean residency gates; `cloud-itonami-iso3166-lao`'s
  legislative-approval-mismatch check routes between LEGISLATIVE bodies
  on a two-criterion textual test with an honest gap band; Nepal's
  check routes between EXECUTIVE bodies on a single numeric threshold
  with no gap band, grounded in FITTA 2075's own दफा १७ text.

  HONEST DISCLOSURE this namespace's own docstring (mirroring
  `marketentry.facts`) carries in full: FITTA's own दफा १७(२) names
  'लगानी बोर्ड ऐन, २०६८' (Investment Board Act, 2068) as the Investment
  Board's governing statute, but this session independently fetched and
  read the Public-Private Partnership and Investment Act, 2075 (Act
  No. 32 of 2075 B.S., certified the SAME date as FITTA), whose own
  दफा ७१ states the 2068 Act was REPEALED by this newer Act while the
  Board's own institutional continuity is preserved. This namespace
  models the THRESHOLD (NPR 6,000,000,000) and the TWO AUTHORITIES
  (Department of Industry / Investment Board Nepal) FITTA's own text
  states -- both of which remain true regardless of which statute is
  currently the Board's own governing instrument -- and does not take a
  position on which Act is 'correct' to cite for the Board's authority;
  see `marketentry.facts` for the full disclosure.

  This namespace is pure data + pure functions -- no I/O, no network
  call to any real government system. It builds the RECORD an operator
  would keep, not the act of submitting a portal registration itself
  (that is `marketentry.operation`'s `:filing/submit`, always
  human-gated -- see README Actuation)."
  (:require [clojure.string :as str]))

(defn- unsigned-certificate
  "Every certificate this actor produces is UNSIGNED -- signature is
  the market-entry operator's act, not this actor's."
  [kind subject record-id]
  {"@context" ["https://www.w3.org/ns/credentials/v2"]
   "type" ["VerifiableCredential" kind]
   "credentialSubject" {"id" subject "record" record-id}
   "proof" nil
   "issued_by_registry" false
   "status" "draft-unsigned"})

(defn- zero-pad [n w]
  (let [s (str n)]
    (str (apply str (repeat (max 0 (- w (count s))) "0")) s)))

(defn compute-engagement-fee
  "The ground-truth engagement fee for `engagement`'s own `:base-fee`
  and `:monitoring-months` x `:monthly-rate` -- a single flat
  base + months x rate calculation, not a full pricing engine."
  [{:keys [base-fee monthly-rate monitoring-months]}]
  (+ (double base-fee)
     (* (double monthly-rate) (double monitoring-months))))

(defn engagement-fee-matches-claim?
  "Does `engagement`'s own `:claimed-fee` equal the independently
  recomputed `compute-engagement-fee`?"
  [{:keys [claimed-fee] :as engagement}]
  (== (double claimed-fee) (compute-engagement-fee engagement)))

(def investment-board-threshold-npr
  "FITTA 2075 दफा १७: foreign investment exceeding this many NPR
  requires Investment Board Nepal approval instead of Department of
  Industry approval."
  6000000000)

(defn investment-approval-authority
  "The ground-truth investment-approval-authority TIER for `engagement`,
  independently recomputed from its own declared
  `:foreign-investment-npr`, per FITTA 2075 दफा १७. Returns
  `:department-of-industry` (at or below the threshold),
  `:investment-board-nepal` (above the threshold), or `nil` when the
  engagement declares no foreign-investment amount at all (this does
  NOT mean no approval is required in reality -- it means this recompute
  has no ground-truth figure to classify, and
  `investment-approval-authority-mismatch?` treats that as 'insufficient
  information to flag a mismatch', never as a violation by default --
  unlike `cloud-itonami-iso3166-lao`'s gap BAND, Nepal's single-threshold
  test has no indeterminate band once a figure IS declared: it always
  resolves to exactly one of the two tiers)."
  [{:keys [foreign-investment-npr]}]
  (when (some? foreign-investment-npr)
    (if (> foreign-investment-npr investment-board-threshold-npr)
      :investment-board-nepal
      :department-of-industry)))

(defn investment-approval-authority-mismatch?
  "Does `engagement` declare a `:claimed-investment-approval-authority`
  that does NOT match the independently recomputed
  `investment-approval-authority`? Entity/engagement-scope-gated the
  same discipline `cloud-itonami-iso3166-lao`'s
  `:claimed-legislative-approval`-gated check uses: an engagement with
  no claimed authority at all is never flagged, and a recompute that
  returns `nil` (no declared foreign-investment amount) is never
  treated as a mismatch either -- this check only fires when it can
  positively demonstrate the claim is wrong, never merely because it
  lacks enough information to confirm the claim is right."
  [{:keys [claimed-investment-approval-authority] :as engagement}]
  (boolean (and claimed-investment-approval-authority
                (let [recomputed (investment-approval-authority engagement)]
                  (and (some? recomputed)
                       (not= claimed-investment-approval-authority recomputed))))))

(defn register-draft
  "Validate + construct the FILING-DRAFT registration DRAFT -- the
  market-entry operator's own act of preparing a portal registration
  package. Pure function -- does not touch any real government system."
  [engagement-id jurisdiction sequence]
  (when-not (and engagement-id (not= engagement-id ""))
    (throw (ex-info "draft: engagement_id required" {})))
  (when-not (and jurisdiction (not= jurisdiction ""))
    (throw (ex-info "draft: jurisdiction required" {})))
  (when (< sequence 0)
    (throw (ex-info "draft: sequence must be >= 0" {})))
  (let [draft-number (str (str/upper-case jurisdiction) "-DFT-" (zero-pad sequence 6))
        record {"record_id" draft-number
                "kind" "filing-draft"
                "engagement_id" engagement-id
                "jurisdiction" jurisdiction
                "immutable" true}]
    {"record" record "draft_number" draft-number
     "certificate" (unsigned-certificate "FilingDraft" draft-number draft-number)}))

(defn register-submit
  "Validate + construct the FILING-SUBMIT registration DRAFT -- the
  market-entry operator's own act of actually submitting the
  registration/filing (always human-gated upstream)."
  [engagement-id jurisdiction sequence]
  (when-not (and engagement-id (not= engagement-id ""))
    (throw (ex-info "submit: engagement_id required" {})))
  (when-not (and jurisdiction (not= jurisdiction ""))
    (throw (ex-info "submit: jurisdiction required" {})))
  (when (< sequence 0)
    (throw (ex-info "submit: sequence must be >= 0" {})))
  (let [submit-number (str (str/upper-case jurisdiction) "-SUB-" (zero-pad sequence 6))
        record {"record_id" submit-number
                "kind" "filing-submit"
                "engagement_id" engagement-id
                "jurisdiction" jurisdiction
                "immutable" true}]
    {"record" record "submit_number" submit-number
     "certificate" (unsigned-certificate "FilingSubmit" submit-number submit-number)}))

(defn append [history result]
  (conj (vec history) (get result "record")))
