# cloud-itonami-iso3166-npl

Open ISO 3166 Blueprint for **NPL**: Nepal.

This repository designs a forkable OSS business for an independent
public-sector market-entry consultant: an already-incorporated operator
(e.g. a `cloud-itonami-cofog-{code}`, `cloud-itonami-isco-{code}`,
`cloud-itonami-unspsc-{segment}` or `cloud-itonami-{ISIC}` blueprint
fork) gets a Compliance Advisor + independent **Market-Entry Compliance
Governor** to navigate public-procurement registration, local business/
tax registration, and local-content rules in Nepal, so the operator
can win and service a government contract without hiring a full in-house
compliance department.

## No robotics premise — digital/data service exemption

Market-entry and procurement-compliance navigation is a pure data/software
service with no physical-domain work (portal registration, document
checklists, regulatory-change monitoring) — the same exemption class as
`cloud-itonami-6310` (HR SaaS replacement) and `cloud-itonami-gtin-*`.
`blueprint.edn` sets `:itonami.blueprint/robotics false` and
`:required-technologies` lists only real capabilities (`:identity`,
`:forms`, `:dmn`, `:bpmn`, `:audit-ledger`), no `:robotics`.

## Core Contract

```text
operator intake + prior filing history
        |
        v
Compliance Advisor -> Market-Entry Compliance Governor -> filing draft, or human sign-off
        |
        v
gated portal registration / filing submission + audit ledger
```

No automated proposal can submit a portal registration or filing the
governor refuses, suppress a compliance record, or claim a legal/tax
conclusion the governor has not cleared. `:filing/submit` is never in any
phase's `:auto` set — it always requires human sign-off (mirrors
`cloud-itonami-M6910`'s `filing-submit-never-auto-at-any-phase`
invariant).

## What this is NOT

- **Not the government of Nepal.** See
  [`docs/business-model.md`](docs/business-model.md) for the boundary with
  `com-etzhayyim-ooyake` (read-only civic mirror), `matsurigoto` (sovereign
  statecraft), `com-etzhayyim-toritsugi` (individual citizen concierge),
  `legal-entity.etzhayyim.com` (read-only data aggregation), and
  `cloud-itonami-M6910` (company incorporation — a different regulatory
  phase this blueprint assumes is already complete).
- **Not legal or tax advice.** Every regulatory claim must cite the
  official source and route final filings to Nepali-licensed counsel
  or a registered agent where the law requires licensed representation.

## Capability layer

Resolves via [`kotoba-lang/iso3166`](https://github.com/kotoba-lang/iso3166)
(ISO 3166 `NPL`). Required capabilities:

- :identity
- :forms
- :dmn
- :bpmn
- :audit-ledger

See [`docs/business-model.md`](docs/business-model.md) and
[`docs/operator-guide.md`](docs/operator-guide.md).

## License

AGPL-3.0-or-later.

## Market-entry / statute catalogs

Governed public-sector market-entry compliance actor, same architecture
as `cloud-itonami-iso3166-ind`/`-pak`/`-lao`, investigated for Nepal's
own market-entry surface (verified 2026-07-23, see the namespace
docstrings for the full research trail, including facts this iteration
could NOT verify -- the Income Tax Act's own primary citation, and a
distinct Nepal-specific "authorized representative" provision):

- `src/marketentry/{facts,governor,phase,sim,operation,registry,store,
  marketentryllm}.cljc` -- the actor. `facts.cljc` cites the Public
  Procurement Act, 2063 (Act No. 36; Public Procurement Monitoring
  Office, `ppmo.gov.np`, own page fetched directly), the Bolpatra e-GP
  portal (`bolpatra.gov.np/egp/`, confirmed via a Wayback Machine
  snapshot since the live site did not respond to this session's fetch
  tooling), the Companies Act, 2063 (Act No. 18; Office of the Company
  Registrar / CAMIS, confirmed via Wayback Machine since `ocr.gov.np`
  also did not respond directly), the Inland Revenue Department's PAN
  regime (`ird.gov.np`, fetched directly), and the Foreign Investment
  and Technology Transfer Act, 2075 (FITTA, Act No. 34; Department of
  Industry, `doind.gov.np`, fetched directly). `governor.cljc`'s
  flagship check independently recomputes whether an engagement's own
  declared foreign-investment amount actually falls under Department-
  of-Industry or Investment-Board-Nepal approval jurisdiction per
  FITTA's own दफा १७ (Section 17) NPR 6,000,000,000 threshold -- a
  single clean monetary-value-threshold routing between two EXECUTIVE
  bodies, grep-verified absent fleet-wide, a genuinely different shape
  from every other iso3166 sibling. This iteration also found (and
  disclosed rather than silently resolved) that FITTA's own text names
  a now-REPEALED "Investment Board Act, 2068" as the Board's governing
  statute -- Investment Board Nepal's own current "Laws" listing names
  only the Public-Private Partnership and Investment Act, 2075, whose
  own दफा ७१ (fetched and read directly) confirms the 2068 Act's repeal
  while preserving the Board's institutional continuity.
- `src/statute/facts.cljc` -- general-law catalog: the Public
  Procurement Act, 2063; the Companies Act, 2063; FITTA, 2075; the
  Public-Private Partnership and Investment Act, 2075 (Investment Board
  Nepal's actual current governing statute); and the Labour Act, 2074
  (Act No. 14; Ministry of Labour, Employment and Social Security,
  `moless.gov.np`, fetched directly). This catalog deliberately carries
  NO Income Tax Act entry -- an honestly-reported gap this iteration
  could not independently confirm by law-number/date this session (see
  namespace docstring), not a fabricated citation.

Every citation is curl/pdftotext/WebFetch-verified against an official
source (`ppmo.gov.np`, `doind.gov.np`, `ibn.gov.np`, `moless.gov.np`,
`ird.gov.np`, plus Wayback Machine snapshots for `ocr.gov.np` and
`bolpatra.gov.np`, both of which did not respond directly to this
session's fetch tooling -- disclosed explicitly rather than silently
substituted). Two legacy-font-encoded government PDFs (the Public
Procurement Act and FITTA) required manual glyph decoding, also
disclosed in the namespace docstrings rather than silently resolved.

## Culture catalog

Alongside the market-entry / statute catalogs, this repo carries a
**country-level regional-culture catalog** (ADR-2607171400 addendum 2,
`cloud-itonami-municipality-culture-catalog` Wave 1, in
`com-junkawasaki/root`) — national dishes, protected products, beverages,
crafts, festivals and heritage sites for Nepal:

- `src/culture/facts.cljc` — the catalog, source of truth (keyed by
  uppercase ISO3, mirroring `statute.facts`).
- `schema/culture.edn` — DataScript schema.
- `data/culture-tx.edn` — derived DataScript tx-data (regenerated from
  the catalog, never hand-edited).

City-level counterparts live in the `cloud-itonami-municipality-*` repos.
Same provenance discipline as the compliance catalogs: every entry cites a
source URL that was actually fetched and read on `:culture/retrieved-at`;
summaries state only what the cited source confirms. An item not in
`culture.facts/catalog` has no spec-basis — never fabricate one.
