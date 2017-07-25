declare const contextPath: string
export class PathUtil {
	public static resolve(url): string {
		if (contextPath) {
			return contextPath + '/' + url
		} else {
			return url
		}
	}
}