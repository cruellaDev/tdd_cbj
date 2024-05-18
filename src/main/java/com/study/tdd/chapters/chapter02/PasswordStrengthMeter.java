package com.study.tdd.chapters.chapter02;

/**
 * <pre>
 * 암호검사기
 * </pre>
 * @version 1.0
 * @author Lee, Ji Won
 * @since 2024.05.17
 * @link    <a href="https://product.kyobobook.co.kr/detail/S000001248962">테스트주도개발시작하기</a>
 */
public class PasswordStrengthMeter {
    public PasswordStrength meter(String s) {
        if (s == null || s.isEmpty()) return PasswordStrength.INVALID;
        int metCounts = getMetCriteriaCounts(s);
        if (metCounts <= 1) return PasswordStrength.WEAK;
        if (metCounts == 2) return PasswordStrength.NORMAL;
        return PasswordStrength.STRONG;
    }

    private int getMetCriteriaCounts(String s) {
        int metCounts = 0;
        if (s.length() >= 8) metCounts++;
        if (meetsContainingNumberCriteria(s)) metCounts++;
        if (meetsContainingUppercaseCriteria(s)) metCounts++;
        return metCounts;
    }

    private boolean meetsContainingNumberCriteria(String s) {
        for (char ch : s.toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                return true;
            }
        }
        return false;
    }

    private boolean meetsContainingUppercaseCriteria(String s) {
        for (char ch : s.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                return true;
            }
        }
        return false;
    }
}
