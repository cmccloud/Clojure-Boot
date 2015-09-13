(ns Clojure-Boot.core
  (:require [reagent.core :as r]))

(def pay-data (r/atom {:hours 40 :salary 40000 :hourly nil}))

(defn simple-component [name]
  [:div.col-md-1.well.well-lg
   [:p.lead "I am a component!"]
   [:p "I have " [:strong "bold"]
    [:span {:style {:color "red"}} " and red "] "text."]
   [:p "My job is to say hi to " name "!"]])

(defn calc-pay []
  (let [{:keys [hours salary hourly] :as data} @pay-data]
    (if (nil? hourly)
      (assoc data :hourly (float (/ salary (* 45 hours))))
      data)))

(defn slider [param value min max]
  [:input {:type "range" :value value :min min :max max
           :style {:width "100%"}
           :on-change (fn [e]
                        (swap! pay-data assoc param (.-target.value e))
                        (when (not= param :hourly)
                          (swap! pay-data assoc :hourly nil)))}])

(defn pay-component []
  (let [{:keys [hours salary hourly]} (calc-pay)]
    [:div
     [:h2 "Hourly Wage Calculator"]
     [:div
      "Salary: $" (int salary)
      [slider :salary salary 0 100000]]
     [:div
      "Hours Worked: " (int hours)
      [slider :hours hours 0 80]]
     [:div
      "Pay per Hour: $" (int hourly)]]))

(defn main-component []
  [:div.container
   (for [names '("Chris" "Dan" "Luke")]
     [simple-component names])])

(defn ^:export run []
  (r/render [pay-component]
            (js/document.getElementById "app")))

(run)
