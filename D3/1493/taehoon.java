import java.util.Scanner;
import java.io.FileInputStream;
import java.util.*;
class Solution
{
    // Pair 클래스 생성
    static class MyPair{
        int first, second;
        MyPair(int f, int s){
            this.first = f;
            this.second = s;
        }
        void setFirst(int f){
            this.first = f;
        }
        int getFirst(){
            return first;
        }
        void setSecond(int s){
            this.second = s;
        }
        int getSecond(){
            return second;
        }
    }

    public static void main(String args[]) throws Exception
    {

        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        // 1+2+3+4+5+... 순으로 좌표 수 증가
        // 따라서 p, q <= 10000이므로 대략 150 + 150 = 300
        // 300 좌표까지의 연산 필요
        // 301*150 = 45010
        // 반복할 때마다 생성하면 메모리 및 시간이 더 걸리므로 전역변수로 생성
        // list 생성 (값을 통한 좌표 얻기 위함)
        List<MyPair> valToPos= new ArrayList<>();
        // 0은 존재하지 않으므로 더미 데이터 추가
        valToPos.add(new MyPair(-1, -1));
        // 좌표를 통해 값을 얻기 위해 2차원 배열 생성
        int [][] posToVal = new int[500][500];
        int x = 1;
        int y = 1;
        for(int cnt=1;cnt<=45000;cnt++){
            valToPos.add(new MyPair(x, y));
            posToVal[x][y] =cnt;
            // 만약 y가 1이 되면 y = x + 1이 되고 x = 1로 초기화
            if(y==1){
                y=x+1;
                x=1;
            }
            // 그 외에는 x는 증가, y는 감소
            else{
                x++;
                y--;
            }
        }

        for(int test_case = 1; test_case <= T; test_case++)
        {

            // 입력 : p, q
            int p = sc.nextInt();
            int q = sc.nextInt();
            // 값 -> 좌표 변환
            MyPair pVal = valToPos.get(p);
            MyPair qVal = valToPos.get(q);

            // 좌표끼리 연산 수행
            MyPair oper = new MyPair(pVal.getFirst() + qVal.getFirst(), pVal.getSecond() + qVal.getSecond());

            // 좌표 -> 값 변환
            int ans = posToVal[oper.getFirst()][oper.getSecond()];

            // 출력 : #test_case ans
            System.out.println("#"+test_case+ " " + ans);
        }
    }
}