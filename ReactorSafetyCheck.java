import java.util.*;

public class ReactorSafetyCheck {
    public static void main(String[] args) {
        List<String> input = Arrays.asList(
            "7 6 4 2 1",
            "1 2 7 8 9",
            "9 7 6 2 1",
            "1 3 2 4 5",
            "8 6 4 4 1",
            "1 3 6 7 9"
        );
        
        int safeReports = 0;

        for (String line : input) {
            String[] parts = line.trim().split("\\s+");
            int[] levels = new int[parts.length];
            for (int i = 0; i < parts.length; i++) {
                levels[i] = Integer.parseInt(parts[i]);
            }

            if (isSafe(levels)) {
                safeReports++;
            }
        }

        System.out.println("Number of safe reports: " + safeReports);
    }

    private static boolean isSafe(int[] levels) {
        if (levels.length < 2) return true;

        boolean increasing = levels[1] > levels[0];
        boolean decreasing = levels[1] < levels[0];

        if (!increasing && !decreasing) return false; // No direction if equal

        for (int i = 1; i < levels.length; i++) {
            int diff = levels[i] - levels[i - 1];

            if (Math.abs(diff) < 1 || Math.abs(diff) > 3) {
                return false; // Invalid difference
            }

            if (increasing && diff <= 0) return false;
            if (decreasing && diff >= 0) return false;
        }

        return true;
    }
}
