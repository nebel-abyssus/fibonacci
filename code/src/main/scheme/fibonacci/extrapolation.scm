(define (pow x n)
        (define (qpow x n product)
                (cond ((= n 0) product)
                      ((even? n) (qpow (* x x) (/ n 2) product))
                      (else (qpow x (- n 1) (* product x)))))
        (cond ((< n 0) (/ 1 (pow x (- n))))
              (else (qpow x n 1))))

(define (layerVol r)
        (- (sphereVol (+ earthR r)) earthVol))

(define (sphereVol r)
        (/ (* 4 pi (pow r 3)) 3))

(define (root x n)
        (expt x (/ 1 n)))

(define pi 3.1415926535897932384626433832795)
(define rabP 1500)
(define rabM 1.5)
(define km 1000)
(define earthVol (* 1.08321 (pow 10 12) (pow km 3)))
(define earthR (root (/ (* earthVol 3) 4 pi) 3))
