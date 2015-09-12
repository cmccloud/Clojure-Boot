(set-env!
 :source-paths #{"src"}
 :resource-paths #{"html"}
 :dependencies '[[adzerk/boot-cljs "1.7.48-3" :scope "test"]
                 [adzerk/boot-reload "0.3.2" :scope "test"]])

(require '[adzerk.boot-cljs :refer :all]
         '[adzerk.boot-reload :refer :all])

(deftask dev
  "Watches for changes and reloads compiled ClojureScript into browser."
  []
  (comp (watch) (reload) (cljs)))
