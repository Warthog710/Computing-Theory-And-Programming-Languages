; Quinn Roemer - 6877
; This code parses the below language using recursive calls on parser functions to determine bad and good input
; If a string is in the language, an accept is printed. Else, the code prints reject.

; S -> CS | LS | lambda
; L -> [S]
; C -> > | < | + | - | , | .


; match : list item -> list or false
; if first item of list equals item return rest of list, else false
(define (match toks tok)
  (cond ((not (list? toks)) false)
        ((empty? toks) false)
        ((equal? (first toks) tok) (rest toks))
        (else false)))

; parseS : list -> list or false
; Consumes prefix of toks derivable from S
; returns list of remaining tokens, or false if no such prefix exists
(define (parseS toks)
  (cond ((not (list? toks)) false)
        ((empty? toks) toks)
        ((equal? (first toks) ">") (parseS (parseC toks)))
        ((equal? (first toks) "<") (parseS (parseC toks)))
        ((equal? (first toks) "+") (parseS (parseC toks)))
        ((equal? (first toks) "-") (parseS (parseC toks)))
        ((equal? (first toks) ",") (parseS (parseC toks)))
        ((equal? (first toks) ".") (parseS (parseC toks)))
        ((equal? (first toks) "[") (parseS (parseL toks)))
        ((equal? (first toks) "]") toks)
        (else false)))

; parseL : list -> list or false
; Consumes prefix of toks derivable from L
; returns list of remaining tokens, or false if no such prefix exists
(define (parseL toks)
  (cond ((not (list? toks)) false)
        ((empty? toks) false)
        ((equal? (first toks) "[") (match (parseS (match toks "[")) "]"))
        (else false)))

; parseC : list -> list or false
; Consumes prefix of toks derivable from C
; returns list of remaining tokens, or false if no such prefix exists
(define (parseC toks)
  (cond ((not (list? toks)) false)
        ((empty? toks) false)
        ((equal? (first toks) ">") (match toks ">"))
        ((equal? (first toks) "<") (match toks "<"))
        ((equal? (first toks) "+") (match toks "+"))
        ((equal? (first toks) "-") (match toks "-"))
        ((equal? (first toks) ",") (match toks ","))
        ((equal? (first toks) ".") (match toks "."))
        (else false)))

; Converts a string to a list of single-character strings, passes that
; list to parseS, and then evaluates to "accept" if result is empty list
(define (test str)
  (if (equal? (parseS (map string (string->list str))) empty)
      "accept"
      "reject"))