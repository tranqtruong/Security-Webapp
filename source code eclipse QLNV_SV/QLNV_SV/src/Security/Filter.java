package Security;

public class Filter {
	public static String cleanSQLI(String input) {
		int count = input.length();
        while(count > 0){
        	input = input.replaceAll("[';-]", ".");
            count--;
        }
        return input;
	}
	
	public static String cleanXSS(String input) {
		int count = input.length();
        while(count > 0){
        	input = input.replaceAll("<script>", ".").replaceAll("</script>", ".").replaceAll("<script", ".").replaceAll("javascript:", ".")
        			.replaceAll("=", ".").replaceAll("<", ".").replaceAll("/>", ".");
            count--;
        }
        return input;
	}
	
	
}
