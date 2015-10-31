(ns parachuting-robots.parser
  (:require [instaparse.core :as insta]))

(defn expr-switch
  ([x] [:expr x])
  ([x y] [:expr y x]))

(defn parse
  [data]
  (->> data
       ((insta/parser
         "<S> = (expr space* | <comment> space*)+
         expr = label* instruction
         <comment> = #'#.*'
         label = word <':'> space*
         <word> = #'[a-zA-z]+'
         <space> = <#'\\s+'>
         instruction = 'left' | 'right' |
         'skipNext' | 'goto' space word"))
       (insta/transform {:expr expr-switch})))

