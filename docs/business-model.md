# Business Model: Independent Public-Sector Market-Entry & Procurement Compliance Service — Nepal

## Classification

- Repository: `cloud-itonami-iso3166-npl`
- ISO 3166: `NPL` (Nepal)
- Activity: public-procurement market-entry and ongoing regulatory-
  compliance navigation for an already-incorporated operator
- Social impact: [:nepali-sme-market-access :public-spend-transparency :cross-border-friction-reduction]

## Customer

- an already-incorporated `cloud-itonami-cofog-{code}` /
  `cloud-itonami-isco-{code}` / `cloud-itonami-unspsc-{segment}` /
  `cloud-itonami-{ISIC}` operator wanting to bid on a Nepali
  public contract
- a foreign SME or civic-tech vendor entering the public sector in
  Nepal for the first time
- a `cloud-itonami-M6910` client that has just completed incorporation and
  now needs public-sector market access

## Offer

- registration walkthrough for Bolpatra (bolpatra.gov.np), Nepal's
  electronic government procurement (e-GP) portal operated under the
  authority of the Public Procurement Monitoring Office (PPMO, an apex
  body under the Office of the Prime Minister and Council of Ministers,
  established under the Public Procurement Act 2007), mandatory for all
  federal, provincial and local public entities
- business/tax registration checklist: a company registration
  certificate from the Office of the Company Registrar (OCR, ocr.gov.np,
  via the CAMIS online system, Companies Act 2063) and a PAN (Permanent
  Account Number) certificate from the Inland Revenue Department
- foreign-investment approval routing: Department of Industry (DOI) for
  foreign investment up to NPR 6,000,000,000, or Investment Board Nepal
  (IBN) above that threshold, per the Foreign Investment and Technology
  Transfer Act, 2075 (FITTA) दफा १७ -- independently verified by the
  Market-Entry Compliance Governor's flagship check (see
  `src/marketentry/governor.cljc`)
- local-content / preferential-procurement navigation: Nepali
  public-procurement preference provisions on qualifying tenders
- ongoing regulatory-change monitoring subscription
- compliance-audit export package for the client's own records

## Revenue

- per-engagement market-entry fee (one-time registration + checklist
  completion)
- recurring regulatory-change monitoring subscription
- compliance-audit export package

## Trust Controls

- any actual portal registration or filing submission requires
  Market-Entry Compliance Governor clearance and always escalates to
  human sign-off (`:filing/submit` is never automated at any phase)
- a false or fabricated regulatory-requirement claim is a HARD hold that
  cannot be overridden by human approval alone — it must be corrected
  against a cited official source first
- a filing that claims the wrong foreign-investment approval authority
  (Department of Industry vs. Investment Board Nepal) for its own
  declared investment amount is a HARD hold, independently recomputed
  against FITTA 2075's own NPR 6,000,000,000 threshold
- this service does **not** provide legal or tax advice; characterization
  and filing on the client's behalf beyond checklist/draft assistance
  routes to Nepali-licensed counsel or a registered agent
- every requirement cites the official portal or regulation, never
  invented

## Boundary with adjacent actors (read before forking)

- **`com-etzhayyim-ooyake`** (etzhayyim/root): read-only civic-wayfinding
  mirror of government structure, non-commercial, barred from acting as
  or for the government (G3 impersonation ban). This blueprint is
  commercial and never claims to be an official channel.
- **`matsurigoto`** (etzhayyim/root): sovereign e-government statecraft —
  literally the government, for etzhayyim's own covenant or an adopting
  nation-state. This blueprint is an independent operator the government
  contracts with or that bids into its procurement — never the
  government.
- **`com-etzhayyim-toritsugi`** (etzhayyim/root): guides a consenting
  INDIVIDUAL citizen through their OWN procedure, non-profit,
  donation-only. This blueprint's client is a business operator, not an
  individual citizen, and it is commercial.
- **`legal-entity.etzhayyim.com`**: read-only aggregated company-registry
  data, no execution. This blueprint executes (gated) registrations.
- **`cloud-itonami-M6910`**: helps a client BECOME a legal entity
  (incorporation, ISIC 6910) — a prior, different regulatory phase
  (company law). This blueprint assumes incorporation is already done and
  handles public-procurement market entry (a different regulatory domain).
- **`cloud-itonami-cofog-{code}`**: a jurisdiction-agnostic operator
  template for ONE public function. This blueprint is the orthogonal
  jurisdiction-specific axis — the two compose (fork a COFOG-function
  blueprint AND this one to operate in Nepal).
