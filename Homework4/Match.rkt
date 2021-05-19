; Quinn Roemer - 6877
; This code verifies the token passed equals the first token of the passed list. If so, it returns the rest of the list.
; Else, this code returns false.

; Examples:
; ’("a" "b" "c") "a" -> ’("b" "c")
; ’("a" "b" "c") "b" -> false
; ’("a") "a" -> ’()
; ’() "a" -> false
; false "a" -> false

; match : list item -> list or false
; if first item of list equals item return rest of list, else false
(define (match toks tok)
  (cond ((not (list? toks)) false)
        ((empty? toks) false)
        ((equal? (first toks) tok) (rest toks))
        (else false)))