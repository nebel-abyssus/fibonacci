(define (mul a n)
        (cond ((zero? n) 0)
              (else (+ a (mul a (- n 1))))))
