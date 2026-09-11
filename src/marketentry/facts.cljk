(ns marketentry.facts
  "Per-jurisdiction public-sector market-entry regulatory catalog -- the
  G2-style spec-basis table the Market-Entry Compliance Governor checks
  every `:jurisdiction/assess` proposal against ('did the advisor cite an
  OFFICIAL public source for this jurisdiction's requirements, or did it
  invent one?').

  Every claim below cites a source this iteration actually fetched (curl
  / WebFetch) and read (pdftotext -layout / raw HTML) on 2026-07-23,
  against Nepal's own official government sites:

  - **Public Procurement Act, 2063** -- Nepal's Public Procurement
    Monitoring Office (PPMO, `ppmo.gov.np`, fetched directly) is
    administratively under the Office of the Prime Minister and Council
    of Ministers (own page text, WebFetch-read: 'सार्वजनिक खरिद अनुगमन
    कार्यालय' [Public Procurement Monitoring Office] ... 'प्रधानमन्त्री
    तथा मन्त्रिपरिषद्को कार्यालय' [Office of the Prime Minister and
    Council of Ministers]). PPMO's own page names 'सार्वजनिक खरिद ऐन,
    २०६३' (Public Procurement Act, 2063) and links its own copy of the
    Act, labelled on-page 'Public Procurement Act 2063 (Nepali)', which
    this iteration downloaded directly
    (`https://giwmscdnone.gov.np/media/pdf_upload/1.%20Procurement%20Act%2C%202063_ysjesb0.pdf`,
    embedded on PPMO's own official page) and converted with
    `pdftotext -layout`. HONEST NOTE ON ENCODING: this PDF uses a legacy
    non-Unicode Nepali font (glyph-for-glyph Devanagari mapped onto
    ASCII code points, the same 'Preeti-style' legacy encoding common in
    Nepali government PDFs of this era) that `pdftotext` cannot
    automatically remap to Unicode -- but the Act's own title and
    numbering are legible/decodable once the substitution is known: the
    extracted text ';fj{hlgs vl/b P]g, @)^#' decodes glyph-for-glyph to
    'सार्वजनिक खरिद ऐन, २०६३' (Public Procurement Act, 2063), and
    '@)^# ;fnsf] P]g g+= #^' decodes to '२०६३ सालको ऐन नं. ३६' (Act No.
    36 of the year 2063 B.S.); the Act's own preamble text also
    confirms it was made by the House of Representatives in the first
    year after the House of Representatives' Proclamation, 2063 was
    issued -- consistent with the commonly-cited '2063 (2007)' framing.
    The same PDF's own amendment table (also decoded) lists three
    amendments through 2075 B.S., including a 'Public Procurement
    (First Amendment) Act, 2073'.
  - **Bolpatra -- the e-GP portal**: PPMO's own page (WebFetch-read)
    names 'विद्युतीय खरिद प्रणाली (e-GP)' at
    `https://www.bolpatra.gov.np/egp/`. `bolpatra.gov.np` itself did not
    respond directly to this session's fetch tooling (connection
    timeout) -- per this task's own disclosed fallback, this iteration
    used the Internet Archive Wayback Machine
    (`archive.org/wayback/available?url=bolpatra.gov.np/egp/`) to fetch
    a 2026-01-22 snapshot of the live portal, whose own page text (read
    directly) confirms: '<title>PPMO</title>' and 'Public Procurement
    Monitoring Office (PPMO) has the vision to establish a centralized
    e-Government portal covering various activities of public
    procurement life cycle including registration of bidders,
    procurement planning, e-tendering, on-line evaluation, contract
    management etc ... hosted at Government Integrated Data Center
    (GIDC) at Singha Durbar and is accessible by the Public Entities and
    suppliers', plus a live 'Register New User' / 'signup-bidder' bidder
    self-registration flow and 'Copyright (c) 2025 Public Procurement
    Monitoring Office'.
  - **Company registration -- the Office of the Company Registrar
    (OCR)**: `ocr.gov.np` also did not respond directly to this
    session's fetch tooling (connection timeout); this iteration used
    the Wayback Machine (2026-05-25 snapshot) as a disclosed fallback.
    The snapshot's own page text (read directly) identifies the site as
    'कम्पनी रजिष्ट्रारको कार्यालय | Office of company register', and
    its own navigation links a dedicated online system 'CAMIS'
    ('कम्पनी प्रशासन व्यवस्थापन सूचना प्रणाली CAMIS', at
    `camis.ocr.gov.np`) for company registration and administration,
    plus a beneficial-ownership disclosure directive ('वास्तविक धनी
    (बेनेफिशरी) को विवरण संकलन...कम्पनी रजिष्ट्रारको निर्देशन
    २०८२।१।२३').
  - **Companies Act, 2063**: OCR's own 'ऐन, नियम, निर्देशिका' (Acts,
    Rules, Directives) listing page (Wayback snapshot, own text read
    directly) titles an entry 'कम्पनी ऐन २०६३ (दोश्रो संशोधन सहित)'
    (Companies Act 2063, with Second Amendment). This iteration
    downloaded the linked PDF directly from
    `giwmscdnone.gov.np/media/pdf_upload/...` (Unicode Devanagari, not
    legacy-font-encoded) and read it with `pdftotext -layout`: its own
    header states 'www.lawcommission.gov.np' (i.e. this is the Law
    Commission's own authoritative text, mirrored via this CDN), its
    own title reads 'कम्पनी ऐन, २०६३' (Companies Act, 2063), its own
    authentication line reads 'प्रमाणीकरण मिति २०६३।७।२४' (authenticated
    2063-07-24 B.S.), and its own numbering reads 'सम्वत् २०६३ सालको
    ऐन नं. १८' (Act No. 18 of the year 2063 B.S.). Its own amendment
    table lists 'कम्पनी (पहिलो संशोधन) ऐन, २०७४' (Companies (First
    Amendment) Act, 2074) and a 2075 constitutional-alignment amendment
    -- the 'second amendment' OCR's own listing page refers to.
  - **Department of Industry (DOI) / foreign investment**:
    `doind.gov.np` (fetched directly) is the Department of Industry
    under the 'उद्योग, वाणिज्य तथा आपूर्ति मन्त्रालय' (Ministry of
    Industry, Commerce and Supplies), own page text (WebFetch-read)
    naming its 'विदेशी लगानी तथा प्रविधि हस्तान्तरण शाखा' (Foreign
    Investment and Technology Transfer Section) and an 'Industry
    Management Information System (IMIS)' for digital registration
    services, plus the Industrial Enterprises Act, 2076.
  - **Foreign Investment and Technology Transfer Act, 2075 (FITTA)**:
    this iteration found DOI's own acts-listing page
    (`doind.gov.np/act`, fetched directly) titles an entry 'विदेशी
    लगानी तथा प्रविधि हस्तान्तरण ऐन, २०७५' and downloaded the linked
    PDF directly from DOI's own upload path
    (`https://doind.gov.np/uploads/notices/Notices-20210423231458200.pdf`),
    read with `pdftotext -layout` (this PDF is legacy-font-encoded like
    the Public Procurement Act PDF, decoded the same way): its own
    title decodes to 'विदेशी लगानी तथा प्रविधि हस्तान्तरण ऐन,२०७५'
    (Foreign Investment and Technology Transfer Act, 2075), its own
    authentication line decodes to '२०७५।१२।१३' (2075-12-13 B.S.), and
    its own numbering decodes to 'सम्वत् २०७५ सालको ऐन नं. ३४' (Act No.
    34 of the year 2075 B.S.) -- consistent with the commonly-cited
    '2075 (2019)' framing.
  - **FLAGSHIP CHECK GROUNDING -- FITTA's own दफा १७ (Section 17),
    'विदेशी लगानी स्वीकृत गर्ने निकाय' (Foreign Investment Approving
    Authority)**, decoded from the same legacy-font PDF, own text:
    '(१) छ अर्ब रुपैयाँसम्मको विदेशी लगानीको स्वीकृति विभागले गर्नेछ।'
    ('(1) Approval of foreign investment up to NPR 6,000,000,000 [six
    billion rupees] shall be given by the Department [of Industry].')
    '(२) छ अर्ब रुपैयाँभन्दा बढीको विदेशी लगानीको लगानी बोर्ड ऐन, २०६८
    अन्तर्गतको लगानी बोर्डले गर्नेछ।' ('(2) Foreign investment exceeding
    NPR 6,000,000,000 shall be [approved] by the Investment Board under
    the Investment Board Act, 2068.') This is a genuinely different
    check SHAPE from every other iso3166 sibling this repo mirrors
    (grep-verified absent as a governor check function name fleet-wide
    at build time): a SINGLE clean monetary-value threshold routing
    between TWO EXECUTIVE bodies (Department of Industry vs. Investment
    Board Nepal) -- unlike `cloud-itonami-iso3166-lao`'s two-criterion
    (land-area OR household-count) routing between two LEGISLATIVE
    bodies, or `cloud-itonami-iso3166-cub`'s sector/modality routing
    among three bodies.
  - **HONEST DISCLOSURE -- a genuine inconsistency this iteration found
    and did NOT silently resolve**: FITTA's own दफा १७(२) (quoted above)
    names 'लगानी बोर्ड ऐन, २०६८' (Investment Board Act, 2068) as the
    Investment Board's governing statute. But Investment Board Nepal's
    own current 'कानुन' (Laws) listing page (`ibn.gov.np/category/laws`,
    fetched directly) lists only 'सार्वजनिक-निजी साझेदारी तथा लगानी
    ऐन २०७५' (Public-Private Partnership and Investment Act, 2075) and
    its 2077 regulations -- NOT the 2068 Act FITTA names. This iteration
    downloaded that 2075 Act's own PDF directly from IBN's own site
    (`giwmscdnone.gov.np`, header again 'www.lawcommission.gov.np') and
    read it with `pdftotext`: its own दफा ७१ ('खारेजी तथा बचाउ' --
    Repeal and Savings), own text, read directly: '(१) पूर्वाधार
    संरचनाको निर्माण तथा सञ्चालनमा निजी लगानी सम्बन्धी ऐन,२०६३ र लगानी
    बोर्ड ऐन,२०६८ खारेज गरिएका छन्।' ('(1) the Act relating to Private
    Investment in the Construction and Operation of Infrastructure
    Structure, 2063 AND the Investment Board Act, 2068 are hereby
    REPEALED'), with subsection (3) confirming the Board's own members
    and executive chief 'are deemed appointed under this [2075] Act' --
    i.e. Investment Board Nepal's institutional CONTINUITY is preserved,
    but its ACTUAL current governing statute is the Public-Private
    Partnership and Investment Act, 2075 (Act No. 32 of 2075 B.S., own
    text: 'संवत् २०७५ सालको ऐन नं. ३२', certified the SAME date as
    FITTA -- '२०७५।१२।१३'), not the repealed 2068 Act FITTA's own
    (currently-fetched) text still names. This catalog's
    `:investment-approval-authority-legal-basis` states BOTH facts
    rather than silently picking one, the same discipline
    `cloud-itonami-iso3166-lao` used for the OISO/OSSO naming drift in
    its own source material.
  - **Inland Revenue Department (IRD) / PAN**: `ird.gov.np` (fetched
    directly, redirects from `www.ird.gov.np`) own page text
    (WebFetch-read) names 'आन्तरिक राजस्व विभाग' (Inland Revenue
    Department) under the Ministry of Finance, a 'PAN नम्बर खोजी' (PAN
    Number Search) service and 'करदाता पोर्टल' (Taxpayer Portal), and
    generically names 'आयकर ऐन' (Income Tax Act) and 'मूल्य अभिवृद्धि
    कर ऐन, २०५२' (VAT Act, 2052) as administered laws. HONEST GAP: this
    iteration could NOT independently confirm the Income Tax Act's own
    law-number or enactment date this session (IRD's own homepage names
    it only generically, with no linked primary text this iteration
    could locate within this session's fetch budget) -- `statute.facts`
    (this repo) therefore does NOT carry an Income Tax Act entry, an
    honestly-reported gap rather than a fabricated citation.
  - **Investment Board Nepal (IBN)**: `ibn.gov.np` (fetched directly)
    own page text (WebFetch-read) identifies 'लगानी बोर्डको कार्यालय'
    (Office of the [Investment] Board) and a 'एकल विन्दु सेवा केन्द्र'
    (Single Window Service Center) at `ossc.ibn.gov.np`.
  - **Labour Act, 2074**: the Ministry of Labour, Employment and Social
    Security's own site (`moless.gov.np`, fetched directly -- NOTE this
    is a different domain from the commonly-guessed 'mole.gov.np', which
    did not resolve) own acts-listing page (own text, read directly)
    titles an entry 'श्रम ऐन, २०७४' (Labour Act, 2074) and this
    iteration downloaded the linked PDF directly
    (`giwmscdnone.gov.np/media/app/public/298/posts/1715075611_100.pdf`,
    header again 'www.lawcommission.gov.np', Unicode Devanagari, read
    with `pdftotext -layout`): its own authentication line reads
    'प्रमाणीकरण तथा प्रकाशित मिति २०७४/५/१९' (authenticated/published
    2074-05-19 B.S.), its own numbering reads 'सम्वत् २०७४ सालको ऐन नं.
    १४' (Act No. 14 of the year 2074 B.S.), amended by 'केही नेपाल ऐन
    संशोधन गने ऐन, २०७५' (2075) -- consistent with the commonly-cited
    '2074/2017' framing.
  - **HONEST GAP -- no distinct 'authorized representative' provision
    located**: unlike `cloud-itonami-iso3166-ind`'s India-resident-
    representative requirement, this iteration did NOT locate a Nepal-
    specific provision requiring a distinct local representative/agent
    separate from OCR company registration itself within this session's
    fetch budget -- `rep-spec-basis` therefore returns `nil` for NPL, an
    honestly-reported gap (the same discipline `cloud-itonami-iso3166-
    lao` used for its own jurisdiction), not an invented requirement.
  - Coverage is reported HONESTLY (see `coverage`): a jurisdiction not
    in this table has NO spec-basis, full stop -- the advisor must not
    fabricate one, and the governor holds if it tries.")

(def catalog
  "iso3 -> requirement map. `:required-evidence` mirrors the generic
  intake/portal-registration/filing evidence set; `:legal-basis` /
  `:owner-authority` / `:provenance` are the G2 citation the governor
  requires before any `:jurisdiction/assess` proposal can commit.
  `:corporate-number-*` grounds the OCR company-registration check;
  `:tax-id-*` grounds the separate PAN/IRD check (Nepal's company
  registration and tax-ID regimes are administered by two DIFFERENT
  agencies, unlike e.g. `cloud-itonami-iso3166-lao`'s bundled
  enterprise-registration-plus-TIN certificate); NPL deliberately
  carries NO `:rep-owner-authority` (see catalog docstring's honest-gap
  note). `:investment-approval-authority-*` grounds this vertical's
  flagship governor check (`investment-approval-authority`/
  `investment-approval-authority-mismatch?` in `marketentry.registry`)."
  {"NPL" {:name "Nepal"
          :owner-authority "Public Procurement Monitoring Office (PPMO) -- Office of the Prime Minister and Council of Ministers; oversees the Bolpatra e-GP portal for bidder registration and the public-procurement lifecycle"
          :legal-basis "Public Procurement Act, 2063 (Act No. 36 of the year 2063 B.S. ~ 2007; own text fetched and read directly, PDF embedded on ppmo.gov.np's own official page, legacy-font-encoded but decoded -- see namespace docstring)"
          :national-spec "Bolpatra e-Government Procurement (e-GP) portal (bolpatra.gov.np/egp/) -- bidder self-registration, procurement planning, e-tendering, on-line evaluation and contract management, per PPMO's own page text"
          :provenance "https://www.ppmo.gov.np/ ; https://www.bolpatra.gov.np/egp/ (confirmed via Wayback Machine 2026-01-22 snapshot, live site unreachable this session) ; https://giwmscdnone.gov.np/media/pdf_upload/1.%20Procurement%20Act%2C%202063_ysjesb0.pdf"
          :required-evidence ["Company Registration Certificate record (Office of the Company Registrar, per Companies Act 2063, via the CAMIS online system)"
                              "PAN (Permanent Account Number) certificate record (Inland Revenue Department)"
                              "Bolpatra e-GP bidder-registration record (Public Procurement Monitoring Office)"
                              "Industry Registration record (Department of Industry, IMIS) -- for FDI-backed engagements"
                              "Foreign-investment approval record (Department of Industry, or Investment Board Nepal above the NPR 6,000,000,000 threshold, per FITTA 2075 दफा १७)"]
          :corporate-number-owner-authority "Office of the Company Registrar (OCR), under the Ministry of Industry, Commerce and Supplies -- CAMIS online company-registration/administration system (camis.ocr.gov.np)"
          :corporate-number-legal-basis "Companies Act, 2063 (Act No. 18 of the year 2063 B.S., authenticated 2063-07-24 B.S.; own text fetched and read directly, PDF header states 'www.lawcommission.gov.np')"
          :corporate-number-provenance "https://ocr.gov.np/ (confirmed via Wayback Machine 2026-05-25 snapshot, live site unreachable this session) ; https://ocr.gov.np/category/act-rules/"
          :tax-id-owner-authority "Inland Revenue Department (IRD), Ministry of Finance -- PAN (Permanent Account Number)"
          :tax-id-legal-basis "IRD's own site (ird.gov.np, fetched directly) confirms PAN registration/search and a taxpayer portal, and generically names an Income Tax Act and the VAT Act, 2052 as administered laws; this iteration could NOT independently confirm the Income Tax Act's own law-number or date this session -- an honest gap, see namespace docstring"
          :tax-id-provenance "https://ird.gov.np/"
          :investment-approval-authority-owner-authority "Department of Industry (DOI), Ministry of Industry, Commerce and Supplies, for foreign investment up to NPR 6,000,000,000; Investment Board Nepal (IBN) for foreign investment exceeding that threshold"
          :investment-approval-authority-legal-basis "Foreign Investment and Technology Transfer Act, 2075 (Act No. 34 of 2075 B.S., authenticated 2075-12-13 B.S.), दफा १७ (own text, read directly): '(१) छ अर्ब रुपैयाँसम्मको विदेशी लगानीको स्वीकृति विभागले गर्नेछ। (२) छ अर्ब रुपैयाँभन्दा बढीको विदेशी लगानीको लगानी बोर्ड ऐन, २०६८ अन्तर्गतको लगानी बोर्डले गर्नेछ।' -- ['(1) approval of foreign investment up to NPR 6,000,000,000 shall be given by the Department; (2) foreign investment exceeding NPR 6,000,000,000 shall be [approved] by the Investment Board under the Investment Board Act, 2068.'] HONEST DISCLOSURE: this session independently fetched and read the Public-Private Partnership and Investment Act, 2075 (Act No. 32 of 2075 B.S., certified the SAME date as FITTA -- 2075-12-13 B.S.), whose own दफा ७१ states the Investment Board Act, 2068 (the Act FITTA's own text above names) was REPEALED by this newer Act, while the Board's own continuity (members, executive chief) is preserved under it -- see namespace docstring for the full text. FITTA's own currently-fetched text still names the repealed 2068 Act; this catalog states both facts rather than silently picking one."
          :investment-approval-authority-criteria {:investment-board-threshold-npr 6000000000}
          :investment-approval-authority-provenance "https://doind.gov.np/uploads/notices/Notices-20210423231458200.pdf ; https://doind.gov.np/act ; https://ibn.gov.np/category/laws/ ; https://ibn.gov.np/content/5/public-private-partnership-and-investment-act-2075/"}
   "USA" {:name "United States" :owner-authority "GSA/SAM.gov" :legal-basis "FAR"
          :national-spec "SAM.gov" :provenance "https://sam.gov/"
          :required-evidence ["EIN record" "SAM.gov registration record" "State business registration record" "SAM UEI verification record"]}
   "IND" {:name "India" :owner-authority "GeM" :legal-basis "General Financial Rules; GeM terms"
          :national-spec "GeM" :provenance "https://gem.gov.in/"
          :required-evidence ["GSTIN/PAN record" "GeM seller registration record" "MCA company registration extract" "Authorized-representative record"]}})

(defn spec-basis
  "The jurisdiction's requirement map, or nil -- nil means NO spec-basis,
  and the governor must hold any proposal that tries to assess or file
  on it."
  [iso3]
  (get catalog iso3))

(defn coverage
  "Honest coverage report: how many of the requested jurisdictions actually
  have a spec-basis entry. Never report a missing jurisdiction as covered."
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s) :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-npl R0: " (count catalog)
                 " jurisdictions seeded with an official spec-basis. "
                 "This is a starting catalog for market-entry navigation, "
                 "not a survey of all ~194 jurisdictions -- extend "
                 "`marketentry.facts/catalog`, never fabricate a "
                 "jurisdiction's requirements.")})))

(defn required-evidence-satisfied?
  "Does `submitted` (a set/coll of evidence keywords or strings) satisfy
  every evidence item listed for `iso3`? Missing spec-basis -> never
  satisfied."
  [iso3 submitted]
  (when-let [{:keys [required-evidence]} (spec-basis iso3)]
    (let [need (count required-evidence)
          have (count (filter (set submitted) required-evidence))]
      (= need have))))

(defn evidence-checklist [iso3]
  (:required-evidence (spec-basis iso3) []))

(defn rep-spec-basis
  "The jurisdiction's representative-related requirement map, or nil when
  this catalog has no such regime. For NPL this is deliberately nil --
  see the namespace docstring's honest-gap note (this iteration did not
  locate a Nepal-specific representative provision distinct from OCR
  company registration itself)."
  [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:rep-owner-authority sb)
      (select-keys sb [:rep-owner-authority :rep-legal-basis :rep-provenance]))))

(defn corporate-number-spec-basis
  "The jurisdiction's company-registration regime, or nil."
  [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:corporate-number-owner-authority sb)
      (select-keys sb [:corporate-number-owner-authority
                       :corporate-number-legal-basis
                       :corporate-number-provenance]))))

(defn tax-id-spec-basis
  "The jurisdiction's tax-identifier (PAN) regime, or nil. Nepal's
  company-registration (OCR) and tax-ID (IRD/PAN) regimes are two
  DIFFERENT agencies, unlike a bundled certificate -- kept as a
  separate accessor from `corporate-number-spec-basis` for that
  reason."
  [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:tax-id-owner-authority sb)
      (select-keys sb [:tax-id-owner-authority :tax-id-legal-basis :tax-id-provenance]))))

(defn investment-approval-authority-spec-basis
  "The jurisdiction's investment-approval-authority-routing regime, or
  nil. For NPL this is real and current -- the flagship check this
  vertical adds is grounded here (FITTA 2075 दफा १७, Department of
  Industry vs. Investment Board Nepal, NPR 6,000,000,000 threshold)."
  [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:investment-approval-authority-owner-authority sb)
      (select-keys sb [:investment-approval-authority-owner-authority
                       :investment-approval-authority-legal-basis
                       :investment-approval-authority-criteria
                       :investment-approval-authority-provenance]))))
