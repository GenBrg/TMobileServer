import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.Objects;

class Token {
	private static final SecureRandom secureRandom = new SecureRandom();
	private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

	public static final Duration TOKEN_EXPIRE_TIME = Duration.ofHours(24);
	public static final int TOKEN_SIZE = 32;

	private final String token;
	private final Instant expireTime;

	Token() {
		byte[] randomBytes = new byte[TOKEN_SIZE * 6 / 8];
		secureRandom.nextBytes(randomBytes);
		token = base64Encoder.encodeToString(randomBytes);
		expireTime = Instant.now().plus(TOKEN_EXPIRE_TIME);
	}

	boolean isExpire() {
		return Instant.now().isAfter(expireTime);
	}

	public String getToken() {
		return token;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o instanceof String) {
			return ((String)o).equals(token);
		} else if (o instanceof Token) {
			return Objects.equals(token, ((Token)o).token);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(token);
	}
}
