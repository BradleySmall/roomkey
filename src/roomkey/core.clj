(ns roomkey.core
  (:require [clj-http.client :as client]))

(import [java.net URLEncoder])
(defn encode
  "URL encode a string"
  [string]
  (URLEncoder/encode string))

(defn search-twit-api
  "build the twitter search string from urlencoded string"
  [string]
  (str "https://api.twitter.com/1.1/search/tweets.json?q=" (encode string)  "&src=typd"))
;(str "https://api.twitter.com/1.1/search/tweets.json?q=" (encode string)  "&src=typd&access_token=AAAA%2FAAA%3DAAAAAAAA"))

(defn -main
  "prints the twitter api search results"
  [& args]
  (let [url-string (search-twit-api (first args))]
    (println url-string)

    ;(println (apply str (:body (client/get url-string))))
    ))
