(ns Clojure-Boot.core
  (:require [reagent.core :as r]))

(js/console.log "Hello World!")

(defn simple-component []
  [:div.col-md-1.well.well-lg
   [:p.lead "I am a component!"]
   [:p "I have " [:strong "bold"]
    [:span {:style {:color "red"}} " and red "] "text."]])

(defn ^:export run []
  (r/render [simple-component]
            (js/document.getElementById "app")))

(run)
