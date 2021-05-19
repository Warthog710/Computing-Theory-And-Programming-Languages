(define (prod xs)
  (if (empty? xs)
      1
      (* (first xs) (prod (rest xs)))))

(define (prod-helper accum xs)
  (if (empty? xs)
      accum
      (prod-helper (* (first xs) accum) (rest xs))))

(prod '[1 2 3 4 5])

(prod-helper 1 '[1 2 3 4 5])

(define (all f xs)
  (cond
    ((empty? xs) true)
    ((f (first xs)) (all f (rest xs)))
    (else false)))

(all number? '[1 2 3 "4" 5])

(lambda rest xs)