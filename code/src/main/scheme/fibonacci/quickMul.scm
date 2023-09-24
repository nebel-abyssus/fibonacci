(define (mul a n)
        (quickMul a 0 n))

(define (quickMul val accum count)
        (cond ((zero? count) accum)
	      ((even? count) (quickMul (double val) accum (/ count 2)))
	      (else (quickMul val (+ accum val) (- count 1)))))

(define (double a)
        (+ a a))
