(define (mul a n)
        (mul-iter a 0 n))

(define (mul-iter val accum count)
        (cond ((zero? count) accum)
	      (else (mul-iter val (+ accum val) (- count 1)))))
