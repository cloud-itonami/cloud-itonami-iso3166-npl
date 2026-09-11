(ns marketentry.governor
  "Market-Entry Compliance Governor -- the independent compliance layer
  that earns the MarketEntry-LLM the right to commit. The LLM has no
  notion of Nepali public-procurement/company/investment law, whether
  the engagement's own declared foreign-investment amount actually
  determines Department-of-Industry-vs-Investment-Board-Nepal approval
  authority under FITTA 2075 दफा १७, whether a claimed engagement fee
  actually equals base + months x rate, whether an OCR company-
  registration certificate or a PAN (tax-ID) record has been verified
  for a filing that requires it, or when a draft stops being a draft and
  becomes a real-world portal registration / filing submission, so this
  MUST be a separate system able to *reject* a proposal and fall back to
  HOLD.

  `:itonami.blueprint/governor` is `:market-entry-compliance-governor`
  (shared family keyword on blueprints).

  This blueprint's own text (docs/business-model.md Trust Controls:
  'any actual portal registration or filing submission requires
  Market-Entry Compliance Governor clearance and always escalates to
  human sign-off'; 'a false or fabricated regulatory-requirement claim
  is a HARD hold') names exactly the checks below.

  Eight checks, in priority order, ALL HARD violations: a human
  approver CANNOT override them. The confidence/actuation gate is
  SOFT: it asks a human to look (low confidence / actuation), and the
  human may approve -- but see `marketentry.phase`: for `:stake
  :actuation/draft-filing`/`:actuation/submit-filing` NO phase ever
  allows auto-commit either. Two independent layers agree that
  actuation is always a human call.

    1. Spec-basis                  -- did the jurisdiction proposal cite
                                       an OFFICIAL source
                                       (`marketentry.facts`), or invent
                                       one?
    2. Evidence incomplete         -- for `:filing/draft`/
                                       `:filing/submit`, has the
                                       jurisdiction actually been
                                       assessed with a full evidence
                                       checklist on file?
    3. Investment-approval-        -- for `:filing/submit`, when the
       authority mismatch             engagement declares a
                                      `:claimed-investment-approval-
                                       authority`, INDEPENDENTLY
                                       recompute whether the
                                       engagement's own declared
                                       `:foreign-investment-npr` figure
                                       actually falls under Department-
                                       of-Industry or Investment-Board-
                                       Nepal jurisdiction per FITTA
                                       2075 दफा १७'s own NPR
                                       6,000,000,000 threshold, and
                                       HARD-hold if not. FLAGSHIP
                                       genuinely new check for the
                                       iso3166 family (grep-verified
                                       absent as a governor check
                                       function name across the
                                       iso3166 siblings present in this
                                       workspace) -- a single clean
                                       monetary-value-threshold
                                       AUTHORITY-ROUTING classification
                                       between two EXECUTIVE bodies, a
                                       check SHAPE genuinely different
                                       from `cloud-itonami-iso3166-lao`'s
                                       two-criterion LEGISLATIVE-body
                                       routing (which has an honest gap
                                       band; Nepal's single threshold
                                       does not).
    4. Engagement fee mismatch     -- for `:filing/submit`,
                                       INDEPENDENTLY recompute whether
                                       the engagement's own `:claimed-
                                       fee` equals `base-fee +
                                       monthly-rate x monitoring-
                                       months` -- honest reapplication
                                       of the ground-truth-recompute
                                       discipline sibling actors use.
    5. OCR company-registration    -- for `:filing/submit`, when the
       unverified                     engagement declares
                                       `:requires-ocr-registration?
                                       true`, INDEPENDENTLY check
                                       `:ocr-registration-verified?`.
                                       CONDITIONAL on the engagement's
                                       own ground truth. Grounded in the
                                       Office of the Company Registrar
                                       (OCR)'s CAMIS company-
                                       registration regime (Companies
                                       Act, 2063).
    6. PAN (tax-ID) unverified     -- for `:filing/submit`, when the
                                       engagement declares
                                       `:requires-pan? true`,
                                       INDEPENDENTLY check
                                       `:pan-verified?`. CONDITIONAL on
                                       the engagement's own ground
                                       truth. Grounded in the Inland
                                       Revenue Department (IRD)'s PAN
                                       regime -- kept as a SEPARATE
                                       check from OCR company
                                       registration because Nepal's
                                       company-registration and tax-ID
                                       regimes are two DIFFERENT
                                       agencies, unlike e.g.
                                       `cloud-itonami-iso3166-lao`'s
                                       bundled enterprise-registration-
                                       plus-TIN certificate.
    7. Confidence floor / actuation
       gate                          -- LLM confidence below threshold,
                                       OR the op is `:filing/draft`/
                                       `:filing/submit` (REAL acts)
                                       -> escalate.

  Two more guards, double-draft/double-submit prevention, are enforced
  off dedicated `:drafted?`/`:submitted?` facts (never a `:status`
  value) -- bringing the total HARD check count to eight (five named
  conditional/structural checks above, plus these two)."
  (:require [marketentry.facts :as facts]
            [marketentry.registry :as registry]
            [marketentry.store :as store]))

(def confidence-floor 0.6)

(def high-stakes
  "Stakes grave enough to always require a human, even when clean.
  Drafting a real portal registration/filing package and submitting a
  real filing are the two real-world actuation events this actor
  performs."
  #{:actuation/draft-filing :actuation/submit-filing})

;; ----------------------------- checks -----------------------------

(defn- spec-basis-violations
  "A `:jurisdiction/assess` (or `:filing/draft`/`:filing/submit`)
  proposal with no spec-basis citation is a HARD violation -- never
  invent a jurisdiction's market-entry requirements."
  [{:keys [op]} proposal]
  (when (contains? #{:jurisdiction/assess :filing/draft :filing/submit} op)
    (let [value (:value proposal)]
      (when (or (empty? (:cites proposal))
                (and (contains? value :spec-basis) (nil? (:spec-basis value))))
        [{:rule :no-spec-basis
          :detail "公式spec-basisの引用が無い提案は法域要件として扱えない"}]))))

(defn- evidence-incomplete-violations
  "For `:filing/draft`/`:filing/submit`, the jurisdiction's required
  registration evidence must actually be satisfied."
  [{:keys [op subject]} st]
  (when (contains? #{:filing/draft :filing/submit} op)
    (let [e (store/engagement st subject)
          assessment (store/assessment-of st subject)]
      (when-not (and assessment
                     (facts/required-evidence-satisfied?
                      (:jurisdiction e) (:checklist assessment)))
        [{:rule :evidence-incomplete
          :detail "法域の必要書類(OCR会社登記/PAN/Bolpatra入札者登録/産業登録/投資承認等)が充足していない状態での提案"}]))))

(defn- investment-approval-authority-mismatch-violations
  "For `:filing/submit`, INDEPENDENTLY recompute whether the
  engagement's own declared foreign-investment amount actually falls
  under the approval authority the engagement CLAIMS -- the flagship
  check this vertical adds. HARD-hold when the engagement declares a
  `:claimed-investment-approval-authority` that does not match the
  independently recomputed authority (FITTA 2075 दफा १७, NPR
  6,000,000,000 threshold)."
  [{:keys [op subject]} st]
  (when (= op :filing/submit)
    (let [e (store/engagement st subject)]
      (when (registry/investment-approval-authority-mismatch? e)
        [{:rule :investment-approval-authority-mismatch
          :detail (str subject " は承認機関(" (:claimed-investment-approval-authority e) ")を申告しているが、"
                      "独立再計算(外国投資技術移転法(2075)第17条のNPR 60億しきい値判定)による正当な承認機関("
                      (registry/investment-approval-authority e) ")と一致しない")}]))))

(defn- engagement-fee-mismatch-violations
  "For `:filing/submit`, INDEPENDENTLY recompute whether the
  engagement's own claimed fee equals base + months x rate."
  [{:keys [op subject]} st]
  (when (= op :filing/submit)
    (let [e (store/engagement st subject)]
      (when-not (registry/engagement-fee-matches-claim? e)
        [{:rule :engagement-fee-mismatch
          :detail (str subject " の申告手数料(" (:claimed-fee e)
                      ")が独立再計算値(" (registry/compute-engagement-fee e) ")と一致しない")}]))))

(defn- ocr-registration-unverified-violations
  "For `:filing/submit`, when the engagement declares
  `:requires-ocr-registration? true`, INDEPENDENTLY check
  `:ocr-registration-verified?` -- CONDITIONAL on the engagement's own
  ground truth. Grounded in the Office of the Company Registrar (OCR)
  CAMIS company-registration regime (Companies Act, 2063)."
  [{:keys [op subject]} st]
  (when (= op :filing/submit)
    (let [e (store/engagement st subject)]
      (when (and (true? (:requires-ocr-registration? e))
                 (not (true? (:ocr-registration-verified? e))))
        [{:rule :ocr-registration-unverified
          :detail (str subject " はOCR会社登記証明書(CAMIS)の確認を要するが未確認 -- 提出提案は進められない")}]))))

(defn- pan-unverified-violations
  "For `:filing/submit`, when the engagement declares `:requires-pan?
  true`, INDEPENDENTLY check `:pan-verified?` -- CONDITIONAL on the
  engagement's own ground truth. Grounded in the Inland Revenue
  Department (IRD) PAN regime, kept SEPARATE from OCR company
  registration because these are two different Nepali agencies."
  [{:keys [op subject]} st]
  (when (= op :filing/submit)
    (let [e (store/engagement st subject)]
      (when (and (true? (:requires-pan? e))
                 (not (true? (:pan-verified? e))))
        [{:rule :pan-unverified
          :detail (str subject " はPAN(税務識別番号)確認を要するが未確認 -- 提出提案は進められない")}]))))

(defn- already-drafted-violations
  "For `:filing/draft`, refuses to draft the SAME engagement twice."
  [{:keys [op subject]} st]
  (when (= op :filing/draft)
    (when (store/engagement-already-drafted? st subject)
      [{:rule :already-drafted
        :detail (str subject " は既にドラフト済み")}])))

(defn- already-submitted-violations
  "For `:filing/submit`, refuses to submit the SAME engagement twice."
  [{:keys [op subject]} st]
  (when (= op :filing/submit)
    (when (store/engagement-already-submitted? st subject)
      [{:rule :already-submitted
        :detail (str subject " は既に提出済み")}])))

(defn check
  "Censors a MarketEntry-LLM proposal against the governor rules.
  Returns {:ok? bool :violations [..] :confidence c :escalate? bool
  :high-stakes? bool :hard? bool}."
  [request _context proposal st]
  (let [hard (into []
                   (concat (spec-basis-violations request proposal)
                           (evidence-incomplete-violations request st)
                           (investment-approval-authority-mismatch-violations request st)
                           (engagement-fee-mismatch-violations request st)
                           (ocr-registration-unverified-violations request st)
                           (pan-unverified-violations request st)
                           (already-drafted-violations request st)
                           (already-submitted-violations request st)))
        conf (:confidence proposal 0.0)
        low? (< conf confidence-floor)
        stakes? (boolean (high-stakes (:stake proposal)))
        hard? (boolean (seq hard))]
    {:ok?          (and (not hard?) (not low?) (not stakes?))
     :violations   hard
     :confidence   conf
     :hard?        hard?
     :escalate?    (and (not hard?) (or low? stakes?))
     :high-stakes? stakes?}))

(defn hold-fact
  "The audit fact written when a proposal is rejected (HOLD)."
  [request context verdict]
  {:t          :governor-hold
   :op         (:op request)
   :actor      (:actor-id context)
   :subject    (:subject request)
   :disposition :hold
   :basis      (mapv :rule (:violations verdict))
   :violations (:violations verdict)
   :confidence (:confidence verdict)})
