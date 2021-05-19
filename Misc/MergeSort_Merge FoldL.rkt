#lang racket

(define (foldL f acc xs)
  (if (empty? xs)
      acc
      (foldL f (f acc (first xs)) (rest xs))))

;(foldL + 0 '[1 2 3])

;(foldL cons empty '(1 2 3))

(define (mergeSort xs)
  (if (empty? xs)
      xs
      (merge (mergeSort (take (/ (length xs) 2) xs)
                        (mergeSort (drop (/ (length xs) 2) xs))))))

(define (merge xs ys)
  (cond ((empty? xs) ys)
        ((empty? ys) xs)
        ((< (first xs) (first ys)) (cons (first xs) (merge (rest xs) ys)
        ((< (first ys) (first xs)) (cons (first ys) (merge xs (rest ys)))))))
)

(mergeSort '[3 6 1 9])

