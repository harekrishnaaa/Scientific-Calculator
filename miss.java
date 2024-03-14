import java.util.*;
public class miss
{
    public static void main(String[] args) {
    
    
        Scanner kb = new Scanner(System.in);
        int a=kb.nextInt();
        int[] arr = new int[a];
        for(int i=0;i<a-1;i++)
        {
            arr[i] = kb.nextInt();
        }
        int sum=0;
        for(int j=0;j<a-1;j++)

        {
            sum = sum + arr[j];
        }
        System.out.println(((a*(a+1))/2)-sum);

    }
}
