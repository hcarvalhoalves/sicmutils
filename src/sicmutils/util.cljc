(ns sicmutils.util
  "Shared utilities between clojure and clojurescript."
  (:refer-clojure :rename {bigint core-bigint
                           int core-int
                           long core-long}
                  #?@(:cljs [:exclude [bigint long int]]))
  (:require #?(:clj [clojure.math.numeric-tower :as nt])
            #?(:cljs goog.math.Integer)
            #?(:cljs goog.math.Long)))

(def compute-sqrt #?(:clj nt/sqrt :cljs Math/sqrt))
(def compute-expt #?(:clj nt/expt :cljs Math/pow))
(def compute-abs #?(:clj nt/abs :cljs Math/abs))
(def numtype #?(:clj Number :cljs js/Number))
(def inttype #?(:clj Integer :cljs goog.math.Integer))
(def longtype #?(:clj Long :cljs goog.math.Long))

(defn bigint [x]
  #?(:clj (core-bigint x)
     :cljs (js/BigInt x)))

(defn int [x]
  #?(:clj (core-int x)
     :cljs (.fromNumber goog.math.Integer x)))

(defn long [x]
  #?(:clj (core-long x)
     :cljs (.fromNumber goog.math.Long x)))

(defn unsupported [s]
  (throw
   #?(:clj (UnsupportedOperationException. s)
      :cljs (js/Error s))))

(defn illegal [s]
  (throw
   #?(:clj (IllegalArgumentException. s)
      :cljs (js/Error s))))

(defn arithmetic-ex [s]
  (throw
   #?(:clj (ArithmeticException. s)
      :cljs (js/Error s))))