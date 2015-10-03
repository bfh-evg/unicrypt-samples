package ch.bfh.unicrypt.crypto.encoder;

import ch.bfh.unicrypt.Example;
import ch.bfh.unicrypt.crypto.encoder.abstracts.AbstractEncoder;
import ch.bfh.unicrypt.crypto.encoder.classes.ZModPrimeToEC;
import ch.bfh.unicrypt.crypto.encoder.classes.ZModToBinaryPolynomialField;
import ch.bfh.unicrypt.crypto.encoder.interfaces.Encoder;
import ch.bfh.unicrypt.math.algebra.additive.classes.ECPolynomialField;
import ch.bfh.unicrypt.math.algebra.additive.classes.ECZModPrime;
import ch.bfh.unicrypt.math.algebra.additive.parameters.ECPolynomialFieldParameters;
import ch.bfh.unicrypt.math.algebra.additive.parameters.ECZModPrimeParameters;
import ch.bfh.unicrypt.math.algebra.dualistic.classes.PolynomialElement;
import ch.bfh.unicrypt.math.algebra.dualistic.classes.PolynomialField;
import ch.bfh.unicrypt.math.algebra.dualistic.classes.ZMod;
import ch.bfh.unicrypt.math.algebra.dualistic.classes.ZModElement;
import ch.bfh.unicrypt.math.algebra.dualistic.classes.ZModPrime;
import ch.bfh.unicrypt.math.algebra.general.interfaces.Element;

/**
 * @author C. Lutz
 * @author R. Haenni
 */
public class ECEncoderExample1 {

	// Example to show how to encode an element from ZModPrime into an elliptic curve over Fq
	public static void example1() throws Exception {

		ECZModPrime ecFp = ECZModPrime.getInstance(ECZModPrimeParameters.SECP521r1);
		ZModPrime zModPrime = ecFp.getFiniteField();
		Encoder encoder = ZModPrimeToEC.getInstance(zModPrime, ecFp, 10);

		Element message = encoder.getDomain().getElementFrom(278);
		Element encMessage = encoder.encode(message);
		Element decMessage = encoder.decode(encMessage);

		System.out.println(message);
		System.out.println(decMessage);
	}

	// Example to show how to encode an element from ZModPrime into an elliptic curve over F2m
	public static void example2() throws Exception {

		ECPolynomialField ecF2m = ECPolynomialField.getInstance(ECPolynomialFieldParameters.SECT113r1);
		ZModPrime zModPrime = ecF2m.getZModOrder();
		AbstractEncoder<ZMod, ZModElement, PolynomialField, PolynomialElement> enc = ZModToBinaryPolynomialField.getInstance(zModPrime, ecF2m.getFiniteField());
		Encoder encoder = ZModPrimeToEC.getInstance(zModPrime, ecF2m, 10);

		Element message = encoder.getDomain().getElementFrom(278);
		Element encMessage = encoder.encode(message);
		Element decMessage = encoder.decode(encMessage);

		System.out.println(message);
		System.out.println(decMessage);
	}

	public static void main(final String[] args) {
		Example.runExamples();
	}

}