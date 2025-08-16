package CombinationCalculate;

import java.util.Arrays;

/**
 * 组合数学工具（质数模数）
 * - 预处理 fac/ifac，O(1) 计算 C(n,k) 和 P(n,k)
 * - 支持按需扩容 ensureCapacity
 * - MOD 必须为质数（例如 998244353, 1_000_000_007）
 */
public class ModCombination {
    private long MOD;
    private long[] fac;   // 阶乘: fac[i] = i! % MOD
    private long[] ifac;  // 逆阶乘: ifac[i] = (i!)^{-1} % MOD
    private int preparedN;

    public void ModCombination(int initN, long mod) {
        this.MOD = mod;
        if (initN < 0) initN = 0;
        fac  = new long[initN + 1];
        ifac = new long[initN + 1];
        build(initN);
    }


    /** 确保至少预处理到 n；如需更大范围会自动扩容并增量更新 */
    public void ensureCapacity(int n) {
        if (n <= preparedN) return;
        int oldN = preparedN;
        // 扩容数组
        fac  = Arrays.copyOf(fac,  n + 1);
        ifac = Arrays.copyOf(ifac, n + 1);

        // 先把 fac 补到 n
        for (int i = Math.max(1, oldN + 1); i <= n; i++) {
            fac[i] = fac[i - 1] * i % MOD;
        }
        // 再从 n 开始回推 ifac（只需一次快速幂求 (n!)^{-1}）
        ifac[n] = modPow(fac[n], MOD - 2);
        for (int i = n; i >= Math.max(1, oldN + 1); i--) {
            ifac[i - 1] = ifac[i] * i % MOD;
        }
        preparedN = n;
    }

    /** 组合数 C(n,k)；超界返回 0 */
    public long C(int n, int k) {
        if (k < 0 || k > n) return 0;
        ensureCapacity(n);
        return fac[n] * ifac[k] % MOD * ifac[n - k] % MOD;
    }

    /** 排列数 P(n,k) = n! / (n-k)! */
    public long P(int n, int k) {
        if (k < 0 || k > n) return 0;
        ensureCapacity(n);
        return fac[n] * ifac[n - k] % MOD;
    }

    /** 阶乘 n! % MOD */
    public long fact(int n) {
        ensureCapacity(n);
        return fac[n];
    }

    /** 逆阶乘 (n!)^{-1} % MOD */
    public long invFact(int n) {
        ensureCapacity(n);
        return ifac[n];
    }

    /** 模幂：返回 a^e % MOD（快速幂） */
    public long modPow(long a, long e) {
        a %= MOD;
        long r = 1;
        while (e > 0) {
            if ((e & 1) == 1) r = (r * a) % MOD;
            a = (a * a) % MOD;
            e >>= 1;
        }
        return r;
    }

    /** 可选：乘法逆元 a^{-1} % MOD（MOD 必须为质数且 a 不为 0） */
    public long inv(long a) {
        return modPow((a % MOD + MOD) % MOD, MOD - 2);
    }

    // 内部：完整重建到 n（构造时使用）
    private void build(int n) {
        fac[0] = 1;
        for (int i = 1; i <= n; i++) fac[i] = fac[i - 1] * i % MOD;
        ifac[n] = modPow(fac[n], MOD - 2);
        for (int i = n; i >= 1; i--) ifac[i - 1] = ifac[i] * i % MOD;
        preparedN = n;
    }


}
