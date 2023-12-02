(define (fib n)
        (cond ((and (negative? n) (even? n)) (- (fib (abs n))))
	      (else (qfib (abs n) (cons 1 0) (cons 0 1)))))

(define (qfib n term accum)
        (cond ((zero? n) (car accum))
	      ((even? n) (qfib (halve n) (fib-double term) accum))
	      (else (qfib (- n 1) term (fib-sum accum term)))))

(define (halve x)
        (quotient x 2))

(define (fib-double x)
        (cons (* (car x) (+ (* 2 (cdr x)) (car x)))
	      (+ (sqr (car x)) (sqr (cdr x)))))

(define (fib-sum a b)
        (cons (+ (* (car a) (cdr b)) (* (+ (car a) (cdr a)) (car b)))
	      (+ (* (car a) (car b)) (* (cdr a) (cdr b)))))

(define (sqr x)
        (* x x))
