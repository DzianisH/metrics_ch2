package my.dash39.metrics_ch2;

import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class MathService {

    public BigInteger calcFactorial(BigInteger base) {
        if (base.signum() < 0) {
            return BigInteger.ZERO;
        }

        var factorial = BigInteger.ONE;
        for (var counter = BigInteger.valueOf(2); counter.compareTo(base) < 1; counter = counter.add(BigInteger.ONE)) {
            factorial = factorial.multiply(counter);
        }

        return factorial;
    }

}
