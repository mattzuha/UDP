public class LCM {
    public static void main(String[] args) {
        int num1 = 3;
        int num2 = 4;
        int num3 = 12;
        int result = lcm(lcm(num1, num2), num3);
        System.out.println(result);
    }

    public static int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    public static int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }
}
