package clrs.string;

public class Rabin_Karp_Matcher {

	public void exec(char[] T, char[] P, int d, int q) {
		int n = T.length;
		int m = P.length;

		int h = 1;
		for (int i = 1; i < m; i++) {
			h *= d;
		}
		h %= q;

		int p = 0, t = 0;
		for (int i = 0; i < m; i++) {
			p = (p * d + nv(P[i])) % q;
			t = (t * d + nv(T[i])) % q;
		}

		for (int s = 0; s <= n - m; s++) {
			if (p == t) {// (p - t) % q == 0
				if (match(T, P, s)) {
					System.out.println("Pattern occurs with shift " + (s + 1));
				}
			}
			if (s < n - m) {
				t = (d * (t - h * nv(T[s])) + nv(T[s + m])) % q;
				if (t < 0) {
					t += q;
				}
			}
		}
	}

	private boolean match(char[] T, char[] P, int s) {
		for (int j = 0; j < P.length; j++) {
			if (T[s + j] != P[j]) {
				return false;
			}
		}
		return true;
	}

	private int nv(char c) {
		return Character.getNumericValue(c);
	}

	public static void main(String[] args) {
		char[] T = "2359023141526739921".toCharArray();
		char[] P = "31415".toCharArray();
		int d = 10;
		int q = 13; // 97
		new Rabin_Karp_Matcher().exec(T, P, d, q);
	}

}
