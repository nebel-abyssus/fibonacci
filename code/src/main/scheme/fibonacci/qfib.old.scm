(define (fib n)
        (qfib 1 0 0 1 n))

(define (qfib pa ca pt ct n)
        (cond ((zero? n) ca)
	      ((even? n) (qfib pa ca (+ (* pt pt) (* ct ct)) (* ct (+ pt ct pt)) (/ n 2)))
	      (else (qfib (+ (* ca ct) (* pa pt)) (+ (* ct (+ pa ca)) (* pt ca)) pt ct (- n 1)))))
