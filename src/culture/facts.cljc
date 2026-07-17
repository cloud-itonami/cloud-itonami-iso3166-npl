(ns culture.facts
  "Country-level regional-culture catalog for Nepal (NPL) -- national
  dishes, protected products, beverages, crafts, festivals and heritage
  sites, per ADR-2607171400 addendum 2 (cloud-itonami-municipality-
  culture-catalog Wave 1, in com-junkawasaki/root). Sibling namespace to
  `marketentry.facts` / `statute.facts` (ADR-2607141700); city-level
  counterparts live in the cloud-itonami-municipality-* repos.

  Catalog is keyed by UPPERCASE ISO3 (mirrors `statute.facts`); entries
  carry no :culture/municipality (that attribute is city-level only).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of culture entries."
  {"NPL"
   [{:culture/id "npl.dish.momo"
     :culture/name "Momo"
     :culture/country "NPL"
     :culture/kind :dish
     :culture/summary "Steamed, filled dumpling in Nepalese and Tibetan cuisine, also popular in Bangladesh, Bhutan and India."
     :culture/url "https://en.wikipedia.org/wiki/Momo_(food)"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "npl.dish.dal-bhat"
     :culture/name "Dal Bhat"
     :culture/country "NPL"
     :culture/kind :dish
     :culture/summary "Lentil-and-rice dish that became Nepal's widely eaten daily meal, especially in the Terai plains and Kathmandu Valley."
     :culture/url "https://en.wikipedia.org/wiki/Dal_bhat"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "npl.dish.sel-roti"
     :culture/name "Sel Roti"
     :culture/name-local "सेल रोटी"
     :culture/country "NPL"
     :culture/kind :dish
     :culture/summary "Traditional Nepalese ring-shaped sweet fried dough made from rice flour."
     :culture/url "https://en.wikipedia.org/wiki/Sel_roti"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "npl.dish.gundruk"
     :culture/name "Gundruk"
     :culture/country "NPL"
     :culture/kind :dish
     :culture/summary "Dish of fermented leafy green vegetables (saag) originating in Nepal."
     :culture/url "https://en.wikipedia.org/wiki/Gundruk"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "npl.beverage.tongba"
     :culture/name "Tongba"
     :culture/country "NPL"
     :culture/kind :beverage
     :culture/summary "Millet-based alcoholic beverage found in the eastern mountainous region of Nepal and neighbouring Sikkim and Darjeeling."
     :culture/url "https://en.wikipedia.org/wiki/Tongba"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "npl.craft.dhaka-topi"
     :culture/name "Dhaka Topi"
     :culture/name-local "ढाका टोपी"
     :culture/country "NPL"
     :culture/kind :craft
     :culture/summary "Traditional Nepalese hand-woven cloth hat, part of Nepalese national dress, woven from Dhaka fabric centered in regions like Palpa and Tansen."
     :culture/url "https://en.wikipedia.org/wiki/Dhaka_topi"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "npl.festival.dashain"
     :culture/name "Dashain"
     :culture/country "NPL"
     :culture/kind :festival
     :culture/summary "Hindu religious festival, also known as Vijaya Dashami, observed primarily in Nepal and in Sikkim, West Bengal, Assam and South India."
     :culture/url "https://en.wikipedia.org/wiki/Dashain"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "npl.heritage.kathmandu-durbar-square"
     :culture/name "Kathmandu Durbar Square"
     :culture/country "NPL"
     :culture/kind :heritage
     :culture/summary "One of three Durbar (royal palace) Squares in the Kathmandu Valley, Nepal, inscribed as a UNESCO World Heritage Site."
     :culture/url "https://en.wikipedia.org/wiki/Kathmandu_Durbar_Square"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [iso3] (get catalog iso3))

(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-npl culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "NPL"))
                 " NPL entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))
