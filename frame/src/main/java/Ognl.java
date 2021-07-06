

import java.util.List;

public class Ognl {
	public Ognl() {
	}

	public static boolean isNotEmpty(Object parameter) {
		if ((parameter instanceof String)) {
			String tmp = (String) parameter;
			return (tmp != null) && (tmp.length() > 0);
		}
		if ((parameter instanceof java.util.Collection)) {
			List tmp = (List) parameter;
			return (tmp != null) && (!tmp.isEmpty());
		}
		return parameter != null;
	}

	public static boolean isEmpty(Object parameter) {
		return !isNotEmpty(parameter);
	}
}
