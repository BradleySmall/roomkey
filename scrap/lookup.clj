(import [java.net URLEncoder])
(defn fetch-url[address]
  (with-open [stream (.openStream (java.net.URL. address))]
    (let  [buf (java.io.BufferedReader. 
                (java.io.InputStreamReader. stream))]
      (apply str (line-seq buf)))))


(println (fetch-url (str "https://api.twitter.com/1.1/search/tweets.json?q=" (URLEncoder/encode "%bradleysmall") "&src=typd")))
