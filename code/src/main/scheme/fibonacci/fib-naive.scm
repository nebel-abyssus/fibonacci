(define (fib n)
        ((if (and (negative? n) (even? n))
             -
             +) (fib-naive (abs n))))

(define (fib-naive n)
        (cond ((= n 0) 0)
              ((= n 1) 1)
              (else (+ (fib (- n 2)) (fib (- n 1))))))
