(define (fib n)
        ((if (and (negative? n) (even? n))
             -
             +) (fib-iter 1 0 (abs n))))

(define (fib-iter prev cur n)
        (cond ((zero? n) cur)
              (else (fib-iter cur (+ prev cur) (- n 1)))))
