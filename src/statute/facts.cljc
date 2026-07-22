(ns statute.facts
  "General-law compliance catalog for Nepal (NPL) -- extends this repo's
  existing `marketentry.facts` (public-sector market-entry/foreign-
  investment only, narrow scope) with a second, orthogonal catalog of
  national statutes an operator generally must track for compliance.
  Mirrors cloud-itonami-iso3166-jpn/-ind/-pak/-lao/-btn/-caf/-cub's
  `statute.facts` (ADR-2607141700, cloud-itonami-compliance-fact-
  federation).

  Every entry cites an OFFICIAL government-hosted URL -- never
  fabricated. All entries below were fetched directly (curl/pdftotext-
  verified, 2026-07-23) from official Nepali government sites
  (`ppmo.gov.np`, `ocr.gov.np` via Wayback Machine, `doind.gov.np`,
  `ibn.gov.np`, `moless.gov.np`).

  - **Public Procurement Act, 2063**: this repo's `marketentry.facts`
    already uses this Act as its market-entry spec-basis; it is ALSO
    catalogued here as a general national-law reference, since an
    operator tracks it both as a market-entry gate and as an ongoing
    public-procurement compliance statute. Act No. 36 of the year 2063
    B.S., own text fetched (PDF embedded on ppmo.gov.np's own page,
    legacy-font-encoded but decoded -- see `marketentry.facts`
    docstring).
  - **Companies Act, 2063**: OCR's own acts listing (Wayback Machine
    snapshot, live `ocr.gov.np` unreachable this session -- disclosed
    fallback) titles this 'कम्पनी ऐन २०६३ (दोश्रो संशोधन सहित)'
    (Companies Act 2063, with Second Amendment). Act No. 18 of the year
    2063 B.S., authenticated 2063-07-24 B.S., own text fetched directly
    (PDF header states 'www.lawcommission.gov.np', i.e. this is the Law
    Commission's own authoritative text, mirrored via a government CDN).
  - **Foreign Investment and Technology Transfer Act, 2075 (FITTA)**:
    this repo's `marketentry.facts` flagship-check basis, also
    catalogued here as an ongoing compliance statute (foreign-investor
    rights/obligations, repatriation, and the investment-approval-
    authority mechanism all live inside this one law). Act No. 34 of
    the year 2075 B.S., authenticated 2075-12-13 B.S., own text fetched
    directly from `doind.gov.np`'s own upload path (legacy-font-encoded,
    decoded).
  - **Public-Private Partnership and Investment Act, 2075**: this
    iteration specifically investigated, rather than assumed by
    analogy, what Investment Board Nepal's OWN current governing
    statute is. IBN's own 'कानुन' (Laws) listing page
    (`ibn.gov.np/category/laws/`, fetched directly) names only this
    Act (and its 2077 regulations), NOT the 'Investment Board Act,
    2068' that FITTA's own दफा १७(२) cross-references by name. This
    iteration downloaded this Act's own PDF directly from IBN's own
    site (header again 'www.lawcommission.gov.np') and read it with
    `pdftotext`: its own दफा ७१ ('खारेजी तथा बचाउ' -- Repeal and
    Savings, own text, read directly) states '(१) ... लगानी बोर्ड ऐन,
    २०६८ खारेज गरिएका छन्।' ('the Investment Board Act, 2068 [is]
    hereby REPEALED'), with subsection (3) confirming the Board's own
    continuity is preserved under this Act. Act No. 32 of the year
    2075 B.S., certified the SAME date as FITTA (2075-12-13 B.S.) --
    see `marketentry.facts` / `marketentry.registry` for the full
    disclosure of this inconsistency, which this catalog states rather
    than silently resolving. This catalog does NOT carry a separate
    'Investment Board Act, 2068' entry, since that Act is (per the
    2075 Act's own text) repealed, and this iteration never
    independently fetched/read its own primary text in the first
    place -- only a cross-reference to its name via FITTA.
  - **Labour Act, 2074**: the Ministry of Labour, Employment and Social
    Security's own acts listing (`moless.gov.np`, fetched directly)
    titles an entry 'श्रम ऐन, २०७४' (Labour Act, 2074). Act No. 14 of
    the year 2074 B.S., authenticated/published 2074-05-19 B.S., own
    text fetched directly (PDF header again 'www.lawcommission.gov.np').
  - **HONEST GAP -- Income Tax Act**: the Inland Revenue Department's
    own homepage (`ird.gov.np`, fetched directly) names an 'आयकर ऐन'
    (Income Tax Act) generically as an administered law, but this
    iteration could NOT independently confirm this Act's own law-number
    or enactment date this session (no linked primary text located
    within this session's fetch budget) -- this catalog therefore does
    NOT carry an Income Tax Act entry, an honestly-reported gap rather
    than a fabricated citation, the same discipline
    `cloud-itonami-iso3166-lao` used for its own jurisdiction's Labour
    Law gap.

  A law not in this table has NO spec-basis, full stop; extend
  `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of statute entries. `:statute/url` + `:statute/law-number`
  are the citation the governor requires before any compliance-fact
  proposal referencing this law can commit."
  {"NPL"
   [{:statute/id "npl.public-procurement-act-2063"
     :statute/title "Public Procurement Act, 2063"
     :statute/jurisdiction "NPL"
     :statute/kind :law
     :statute/law-number "Act No. 36 of the year 2063 B.S. (commonly cited as ~2007 AD); own text decoded from a legacy-font PDF fetched from a government CDN, embedded on ppmo.gov.np's own official page. HONEST NOTE: the Act's own authentication day/month glyphs were decoded with lower confidence than the Act number/year (legacy-font substitution, not independently cross-checked) -- only the year (2063 B.S.) and Act No. (36) are asserted with high confidence"
     :statute/url "https://giwmscdnone.gov.np/media/pdf_upload/1.%20Procurement%20Act%2C%202063_ysjesb0.pdf"
     :statute/url-provenance :official-ppmo-gov-np-embedded-pdf
     :statute/enacted-date "2063 B.S. (exact day/month not independently converted to Gregorian this session)"
     :statute/retrieved-at "2026-07-23"
     :statute/topic #{:public-procurement}}
    {:statute/id "npl.companies-act-2063"
     :statute/title "Companies Act, 2063 (with Second Amendment)"
     :statute/jurisdiction "NPL"
     :statute/kind :law
     :statute/law-number "Act No. 18 of the year 2063 B.S., authenticated 2063-07-24 B.S.; own text fetched directly, PDF header states 'www.lawcommission.gov.np'"
     :statute/url "https://ocr.gov.np/content/49/companies-act-2063--as-amended-/"
     :statute/url-provenance :official-ocr-gov-np-via-wayback-machine
     :statute/enacted-date "2063-07-24 B.S. (authenticated date read directly from own PDF header; not independently converted to Gregorian this session)"
     :statute/retrieved-at "2026-07-23"
     :statute/topic #{:corporate-governance :incorporation}}
    {:statute/id "npl.fitta-2075"
     :statute/title "Foreign Investment and Technology Transfer Act, 2075"
     :statute/jurisdiction "NPL"
     :statute/kind :law
     :statute/law-number "Act No. 34 of the year 2075 B.S., authenticated 2075-12-13 B.S. (commonly cited as ~2019 AD); own text fetched directly from doind.gov.np's own upload path, legacy-font-encoded (decoded)"
     :statute/url "https://doind.gov.np/uploads/notices/Notices-20210423231458200.pdf"
     :statute/url-provenance :official-doind-gov-np
     :statute/enacted-date "2075-12-13 B.S. (authenticated date read directly, decoded from legacy-font PDF; not independently converted to Gregorian this session)"
     :statute/retrieved-at "2026-07-23"
     :statute/topic #{:foreign-investment}}
    {:statute/id "npl.ppp-and-investment-act-2075"
     :statute/title "Public-Private Partnership and Investment Act, 2075"
     :statute/jurisdiction "NPL"
     :statute/kind :law
     :statute/law-number "Act No. 32 of the year 2075 B.S., certified 2075-12-13 B.S. (same date as FITTA); own text fetched directly, PDF header states 'www.lawcommission.gov.np'; own दफा ७१ repeals the Investment Board Act, 2068 while preserving Investment Board Nepal's institutional continuity -- see namespace docstring for the full disclosure"
     :statute/url "https://ibn.gov.np/content/5/public-private-partnership-and-investment-act-2075/"
     :statute/url-provenance :official-ibn-gov-np
     :statute/enacted-date "2075-12-13 B.S. (authenticated date read directly from own PDF header; not independently converted to Gregorian this session)"
     :statute/retrieved-at "2026-07-23"
     :statute/topic #{:foreign-investment :infrastructure}}
    {:statute/id "npl.labour-act-2074"
     :statute/title "Labour Act, 2074"
     :statute/jurisdiction "NPL"
     :statute/kind :law
     :statute/law-number "Act No. 14 of the year 2074 B.S., authenticated/published 2074-05-19 B.S. (commonly cited as ~2017 AD); own text fetched directly from moless.gov.np's own linked PDF, PDF header states 'www.lawcommission.gov.np'"
     :statute/url "https://moless.gov.np/content/11121/11121-%E0%A4%B6%E0%A4%B0%E0%A4%AE-%E0%A4%90%E0%A4%A8-%E0%A5%A8%E0%A5%A6%E0%A5%AD%E0%A5%AA/"
     :statute/url-provenance :official-moless-gov-np
     :statute/enacted-date "2074-05-19 B.S. (authenticated date read directly from own PDF header; not independently converted to Gregorian this session)"
     :statute/retrieved-at "2026-07-23"
     :statute/topic #{:labor}}]})

(defn spec-basis
  "The jurisdiction's statute vector, or nil -- nil means NO spec-basis
  for that jurisdiction yet."
  [iso3]
  (get catalog iso3))

(defn coverage
  "Honest coverage report, same shape/discipline as `marketentry.facts/coverage`:
  never report a missing jurisdiction as covered."
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-npl statute.facts Wave 0 (ADR-2607141700): "
                 (count (get catalog "NPL")) " NPL statutes seeded with an "
                 "official citation. Extend `statute.facts/catalog`, never "
                 "fabricate a law-id or URL.")})))

(defn by-topic
  "Statutes for `iso3` tagged with `topic` (e.g. :labor, :foreign-investment)."
  [iso3 topic]
  (filterv #(contains? (:statute/topic %) topic) (spec-basis iso3)))
