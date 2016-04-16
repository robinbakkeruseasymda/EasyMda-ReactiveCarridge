package flca.mda.api.util;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

import org.joda.time.DateTime;

public class RandomDataUtils {

	private static Random sRandom = new Random();

	private static RandomDataUtils sGen = new RandomDataUtils();

	public String name() {
		StringBuffer sb = new StringBuffer();
		int length = sRandom.nextInt(10);
		if (length < 3)
			length = 3;
		for (int i = 0; i <= length; i++) {
			int x = sRandom.nextInt(letters.length - 1);
			sb.append(letters[x]);
		}
		return sb.toString().substring(0, 1).toUpperCase() + sb.substring(1);
	}

	public String initials() {
		StringBuffer sb = new StringBuffer();
		int length = sRandom.nextInt(2);
		for (int i = 0; i <= length; i++) {
			int x = sRandom.nextInt(letters.length - 1);
			sb.append(letters[x]);
		}
		return sb.toString().toLowerCase();
	}

	public String letter() {
		String s = "";
		int x = sRandom.nextInt(letters.length - 1);
		s += letters[x];
		return s.toLowerCase();
	}

	public int nr(int min, int max) {
		return min + sRandom.nextInt(max - min);
	}

	public Date date(int minYear, int maxYear) {
		int y = nr(minYear, maxYear);
		int m = nr(1, 12);
		int d = nr(1, 28);
		DateTime dt = new DateTime(y, m, d, 0, 0, 0, 0);
		return dt.toDate();
	}

	public String postcode() {
		int nrs = nr(1000, 9999);
		return nrs + letter().toUpperCase() + letter().toUpperCase();
	}

	private static char letters[] = new char[] { 'a', 'a', 'a', 'a', 'a', 'a',
			'e', 'e', 'e', 'e', 'e', 'e', 'o', 'o', 'o', 'o', 'o', 'u', 'u',
			'u', 'u', 'i', 'i', 'i', 'i', 'b', 'b', 'c', 'd', 'd', 'f', 'f',
			'g', 'g', 'h', 'j', 'k', 'k', 'l', 'l', 'm', 'm', 'n', 'n', 'p',
			'p', 'r', 'r', 's', 's', 't', 't', 'v', 'w', 'z', };

	private static int sVOLGR = 0;

	public static long uniqueNumber() {
		return new Date().getTime() + sVOLGR++;
	}

	public static String randomString() {
		return sGen.name();
	}

	public static int randomint(int aMax) {
		return sRandom.nextInt(aMax);
	}

	public static int randomint() {
		return sRandom.nextInt(1000);
	}

	public static short randomshort() {
		return (short) sRandom.nextInt(1000);
	}

	public static long randomlong() {
		return sRandom.nextLong();
	}

	public static double randomdouble() {
		return sRandom.nextDouble();
	}

	public static float randomfloat() {
		return sRandom.nextFloat();
	}

	public static boolean randomboolean() {
		return sRandom.nextBoolean();
	}

	public static Short randomShort() {
		return Short.valueOf(randomshort());
	}

	public static Integer randomInteger() {
		return Integer.valueOf(randomint());
	}

	public static Long randomLong() {
		return Long.valueOf(randomlong());
	}

	public static Double randomDouble() {
		return Double.valueOf(randomdouble());
	}

	public static Float randomFloat() {
		return Float.valueOf(randomfloat());
	}

	public static BigDecimal randomBigDecimal() {
		return BigDecimal.valueOf(randomdouble());
	}

	public static Boolean randomBoolean() {
		return Boolean.valueOf(randomboolean());
	}

	public static Date randomDate() {
		return sGen.date(1950, 2000);// TODO
	}

	public static Timestamp randomTimestamp() {
		return new Timestamp(new Date().getTime());
	}

	public static Enum<?> randomEnum(Class<?> aEnumClass) {
		if (aEnumClass != null) {
			try {
				Method m = aEnumClass.getMethod("values", new Class[] {});
				Enum<?> emumvalues[] = (Enum[]) m.invoke(null, new Object[] {});
				int idx = sRandom.nextInt(emumvalues.length - 1);
				return (emumvalues[idx]);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}
	}

	// ----
	// public enum Kind
	// {
	// FIRSTNAME,
	// LASTNAME,
	// DOB,
	// POSTALCODE,
	// PHONE,
	// MOBILE;
	// }

}
